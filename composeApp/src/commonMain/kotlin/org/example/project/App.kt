package org.example.project

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import org.example.project.data.NoteRepository
import org.example.project.platform.NetworkMonitor
import org.example.project.ui.notes.*
import org.example.project.ui.profile.*
import org.example.project.ui.theme.AppTheme
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.compose.koinInject

sealed class Screen(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Notes : Screen("notes", "Notes", Icons.Default.Description)
    object Favorites : Screen("favorites", "Saved", Icons.Default.Favorite)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
}

@Composable
fun App() {
    KoinContext {
        val repository: NoteRepository = koinInject()
        val networkMonitor: NetworkMonitor = koinInject()
        val isOnline by networkMonitor.isOnline.collectAsState()

        val profileViewModel: ProfileViewModel = koinViewModel()
        val notesViewModel: NotesViewModel = koinViewModel()
        val profileState by profileViewModel.uiState.collectAsState()
        
        val navController = rememberNavController()

        AppTheme(darkTheme = profileState.isDarkMode) {
            Scaffold(
                topBar = {
                    if (!isOnline) {
                        Surface(
                            color = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.onErrorContainer
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(4.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.WifiOff, null, modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(8.dp))
                                Text("Offline Mode", style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                },
                bottomBar = {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    
                    val bottomBarScreens = listOf(Screen.Notes, Screen.Favorites, Screen.Profile)
                    val showBottomBar = bottomBarScreens.any { it.route == currentDestination?.route }

                    if (showBottomBar) {
                        NavigationBar {
                            bottomBarScreens.forEach { screen ->
                                NavigationBarItem(
                                    icon = { Icon(screen.icon, contentDescription = screen.label) },
                                    label = { Text(screen.label) },
                                    selected = currentDestination?.route == screen.route,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            ) { padding ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.Notes.route,
                    modifier = Modifier.padding(padding)
                ) {
                    composable(Screen.Notes.route) {
                        NotesScreen(
                            viewModel = notesViewModel,
                            onNoteClick = { id -> navController.navigate("note_detail/$id") },
                            onAddNote = { navController.navigate("add_edit_note") }
                        )
                    }
                    composable(Screen.Favorites.route) {
                        NotesScreen(
                            viewModel = notesViewModel,
                            onNoteClick = { id -> navController.navigate("note_detail/$id") },
                            onAddNote = {},
                            isFavoritesOnly = true
                        )
                    }
                    composable(Screen.Profile.route) {
                        ProfileScreen(
                            viewModel = profileViewModel,
                            onEditClick = { navController.navigate("edit_profile") }
                        )
                    }
                    composable("edit_profile") {
                        EditProfileScreen(
                            viewModel = profileViewModel,
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable(
                        "note_detail/{noteId}",
                        arguments = listOf(navArgument("noteId") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val noteId = backStackEntry.arguments?.getLong("noteId") ?: return@composable
                        NoteDetailScreen(
                            repository = repository,
                            noteId = noteId,
                            onEdit = { id -> navController.navigate("add_edit_note?noteId=$id") },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable(
                        "add_edit_note?noteId={noteId}",
                        arguments = listOf(navArgument("noteId") { 
                            type = NavType.LongType
                            nullable = false
                            defaultValue = -1L
                        })
                    ) { backStackEntry ->
                        val noteId = backStackEntry.arguments?.getLong("noteId")?.takeIf { it != -1L }
                        AddEditNoteScreen(
                            repository = repository,
                            noteId = noteId,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
