package com.imcys.bilibilias.download

import com.imcys.bilibilias.data.model.download.DownloadViewInfo
import com.imcys.bilibilias.data.model.video.ASLinkResultType
import kotlinx.coroutines.flow.StateFlow

interface SharedDownloadManager {
    fun getAllDownloadTasks(): StateFlow<List<AppDownloadTask>>

    suspend fun initDownloadList()

    suspend fun addDownloadTask(
        asLinkResultType: ASLinkResultType,
        downloadViewInfo: DownloadViewInfo,
    )

    suspend fun pauseTask(segmentId: Long)

    suspend fun resumeTask(segmentId: Long)

    suspend fun cancelTask(segmentId: Long)

    suspend fun downloadImageToAlbum(
        imageUrl: String,
        fileName: String,
        saveDirName: String,
    )
}

