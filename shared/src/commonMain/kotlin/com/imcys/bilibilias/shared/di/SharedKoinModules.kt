package com.imcys.bilibilias.shared.di

import com.imcys.bilibilias.data.di.repositoryModule
import com.imcys.bilibilias.database.di.databaseModule
import com.imcys.bilibilias.datastore.di.dataStoreModule
import com.imcys.bilibilias.network.di.netWorkModule
import com.imcys.bilibilias.ui.BILIBILIASAppViewModel
import com.imcys.bilibilias.ui.analysis.AnalysisViewModel
import com.imcys.bilibilias.ui.download.DownloadViewModel
import com.imcys.bilibilias.ui.event.playvoucher.PlayVoucherErrorViewModel
import com.imcys.bilibilias.ui.event.requestFrequent.RequestFrequentViewModel
import com.imcys.bilibilias.ui.home.HomeViewModel
import com.imcys.bilibilias.ui.login.CookieLoginViewModel
import com.imcys.bilibilias.ui.login.QRCodeLoginViewModel
import com.imcys.bilibilias.ui.setting.SettingViewModel
import com.imcys.bilibilias.ui.setting.contract.NamingConventionViewModel
import com.imcys.bilibilias.ui.setting.developer.LineConfigViewModel
import com.imcys.bilibilias.ui.setting.download.DownloadConfigViewModel
import com.imcys.bilibilias.ui.setting.layout.LayoutTypesetViewModel
import com.imcys.bilibilias.ui.setting.platform.ParsePlatformViewModel
import com.imcys.bilibilias.ui.setting.roam.RoamViewModel
import com.imcys.bilibilias.ui.setting.storage.StorageManagementViewModel
import com.imcys.bilibilias.ui.tools.calendar.CalendarViewModel
import com.imcys.bilibilias.ui.tools.calendar.detail.SubjectDetailViewModel
import com.imcys.bilibilias.ui.tools.donate.DonateViewModel
import com.imcys.bilibilias.ui.tools.parser.WebParserViewModel
import com.imcys.bilibilias.ui.user.UserViewModel
import com.imcys.bilibilias.ui.user.bangumifollow.BangumiFollowViewModel
import com.imcys.bilibilias.ui.user.folder.UserFolderViewModel
import com.imcys.bilibilias.ui.user.history.UserPlayHistoryViewModel
import com.imcys.bilibilias.ui.user.like.LikeVideoViewModel
import com.imcys.bilibilias.ui.user.work.WorkListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val appModules = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::QRCodeLoginViewModel)
    viewModelOf(::BILIBILIASAppViewModel)
    viewModelOf(::UserViewModel)
    viewModelOf(::AnalysisViewModel)
    viewModelOf(::DownloadViewModel)
    viewModelOf(::PlayVoucherErrorViewModel)
    viewModelOf(::RoamViewModel)
    viewModelOf(::WorkListViewModel)
    viewModelOf(::BangumiFollowViewModel)
    viewModelOf(::UserFolderViewModel)
    viewModelOf(::LikeVideoViewModel)
    viewModelOf(::SettingViewModel)
    viewModelOf(::LayoutTypesetViewModel)
    viewModelOf(::UserPlayHistoryViewModel)
    viewModelOf(::CookieLoginViewModel)
    viewModelOf(::DonateViewModel)
    viewModelOf(::StorageManagementViewModel)
    viewModelOf(::NamingConventionViewModel)
    viewModelOf(::RequestFrequentViewModel)
    viewModelOf(::LineConfigViewModel)
    viewModelOf(::DownloadConfigViewModel)
    viewModelOf(::WebParserViewModel)
    viewModelOf(::ParsePlatformViewModel)
    viewModelOf(::CalendarViewModel)
    viewModelOf(::SubjectDetailViewModel)
}

fun sharedKoinModules(): List<Module> = listOf(
    databaseModule,
    dataStoreModule,
    netWorkModule,
    repositoryModule,
    appModules
)
