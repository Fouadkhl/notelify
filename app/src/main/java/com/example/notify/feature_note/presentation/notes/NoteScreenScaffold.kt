package com.example.notify.feature_note.presentation.notes

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notify.feature_note.presentation.notes.components.NoteItem
import com.example.notify.feature_note.presentation.notes.components.RadioSection
import com.example.notify.feature_note.presentation.notes.components.TopAppBar
import com.example.notify.feature_note.presentation.util.components.note_Edit_Screen
import kotlinx.coroutines.launch


@Composable
fun NoteScreenScoffold(
    viewModel: NoteViewModel = hiltViewModel(),
    navController: NavController
) {

    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(note_Edit_Screen)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(appName = "Notelify") {
                viewModel.onEvent(NoteEvent.ToggleOrderSection)
            }
            AnimatedVisibility(
                visible = state.orderSectionVisibility,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                RadioSection(onOrderChange = {
                    // todo  1 implement onorderchange
                })
            }

            LazyColumn (contentPadding = PaddingValues(vertical = 12.dp)){
                items(state.notes) { note ->
                    NoteItem(
                        note = note,
                        navController
                    ) {
                        // onDeleteClick
                        viewModel.onEvent(NoteEvent.DeleteNote(note))
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                "Note deleted", "Undo"
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                viewModel.onEvent(NoteEvent.RestoreNote)
                            }
                        }
                    }

                }
            }
        }
    }

}