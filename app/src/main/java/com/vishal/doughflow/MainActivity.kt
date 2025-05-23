package com.vishal.doughflow

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vishal.doughflow.pages.Add
import com.vishal.doughflow.pages.Categories
import com.vishal.doughflow.pages.Expenses
import com.vishal.doughflow.pages.Settings
import com.vishal.doughflow.ui.theme.DoughFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        enableEdgeToEdge()
        setContent {
            DoughFlowTheme {
                var showBottomBar by rememberSaveable { mutableStateOf(true) }
                val navController  = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                showBottomBar = when (backStackEntry?.destination?.route) {
                    "settings/categories" -> false
                    else -> true
                }
                Scaffold(
                    bottomBar = {
                        if( showBottomBar){
                            NavigationBar {
                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "expenses",
                                    onClick = {navController.navigate("expenses"){
                                        run {
                                            launchSingleTop = true
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            restoreState = true
                                        }
                                    } },
                                    label = {
                                        Text("Expenses")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.upload),
                                            contentDescription = "Upload"
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "reports",
                                    onClick = { navController.navigate("reports"){
                                        run {
                                            launchSingleTop = true
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            restoreState = true
                                        }
                                    } },
                                    label = {
                                        Text("Reports")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.bar_chart),
                                            contentDescription = "Reports"
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "add",
                                    onClick = { navController.navigate("add"){
                                        run {
                                            launchSingleTop = true
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            restoreState = true
                                        }
                                    } },
                                    label = {
                                        Text("Add")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.add),
                                            contentDescription = "Add"
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route?.startsWith("settings") ?: false,
                                    onClick = {navController.navigate("settings"){
                                        run {
                                            launchSingleTop = true
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            restoreState = true
                                        }
                                    }  },
                                    label = {
                                        Text("Settings")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.settings_outlined),
                                            contentDescription = "Settings"
                                        )
                                    }
                                )

                            }
                        }
                    },
                    content = { innerPadding ->
                        NavHost(navController = navController, startDestination = "expenses"){
                            composable("expenses"){
                                Surface(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)) {
                                    Expenses(navController)
                                }
                            }
                            composable("reports"){
                                Surface(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)) {
                                    Expenses(navController)
                                }
                            }
                            composable("add"){
                                Surface(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)) {
                                    Add(navController)
                                }
                            }
                            composable("settings"){
                                Surface(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)) {
                                    Settings(navController)
                                }
                            }
                            composable("settings/categories"){
                                Surface(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)) {
                                    Categories(navController)
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}
