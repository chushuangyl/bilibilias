package com.imcys.bilibilias.shared

import androidx.compose.ui.window.ComposeUIViewController
import com.imcys.bilibilias.ui.BILIBILIASAppScreen
import com.imcys.bilibilias.ui.theme.BILIBILIASTheme

fun MainViewController() = ComposeUIViewController {
    initKoin()
    BILIBILIASTheme {
        BILIBILIASAppScreen()
    }
}
