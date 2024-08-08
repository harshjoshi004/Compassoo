package com.molog.compassoo_project.educationModule.createEducationAccount.presentationLayer

import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.molog.compassoo_project.MainAppViewModel
import com.molog.compassoo_project.R
import com.molog.compassoo_project.Screen
import com.molog.compassoo_project.educationModule.createEducationAccount.domainLayer.UserData
import com.molog.compassoo_project.ui.theme.TextColor1
import com.molog.compassoo_project.ui.theme.TextColor2
import com.molog.compassoo_project.ui.theme.TextColor4
import com.molog.compassoo_project.ui.theme.futurafamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomiseYourSearchFormLayout(navController: NavHostController, mainAppViewModel: MainAppViewModel) {
    val colorLight = (mainAppViewModel.currentNavigation.value as Screen.Navigation).nColor1
    val colorDark = (mainAppViewModel.currentNavigation.value as Screen.Navigation).nColor2
    val createAccountViewModel = viewModel<CreateAccountViewModel>()
    val formContext = LocalContext.current
    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = colorDark,
        unfocusedBorderColor = TextColor2,
        cursorColor = colorDark,
        focusedTextColor = TextColor1,
        unfocusedTextColor = TextColor2,
        focusedLabelColor = colorDark,
        unfocusedLabelColor = TextColor2
    )

    Column(
//Parent Column
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        //Child Column1
        CustomiseYourSearchFormHeader(
            navController = navController,
            colorDark = colorDark,
            colorLight = colorLight
        )

        Column( //Child Column2
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            //Title
            Text(
                text = "Choose Your Higher Qualification",
                fontFamily = futurafamily,
                color = TextColor2
            )

            //Enter Filter1: String
            val filter1St = remember { mutableStateOf("") }
            val isFilter1Expanded = remember { mutableStateOf(false) }
            val filter1List = listOf("Degree", "Diploma", "Trade")

            ExposedDropdownMenuBox(
                expanded = isFilter1Expanded.value,
                onExpandedChange = { isFilter1Expanded.value = it }) {
                OutlinedTextField(
                    label = { Text(text = "Degree / Diploma / Trade", fontFamily = futurafamily) },
                    value = filter1St.value,
                    onValueChange = { },
                    readOnly = true,
                    singleLine = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    colors = textFieldColors,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isFilter1Expanded.value) }
                )
                ExposedDropdownMenu(
                    modifier = Modifier.background(TextColor4),
                    expanded = isFilter1Expanded.value,
                    onDismissRequest = { isFilter1Expanded.value = false }) {
                    filter1List.forEach { filter1 ->
                        DropdownMenuItem(text = {
                            Text(
                                text = filter1,
                                fontFamily = futurafamily,
                                color = colorDark
                            )
                        },
                            modifier = Modifier.background(TextColor4), onClick = {
                                filter1St.value = filter1
                                isFilter1Expanded.value = false
                            })
                    }
                }
            }

            //Enter Filter2: String
            val filter2St = remember { mutableStateOf("") }
            val isFilter2Expanded = remember { mutableStateOf(false) }
            val filter2List = listOf("Bachelors", "Masters", "PhD")

            ExposedDropdownMenuBox(
                expanded = isFilter2Expanded.value,
                onExpandedChange = { isFilter2Expanded.value = it }) {
                OutlinedTextField(
                    label = { Text(text = "Select your Course", fontFamily = futurafamily) },
                    value = filter2St.value,
                    onValueChange = { },
                    readOnly = true,
                    singleLine = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    colors = textFieldColors,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isFilter2Expanded.value) }
                )
                ExposedDropdownMenu(
                    modifier = Modifier.background(TextColor4),
                    expanded = isFilter2Expanded.value,
                    onDismissRequest = { isFilter2Expanded.value = false }) {
                    filter2List.forEach { filter2 ->
                        DropdownMenuItem(text = {
                            Text(
                                text = filter2,
                                fontFamily = futurafamily,
                                color = colorDark
                            )
                        },
                            modifier = Modifier.background(TextColor4), onClick = {
                                filter2St.value = filter2
                                isFilter2Expanded.value = false
                            })
                    }
                }
            }

            //Enter Filter2: String
            val filter3St = remember { mutableStateOf("") }
            val isFilter3Expanded = remember { mutableStateOf(false) }
            val filter3List = listOf("Business Administration", "Engineering", "Medical", "Pharmacy")

            ExposedDropdownMenuBox(
                expanded = isFilter3Expanded.value,
                onExpandedChange = { isFilter3Expanded.value = it }) {
                OutlinedTextField(
                    label = {
                        Text(
                            text = "Select your Sprecialization",
                            fontFamily = futurafamily
                        )
                    },
                    value = filter3St.value,
                    onValueChange = { },
                    readOnly = true,
                    singleLine = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    colors = textFieldColors,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isFilter3Expanded.value) }
                )
                ExposedDropdownMenu(
                    modifier = Modifier.background(TextColor4),
                    expanded = isFilter3Expanded.value,
                    onDismissRequest = { isFilter3Expanded.value = false }) {
                    filter3List.forEach { filter3 ->
                        DropdownMenuItem(text = {
                            Text(
                                text = filter3,
                                fontFamily = futurafamily,
                                color = colorDark
                            )
                        },
                            modifier = Modifier.background(TextColor4), onClick = {
                                filter3St.value = filter3
                                isFilter3Expanded.value = false
                            })
                    }
                }
            }

            //ButtonsRow
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)){
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = TextColor2,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)){
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterEnd)
                            .border(1.dp, colorDark, RoundedCornerShape(20)),
                        contentAlignment = Alignment.Center
                    ){
                        TextButton(
                            onClick = {
                                // Navigate with parameters
                                if(filter1St.value.isBlank() || filter2St.value.isBlank() || filter3St.value.isBlank()) {
                                    Toast.makeText(formContext, "Fields Are Empty!", Toast.LENGTH_SHORT).show()
                                } else {
                                    navController.navigate(Screen.EducationNavigationScreen.SearchCollegesScreen.route +
                                            "/${filter1St.value}/${filter2St.value}/${filter3St.value}")
                                }
                            },
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                            colors = ButtonDefaults.textButtonColors(contentColor = TextColor1)
                        ) {
                            Text(text = "Done", fontFamily = futurafamily, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomiseYourSearchFormHeader(navController: NavHostController, colorDark: Color, colorLight: Color){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Progress indicator
        CustomiseYourSearchProgressIndicator(progress = 20,
            colorDark = colorDark, colorLight = colorLight)

        Spacer(modifier = Modifier.size(64.dp))

        // Heading
        Text(text = "Customise Your Search",
            fontWeight = FontWeight.Medium, color = TextColor1,
            fontSize = 24.sp, fontFamily = futurafamily)
        Spacer(modifier = Modifier.size(16.dp))

        // Subheading
        Text(text = "Enter your details and set up your New to Canada account right here",
            fontWeight = FontWeight.Normal, color = TextColor2,
            fontFamily = futurafamily, textAlign = TextAlign.Center)

        // Divider
        Spacer(modifier = Modifier.size(64.dp))
        Divider()
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
fun CustomiseYourSearchProgressIndicator(progress: Int, colorDark: Color, colorLight: Color){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically
            ) {
                //Logo
                Icon(painter = painterResource(id = R.drawable.sort_your_seach_logo),
                    contentDescription = null,
                    tint = colorDark,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(20.dp)
                )

                //Create Your Account
                Text(text = "Customise Your Search", color = TextColor1, fontFamily = futurafamily, fontSize = 14.sp)

                //Right Arrow
                Icon(imageVector = Icons.Default.ChevronRight,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(20.dp),
                    contentDescription = null
                )
            }

            //Percentage of Progress
            Text(text = "$progress %", fontSize = 12.sp, color = TextColor1, fontFamily = futurafamily, modifier = Modifier.padding(8.dp))
        }

        //Progress Bars
        LinearProgressIndicator(progress = progress/100f,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = colorDark,
            strokeCap = StrokeCap.Round,
            trackColor = colorLight
        )
    }
}