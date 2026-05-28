package com.imcys.bilibilias.shared.platform

import com.imcys.bilibilias.common.utils.StorageInfoData

expect object StoragePlatform {
    suspend fun getStorageInfoData(): StorageInfoData

    suspend fun hasDownloadSAFPermission(): Boolean

    fun clearCache(): Boolean

    fun openDownloadDirectory(): Boolean

    fun restartApplication()
}
