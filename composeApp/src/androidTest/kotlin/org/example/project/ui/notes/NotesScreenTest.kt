package org.example.project.ui.notes

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import io.mockk.every
import io.mockk.mockk
import org.example.project.data.NoteRepository
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class NotesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val repository = mockk<NoteRepository>(relaxed = true)

    @Test
    fun notesScreen_showsTitle() {
        every { repository.getAllNotes() } returns flowOf(emptyList())
        every { repository.getFavoriteNotes() } returns flowOf(emptyList())
        val viewModel = NotesViewModel(repository)

        composeTestRule.setContent {
            NotesScreen(
                viewModel = viewModel,
                onNoteClick = {},
                onAddNote = {}
            )
        }

        composeTestRule.onNodeWithText("My Notes").assertExists()
    }

    @Test
    fun notesScreen_canSearch() {
        every { repository.getAllNotes() } returns flowOf(emptyList())
        every { repository.getFavoriteNotes() } returns flowOf(emptyList())
        val viewModel = NotesViewModel(repository)

        composeTestRule.setContent {
            NotesScreen(
                viewModel = viewModel,
                onNoteClick = {},
                onAddNote = {}
            )
        }

        val searchField = composeTestRule.onNodeWithText("Search notes...")
        searchField.assertExists()
        searchField.performTextInput("Shopping")
    }

    @Test
    fun notesScreen_fabExists() {
        every { repository.getAllNotes() } returns flowOf(emptyList())
        every { repository.getFavoriteNotes() } returns flowOf(emptyList())
        val viewModel = NotesViewModel(repository)

        composeTestRule.setContent {
            NotesScreen(
                viewModel = viewModel,
                onNoteClick = {},
                onAddNote = {}
            )
        }

        composeTestRule.onNodeWithContentDescription("Add Note").assertExists()
    }
}
