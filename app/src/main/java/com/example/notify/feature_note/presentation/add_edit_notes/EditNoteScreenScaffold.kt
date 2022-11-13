package com.example.notify.feature_note.presentation.add_edit_notes

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notify.R
import com.example.notify.feature_note.presentation.add_edit_notes.components.EditScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun EditNoteScreenScaffold(
    noteColor: Int,
    navController: NavController,
    editNoteViewModel: EditNoteViewModel = hiltViewModel()
) {
    val animatedBackgroundColor = remember {
        Animatable(Color(if (noteColor != -1) noteColor else editNoteViewModel.colorState.value))
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        scaffoldState.snackbarHostState.showSnackbar(editNoteViewModel.titleState.value)

        editNoteViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is EditNoteViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                is EditNoteViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // in the case we save a note
                    editNoteViewModel.onEvent(EditNoteEvent.SaveNote)
                },
                backgroundColor = Color.White
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_save), contentDescription = "")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(Modifier.fillMaxSize()) {
            EditScreen(
                animatedBackgroundColor.value,
                onColorChange = { pickedColor ->
                    scope.launch {
                        animatedBackgroundColor.animateTo(
                            targetValue = pickedColor,
                            animationSpec = tween(300)
                        )
                    }
                    editNoteViewModel.onEvent(EditNoteEvent.ChangeColorEvent(pickedColor.toArgb()))
                },
                onTitleChanged = {
                    editNoteViewModel.onEvent(EditNoteEvent.EnteredTitleEvent(it))
                },
                onDescriptionChanged = {
                    editNoteViewModel.onEvent(EditNoteEvent.EnteredDescriptionEvent(it))
                }
            )
        }
    }
}
