package com.imcys.bilibilias.shared.platform

import android.Manifest
import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.imcys.bilibilias.common.utils.AsRegexUtil
import org.koin.mp.KoinPlatform.getKoin
import java.net.URLDecoder
import java.net.URLEncoder
import java.security.MessageDigest
import kotlin.compareTo
import kotlin.printStackTrace
import kotlin.text.compareTo

val koinApplication: Context = getKoin().get<Application>()


@Composable
actual fun rememberLegacyStoragePermissionController(onDenied: () -> Unit): LegacyStoragePermissionController {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (!granted) onDenied()
    }

    return remember(context, launcher, onDenied) {
        object : LegacyStoragePermissionController {
            override val shouldRequest: Boolean
                get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.Q &&
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) != PackageManager.PERMISSION_GRANTED

            override fun request() {
                launcher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }
}

actual fun openLink(url: String): Boolean {
    return try {
        koinApplication.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                url.toUri()
            )
        )
        true
    } catch (e: Exception) {
        false
    }
}

actual fun format(format: String, vararg args: Any?): String {
    return String.format(format, *args)
}

@Composable
actual fun rememberAppSignature(): String? {
    val context = LocalContext.current
    val packageName = context.packageName
    return remember(packageName, context) {
        try {
            val packageInfo = context.packageManager.getPackageInfo(
                packageName,
                if (android.os.Build.VERSION.SDK_INT >= 28)
                    PackageManager.GET_SIGNING_CERTIFICATES else PackageManager.GET_SIGNATURES
            )
            val signatures = if (android.os.Build.VERSION.SDK_INT >= 28) {
                packageInfo.signingInfo?.apkContentsSigners
            } else {
                @Suppress("DEPRECATION")
                packageInfo.signatures
            }
            val cert = signatures?.getOrNull(0)?.toByteArray()
            if (cert != null) {
                val md = MessageDigest.getInstance("SHA1")
                val publicKey = md.digest(cert)
                publicKey.joinToString(":") { "%02X".format(it) }
            } else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

actual fun getAppVersion(): Pair<Long, String> {
    val context = koinApplication
    val pm = context.packageManager
    val pkg = context.packageName
    val pi = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        pm.getPackageInfo(pkg, PackageManager.PackageInfoFlags.of(0))
    } else {
        @Suppress("DEPRECATION") pm.getPackageInfo(pkg, 0)
    }

    val code = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        pi.longVersionCode
    } else {
        @Suppress("DEPRECATION") pi.versionCode.toLong()
    }
    val name = pi.versionName ?: "unknown"

    return code to name
}


actual fun setClipboardText(text: String) {
    val context = koinApplication
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("", text)
    clipboard.setPrimaryClip(clip)
}

actual fun getClipboardText(): String? {
    val context = koinApplication
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    return clipboard.primaryClip?.getItemAt(0)?.text?.toString()
}

actual fun urlEncode(url: String): String {
    return URLEncoder.encode(url, "UTF-8")
}

actual fun urlDecode(url: String): String {
    return URLDecoder.decode(url, "UTF-8")
}

actual fun readAndConsumeClipboardText(): String? {
    val context = koinApplication
    val clipboard =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager ?: return null
    val clip = clipboard.primaryClip ?: return null

    val text = clip.getItemAt(0)
        .coerceToText(context)
        ?.toString()
        ?.trim()
        .takeIf { !it.isNullOrEmpty() }
        ?: return null

    if (AsRegexUtil.parse(text) == null) {
        return null
    }

    // 清空，避免重复处理
    clipboard.setPrimaryClip(ClipData.newPlainText("", ""))
    return text
}
