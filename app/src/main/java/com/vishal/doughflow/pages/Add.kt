package com.vishal.doughflow.pages

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vishal.doughflow.components.DatePickerModal
import com.vishal.doughflow.components.TableRow
import com.vishal.doughflow.components.UnstyledTextField
import com.vishal.doughflow.models.Recurrence
import com.vishal.doughflow.ui.theme.BackgroundElevated
import com.vishal.doughflow.ui.theme.DividerColor
import com.vishal.doughflow.ui.theme.DoughFlowTheme
import com.vishal.doughflow.ui.theme.Shapes
import com.vishal.doughflow.ui.theme.TopAppBarBackground
import com.vishal.doughflow.viewmodels.AddViewModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(navController: NavController, vm: AddViewModel = viewModel()) {

    val state by vm.uiState.collectAsState()
//    var amount by remember { mutableStateOf("") }
//    var note by remember { mutableStateOf("") }
    // TODO: refactor this into a ViewModel because we're losing the values when changing orientation
    val recurrences = listOf(
        Recurrence.None,
        Recurrence.Daily,
        Recurrence.Weekly,
        Recurrence.Monthly,
        Recurrence.Yearly
    )
    var selectedRecurrence by remember {
        mutableStateOf("None")
    }
    val categories = listOf("Groceries","HangOut","Dinner","Travel","Fuel")
    var selectedCategory by remember {
        mutableStateOf("Choose")
    }

    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }
    val dateInString = SimpleDateFormat("MMM dd, yyyy").format(Date())
    val formattedDate = selectedDateMillis?.let {
        SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(it))
    } ?: dateInString

    Scaffold(
        topBar = {
            MediumTopAppBar(title = { Text("Add") }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground
            ))
        },
        content = {innerPadding ->
            Column(modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Column(modifier = Modifier
                    .padding(16.dp)
                    .clip(Shapes.large)
                    .background(BackgroundElevated)
                    .fillMaxWidth()
                ) {
                    TableRow(label = "Amount", detailContent = {
                        UnstyledTextField(
                            value = state.amount ,
                            onValueChange = vm::setAmount,
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
                                    modifier = Modifier.fillMaxWidth().padding(end = 16.dp)
                                )
                            }
                        )
                    })
                    HorizontalDivider(modifier = Modifier
                        .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
                    )
                    TableRow(label = "Recurrence", detailContent = {
                        var recurrenceMenuOpened by remember {
                            mutableStateOf(false)
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(
                                onClick = {recurrenceMenuOpened = true},
                                shape = Shapes.large,
                                contentPadding = PaddingValues(0.dp)
                            ){
                                Text(
                                    selectedRecurrence,
                                    color = Color.White,
                                )
                                DropdownMenu(expanded = recurrenceMenuOpened,
                                    onDismissRequest = {recurrenceMenuOpened = false}) {
                                    recurrences.forEach { recurrence ->
                                        DropdownMenuItem(
                                            text = { Text(recurrence.name) },
                                            onClick = {
                                                selectedRecurrence = recurrence.name
                                                recurrenceMenuOpened = false }
                                        )
                                    }
                                }
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "Settings",
                                    tint = Color.White
                                )
                            }
                        }


                    })
                    HorizontalDivider(modifier = Modifier
                        .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
                    )
                    var isDatePickerVisible by remember { mutableStateOf(false) }
                    TableRow(label = "Date", detailContent = {
                        TextButton(
                            onClick = { isDatePickerVisible = true },
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(text = formattedDate, color = Color.White, modifier = Modifier.padding(end = 16.dp))

                        }
                        if (isDatePickerVisible) {
                            DatePickerModal(
                                initialSelectedDateMillis = selectedDateMillis,
                                onDateSelected = { millis ->
                                    val selectedDate = millis?.let { millis ->
                                        Instant.ofEpochMilli(millis)
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDate()
                                    }
                                    selectedDate?.let { vm.setDate(it) }
                                    selectedDateMillis = millis
                                    isDatePickerVisible = false
                                },
                                onDismiss = { isDatePickerVisible = false },
                            )
                        }

                    })
                    HorizontalDivider(modifier = Modifier
                        .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
                    )
                    TableRow(label = "Note", detailContent = {
                        UnstyledTextField(
                            value = state.note?: "",
                            onValueChange = {vm.setNote(it)},
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.Right,
                            ),
                            placeholder = {
                                Text(
                                    text = "Note",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Right,
                                    modifier = Modifier.fillMaxWidth().padding(end = 16.dp)
                                )
                            }
                        )
                    })
                    HorizontalDivider(modifier = Modifier
                        .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
                    )
                    TableRow(label = "Category", detailContent = {
                        var categoriesMenuOpened by remember {
                            mutableStateOf(false)
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            TextButton(
                                onClick = {categoriesMenuOpened = true},
                                shape = Shapes.large,
                                contentPadding = PaddingValues(0.dp)
                            ){
                                Text(selectedCategory,
                                    color = Color.White
                                )
                                DropdownMenu(expanded = categoriesMenuOpened,
                                    onDismissRequest = {categoriesMenuOpened = false}) {
                                    categories.forEach { category ->
                                        DropdownMenuItem(
                                            text = {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                                ) {
                                                    Surface (modifier = Modifier
                                                        .size(10.dp),
                                                        shape = CircleShape,
                                                        color = Color.White
                                                    ) {

                                                    }
                                                    Text(category)
                                                }
                                            },
                                            onClick = {
                                                selectedCategory = category
                                                categoriesMenuOpened = false
                                            }
                                        )
                                    }
                                }
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "Settings",
                                    tint = Color.White,
                                    modifier = Modifier.padding(0.dp)
                                )
                            }
                        }

                    })
                }
                Button(onClick = {},
                    shape = Shapes.large
                )
                {
                    Text("Submit expense")
                }
            }
        }
    )
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewAdd(navController: NavController = rememberNavController()){
    DoughFlowTheme {
        Add(navController)
    }
}