package org.example.project.di

import org.example.project.data.NoteRepository
import org.example.project.database.NoteDatabase
import org.example.project.data.DatabaseDriverFactory
import org.example.project.platform.getDeviceInfo
import org.example.project.ui.notes.NotesViewModel
import org.example.project.ui.profile.ProfileViewModel
import org.example.project.ai.AiService
import org.example.project.ai.GeminiAiService
import org.example.project.ai.AiViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(dataModule, viewModelModule, platformModule())
    }

// called by iOS
fun initKoin() = initKoin {}

val dataModule = module {
    single { 
        val driver = get<DatabaseDriverFactory>().createDriver()
        NoteDatabase(driver)
    }
    single { NoteRepository(get()) }
    
    // AI Service
    single<AiService> { 
        val apiKey = "AIzaSyDIDd8xRL1r0IsXbsjJr8EeVa_6TflfM3Y"
        GeminiAiService(apiKey = apiKey) 
    }
}

val viewModelModule = module {
    factory { NotesViewModel(get()) }
    factory { ProfileViewModel(get()) }
    factory { AiViewModel(get()) }
}

expect fun platformModule(): Module
