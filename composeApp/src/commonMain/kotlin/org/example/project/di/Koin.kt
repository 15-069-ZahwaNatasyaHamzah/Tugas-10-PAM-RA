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
    
    factory { NotesViewModel(get()) }
    factory { ProfileViewModel(get()) }
}

expect fun platformModule(): Module
