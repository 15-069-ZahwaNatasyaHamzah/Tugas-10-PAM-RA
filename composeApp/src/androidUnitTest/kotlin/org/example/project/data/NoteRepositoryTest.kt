package org.example.project.data

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.project.database.NoteDatabase
import org.example.project.domain.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class NoteRepositoryTest {
    private lateinit var repository: NoteRepository
    private lateinit var database: NoteDatabase

    @Before
    fun setup() {
        val driver = AndroidSqliteDriver(
            schema = NoteDatabase.Schema,
            context = RuntimeEnvironment.getApplication(),
            name = null // in-memory
        )
        database = NoteDatabase(driver)
        repository = NoteRepository(database)
    }

    @Test
    fun `test insert and get all notes`() = runBlocking {
        repository.insertNote(Note(title = "Title 1", content = "Content 1"))
        repository.insertNote(Note(title = "Title 2", content = "Content 2"))

        val notes = repository.getAllNotes().first()
        assertEquals(2, notes.size)
    }

    @Test
    fun `test delete note`() = runBlocking {
        repository.insertNote(Note(title = "Title 1", content = "Content 1"))
        val notesBefore = repository.getAllNotes().first()
        val noteId = notesBefore[0].id!!

        repository.deleteNote(noteId)
        val notesAfter = repository.getAllNotes().first()
        assertTrue(notesAfter.isEmpty())
    }

    @Test
    fun `test update note`() = runBlocking {
        repository.insertNote(Note(title = "Old Title", content = "Old Content"))
        val note = repository.getAllNotes().first()[0]

        repository.insertNote(note.copy(title = "New Title", content = "New Content"))
        val updatedNote = repository.getNoteById(note.id!!)

        assertNotNull(updatedNote)
        assertEquals("New Title", updatedNote.title)
    }

    @Test
    fun `test search notes`() = runBlocking {
        repository.insertNote(Note(title = "Apple", content = "Fruit"))
        repository.insertNote(Note(title = "Banana", content = "Fruit"))

        val searchResult = repository.searchNotes("App").first()
        assertEquals(1, searchResult.size)
        assertEquals("Apple", searchResult[0].title)
    }

    @Test
    fun `test toggle favorite`() = runBlocking {
        repository.insertNote(Note(title = "Title", content = "Content"))
        val note = repository.getAllNotes().first()[0]

        repository.insertNote(note.copy(isFavorite = true))
        val favoriteNotes = repository.getFavoriteNotes().first()
        
        assertEquals(1, favoriteNotes.size)
        assertTrue(favoriteNotes[0].isFavorite)
    }
}
