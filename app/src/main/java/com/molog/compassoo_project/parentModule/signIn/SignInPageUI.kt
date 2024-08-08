package com.molog.compassoo_project.parentModule.signIn

import android.app.Activity.RESULT_OK
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.identity.Identity
import com.molog.compassoo_project.MainAppViewModel
import com.molog.compassoo_project.R
import com.molog.compassoo_project.Screen
import com.molog.compassoo_project.ui.theme.TextColor2
import com.molog.compassoo_project.ui.theme.TextColor3
import com.molog.compassoo_project.ui.theme.TextColor4
import com.molog.compassoo_project.ui.theme.futurafamily
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SignInPageUI(mainViewModel: MainAppViewModel, signInViewModel: SignInViewModel, navcon: NavHostController){
    val state by signInViewModel.state.collectAsState()
    val viewModel: SignInViewModel = viewModel()
    val context = LocalContext.current

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            oneTapClient = Identity.getSignInClient(context),
            context = context
        )
    }
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }
    LaunchedEffect(key1 = state.isSignInSuccessful) {
        if(state.isSignInSuccessful){
            Toast.makeText(context, "Sign in successful", Toast.LENGTH_LONG).show()
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if(result.resultCode == RESULT_OK) {
                MainScope().launch {
                    val signInResult = googleAuthUiClient.signInWithIntent(intent = result.data ?: return@launch)
                    viewModel.onSignInResult(signInResult)
                    Toast.makeText(context, "Sign in successful!", Toast.LENGTH_SHORT).show()

                    val user = googleAuthUiClient.getSignedInUser()
                    signInViewModel.setMyUserData(value = user)

                    //navigation logic
                    mainViewModel.signInDone()
                    navcon.navigate(Screen.Navigation.ParentHomeNavigation.route) {
                        popUpTo(Screen.Navigation.SignInNavigation.route) { inclusive = true }
                    }
                }
            } else {
                Toast.makeText(context, "Sign in failed ${result.resultCode}", Toast.LENGTH_LONG).show()
            }
        }
    )

    SignInPageUI(
        navController = navcon,
        mainViewModel = mainViewModel,
        signInFun = {
            MainScope().launch {
                val signInIntentSender = googleAuthUiClient.signIn()
                launcher.launch(
                    IntentSenderRequest.Builder(
                        signInIntentSender ?: return@launch
                    ).build()
                )
            }
        }
    )
}


@Composable
fun SignInPageUI(signInFun:()->Unit, navController: NavHostController, mainViewModel: MainAppViewModel) {
    val sysUiController = rememberSystemUiController()
    sysUiController.isStatusBarVisible = false
    sysUiController.setNavigationBarColor(color = Color.Transparent)
    sysUiController.setStatusBarColor(color = Color.Transparent)

    Box(modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.google_sign_in_bg),
                contentScale = ContentScale.Crop
            ),
    ) {
        //Gradient Box
        Box(modifier = Modifier.background(Brush.verticalGradient(colorStops = arrayOf(
                0f to Color.Black,
                0.5f to Color.Transparent,
                1f to Color.Black
            )))
        ){
            //Inset Box
            Box(modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                //Button Skip
                Column(modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(text = "Welcome to Compassoo",
                        style = TextStyle(fontSize = 24.sp, color = TextColor4, fontFamily = futurafamily, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "To continue, please sign in with your Google account.",
                        style = TextStyle(fontSize = 16.sp, color = TextColor4, fontFamily = futurafamily),
                        modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                    ) {
                        Box(modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.CenterEnd)
                                .border(1.dp, TextColor3, RoundedCornerShape(20)),
                            contentAlignment = Alignment.Center
                        ) {
                            TextButton(onClick = {
                                    mainViewModel.signInDone()
                                    navController.navigate(Screen.Navigation.ParentHomeNavigation.route) {
                                        popUpTo(Screen.Navigation.SignInNavigation.route) {
                                            inclusive = true
                                        }
                                    }
                                },
                                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                                colors = ButtonDefaults.textButtonColors(contentColor = TextColor4)
                            ) {
                                Text(text = "Skip")
                            }
                        }
                    }
                }

                // Continue with Google Button
                Button(shape = RoundedCornerShape(20f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .border(1.dp, TextColor2, RoundedCornerShape(20f))
                        .align(Alignment.BottomCenter),
                    onClick = {
                        signInFun()
                    }
                ) {
                    Row(modifier = Modifier.padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(painter = painterResource(id = R.drawable.google_logo),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        Text(fontWeight = FontWeight.Light,
                            fontFamily = futurafamily,
                            text = "Continue with Google"
                        )
                    }
                }
            }
        }
    }
}