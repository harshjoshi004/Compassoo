package com.molog.compassoo_project.educationModule.searchColleges.presentationLayer

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.molog.compassoo_project.MainAppViewModel
import com.molog.compassoo_project.Screen
import com.molog.compassoo_project.ui.theme.MyTopAppBar
import com.molog.compassoo_project.ui.theme.TextColor1
import com.molog.compassoo_project.ui.theme.TextColor2
import com.molog.compassoo_project.ui.theme.TextColor3
import com.molog.compassoo_project.ui.theme.TextColor4
import com.molog.compassoo_project.ui.theme.futurafamily

//inject sign in viewModel from education graph

@Composable
@Preview(showBackground = true)
fun FilterScreenPreview(){
    FilterScreen(mainAppViewModel = viewModel<MainAppViewModel>(), navController = rememberNavController())
}
//for dummy commit

@Composable
fun FilterScreen(mainAppViewModel: MainAppViewModel, navController: NavController){
    val colorDark = (mainAppViewModel.currentNavigation.value as Screen.Navigation.EducationModuleNavigation).nColor2
    val filterList = listOf("Category","Field of Study","Course","Exams Accepted","Fees","Location")
    val filterState = remember { mutableStateOf(filterList[0]) }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = mainAppViewModel.currentNavigation.value.title,
                navAction = { /*TODO*/ },
                prScore = 0.7f,
                actionFun = { /*TODO*/ },
                colorDark = colorDark
            )
        },
        bottomBar = {

        }
    ) { scaffoldPadding ->

        Column(modifier = Modifier
            .padding(scaffoldPadding)
            .fillMaxSize()
        ) {
            //Secondary App Bar
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            ) {
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterStart)) {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        tint = TextColor3,
                        modifier = Modifier.size(18.dp),
                        contentDescription = null)
                }
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    text = "Filters",
                    color = TextColor3,
                    fontFamily = futurafamily,
                    fontSize = 14.sp)
            }
            //Horizontal Content Divider
            Box(modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                .clip(RoundedCornerShape(50))
                .background(TextColor4)
                .height(1.dp)
                .fillMaxWidth())

            //Parent Row
            Row(modifier = Modifier
                .fillMaxSize()
                .weight(1f)
            ){
                //SideBarColumn
                Column(modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    .fillMaxHeight()
                ) {
                    filterList.forEach { filter->
                        TextButton(onClick = { filterState.value = filter }) {
                            Text(text = filter,
                                color = if (filterState.value == filter) colorDark
                                    else TextColor1,
                                fontFamily = futurafamily,
                                fontSize = 14.sp,
                            )
                        }
                    }
                }

                //Vertical Content Divider
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp, start = 8.dp)
                        .clip(RoundedCornerShape(50))
                        .background(TextColor4)
                        .fillMaxHeight()
                        .width(1.dp)
                )

                //SideBarDependent Composable
                Column(modifier = Modifier.fillMaxSize()) {
                    Crossfade(targetState = filterState.value, label = "SideBarDependent Composable") { selectedFilter->
                        when(selectedFilter){
                            "Category" -> CategoryFilterComposable(colorDark)
                            "Field of Study" -> FieldOfStudyFilterComposable(colorDark)
                            "Course" -> CourseFilterComposable(colorDark)
                            "Exams Accepted" -> ExamsAcceptedFilterComposable(color = colorDark)
                            "Fees" -> FeeFilterComposable(color = colorDark)
                            "Location" -> LocationFilterComposable(color = colorDark)
                        }
                    }
                }
            }
            //Button Row
            Row(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()) {

                //Reset Changes Button

                Button(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10))
                        .border(1.dp, Color(0xFFDDF0D5), RoundedCornerShape(10)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = TextColor2
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "Reset Changes",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                        fontFamily = futurafamily
                    )
                }


                Spacer(modifier = Modifier.size(16.dp))

                //Apply Changes Button

                Button(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10))
                        .border(0.5.dp, Color(0xFFDDF0D5), RoundedCornerShape(10))
                        .background(
                            Brush.horizontalGradient(
                                colorStops = arrayOf(
                                    0f to Color(0xFF3C4B17),
                                    1f to Color(0xFF252B0B)
                                )
                            )
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "Apply",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                        fontFamily = futurafamily
                    )
                }

            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExamsAcceptedFilterComposable(color:Color){
    val examsAcceptedList = mutableListOf(
        "IELTS",
        "TOEFL",
        "GMAT",
        "GRE",
        "PTE",
        "SAT",
        "MCAT",
        "TCF",
        "TEF"
    )
    val selectedExamState = remember {
        mutableStateOf(examsAcceptedList[0]) //export
    }

    LazyColumn {
        item {
            Spacer(modifier = Modifier.size(32.dp))
        }
        examsAcceptedList.forEach { exam->
            item {
                Column(modifier = Modifier.animateItemPlacement()) {
                    Text(
                        text = exam,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .pointerInput(Unit) {
                                detectTapGestures { _ ->
                                    selectedExamState.value = exam
                                }
                            },
                        fontFamily = futurafamily,
                        fontSize = 14.sp,
                        color = if (selectedExamState.value == exam) color else TextColor1,
                        fontWeight = FontWeight.Light
                    )

                    if (exam != examsAcceptedList.last())
                        Box(
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp)
                                .clip(RoundedCornerShape(50))
                                .background(TextColor4)
                                .height(1.dp)
                                .fillMaxWidth()
                        )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeeFilterComposable(color: Color){
    val feeRangeList = listOf(
        5000..10000,
        10000..15000,
        15000..20000,
        20000..25000,
        25000..30000,
        30001..Int.MAX_VALUE // Representing "Above $30,000"
    )
    val selectedFeeState = remember {
        mutableStateOf(feeRangeList[0])
    }

    LazyColumn {
        item { 
            Spacer(modifier = Modifier.size(32.dp))
        }
        feeRangeList.forEachIndexed { ind, feeRange ->
            item {
                Column(modifier = Modifier.animateItemPlacement()) {
                    Text(
                        text = if(feeRange.last != Int.MAX_VALUE) "$ ${feeRange.first} - $ ${feeRange.last}"
                            else "Above $ ${feeRange.first}",
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .pointerInput(Unit) {
                                detectTapGestures { _ ->
                                    selectedFeeState.value = feeRange
                                }
                            },
                        fontFamily = futurafamily,
                        fontSize = 14.sp,
                        color = if (selectedFeeState.value == feeRange) color else TextColor1,
                        fontWeight = FontWeight.Light
                    )

                    if (ind != feeRangeList.size - 1)
                        Box(
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp)
                                .clip(RoundedCornerShape(50))
                                .background(TextColor4)
                                .height(1.dp)
                                .fillMaxWidth()
                        )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CourseFilterComposable(color: Color) {
    val searchStringState = remember {
        mutableStateOf("")
    }

    val courseList = mutableListOf(
        "MSc in Management",
        "(BCom) Bachelor of Commerce",
        "(BBA) Bachelor of Business Administration",
        "(BCom) Bachelor of Commerce",
        "(MBA) Master of Business Administration",
        "(MSCM) Master of Supply Chain Management",
        "(MMAI) Master of Management in Artificial Intelligence",
        "(BAcc) Bachelor of Accounting",
        "(BBA) Bachelor of Business Administration",
        "(MBA) Master of Business Administration",
        "MSc in Management",
        "(BCom) Bachelor of Commerce",
        "(MSCM) Master of Supply Chain Management",
        "(MMAI) Master of Management in Artificial Intelligence"
    )

    val selectedCourseState = remember {
        mutableStateOf(courseList[0])
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        //The Search Bar
        item {
            Box(modifier = Modifier
                .padding(16.dp)
                .shadow(4.dp, RoundedCornerShape(50))
                .clip(RoundedCornerShape(50))
                .background(Color.White)
                .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd

            ){
                Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                    BasicTextField(value = searchStringState.value, onValueChange = {searchStringState.value = it}, singleLine = true,
                        modifier = Modifier.weight(1f), textStyle = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = futurafamily,
                            color = TextColor1,
                            textAlign = TextAlign.End)
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Search, modifier = Modifier.size(20.dp),
                            tint = TextColor1, contentDescription = null
                        )
                    }
                }
            }
        }

        val updatedCourseList = courseList.filter { it.contains(searchStringState.value, ignoreCase = true) }

        //The rest of the list
        updatedCourseList.forEachIndexed { ind, course->
            item {
                Column(modifier = Modifier.animateItemPlacement()) {
                    Text(text = course, fontFamily = futurafamily, fontSize = 14.sp,
                        color = if(course == selectedCourseState.value) color else TextColor1, fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(horizontal = 16.dp))

                    if(ind != updatedCourseList.size - 1)
                        Box(modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp)
                            .clip(RoundedCornerShape(50))
                            .background(TextColor4)
                            .height(1.dp)
                            .fillMaxWidth())
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LocationFilterComposable(color: Color) {
    val searchLocationState = remember {
        mutableStateOf("")
    }
    val locationList = mutableListOf(
        "Toronto",
        "Montreal",
        "Vancouver",
        "Quebec City",
        "Whistler",
        "Banff",
        "Ottawa",
        "Montreal",
        "Vancouver",
        "Quebec City",
        "Whistler",
        "Banff",
        "Ottawa",
        "Quebec City",
        "Banff",
        "Calgary",
        "Edmonton",
        "Winnipeg",
        "Halifax",
        "London",
        "Waterloo",
        "Hamilton",
        "Victoria",
        "Kingston",
        "Saskatoon",
        "St. John's"
    )
    val selectedLocationState = remember {mutableStateOf(locationList[0])}

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        //The Search Bar
        item {
            Box(modifier = Modifier
                .padding(16.dp)
                .shadow(4.dp, RoundedCornerShape(50))
                .clip(RoundedCornerShape(50))
                .background(Color.White)
                .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd

            ){
                Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                    BasicTextField(value = searchLocationState.value, onValueChange = {searchLocationState.value = it}, singleLine = true,
                        modifier = Modifier.weight(1f), textStyle = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = futurafamily,
                            color = TextColor1,
                            textAlign = TextAlign.End)
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Search, modifier = Modifier.size(20.dp),
                            tint = TextColor1, contentDescription = null
                        )
                    }
                }
            }
        }

        val updatedLocationList = locationList.filter { it.contains(searchLocationState.value, ignoreCase = true) }

        //The rest of the list
        updatedLocationList.forEachIndexed { ind, location->
            item {
                Column(modifier = Modifier.animateItemPlacement()) {
                    Text(text = location, modifier = Modifier.padding(horizontal = 16.dp),
                        fontFamily = futurafamily, fontSize = 14.sp, color = TextColor1, fontWeight = FontWeight.Light)

                    //Divider
                    if(ind != updatedLocationList.size - 1)
                        Box(modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp)
                            .clip(RoundedCornerShape(50))
                            .background(TextColor4)
                            .height(1.dp)
                            .fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun FieldOfStudyFilterComposable(color: Color) {
    val fieldOfStudyList = mutableListOf(
        "Engineering", "Business / Management", "Design",
        "Humanities", "Architecture", "Social Sciences", "Law", "Medicine"
    )
    val fieldState = remember { mutableStateOf(fieldOfStudyList[0]) } //MainUsage
    Column {
        fieldOfStudyList.forEach { field ->
            Crossfade(targetState = fieldState.value, label = "fieldState") { selectedField ->
                ButtonChip(text = field, color = if (field == selectedField) color else TextColor3) {
                    fieldState.value = field
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryFilterComposable(color:Color){
    val qualificationsList = listOf("Degree","Trade","Diploma")
    val degreeList = listOf("Bachelors","Masters","Ph.D")
    val tradeList = listOf("HVAC Technician", "Automotive Technician", "Machinist","Chef")
    val diplomaList = listOf("Business Administration", "Information Technology", "Graphic Design", "Nursing", "Journalism", "Accounting")
    val qualificationState = remember { mutableStateOf(qualificationsList[0]) } //MainUsage
    val categoryState = remember { mutableStateOf(qualificationsList[0]) } //MainUsage
    val degreeState = remember { mutableStateOf(degreeList[0]) }
    val tradeState = remember { mutableStateOf(tradeList[0]) }
    val diplomaState = remember { mutableStateOf(diplomaList[0]) }

    Column(modifier = Modifier.fillMaxSize()) {
        //Qualifications
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), textAlign = TextAlign.Start,
            text = "Qualification Level", color = TextColor3, fontFamily = futurafamily, fontSize = 12.sp)
        FlowRow(modifier = Modifier.fillMaxWidth()) {
            qualificationsList.forEach { qualification ->
                ButtonChip(text = qualification, color =
                    if(qualificationState.value == qualification) color else TextColor3
                ){
                    qualificationState.value = qualification
                }
            }
        }

        //Horizontal Content Divider
        Box(modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .clip(RoundedCornerShape(50))
            .background(TextColor4)
            .height(1.dp)
            .fillMaxWidth())

        //Degree
        Crossfade(targetState = qualificationState.value, label = "") { selectedQualification ->
            when(selectedQualification){
                "Degree" -> Column{
                    Text(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp), textAlign = TextAlign.Start,
                        text = "Choose your Degree", color = TextColor3, fontFamily = futurafamily, fontSize = 12.sp)

                    FlowRow(modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()) {
                        degreeList.forEach { degree ->
                            ButtonChip(text = degree, color =
                            if (degreeState.value == degree) color else TextColor3,
                                onClick = {
                                    degreeState.value = degree
                                    categoryState.value = degree
                                }
                            )
                        }
                    }
                }
                "Trade" -> Column{
                    Text(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp), textAlign = TextAlign.Start,
                        text = "Choose your Trade", color = TextColor3, fontFamily = futurafamily, fontSize = 12.sp)

                    FlowRow(modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()) {
                        tradeList.forEach { trade ->
                            ButtonChip(text = trade, color =
                            if (tradeState.value == trade) color else TextColor3,
                                onClick = {
                                    tradeState.value = trade
                                    categoryState.value = trade
                                }
                            )
                        }
                    }
                }
                "Diploma" -> Column{
                    Text(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp), textAlign = TextAlign.Start,
                        text = "Choose your Diploma", color = TextColor3, fontFamily = futurafamily, fontSize = 12.sp)

                    FlowRow(modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()) {
                        diplomaList.forEach { diploma ->
                            ButtonChip(text = diploma, color =
                            if (diplomaState.value == diploma) color else TextColor3,
                                onClick = {
                                    diplomaState.value = diploma
                                    categoryState.value = diploma
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonChip(text:String, color: Color, onClick:()->Unit ){
    Box(modifier = Modifier
        .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
        .wrapContentSize()
        .border(1.dp, color, RoundedCornerShape(10))
        .clip(RoundedCornerShape(10))
        .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 12.sp, fontFamily = futurafamily, color = color,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp))
    }
}