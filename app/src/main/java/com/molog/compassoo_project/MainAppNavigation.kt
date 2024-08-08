package com.molog.compassoo_project

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.molog.compassoo_project.parentModule.compassoHome.ParentHome
import com.molog.compassoo_project.parentModule.parentHomeGraph
import com.molog.compassoo_project.parentModule.signIn.SignInViewModel
import com.molog.compassoo_project.parentModule.signInGraph

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainAppNavigation(){
    val mainAppViewModel: MainAppViewModel = viewModel()
    val signInViewModel:SignInViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(modifier = Modifier,
        navController = navController,
        startDestination =
            if(mainAppViewModel.signInDone.value)
                Screen.Navigation.ParentHomeNavigation.route
            else
                Screen.Navigation.SignInNavigation.route
    ){
        signInGraph(mainAppViewModel, signInViewModel, navController)
        parentHomeGraph(mainAppViewModel, signInViewModel, navController)
    }
}