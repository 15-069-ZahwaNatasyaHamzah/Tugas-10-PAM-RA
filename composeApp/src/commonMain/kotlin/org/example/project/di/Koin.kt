package org.example.project.di

import org.example.project.data.NoteRepository
import org.example.project.database.NoteDatabase
import org.example.project.data.DatabaseDriverFactory
import org.example.project.platform.getDeviceInfo
import org.example.project.ui.notes.NotesViewModel
import org.example.project.ui.profile.ProfileViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

import org.example.project.ai.AiService
import org.example.project.ai.GeminiAiService
import org.example.project.ai.AiViewModel

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule, platformModule())
    }

// called by iOS
fun initKoin() = initKoin {}

val commonModule = module {
    single { 
        val driver = get<DatabaseDriverFactory>().createDriver()
        NoteDatabase(driver)
    }
    single { NoteRepository(get()) }
    
    single { getDeviceInfo() }
    
    // AI Service - REPLACE WITH YOUR ACTUAL API KEY FROM https://aistudio.google.com/
    single<AiService> { 
        val apiKey = "AIzaSyDIDd8xRL1r0IsXbsjJr8EeVa_6TflfM3Y"
        GeminiAiService(apiKey = apiKey) 
    }
    
    factory { NotesViewModel(get()) }
    factory { ProfileViewModel(get()) }
    factory { AiViewModel(get()) }
}

expect fun platformModule(): Module
