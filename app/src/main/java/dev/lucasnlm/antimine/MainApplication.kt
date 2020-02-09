package dev.lucasnlm.antimine

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import dev.lucasnlm.antimine.core.analytics.AnalyticsManager
import dev.lucasnlm.antimine.core.analytics.Event
import dev.lucasnlm.antimine.di.AppModule
import dev.lucasnlm.antimine.di.DaggerAppComponent
import javax.inject.Inject

class MainApplication : DaggerApplication() {

    @Inject
    lateinit var analyticsManager: AnalyticsManager

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .application(this)
            .appModule(AppModule(this))
            .build()

    override fun onCreate() {
        super.onCreate()
        analyticsManager.setup(applicationContext, mapOf())
        analyticsManager.sentEvent(Event.Open())
    }
}