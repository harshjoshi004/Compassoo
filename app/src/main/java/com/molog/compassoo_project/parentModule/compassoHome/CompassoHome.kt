package com.molog.compassoo_project.parentModule.compassoHome

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.molog.compassoo_project.MainAppViewModel
import com.molog.compassoo_project.R
import com.molog.compassoo_project.moduleList
import com.molog.compassoo_project.ui.theme.TextColor2
import com.molog.compassoo_project.ui.theme.futurafamily
import com.molog.compassoo_project.parentModule.signIn.SignInViewModel
import com.molog.compassoo_project.ui.theme.MyBottomBar


@Composable
fun ParentHome(mainViewModel: MainAppViewModel, signInViewModel: SignInViewModel, navController: NavHostController) {
    val sysUiCon = rememberSystemUiController()
    sysUiCon.isStatusBarVisible = true
    sysUiCon.isNavigationBarVisible = true
    sysUiCon.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = true
    )
    sysUiCon.setNavigationBarColor(
        color = Color.Black,
        darkIcons = false
    )

    Scaffold(
        topBar = {
            CompassoTopAppBar(
                title = mainViewModel.currentScreen.value.title,
                iconUrl = signInViewModel.myUserData.value?.userPhotoUrl?:"https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y"
            )
        },
        bottomBar = {
            MyBottomBar(mainViewModel, navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
            ) {
                if(!signInViewModel.isMyUserDataNull())
                    item { Column {
                        Text(text = "Welcome! ${signInViewModel.myUserData.value?.userName}",
                            fontWeight = FontWeight.Medium,
                            fontFamily = futurafamily)
                        Spacer(modifier = Modifier.size(24.dp)) }
                    }

                moduleList.forEach {
                    item { CompassoHomeExploreItem(name = it.nTitle,
                        color1 = it.nColor1,
                        color2 = it.nColor2,
                        logo = it.nIcon,
                        completionStatus = it.completionStatus,
                        onClick = {
                            navController.navigate(it.nRoute) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    ) }
                    item { Spacer(modifier = Modifier.size(24.dp)) }
                }
            }
        }
    }
}

@Composable
fun CompassoHomeExploreItem(
    name: String,
    color1: Color,
    color2: Color,
    @DrawableRes logo: Int,
    completionStatus: Int,
    onClick : ()->Unit
){
    Box(modifier = Modifier
        .height(100.dp)
        .border(1.dp, color2, RoundedCornerShape(10))
        .clip(RoundedCornerShape(10))
        .background(Brush.horizontalGradient(colorStops = arrayOf(
            0.4f to color1,
            1f to Color.White
        )))
        .fillMaxWidth()
        .clickable {
            onClick()
        }
    ){
        Image(
            modifier = Modifier
                .padding(end = 24.dp)
                .size(60.dp)
                .align(Alignment.CenterEnd),
            painter = painterResource(id = logo),
            contentScale = ContentScale.Fit,
            contentDescription = null
        )

        Text(text = name,
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterStart),
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            fontFamily = futurafamily
        )


        LinearProgressIndicator(
            trackColor = color1,
            color = color2,
            strokeCap = StrokeCap.Square,
            progress = {(completionStatus.toFloat()/100)},
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(8.dp),
        )
    }
}

@Composable
fun CompassoHomeSearchBox(){
    Box(modifier = Modifier
        .height(50.dp)
        .fillMaxWidth()
    ){
        Row {
            Spacer(modifier = Modifier.size(15.dp))
            Box(//Box1
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .border(1.dp, TextColor2, RoundedCornerShape(25)),
                contentAlignment = Alignment.Center
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Spacer(modifier = Modifier.size(8.dp))
                    Icon(
                        modifier = Modifier, //.size(28.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = null)
                    Spacer(modifier = Modifier.size(8.dp))
                    BasicTextField(
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(
                            //fontSize = 25.sp,
                            fontFamily = futurafamily,
                            fontWeight = FontWeight.Light,
                        ),
                        value = "Search",
                        onValueChange = {}
                    )
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Box(//Box2
                modifier = Modifier
                    .size(50.dp)
                    .border(1.dp, TextColor2, RoundedCornerShape(25)),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painterResource(id = R.drawable.tune_icon),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp),
                    tint = TextColor2
                )
            }
            Spacer(modifier = Modifier.size(15.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompassoTopAppBar(title: String, iconUrl: String?){
    CenterAlignedTopAppBar(
        modifier = Modifier.shadow(1.dp),
        colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
        ),
        title = {
            Image(modifier = Modifier.height(24.dp),
                painter = painterResource(id = R.drawable.compasso_logo),
                contentDescription = null
            )
        },
        navigationIcon = {
            Icon(imageVector = Icons.Default.Menu,
                contentDescription = null,
                tint = TextColor2,
                modifier = Modifier
                    .padding(start = 24.dp)
                    .size(30.dp)
            )
        },
        actions = {
            println("Harsh: icon url $iconUrl")
            Image(painter = rememberAsyncImagePainter(iconUrl),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 24.dp)
                    .clip(CircleShape)
                    .size(30.dp),
            )
        }
    )
}
