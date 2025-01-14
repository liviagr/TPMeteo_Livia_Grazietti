/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package csc.livia.weatherapp

import android.app.Activity
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android.architecture.blueprints.todoapp.TodoNavigationActions
import csc.livia.weatherapp.ui.HomeDestination
import csc.livia.weatherapp.ui.HomeScreen
import csc.livia.weatherapp.ui.searchBar.SearchBarViewModel
import csc.livia.weatherapp.ui.weatherDetails.WeatherDetailsScreen
//import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskScreen
//import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsScreen
//import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailScreen
//import com.example.android.architecture.blueprints.todoapp.tasks.TasksScreen
//import com.example.android.architecture.blueprints.todoapp.util.AppModalDrawer
import kotlinx.coroutines.CoroutineScope

@Composable
fun WeatherNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = HomeDestination.route,
    navActions: TodoNavigationActions = remember(navController) {
        TodoNavigationActions(navController)
    }
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination
    val viewModel: SearchBarViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                name = "test", viewModel = viewModel,
                onSearchTriggered = { city ->
                    navController.navigate("weatherDetails/$city")
                },
                navigateBack = {navController.popBackStack()}
            )
        }

        composable("weatherDetails/{city}") {
            // Weather details screen
            WeatherDetailsScreen(navigateBack = {navController.popBackStack()}
            )
        }

    }
}

// Keys for navigation
const val ADD_EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 1
const val DELETE_RESULT_OK = Activity.RESULT_FIRST_USER + 2
const val EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 3

