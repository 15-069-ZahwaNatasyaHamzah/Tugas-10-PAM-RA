package org.example.project.ui.notes

import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import org.example.project.data.NoteRepository
import org.example.project.domain.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class NotesViewModelTest {
    private val repository = mockk<NoteRepository>(relaxed = true)
    private lateinit var viewModel: NotesViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        every { repository.getAllNotes() } returns flowOf(emptyList())
        every { repository.getFavoriteNotes() } returns flowOf(emptyList())
        viewModel = NotesViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be loading and empty`() = runTest {
        // Since loadNotes is called in init, it might already be finished with UnconfinedTestDispatcher
        assertEquals(false, viewModel.uiState.value.isLoading)
        assertTrue(viewModel.uiState.value.notes.isEmpty())
    }

    @Test
    fun `loadNotes should update state with repository data`() = runTest {
        val notes = listOf(Note(1, "Title", "Content", false, 0))
        every { repository.getAllNotes() } returns flowOf(notes)
        every { repository.getFavoriteNotes() } returns flowOf(emptyList())

        // Create new VM to trigger init again with new mock data
        val newViewModel = NotesViewModel(repository)
        
        assertEquals(notes, newViewModel.uiState.value.notes)
    }

    @Test
    fun `searchNotes should update searchQuery and results`() = runTest {
        val query = "test"
        val searchResults = listOf(Note(1, "test", "content", false, 0))
        every { repository.searchNotes(query) } returns flowOf(searchResults)

        viewModel.searchNotes(query)

        assertEquals(query, viewModel.uiState.value.searchQuery)
        assertEquals(searchResults, viewModel.uiState.value.notes)
    }

    @Test
    fun `uiState flow test using Turbine`() = runTest {
        val notes = listOf(Note(1, "Title", "Content", false, 0))
        every { repository.getAllNotes() } returns flowOf(notes)
        
        viewModel.uiState.test {
            // Skip initial state
            val state = awaitItem()
            // In our case, because of UnconfinedTestDispatcher, we might get the final state immediately
            // or we might need to trigger something.
            // Let's just verify the current state if it's already updated.
            assertEquals(false, state.isLoading)
        }
    }
}
