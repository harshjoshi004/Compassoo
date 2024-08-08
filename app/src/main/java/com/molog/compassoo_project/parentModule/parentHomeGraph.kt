package com.molog.compassoo_project.parentModule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.molog.compassoo_project.MainAppViewModel
import com.molog.compassoo_project.Screen
import com.molog.compassoo_project.educationModule.educationGraph
import com.molog.compassoo_project.parentModule.compassoHome.ParentHome
import com.molog.compassoo_project.parentModule.signIn.SignInViewModel
import com.molog.compassoo_project.ui.theme.MyBottomBar
import com.molog.compassoo_project.ui.theme.MyTopAppBar

fun NavGraphBuilder.parentHomeGraph(
    mainAppViewModel: MainAppViewModel,
    signInViewModel: SignInViewModel,
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.ParentNavigatorScreen.ParentHomeScreen.route,
        route = Screen.Navigation.ParentHomeNavigation.route
    ){
        composable(Screen.ParentNavigatorScreen.ParentHomeScreen.route) {
            ParentHome(mainAppViewModel, signInViewModel, navController)
        }
        composable(Screen.ParentNavigatorScreen.ParentTrackerScreen.route) {
            ParentTracker(mainAppViewModel, signInViewModel, navController)
        }
        educationGraph(mainAppViewModel,navController)
    }
}

@Composable
fun ParentTracker(
    mainAppViewModel: MainAppViewModel, 
    signInViewModel: SignInViewModel, 
    navController: NavHostController
){
    Scaffold(
        topBar = { MyTopAppBar(
            title = mainAppViewModel.currentNavigation.value.title,
            navAction = { /*TODO*/ },
            prScore = 0.4f,
            actionFun = { /*TODO*/ },
            colorDark = (mainAppViewModel.currentNavigation.value as Screen.Navigation).nColor2
        )},
        bottomBar = { MyBottomBar(mainAppViewModel = mainAppViewModel, navController = navController)}
    ) {innerpadding->
        Column(modifier = Modifier.padding(innerpadding)) {
            Text(text = "Tracker")
        }
    }
}