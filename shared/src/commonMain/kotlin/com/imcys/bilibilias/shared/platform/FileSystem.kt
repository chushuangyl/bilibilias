package com.imcys.bilibilias.shared.platform

data class FileInfo(
    val path: String,
    val mimeType: String? = null
)

expect object FileSystem {
    fun openFile(fileInfo: FileInfo): Boolean

    fun fileExists(path: String): Boolean

    fun deleteFile(path: String): DeleteResult

    enum class DeleteResult {
        SUCCESS, FILE_NOT_EXIST, FAILED
    }

}
