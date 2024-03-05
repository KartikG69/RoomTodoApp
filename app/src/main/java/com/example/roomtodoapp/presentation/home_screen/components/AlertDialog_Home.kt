package com.example.roomtodoapp.presentation.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomtodoapp.R
import com.example.roomtodoapp.domain.model.Todo
import com.example.roomtodoapp.presentation.MainViewModel
import com.example.roomtodoapp.presentation.common.taskTextStyle
import com.example.roomtodoapp.presentation.common.toastMsg
import kotlinx.coroutines.job

@Composable
fun AlertDialog_Home(
    openDialog: Boolean,
    onClose: () -> Unit,
    mainViewModel: MainViewModel
) {
    var text: String by remember { mutableStateOf("") }
    var isImportant by remember { mutableStateOf(false) }

    val todo = Todo(0, text, isImportant)

    val focusRequester = FocusRequester()
    val context = LocalContext.current

    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = "Todo",
                    fontFamily = FontFamily(Font(R.font.nunito_regular)),
                    color = Color(0xFF181717)
                )
            },
            text = {
                LaunchedEffect(
                    key1 = true,
                    block = {
                        coroutineContext.job.invokeOnCompletion {
                            focusRequester.requestFocus()
                        }
                    })

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        placeholder = {
                            Text(
                                text = "Add Task",
                                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                                color = Color(0xFF181717)
                            )
                        },
                        shape = RectangleShape,
                        modifier = Modifier
                            .focusRequester(focusRequester),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (text.isNotBlank()) {
                                    mainViewModel.insertTodo(todo = todo)
                                    text = ""
                                    isImportant = false
                                    onClose()
                                } else {
                                    toastMsg(
                                        context,
                                        "Empty Task!"
                                    )
                                }
                            }
                        ),
                        trailingIcon = {
                            IconButton(onClick = { text = "" }) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = null
                                )
                            }
                        },
                        textStyle = taskTextStyle
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Important",
                            fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            color = Color(0xFF181717),
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Checkbox(
                            checked = isImportant,
                            onCheckedChange = { isImportant = it })
                    }
                }
            },
            onDismissRequest = {
                onClose()
                text = ""
                isImportant = false
            },
            confirmButton = {
                Button(onClick = {
                    if (text.isNotBlank()) {
                        mainViewModel.insertTodo(todo = todo)
                        text = ""
                        isImportant = false
                        onClose()
                    } else {
                        toastMsg(
                            context,
                            "Empty Task!"
                        )
                    }
                }) {
                    Text(
                        text = "Save"
                    )
                }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                    onClose()
                    text = ""
                    isImportant = false
                }) {
                    Text(text = "Close",fontFamily = FontFamily(Font(R.font.nunito_regular)),
                        color = Color(0xFF181717))
                }
            }
            )
    }
}