package com.vishal.doughflow.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vishal.doughflow.components.TableRow
import com.vishal.doughflow.ui.theme.BackgroundElevated
import com.vishal.doughflow.ui.theme.DividerColor
import com.vishal.doughflow.ui.theme.Shapes
import com.vishal.doughflow.ui.theme.TopAppBarBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController){

    Scaffold(
        topBar = {
            MediumTopAppBar(title = { Text("Settings") }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground
            ))
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Column (
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(shape = Shapes.large)
                        .fillMaxWidth()
                        .background(BackgroundElevated)
                ){
                    TableRow("Categories", hasArrow = true, modifier = Modifier.clickable {
                        navController.navigate("settings/categories")
                    })
                    HorizontalDivider(modifier = Modifier.padding(start = 16.dp), thickness = 1.dp, color = DividerColor)
                    TableRow("Erase all data", isDestructive = true)
                }
            }

        }
    )
}