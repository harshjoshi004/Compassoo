package com.molog.compassoo_project

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.molog.compassoo_project.educationModule.searchColleges.presentationLayer.FilterScreenPreview
import com.molog.compassoo_project.educationModule.searchColleges.presentationLayer.PreviewMySearchScreen
import com.molog.compassoo_project.ui.theme.CompassooProjectTheme
import com.molog.compassoo_project.ui.theme.PreviewBottomBar

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val vm = viewModel<MainAppViewModel>()
            vm.currentNavigation.value = Screen.Navigation.EducationModuleNavigation
            CompassooProjectTheme {
                MainAppNavigation()
                //PreviewMySearchScreen()
                //FilterScreenPreview()
            }
        }
    }
}