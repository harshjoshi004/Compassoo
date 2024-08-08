package com.molog.compassoo_project.educationModule

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.molog.compassoo_project.MainAppViewModel
import com.molog.compassoo_project.Screen
import com.molog.compassoo_project.educationModule.createEducationAccount.presentationLayer.CreateAccountFormLayout
import com.molog.compassoo_project.educationModule.createEducationAccount.presentationLayer.CustomiseYourSearchFormLayout
import com.molog.compassoo_project.educationModule.searchColleges.presentationLayer.SearchCollegesScreen

fun NavGraphBuilder.educationGraph(mainAppViewModel: MainAppViewModel, navController: NavHostController){
    navigation(
        route = Screen.Navigation.EducationModuleNavigation.route,
        startDestination = Screen.EducationNavigationScreen.CreateEducationAccountScreen.route
    ) {
        composable(Screen.EducationNavigationScreen.CreateEducationAccountScreen.route){
            CreateAccountFormLayout(navController = navController, mainAppViewModel = mainAppViewModel)
        }
        composable(Screen.EducationNavigationScreen.CustomiseYourSearchScreen.route){
            CustomiseYourSearchFormLayout(navController = navController, mainAppViewModel = mainAppViewModel)
        }
        composable(Screen.EducationNavigationScreen.SearchCollegesScreen.route + "/{filter1}/{filter2}/{filter3}"){
            backStackEntry->
            val filter1 = backStackEntry.arguments?.getString("filter1") ?: ""
            val filter2 = backStackEntry.arguments?.getString("filter2") ?: ""
            val filter3 = backStackEntry.arguments?.getString("filter3") ?: ""
            SearchCollegesScreen(mainViewModel = mainAppViewModel, navController = navController, filter1 = filter1, filter2 = filter2, filter3 = filter3)
        }
    }
}