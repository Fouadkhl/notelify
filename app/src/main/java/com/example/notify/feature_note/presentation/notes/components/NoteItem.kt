package com.example.notify.feature_note.presentation.notes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import androidx.navigation.NavController
import com.example.notify.feature_note.domain.model.Note
import com.example.notify.feature_note.presentation.util.components.note_Edit_Screen
import com.example.notify.ui.theme.BabyBlue
import com.example.notify.ui.theme.Shapes

@Composable
fun NoteItem(
    note: Note,
    navController: NavController,
    onDeleteClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp , vertical = 6.dp)
            .clip(CutCornerShape(topEnd = 18.dp))
            .clickable {
                navController.navigate(note_Edit_Screen + "?noteId=${note.id}&noteColor=${note.colorPriority}")
            },
        color = Color(note.colorPriority),
        contentColor = MaterialTheme.colors.onPrimary,
        elevation = 3.dp,
        shape = Shapes.medium
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Surface(
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.TopEnd),
                color = Color(ColorUtils.blendARGB(note.colorPriority, 0x0000000, 0.2f)),
                shape = RoundedCornerShape(bottomStart = 4.dp)
            ) {}
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = note.title,
                        fontSize = 18.sp,
                        modifier = Modifier.weight(9f),
                        color = Color.Black
                    )
                    IconButton(
                        onClick = onDeleteClick,
                        Modifier
                            .weight(1f)
                            .align(Alignment.Bottom)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete, contentDescription = "",
                            tint = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = note.description,
                    color = Color.DarkGray,
                    fontSize = 16.sp,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


/***************************Preview**************************************/


//@Preview
//@Composable
//fun NoteItemPreview() {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(18.dp), contentAlignment = Alignment.Center
//    ) {
//        NoteItem(
//            note = Note(
//                1,
//                "Relative theory",
//                "relative theory is an enstein theory that is distrbuted to the world in 1905 " +
//                        "for the specific relativity and for the general relativity is distributed in 1915",
//                1000,
//                BabyBlue.toArgb()
//            ),
//
//            ,
//            onDeleteClick = {}
//        )
//    }
//
//}
