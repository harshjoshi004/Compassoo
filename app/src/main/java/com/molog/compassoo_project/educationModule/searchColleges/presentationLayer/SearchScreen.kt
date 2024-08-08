package com.molog.compassoo_project.educationModule.searchColleges.presentationLayer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.molog.compassoo_project.MainAppViewModel
import com.molog.compassoo_project.Screen
import com.molog.compassoo_project.ui.theme.MyBottomBar
import com.molog.compassoo_project.ui.theme.MyTopAppBar
import com.molog.compassoo_project.ui.theme.TextColor1
import com.molog.compassoo_project.ui.theme.TextColor2
import com.molog.compassoo_project.ui.theme.TextColor3
import com.molog.compassoo_project.ui.theme.TextColor4
import com.molog.compassoo_project.ui.theme.futurafamily
import com.molog.compassoo_project.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun PreviewMySearchScreen(){
    SearchCollegesScreen(
        mainViewModel = viewModel<MainAppViewModel>(),
        navController = rememberNavController(),
        filter1 = "BA",
        filter2 = "BA",
        filter3 = "Business"
    )
}

@Composable
fun FilterRow(colorDark: Color, collegeViewModel: SearchCollegeViewModel){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
    ) {
        //Sort Icon
        item {
            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .pointerInput(Unit) {
                        detectTapGestures { _ ->
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(painter = painterResource(id = R.drawable.sorting), contentDescription = null,
                    modifier = Modifier
                        .padding(2.dp)
                        .size(16.dp), tint = TextColor2)
                Text(text = "Sort", fontSize = 12.sp, fontWeight = FontWeight.Light, fontFamily = futurafamily, color = TextColor2)
            }
        }
        //Enable Eligibility
        item {
            val eligibilityState = remember{ mutableStateOf(false) }
            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .pointerInput(Unit) {
                        detectTapGestures { _ ->

                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomSwitch(
                    isChecked = eligibilityState.value,
                    onCheckedChange = {eligibilityState.value = !eligibilityState.value },
                    color = colorDark
                )
                Text(text = "Eligibility", fontSize = 12.sp, fontWeight = FontWeight.Light, fontFamily = futurafamily, color = TextColor2)
            }
        }

        collegeViewModel.searchResultModifierList.forEach { string->
            string?.let { nonNullString->
                item {
                    Box(modifier = Modifier
                        .padding(end = 16.dp)
                        .border(1.dp, TextColor4, RoundedCornerShape(10))
                    ){
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = nonNullString,
                                fontSize = 12.sp,
                                fontFamily = futurafamily,
                                color = TextColor3,
                                modifier = Modifier.padding(start = 16.dp,top = 8.dp, end = 8.dp, bottom = 8.dp)
                            )
                            Icon(
                                modifier = Modifier.padding(top = 8.dp, end = 16.dp, bottom = 8.dp).size(12.dp),
                                tint = TextColor3,
                                imageVector = Icons.Default.Close,
                                contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchCollegesScreen(mainViewModel: MainAppViewModel, navController: NavController, filter1:String,filter2:String,filter3:String) {
    val searchViewModel = viewModel<SearchCollegeViewModel>()
    val collegeDetails by searchViewModel.collegeDetails.collectAsState()
    val loading by searchViewModel.loading.collectAsState()
    val error by searchViewModel.error.collectAsState()

    val colorLight = (mainViewModel.currentNavigation.value as Screen.Navigation).nColor1
    val colorDark = (mainViewModel.currentNavigation.value as Screen.Navigation).nColor2

    var filter1st by remember { mutableStateOf<String?>(filter1) }
    var filter2st by remember { mutableStateOf<String?>(filter2) }
    var filter3st by remember { mutableStateOf<String?>(filter3) }

    searchViewModel.searchResultModifierList.add(filter1st)
    searchViewModel.searchResultModifierList.add(filter2st)
    searchViewModel.searchResultModifierList.add(filter3st)

    LaunchedEffect(key1 = Unit) {
        searchViewModel.searchColleges(filter2st, filter3st)
    }

    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = colorDark,
        unfocusedBorderColor = TextColor4,
        cursorColor = colorDark,
        focusedTextColor = TextColor1,
        unfocusedTextColor = TextColor2,
        focusedLabelColor = colorDark,
        unfocusedLabelColor = TextColor2
    )
    Scaffold(
        topBar = { MyTopAppBar(
            title = mainViewModel.currentNavigation.value.title,
            navAction = { /*TODO*/ },
            prScore = 0.8f,
            actionFun = { /*TODO*/ },
            colorDark = colorDark
        )},
        bottomBar = {
            MyBottomBar(mainViewModel, navController)
        }
    ) { innerpadding->
        Box(modifier = Modifier.padding(innerpadding)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {
                    //Enter Name: String
                    val searchItemSt = remember { mutableStateOf("") }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            colors = textFieldColors,
                            label = { Text(text = "Search", fontFamily = futurafamily) },
                            value = searchItemSt.value, onValueChange = { searchItemSt.value = it },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        searchViewModel.searchColleges(
                                            filter2st,
                                            filter3st
                                        )
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        tint = TextColor2,
                                        contentDescription = null
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            singleLine = true
                        )

                        //Filter Button
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .pointerInput(Unit) {
                                    detectTapGestures { _ ->
                                    }
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(painter = painterResource(id = R.drawable.filter), contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(20.dp), tint = TextColor2)
                            Text(text = "Filter", fontSize = 12.sp, fontWeight = FontWeight.Light, fontFamily = futurafamily, color = TextColor2)
                        }
                    }
                }

                item {
                    Column(modifier = Modifier) {
                        Box(modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(TextColor4)
                        )
                        FilterRow(colorDark, searchViewModel)
                    }
                }

                item {
                    if (loading) {
                        Column (modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(400.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(color = colorDark)

                            Spacer(modifier = Modifier.size(32.dp))

                            Text(text = "Finding You Best Schools", fontFamily = futurafamily, color = TextColor1, fontSize = 20.sp)

                            Spacer(modifier = Modifier.size(8.dp))

                            Text(text = "We are curating a list of universitites that matches your requirements and qualification.",
                                fontFamily = futurafamily, fontSize = 14.sp, color = TextColor3, fontWeight = FontWeight.Light,
                                textAlign = TextAlign.Center)
                        }
                    }
                }

                item {
                    error?.let {
                        Column (modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(400.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(painterResource(id = R.drawable.somethingwentwrong), modifier = Modifier.size(80.dp),
                                contentDescription = null, tint = colorLight)

                            Spacer(modifier = Modifier.size(4.dp))

                            Text(text = "Something went wrong: $it", textAlign = TextAlign.Center, color = TextColor2,
                                fontFamily = futurafamily, fontSize = 20.sp, fontWeight = FontWeight.Light)
                        }
                    }
                }

                collegeDetails?.let {
                    it.collegeDetails?.let { collegeDetailsList ->
                        items(collegeDetailsList) { collegeDetails ->
                            Column(modifier = Modifier.animateItemPlacement()) {
                                CollegeSearchCard(
                                    collegeDetail = collegeDetails,
                                    color = colorDark
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
