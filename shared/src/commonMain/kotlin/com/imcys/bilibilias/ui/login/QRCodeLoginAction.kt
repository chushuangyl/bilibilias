package com.imcys.bilibilias.ui.login

internal expect fun saveQRCodeImageToGalleryByPlatform(qrCodeImageUrl: String?): Boolean

internal expect fun goToScanQRByPlatform(): Boolean