package com.vishal.doughflow.pages

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.vishal.doughflow.components.TableRow
import com.vishal.doughflow.components.UnstyledTextField
import com.vishal.doughflow.ui.theme.BackgroundElevated
import com.vishal.doughflow.ui.theme.DividerColor
import com.vishal.doughflow.ui.theme.DoughFlowTheme
import com.vishal.doughflow.ui.theme.Shapes
import com.vishal.doughflow.ui.theme.TopAppBarBackground
import com.vishal.doughflow.ui.theme.Typography
import com.vishal.doughflow.viewmodels.CategoriesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Categories(navController: NavController, vm: CategoriesViewModel = viewModel()){

    val colorPickerController = rememberColorPickerController()

    val uiState by vm.uiState.collectAsState()

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Categories") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground
                ),
                navigationIcon = {
                    Row (verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(0.dp)){
                        IconButton(
                            onClick =  navController::popBackStack ,
                            colors = IconButtonColors(
                                contentColor = Color(0xFF0A84FF),
                                containerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                disabledContentColor = Color.Transparent
                            )

                        ){
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowLeft,
                                contentDescription = "Localized description",
                                modifier = Modifier.size(36.dp),

                            )
                        }
                        Text("Settings", style = Typography.bodyMedium, color = Color(0xFF0A84FF))
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Column(modifier = Modifier.weight(1f)){
                    LazyColumn(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(Shapes.large)
                            .background(BackgroundElevated)
                            .fillMaxWidth()
                    ) {
                        itemsIndexed(uiState.categories) { index, category ->
                            TableRow {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                ) {
                                    Surface(
                                        color = category.color,
                                        shape = CircleShape,
                                        border = BorderStroke(
                                            width = 2.dp,
                                            color = Color.White
                                        ),
                                        modifier = Modifier.size(16.dp)
                                    ) {}
                                    Text(
                                        category.name,
                                        modifier = Modifier.padding(
                                            horizontal = 16.dp,
                                            vertical = 10.dp
                                        ),
                                        style = Typography.bodyMedium,
                                    )
                                }
                            }
                            if (index < uiState.categories.size - 1) {
                                Divider(
                                    modifier = Modifier.padding(start = 16.dp),
                                    thickness = 1.dp,
                                    color = DividerColor
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    if (uiState.colorPickerShowing) {
                        Dialog(onDismissRequest =  vm::hideColorPicker ) {
                            Surface(color = BackgroundElevated, shape = Shapes.large) {
                                Column(
                                    modifier = Modifier.padding(all = 30.dp)
                                ) {
                                    Text("Select a color", style = Typography.titleLarge)
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 24.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        AlphaTile(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(60.dp)
                                                .clip(RoundedCornerShape(6.dp)),
                                            controller = colorPickerController
                                        )
                                    }
                                    HsvColorPicker(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(300.dp)
                                            .padding(10.dp),
                                        controller = colorPickerController,
                                        onColorChanged = { envelope ->
                                            vm.setNewCategoryColor(envelope.color)
                                        },
                                    )
                                    TextButton(
                                        onClick = vm::hideColorPicker ,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 24.dp),
                                    ) {
                                        Text("Done")
                                    }
                                }
                            }
                        }
                    }
                    Surface(
                        onClick = vm::showColorPicker,
                        shape = CircleShape,
                        color = uiState.newCategoryColor,
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.White
                        ),
                        modifier = Modifier.size(width = 24.dp, height = 24.dp)
                    ) {}
                    Surface(
                        color = BackgroundElevated,
                        modifier = Modifier
                            .height(44.dp)
                            .weight(1f)
                            .padding(start = 16.dp),
                        shape = Shapes.large,
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            UnstyledTextField(
                                value = uiState.newCategoryName,
                                onValueChange = vm::setNewCategoryName ,
                                placeholder = { Text("New Category",Modifier.padding(start = 8.dp)) },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                maxLines = 1,
                            )
                        }
                    }
                    IconButton(
                        onClick = vm::createNewCategory ,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    ) {
                        Icon(
                            Icons.Filled.Send,
                            "Create category"
                        )
                    }
                }
            }
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CategoriesPreview(navController: NavController = rememberNavController()){

    val vm = CategoriesViewModel()
    DoughFlowTheme {
        Categories(navController, vm )
    }
}