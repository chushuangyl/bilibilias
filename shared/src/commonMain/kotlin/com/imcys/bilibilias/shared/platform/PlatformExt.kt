package com.imcys.bilibilias.shared.platform

import androidx.compose.runtime.Composable


/**
 * 跳转网页
 */
expect fun openLink(url: String): Boolean

/**
 * 字符串模板
 */
expect fun format(format: String, vararg args: Any?): String

/**
 * 获取AppSignature
 */
@Composable
expect fun rememberAppSignature(): String?

/**
 * 获取App版本
 */
expect fun getAppVersion(): Pair<Long, String>

/**
 * 设置剪切板信息
 */
expect fun setClipboardText(text: String)

/**
 * 获取剪切板信息
 */
expect fun getClipboardText(): String?

/**
 * URL编码
 */
expect fun urlEncode(url: String): String
/**
 * URL解码
 */
expect fun urlDecode(url: String): String


/**
 * 读取并消费剪贴板文本（读取后清空）
 * @return 有效文本或 null
 */
expect fun readAndConsumeClipboardText(): String?
