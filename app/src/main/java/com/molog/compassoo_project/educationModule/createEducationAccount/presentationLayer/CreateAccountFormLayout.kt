package com.molog.compassoo_project.educationModule.createEducationAccount.presentationLayer

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.molog.compassoo_project.MainAppViewModel
import com.molog.compassoo_project.Screen
import com.molog.compassoo_project.educationModule.createEducationAccount.domainLayer.UserData
import com.molog.compassoo_project.ui.theme.SvgImage
import com.molog.compassoo_project.ui.theme.SvgImageBackground
import com.molog.compassoo_project.ui.theme.TextColor1
import com.molog.compassoo_project.ui.theme.TextColor2
import com.molog.compassoo_project.ui.theme.TextColor3
import com.molog.compassoo_project.ui.theme.TextColor4
import com.molog.compassoo_project.ui.theme.futurafamily
import com.molog.compassoo_project.R
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun CreateAccountProgressIndicator(progress: Int, colorDark: Color, colorLight: Color){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
            //Logo
                Icon(painter = painterResource(id = R.drawable.create_your_account_logo),
                    contentDescription = null,
                    tint = colorDark,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(20.dp))

            //Create Your Account
                Text(text = "Create your Account", color = TextColor1, fontFamily = futurafamily, fontSize = 14.sp)

            //Right Arrow
                Icon(imageVector = Icons.Default.ChevronRight,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(20.dp),
                    contentDescription = null)
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
            trackColor = colorLight)
    }
}

