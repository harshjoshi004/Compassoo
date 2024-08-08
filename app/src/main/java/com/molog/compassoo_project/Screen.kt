package com.molog.compassoo_project

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.molog.compassoo_project.Screen.Navigation
import com.molog.compassoo_project.ui.theme.TextColor1
import com.molog.compassoo_project.ui.theme.TextColor2

sealed class Screen(val title: String, val route: String) {
    sealed class Navigation(
        val nTitle: String,
        val nRoute: String,
        val nColor1: Color,
        val nColor2: Color,
        @DrawableRes val nIcon: Int,
        val completionStatus : Int
    ) : Screen(nTitle, nRoute)
    {
        data object SignInNavigation : Navigation(
            nTitle = "Sign-In",
            nRoute = "sign_in_navigation",
            nColor1 = Color.Green,
            nColor2 = Color.Blue,
            nIcon = R.drawable.google_logo,
            completionStatus = 80
        )
        data object ParentHomeNavigation : Navigation(
            nTitle = "Home",
            nRoute = "parent_home_navigation",
            nColor1 = Color.Yellow,
            nColor2 = Color.Magenta,
            nIcon = R.drawable.baseline_deblur_24,
            completionStatus = 20
        )
        data object EducationModuleNavigation : Navigation(
            nTitle = "Education",
            nRoute = "education_module_navigation",
            nColor1 = Color(0xFFDDF0D5),
            nColor2 = Color(0xFF4FA22C),
            nIcon = R.drawable.education_logo,
            completionStatus = 50
        )
        data object VisaModuleNavigation : Navigation(
            nTitle = "Visa",
            nRoute = "visa_module_navigation",
            nColor1 = Color(0xFFFFECF1),
            nColor2 = Color(0xFFD27B7B),
            nIcon = R.drawable.visa_logo,
            completionStatus = 20
        )
        data object BankingModuleNavigation : Navigation(
            nTitle = "Banking",
            nRoute = "banking_module_navigation",
            nColor1 = Color(0xFFDFE9F2),
            nColor2 = Color(0xFF2B94F2),
            nIcon = R.drawable.banking_logo,
            completionStatus = 70
        )
        data object AccomodationModuleNavigation : Navigation(
            nTitle = "Accomodation",
            nRoute = "accomodation_module_navigation",
            nColor1 = Color(0xFFF3E6D7),
            nColor2 = Color(0xFFECA858),
            nIcon = R.drawable.accommodation_logo,
            completionStatus = 70
        )
    }

    sealed class SignInNavigationScreen(lNtitle: String, lNroute: String) : Screen(lNtitle, lNroute){
        data object Montage1Screen : SignInNavigationScreen("MontageScreen1", "montage_1_screen")
        data object Montage2Screen : SignInNavigationScreen("MontageScreen2", "montage_2_screen")
        data object SignInPageScreen : SignInNavigationScreen("SignInPage", "sign_in_page_screen")
    }

    sealed class ParentNavigatorScreen(pNtitle: String, pNroute: String) : Screen(pNtitle, pNroute){
        data object ParentHomeScreen : ParentNavigatorScreen("Home", "parent_home_screen")
        data object ParentTrackerScreen : ParentNavigatorScreen("Tracker", "parent_tracker_screen")
    }

    sealed class EducationNavigationScreen(eNtitle: String, eNroute: String) : Screen(eNtitle, eNroute){
        data object CreateEducationAccountScreen: EducationNavigationScreen("Create Education Account", "create_education_account_screen")
        data object SearchCollegesScreen: EducationNavigationScreen("Search Colleges", "search_colleges_screen")
        data object CustomiseYourSearchScreen: EducationNavigationScreen("Customise Your Search", "search_colleges_screen")
    }
}

val moduleList = mutableListOf<Screen.Navigation>(
    Navigation.EducationModuleNavigation,
    Navigation.VisaModuleNavigation,
    Navigation.BankingModuleNavigation,
    Navigation.AccomodationModuleNavigation
)
data class BottomBarScreen(
    val title:String,
    val route: String,
    @DrawableRes val logo: Int,
    val selectedColor: Color,
    val unselectedColor: Color,
    val isSelected: Boolean
)

val bottomBarScreens = mutableListOf<BottomBarScreen>(
    BottomBarScreen(
        title = "Home",
        route = Screen.ParentNavigatorScreen.ParentHomeScreen.route,
        logo = R.drawable.home_bottom_bar_logo,
        selectedColor = TextColor1,
        unselectedColor = TextColor2,
        isSelected = false
    ),
    BottomBarScreen(
        title = "Compassoo",
        route = Screen.ParentNavigatorScreen.ParentHomeScreen.route,
        logo = R.drawable.compassoo_app_drawer_logo,
        selectedColor = TextColor1,
        unselectedColor = TextColor2,
        isSelected = false
    ),
    BottomBarScreen(
        title = "Tracker",
        route = Screen.ParentNavigatorScreen.ParentTrackerScreen.route,
        logo = R.drawable.tracker_app_bar_logo,
        selectedColor = TextColor1,
        unselectedColor = TextColor2,
        isSelected = false
    )
)