package by.alexeypalto.sampleadastra

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import by.alexeypalto.sampleadastra.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class AdastraApp: Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchAndroidInjector
    }

    companion object {
        var INSTANCE: AdastraApp? = null
            private set
    }

    override fun onCreate() {
        INSTANCE = this
        super.onCreate()
        DaggerAppComponent.builder().context(this.applicationContext).build().injectApp(this)
//        Timber.plant(Timber.asTree())
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}