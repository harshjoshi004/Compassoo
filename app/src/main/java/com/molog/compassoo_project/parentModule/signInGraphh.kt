package com.molog.compassoo_project.parentModule

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.molog.compassoo_project.MainAppViewModel
import com.molog.compassoo_project.Screen
import com.molog.compassoo_project.parentModule.montage.Montage1UI
import com.molog.compassoo_project.parentModule.montage.Montage2UI
import com.molog.compassoo_project.parentModule.signIn.SignInPageUI
import com.molog.compassoo_project.parentModule.signIn.SignInPageUI
import com.molog.compassoo_project.parentModule.signIn.SignInViewModel

@RequiresApi(Build.VERSION_CODES.R)
fun NavGraphBuilder.signInGraph(mainAppViewModel: MainAppViewModel, signInViewModel: SignInViewModel, navController: NavHostController){
    navigation(
        route = Screen.Navigation.SignInNavigation.route,
        startDestination = Screen.SignInNavigationScreen.Montage1Screen.route
    ) {
        composable(Screen.SignInNavigationScreen.Montage1Screen.route) {
            Montage1UI(mainAppViewModel, signInViewModel, navController)
        }
        composable(Screen.SignInNavigationScreen.Montage2Screen.route) {
            Montage2UI(mainAppViewModel, signInViewModel, navController)
        }
        composable(Screen.SignInNavigationScreen.SignInPageScreen.route) {
            SignInPageUI(mainAppViewModel, signInViewModel, navController)
        }
    }
}