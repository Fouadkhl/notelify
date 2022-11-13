package com.example.notify.feature_note.presentation.add_edit_notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notify.feature_note.domain.model.Note
import com.example.notify.feature_note.presentation.add_edit_notes.EditNoteViewModel

@Composable
fun ColorRow(
    noteViewModel: EditNoteViewModel = hiltViewModel(),
    pickColor: (Color) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (i in 0..4){
            val color  = if (noteViewModel.colorState.value == Note.noteColors[i].toArgb())Color.DarkGray else  Color.Transparent
            CircleColor(
                color = Note.noteColors[i],
                borderColor = color,
                onClick = {
                    pickColor(it)
                })
        }

    }
}

@Composable
fun CircleColor(
    color: Color = Color.Red,
    borderColor: Color = Color.Transparent,
    onClick: (Color) -> Unit
) {
    Surface(
        color = color,
        modifier = Modifier
            .size(55.dp)
            .clip(CircleShape)
            .border(2.dp, borderColor, CircleShape)
            .clickable {
                onClick(color)
            },
        elevation = 3.dp
    ) {}
}

@Composable
fun NoteTextField(
    hint: String,
    textStyle: TextStyle = TextStyle(fontSize = MaterialTheme.typography.body1.fontSize),
    maxTextLines: Int = 2,
    textfieldHeight: Dp = 90.dp,
    editNoteViewModel: EditNoteViewModel = hiltViewModel(),
    onTextChanged: (String) -> Unit
) {
    var title by remember {
        mutableStateOf(editNoteViewModel.titleState.value)
    }

    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        value = title,
        onValueChange = {
            title = it
            onTextChanged(it)
        },
        label = {
            Text(text = hint, style = textStyle, modifier = Modifier.height(textfieldHeight))
        },
        maxLines = maxTextLines,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Magenta,
            textColor = Color.DarkGray
        )
    )


}

@Composable
fun EditScreen(
    noteBackgroundColor: Color,
    onColorChange: (Color) -> Unit,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged :(String) -> Unit
) {

    Column(
        Modifier
            .fillMaxSize()
            .background(noteBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        ColorRow() {
            onColorChange(it)
        }
        Spacer(modifier = Modifier.height(24.dp))
        NoteTextField(
            hint = "Title",
            textStyle = TextStyle(fontSize = 24.sp),
            onTextChanged = onTitleChanged
        )
        Spacer(modifier = Modifier.height(18.dp))
        NoteTextField(
            hint = "Description",
            textStyle = TextStyle(fontSize = 18.sp),
            textfieldHeight = 450.dp,
            maxTextLines = 20 ,
            onTextChanged = onDescriptionChanged
        )

    }
}


/***********************Preview***********************/

@Preview(backgroundColor = 0xffffffff, showBackground = true)
@Composable
fun ColorRowPreview() {
    ColorRow() {

    }
}

//@Preview(backgroundColor = 0xffffffff, showBackground = true)
//@Composable
//fun EditScreenPrev() {
//    EditScreen(Color(0xfffffff), onColorChange = {}, onTitleChanged = {}) {
//    }
//}
