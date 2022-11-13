package com.example.notify.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notify.R
import com.example.notify.feature_note.domain.util.NoteOrder
import com.example.notify.feature_note.domain.util.OrderType

@Composable
fun RadioItem(
    radioText: String,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = isSelected,
            onClick = { onSelect() },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colors.primary,
                unselectedColor = MaterialTheme.colors.onBackground
            )
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = radioText, color = Color.LightGray, style = MaterialTheme.typography.body1)
    }
}

@Composable
fun RadioSection(
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending), // to update the ordertype
    onOrderChange: (NoteOrder) -> Unit
) {

    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            RadioItem(
                radioText = "Date",
                isSelected = noteOrder is NoteOrder.Date,
                onSelect = {
                    onOrderChange(NoteOrder.Date(orderType = noteOrder.orderType))
                })

            Spacer(modifier = Modifier.width(8.dp))
            RadioItem(
                radioText = "Title",
                isSelected = noteOrder is NoteOrder.Title,
                onSelect = {
                    onOrderChange(NoteOrder.Title(orderType = noteOrder.orderType))
                })
            Spacer(modifier = Modifier.width(8.dp))

            RadioItem(
                radioText = "Priority",
                isSelected = noteOrder is NoteOrder.ColorPriorety,
                onSelect = {
                    onOrderChange(NoteOrder.ColorPriorety(orderType = noteOrder.orderType))
                })
        }

        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            RadioItem(
                radioText = "Descending",
                isSelected = noteOrder.orderType is OrderType.Descending,
                onSelect = {
//                    onOrderChange(NoteOrder.Date(OrderType.Descending)) //todo to edit 1
                })

            Spacer(modifier = Modifier.width(8.dp))
            RadioItem(
                radioText = "Ascending",
                isSelected = noteOrder.orderType is OrderType.Ascending,
                onSelect = {
//                    onOrderChange(NoteOrder.Date(OrderType.Descending)) //todo  to edit 2
                })
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}


@Composable
fun TopAppBar(appName: String , filterOnClick : () ->Unit) {
    Row(
        modifier = Modifier.padding(horizontal = 18.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = appName, fontSize = 24.sp)
        IconButton(onClick = {filterOnClick() }) {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_baseline_tune_24
                ),
                contentDescription = ""
            )
        }
    }


}


/*****************Preview******************/


@Preview
@Composable
fun RadioSectionPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        RadioSection(onOrderChange = {})
    }
}

@Preview(backgroundColor = 0xffffffff)
@Composable
fun AppBarPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        TopAppBar(appName = "Notefy App"){}
    }
}






