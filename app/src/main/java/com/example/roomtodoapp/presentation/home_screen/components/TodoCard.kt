package com.example.roomtodoapp.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomtodoapp.domain.model.Todo
import com.example.roomtodoapp.presentation.common.taskTextStyle
import com.example.roomtodoapp.ui.theme.LightBlue
import com.example.roomtodoapp.ui.theme.LightGreen
import com.example.roomtodoapp.ui.theme.LightOrange
import com.example.roomtodoapp.ui.theme.LightPurple
import com.example.roomtodoapp.ui.theme.LightYellow

@Composable
fun TodoCard(
    todo: Todo,
    onDone:() -> Unit,
    onUpdate:(id: Int) -> Unit
    ) {

    val taskColor = listOf(LightYellow, LightBlue, LightGreen, LightOrange, LightPurple).random()

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = taskColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            IconButton(
                onClick = { onDone()},
                modifier = Modifier
                    .weight(1f)
            ){
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = null
                )
            }
            Text(
                text = todo.task,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(8f),
                style = taskTextStyle
            )
            if(todo.isImportant) {
                Icon(imageVector = Icons.Rounded.Star,
                    contentDescription = null,
                    modifier = Modifier.weight(1f)
                ) }
                    IconButton(onClick = { onUpdate(todo.id) }, modifier = Modifier.weight(1f)) {
                        Icon(imageVector = Icons.Rounded.Edit,contentDescription = null)
                }
            }
        }
    }