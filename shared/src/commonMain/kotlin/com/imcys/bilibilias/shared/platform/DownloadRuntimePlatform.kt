package com.imcys.bilibilias.shared.platform

expect object DownloadRuntimePlatform {
    val maxSupportedConcurrentDownloads: Int

    fun applyFfmpegRuntimeConfig(
        maxConcurrentDownloads: Int,
        enabledConcurrentMerge: Boolean,
    )
}
