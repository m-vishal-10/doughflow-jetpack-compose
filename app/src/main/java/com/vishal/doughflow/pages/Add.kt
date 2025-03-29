package com.vishal.doughflow.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vishal.doughflow.components.TableRow
import com.vishal.doughflow.components.UnstyledTextField
import com.vishal.doughflow.ui.theme.BackgroundElevated
import com.vishal.doughflow.ui.theme.DividerColor
import com.vishal.doughflow.ui.theme.Shapes
import com.vishal.doughflow.ui.theme.TopAppBarBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(navController: NavController) {
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            MediumTopAppBar(title = { Text("Add") }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground
            ))
        },
        content = {innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Column(modifier = Modifier
                    .padding(16.dp)
                    .clip(Shapes.large)
                    .background(BackgroundElevated)
                    .fillMaxWidth()
                ) {
                    TableRow("Amount") {
                        UnstyledTextField(
                            value = amount,
                            onValueChange = { amount = it },
                            modifier = Modifier
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.Right,

                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            placeholder = {
                                Text(
                                    text = "Amount",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Right,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        )

                    }
                    HorizontalDivider(modifier = Modifier
                        .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
                    )
                    TableRow("Recurrence")
                    HorizontalDivider(modifier = Modifier
                        .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
                    )
                    TableRow("Date")
                    HorizontalDivider(modifier = Modifier
                        .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
                    )
                    TableRow("Note") {
                        UnstyledTextField(
                            value = note,
                            onValueChange = {note = it},
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.Right,
                            ),
                            placeholder = {
                                Text(
                                    text = "Note",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Right,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        )
                    }
                    HorizontalDivider(modifier = Modifier
                        .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
                    )
                    TableRow("Category")
                }
            }
        }
    )
}