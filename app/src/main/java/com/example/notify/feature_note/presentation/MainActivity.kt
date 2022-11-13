package com.example.notify.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notify.feature_note.presentation.add_edit_notes.EditNoteScreenScaffold
import com.example.notify.feature_note.presentation.notes.NoteScreenScoffold
import com.example.notify.feature_note.presentation.util.components.note_Edit_Screen
import com.example.notify.feature_note.presentation.util.components.note_Screen
import com.example.notify.ui.theme.NotifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotifyTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(navController = navController, note_Screen) {
                    composable(note_Screen) {
                        NoteScreenScoffold(navController = navController)
                    }
                    composable(
                        "$note_Edit_Screen?noteId={noteId}&noteColor={noteColor}",
                        arguments = listOf(
                            navArgument("noteId") {
                                NavType.IntType
                                defaultValue = -1
                            },
                            navArgument("noteColor") {
                                NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) { backStackEntry ->
//                        val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
                        val noteColor = backStackEntry.arguments?.getInt("noteColor") ?: -1
                        EditNoteScreenScaffold(noteColor , navController)
                    }
                }
            }
        }
    }
}
