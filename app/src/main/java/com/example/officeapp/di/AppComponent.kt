package com.example.officeapp.di

import android.app.Application
import android.content.Context
import com.example.officeapp.di.module.DomainModule
import com.example.officeapp.di.module.NetworkModule
import com.example.officeapp.di.module.ViewModelsModule
import com.example.officeapp.presentation.MainActivity
import com.example.officeapp.utils.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ViewModelsModule::class,
        DomainModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }
}

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideSessionManager(context: Context): SessionManager {
        return SessionManager(context)
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

}