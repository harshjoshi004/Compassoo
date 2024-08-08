package com.molog.compassoo_project.ui.theme

import androidx.compose.foundation.Image
import com.molog.compassoo_project.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pix
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.molog.compassoo_project.MainAppViewModel
import com.molog.compassoo_project.bottomBarScreens

@Composable
fun MyBottomBar(
    mainAppViewModel: MainAppViewModel,
    navController: NavController
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = TextColor2,
        modifier = Modifier.shadow(elevation = 4.dp)
    ) {
        bottomBarScreens.forEachIndexed { ind, screen ->
            if(screen.logo == R.drawable.compassoo_app_drawer_logo)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = screen.logo),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = screen.title,
                        color = if(screen.isSelected) screen.selectedColor else screen.unselectedColor,
                        fontFamily = futurafamily,
                        fontSize = 12.sp
                    )
                }

            else
            NavigationBarItem(
                selected = screen.isSelected,
                label = {

                },
                onClick = {
                     if(!screen.isSelected)
                     navController.navigate(screen.route)
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = screen.logo),
                            modifier = Modifier.size(20.dp),
                            tint = if (screen.isSelected) screen.selectedColor else screen.unselectedColor,
                            contentDescription = null
                        )
                        Text(
                            text = screen.title,
                            color = if(screen.isSelected) screen.selectedColor else screen.unselectedColor,
                            fontFamily = futurafamily,
                            fontSize = 12.sp
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomBar(){
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Home",
                navAction = { /*TODO*/ },
                prScore = 0.8f,
                actionFun = { /*TODO*/ },
                colorDark = Color.Magenta
            )
        },
        bottomBar = {
            MyBottomBar(viewModel<MainAppViewModel>(), rememberNavController())
        }
    ) {innerPadding->
        Box(modifier = Modifier
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize())
    }
}