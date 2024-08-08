package com.molog.compassoo_project.parentModule.montage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.molog.compassoo_project.MainAppViewModel
import com.molog.compassoo_project.R
import com.molog.compassoo_project.Screen
import com.molog.compassoo_project.parentModule.signIn.SignInViewModel
import com.molog.compassoo_project.ui.theme.SvgImage
import com.molog.compassoo_project.ui.theme.SvgImageBackground
import com.molog.compassoo_project.ui.theme.TextColor3
import com.molog.compassoo_project.ui.theme.TextColor4
import com.molog.compassoo_project.ui.theme.futurafamily

@Composable
fun Montage1UI(mainViewModel: MainAppViewModel, signInViewModel: SignInViewModel, navController: NavHostController){
    val sysUiController = rememberSystemUiController()
    sysUiController.isStatusBarVisible = false
    sysUiController.setStatusBarColor(
        color = Color.Transparent,
    )
    sysUiController.setNavigationBarColor(color = Color.Transparent)

    //ParentBox
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        SvgImageBackground(assetName = "montage1bg.svg", modifier = Modifier.fillMaxSize())

        //Text Overlays
        Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0f to Color.Transparent,
                            1f to Color.Black
                        )
                    )
                )
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            // Text Titles
            Text(modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                text = "Welcome to",
                fontSize = 30.sp,
                fontFamily = futurafamily,
                color = TextColor4,
                fontWeight = FontWeight.Normal,
            )

            Image(painter = painterResource(id = R.drawable.compasso_logo),
                contentDescription = null,
                colorFilter = ColorFilter.tint(TextColor4),
                modifier = Modifier.height(60.dp).padding(start = 16.dp, end = 16.dp)
            )

            Spacer(modifier = Modifier.size(16.dp))

            //Text Para
            Text(modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                text = "Your all-in-one mobile companion for a seamless journey to visiting or relocating to Canada, with tailored guides, interactive tools, and personalized support at your fingertips",
                fontSize = 16.sp,
                fontFamily = futurafamily,
                color = TextColor4,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.size(24.dp))

            //Button Group
            Box(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)){
                Box(modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterEnd)
                        .border(1.dp, TextColor3, RoundedCornerShape(20)),
                    contentAlignment = Alignment.Center
                ){
                    TextButton(onClick = { navController.navigate(Screen.SignInNavigationScreen.Montage2Screen.route) },
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                        colors = ButtonDefaults.textButtonColors(contentColor = TextColor4)
                    ) {
                        Text(text = "Next")
                    }
                }
            }

            Spacer(modifier = Modifier.size(24.dp))

            //Progress Dots
            SvgImage(assetName = "montage1_progress_dots.svg",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )

            // Ending spacer to prevent overlapping with system bars
            Spacer(modifier = Modifier.systemBarsPadding().size(16.dp))
        }
    }
}

@Composable
fun Montage2UI(mainViewModel: MainAppViewModel, signInViewModel: SignInViewModel, navController: NavHostController){
    val sysUiController = rememberSystemUiController()
    sysUiController.isStatusBarVisible = false
    sysUiController.setStatusBarColor(
        color = Color.Transparent,
    )
    sysUiController.setNavigationBarColor(color = Color.Transparent)

    //ParentBox
    Box(modifier = Modifier.fillMaxSize()) {
        SvgImageBackground(assetName = "montage2bg.svg", modifier = Modifier.fillMaxSize())

        //Text Overlays
        Column(modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .background(
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        0f to Color.Transparent,
                        1f to Color.Black
                    )
                )
            )
            .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            // Text Titles
            Text(text = "Seamless journey to",
                fontSize = 30.sp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                fontFamily = futurafamily,
                color = TextColor4,
                fontWeight = FontWeight.Normal,
            )

            Text(text = "Canada",
                fontSize = 50.sp,
                fontFamily = futurafamily,
                color = TextColor4,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            )

            Spacer(modifier = Modifier.size(16.dp))

            //Text Para
            Text(text = "Compassoo offers tailored modules for education, visa, banking, and accommodation, providing all the essential tools and information for your move to Canada.",
                fontSize = 16.sp,
                fontFamily = futurafamily,
                color = TextColor4,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            )

            Spacer(modifier = Modifier.size(24.dp))

            //Button Group
            Box(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)){
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterEnd)
                        .border(1.dp, TextColor3, RoundedCornerShape(20)),
                    contentAlignment = Alignment.Center
                ){
                    TextButton(onClick = { navController.navigate(Screen.SignInNavigationScreen.SignInPageScreen.route) },
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                        colors = ButtonDefaults.textButtonColors(contentColor = TextColor4)
                    ) {
                        Text(text = "Next")
                    }
                }
            }

            Spacer(modifier = Modifier.size(24.dp))

            //Progress Dots
            SvgImage(assetName = "montage2_progress_dots.svg",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )

            // Ending spacer to prevent overlapping with system bars
            Spacer(modifier = Modifier.systemBarsPadding().size(16.dp))
        }
    }
}