@Composable
fun CreateAccountFormHeader(navController: NavHostController, colorDark: Color, colorLight: Color){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
    // Progress indicator
        CreateAccountProgressIndicator(progress = 20,
            colorDark = colorDark, colorLight = colorLight)

        //Spacer(modifier = Modifier.size(64.dp))
        Spacer(modifier = Modifier.size(16.dp))

        // Skip Button
        Box(modifier = Modifier.fillMaxWidth()){
            TextButton(modifier = Modifier.align(Alignment.TopEnd),
                onClick = { navController.navigate(Screen.EducationNavigationScreen.CustomiseYourSearchScreen.route) }
            ) { Text(text = "Skip", color = TextColor1, fontFamily = futurafamily, textDecoration = TextDecoration.Underline) }
        }
        Spacer(modifier = Modifier.size(16.dp))

    // Heading
        Text(text = "Create Your Account",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountFormLayout(navController: NavHostController, mainAppViewModel: MainAppViewModel){
    val colorLight = (mainAppViewModel.currentNavigation.value as Screen.Navigation).nColor1
    val colorDark = (mainAppViewModel.currentNavigation.value as Screen.Navigation).nColor2
    val createAccountViewModel = viewModel<CreateAccountViewModel>()
    val formSubmissionStatus by createAccountViewModel.statusMessageState.collectAsState()
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

    LaunchedEffect(key1 = formSubmissionStatus) {
        when(formSubmissionStatus) {
            "100" -> {
                Toast.makeText(formContext, "Form submitted successfully!", Toast.LENGTH_SHORT).show()
                createAccountViewModel.resetState()
                navController.navigate(Screen.EducationNavigationScreen.CustomiseYourSearchScreen.route)
            }
            "401" -> {
                Toast.makeText(formContext, "User Already Exists, Welcome Back!", Toast.LENGTH_SHORT).show()
                createAccountViewModel.resetState()
                navController.navigate(Screen.EducationNavigationScreen.CustomiseYourSearchScreen.route)
            }
            "200" -> {
                Toast.makeText(formContext, "Form submission failed!", Toast.LENGTH_SHORT).show()
                createAccountViewModel.resetState()
            }
            else -> Unit
        }
    }

    Column(//Parent Column
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        //Child Column1
        CreateAccountFormHeader(navController = navController, colorDark = colorDark, colorLight = colorLight)

        Column(//Child Column2
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(text = "Personal Details", fontFamily = futurafamily, color = TextColor2)

            //Enter Name: String
            val nameSt = remember { mutableStateOf("") }
            var nameError by remember{mutableStateOf<String?>(null)}
            OutlinedTextField(colors = textFieldColors,
                label = { Text(text = "Enter Your Full Name", fontFamily = futurafamily) },
                value = nameSt.value, onValueChange = { it->
                    nameSt.value = it
                    nameError = if(it.isBlank()) "This field cannot be empty." else null
                },
                isError = nameError != null,
                supportingText = {
                    if (nameError != null) {
                        Text(text = "Invalid Name!", color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth(), singleLine = true)

            //Enter Phone Number: String
            val phoneSt = remember { mutableStateOf("") }
            val isCountryCodeExpanded = remember { mutableStateOf(false) }
            val countryCodeSt = remember { mutableStateOf("+91") }
            var phoneNumberError by remember { mutableStateOf<String?>(null) }
            val countryCodeList = listOf(
                "+91" to "India",
                "+1" to "United States",
                "+44" to "United Kingdom",
                "+355" to "Portugal",
                "+49" to "Germany",
                "+86" to "China",
                "+971" to "Russia"
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                //Country Code Part
                ExposedDropdownMenuBox(
                    expanded = isCountryCodeExpanded.value,
                    onExpandedChange = { it -> isCountryCodeExpanded.value = it }) {
                    OutlinedTextField(
                        colors = textFieldColors,
                        value = countryCodeSt.value,
                        onValueChange = {

                        },
                        singleLine = true,
                        readOnly = true,
                        modifier = Modifier
                            .width(110.dp)
                            .padding(bottom = 8.dp)
                            .menuAnchor()
                            .weight(1f),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isCountryCodeExpanded.value) }
                    )
                    ExposedDropdownMenu(
                        modifier = Modifier.background(TextColor4),
                        expanded = isCountryCodeExpanded.value,
                        onDismissRequest = { isCountryCodeExpanded.value = false }) {
                        countryCodeList.forEach { countryCode ->
                            DropdownMenuItem(
                                text = { Text(text = "${countryCode.first} (${countryCode.second})", fontFamily = futurafamily, color = colorDark) },
                                modifier = Modifier.background(TextColor4),
                                onClick = {
                                    countryCodeSt.value = countryCode.first
                                    isCountryCodeExpanded.value = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                //Phone Number Part
                OutlinedTextField(
                    label = { Text(text = "Phone Number", fontFamily = futurafamily) },
                    value = phoneSt.value,
                    onValueChange = {it->
                        phoneSt.value = it
                        phoneNumberError = validatePhoneNumber(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    colors = textFieldColors,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    isError = phoneNumberError != null,
                    supportingText = {
                        if (phoneNumberError != null) {
                            Text(text = "Invalid Phone Number!", color = MaterialTheme.colorScheme.error)
                        }
                    }
                )
            }

            //Enter Email: String
            val emailSt = remember { mutableStateOf("") }
            var emailErrorState by remember { mutableStateOf<String?>(null) }
            OutlinedTextField(
                label = { Text(text = "E-Mail", fontFamily = futurafamily) },
                value = emailSt.value,
                onValueChange = { it->
                    emailSt.value = it
                    emailErrorState = validateEmail(it)
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = textFieldColors,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = emailErrorState != null,
                supportingText = {
                    if (emailErrorState != null) {
                        Text(text = "Invalid Email!", color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            Text(text = "Exams Taken", fontFamily = futurafamily, color = TextColor2)

            //Enter Exams: List<String>
            val isExamsExpanded = remember { mutableStateOf(false) }
            val selectedExamString = remember { mutableStateOf("") }
            val examList = listOf("IELTS", "SAT", "GMAT", "GRE", "TOEFL", "TEF", "TCF", "ACT")
            val selectedExamList = remember { mutableStateListOf<String>() }
            ExposedDropdownMenuBox(
                expanded = isExamsExpanded.value,
                onExpandedChange = { isExamsExpanded.value = it }) {
                OutlinedTextField(
                    label = { Text(text = "Select Exams Taken", fontFamily = futurafamily) },
                    value = selectedExamString.value,
                    onValueChange = {},
                    singleLine = true,
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    colors = textFieldColors,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExamsExpanded.value) }
                )
                ExposedDropdownMenu(
                    modifier = Modifier.background(TextColor4),
                    expanded = isExamsExpanded.value,
                    onDismissRequest = { isExamsExpanded.value = false },
                ) {
                    examList.forEach { exam ->
                        DropdownMenuItem(
                            text = { Text(text = exam, fontFamily = futurafamily, color = colorDark) },
                            modifier = Modifier.background(TextColor4),
                            onClick = {
                            if (!selectedExamList.contains(exam)) {
                                if (selectedExamList.isEmpty()) {
                                    selectedExamString.value += exam
                                } else {
                                    selectedExamString.value += (" / $exam")
                                }
                                selectedExamList.add(exam)
                                isExamsExpanded.value = false
                            } else {
                                isExamsExpanded.value = false
                            }
                        })
                    }
                }
            }

            Text(text = "Profile Details", fontFamily = futurafamily, color = TextColor2)

            //Enter Work Experience: Int
            val workExperienceSt = remember { mutableIntStateOf(0) }
            val isWorkExpanded = remember { mutableStateOf(false) }
            val workExList = listOf(0, 1, 2, 3, 4, 5)

            ExposedDropdownMenuBox(
                expanded = isWorkExpanded.value,
                onExpandedChange = { isWorkExpanded.value = it }) {
                OutlinedTextField(
                    label = { Text(text = "Select Work Experience", fontFamily = futurafamily) },
                    value = workExperienceSt.intValue.toString(),
                    suffix = { Text(text = "years", fontFamily = futurafamily, color = colorDark) },
                    onValueChange = { },
                    readOnly = true,
                    singleLine = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    colors = textFieldColors,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isWorkExpanded.value) }
                )
                ExposedDropdownMenu(
                    modifier = Modifier.background(TextColor4),
                    expanded = isWorkExpanded.value,
                    onDismissRequest = { isWorkExpanded.value = false }) {
                    workExList.forEach { workEx ->
                        DropdownMenuItem(text = { Text(text = workEx.toString(), fontFamily = futurafamily, color = colorDark) },
                            modifier = Modifier.background(TextColor4), onClick = {
                            workExperienceSt.intValue = workEx
                            isWorkExpanded.value = false
                        })
                    }
                }
            }

            //Select Qualifications
            val isQualificationExpanded = remember { mutableStateOf(false) }
            val selectedQualificationString = remember { mutableStateOf("") }
            val qualificationList = listOf("10th", "12th", "Bachelor's", "Masters", "PhD")
            val selectedQualificationList = remember { mutableStateListOf<String>() }
            ExposedDropdownMenuBox(
                expanded = isQualificationExpanded.value,
                onExpandedChange = { isQualificationExpanded.value = it }) {
                OutlinedTextField(
                    label = { Text(text = "Select Qualifications", fontFamily = futurafamily) },
                    value = selectedQualificationString.value,
                    onValueChange = {},
                    singleLine = true,
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    colors = textFieldColors,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isQualificationExpanded.value) }
                )
                ExposedDropdownMenu(
                    modifier = Modifier.background(TextColor4),
                    expanded = isQualificationExpanded.value,
                    onDismissRequest = { isQualificationExpanded.value = false }) {
                    qualificationList.forEach { qualification ->
                        DropdownMenuItem(
                            text = { Text(text = qualification, fontFamily = futurafamily, color = colorDark) },
                            modifier = Modifier.background(TextColor4),
                            onClick = {
                                if (!selectedQualificationList.contains(qualification)) {
                                    if (selectedQualificationList.isEmpty()) {
                                        selectedQualificationString.value += qualification
                                    } else {
                                        selectedQualificationString.value += (" / $qualification")
                                    }
                                    selectedQualificationList.add(qualification)
                                    isQualificationExpanded.value = false
                                } else {
                                    isQualificationExpanded.value = false
                                }
                            }
                        )
                    }
                }
            }
            //User PR
            val userPRSt = remember { mutableStateOf(false) }
            val isUserPrExpanded = remember { mutableStateOf(false) }
            val userPRList = listOf("Yes" to true, "No" to false)
            val selectedUserPRString = remember { mutableStateOf("") }

            ExposedDropdownMenuBox(
                expanded = isUserPrExpanded.value,
                onExpandedChange = { isUserPrExpanded.value = it }
            ) {
                OutlinedTextField(
                    label = { Text(text = "Do you have PR?", fontFamily = futurafamily) },
                    value = selectedUserPRString.value,
                    onValueChange = {},
                    singleLine = true,
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    colors = textFieldColors,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isUserPrExpanded.value) }
                )
                ExposedDropdownMenu(
                    modifier = Modifier.background(TextColor4),
                    expanded = isUserPrExpanded.value,
                    onDismissRequest = { isUserPrExpanded.value = false }
                ) {
                    userPRList.forEach { userPR ->
                        DropdownMenuItem(
                            text = { Text(text = userPR.first, fontFamily = futurafamily, color = colorDark) },
                            modifier = Modifier.background(TextColor4),
                            onClick = {
                                selectedUserPRString.value = userPR.first
                                userPRSt.value = userPR.second
                                isUserPrExpanded.value = false
                            }
                        )
                    }
                }
            }
            //Language
            val isLanguagesExpanded = remember { mutableStateOf(false) }
            val selectedLanguagesString = remember { mutableStateOf("") }
            val languagesList =
                listOf("English", "French", "Hindi", "Spanish", "German", "Italian", "Japanese")
            val selectedLanguagesList = remember { mutableStateListOf<String>() }
            ExposedDropdownMenuBox(
                expanded = isLanguagesExpanded.value,
                onExpandedChange = { isLanguagesExpanded.value = it }) {
                OutlinedTextField(
                    label = { Text(text = "Select Languages", fontFamily = futurafamily) },
                    value = selectedLanguagesString.value,
                    onValueChange = {},
                    singleLine = true,
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    colors = textFieldColors,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isLanguagesExpanded.value) }
                )
                ExposedDropdownMenu(
                    modifier = Modifier.background(TextColor4),
                    expanded = isLanguagesExpanded.value,
                    onDismissRequest = { isLanguagesExpanded.value = false }) {
                    languagesList.forEach { language ->
                        DropdownMenuItem(
                            text = { Text(text = language, fontFamily = futurafamily, color = colorDark) },
                            modifier = Modifier.background(TextColor4),
                            onClick = {
                                if (!selectedLanguagesList.contains(language)) {
                                    if (selectedLanguagesList.isEmpty()) {
                                        selectedLanguagesString.value += language
                                    } else {
                                        selectedLanguagesString.value += (" / $language")
                                    }
                                    selectedLanguagesList.add(language)
                                    isLanguagesExpanded.value = false
                                } else {
                                    isLanguagesExpanded.value = false
                                }
                            }
                        )
                    }
                }
            }
            //Enter Resume: String
            //ButtonsRow
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)){
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
                                val userData = UserData(
                                    name = nameSt.value,
                                    email = emailSt.value,
                                    profilePicture = "",
                                    phone = phoneSt.value,
                                    exams = selectedExamList,
                                    workExperience = workExperienceSt.intValue,
                                    qualifications = selectedQualificationList,
                                    userPR = userPRSt.value,
                                    language = selectedLanguagesList,
                                    resume = ""
                                )

                                if(isUserDataValid(userData) && nameError == null && phoneNumberError == null && emailErrorState == null){
                                    print("Create Account Form: Data is valid")
                                    val status = createAccountViewModel.postUserData(userData)
                                }
                                else{
                                    println("Create Account Form: Data is empty or invalid")
                                    Toast.makeText(formContext, "Check details again, all details are compulsory!", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                            colors = ButtonDefaults.textButtonColors(contentColor = TextColor1)
                        ) {
                            Text(text = "Save & Continue", fontFamily = futurafamily, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

// Function to validate the phone number
fun validatePhoneNumber(input: String):String? {
    val localPhoneNumberPattern = """^\d{7,15}$"""

    return when {
        input.isBlank() -> "This field cannot be empty."
        !input.matches(localPhoneNumberPattern.toRegex()) -> "Phone number must be between 7 and 15 digits."
        else -> null
    }
}

// Function to validate the email
fun validateEmail(input: String):String? {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    return when {
        input.isBlank() -> "This field cannot be empty."
        !input.matches(emailPattern.toRegex()) -> "Invalid email address."
        else -> null
    }
}

fun isUserDataValid(user: UserData): Boolean {
    return user.name.isNotBlank() || user.phone.isNotBlank() || user.email.isNotBlank() || user.exams.isNotEmpty() || user.qualifications.isNotEmpty() || user.language.isNotEmpty()
}