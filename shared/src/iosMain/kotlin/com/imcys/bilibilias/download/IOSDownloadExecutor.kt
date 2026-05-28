package com.imcys.bilibilias.download

class IOSDownloadExecutor : SharedDownloadExecutor{
    override suspend fun downloadFile(
        downloadUrl: String,
        savePath: String,
        referer: String,
        onProgress: (Float) -> Unit
    ): Boolean = false
}