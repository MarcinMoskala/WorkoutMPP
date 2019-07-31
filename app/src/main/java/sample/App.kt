package sample

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

@Suppress("unused") // Used on AndroidManifest
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val androidModule = module {
            single<Timer> { AndroidTimer() }
            single<Speaker> { AndroidSpeaker(get()) }
            viewModel { WorkoutViewModel(get(), get()) }
        }
        startKoin(this, listOf(androidModule))
    }
}
