package com.molog.compassoo_project.educationModule.searchColleges.presentationLayer

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.molog.compassoo_project.ui.theme.SvgImage
import com.molog.compassoo_project.ui.theme.TextColor1
import com.molog.compassoo_project.ui.theme.TextColor2
import com.molog.compassoo_project.ui.theme.TextColor4
import com.molog.compassoo_project.ui.theme.futurafamily
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.MutableState
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import com.molog.compassoo_project.educationModule.searchColleges.domainLayer.CollegeDetail
import com.molog.compassoo_project.educationModule.searchColleges.domainLayer.Course
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@Composable
fun CollegeSearchCard(color: Color, collegeDetail: CollegeDetail){
    var isExpanded = remember { mutableStateOf(false) }

    val dropFun:()->Unit = {
        isExpanded.value = !isExpanded.value
    }
    val curCourse = remember {
        mutableStateOf(collegeDetail.courses[0])
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(1.dp, TextColor4)
            .padding(16.dp)
            .animateContentSize()
    ) {
        TopRow(collegeDetail,color)
        MidRow(color, dropFun, isExpanded, curCourse)

        if (isExpanded.value) {
            ExpandableContent(color, collegeDetail.courses, curCourse)
        } else {
            curCourse.value?.let {
                LowerRow(color, it)
            }
        }
    }
}

@Composable
fun TopRow(collegeDetail: CollegeDetail, color: Color){
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Image
            SvgImage(assetName = "fern-hill-school.svg", modifier = Modifier
                .padding(4.dp)
                .size(40.dp))

            Column(modifier = Modifier.padding(end = 48.dp)) {
                Text(
                    text = collegeDetail.universityName,
                    fontFamily = futurafamily,
                    color = TextColor1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.size(8.dp))
//Detail not provided: UNIVERSITY NAME AND COLLEGE NAMES SHOULD BE DIFFERENT
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = com.molog.compassoo_project.R.drawable.baseline_school_24),
                        contentDescription = null,
                        tint = TextColor1,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = "University Of Toronto",
                        fontFamily = futurafamily,
                        color = TextColor1,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                }
//Detail not provided: LOCATION IS NOT GIVEN IN RESPONSE
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = com.molog.compassoo_project.R.drawable.baseline_location_on_24),
                        contentDescription = null,
                        tint = TextColor1,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = "Ontorio, London",
                        fontFamily = futurafamily,
                        color = TextColor1,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = com.molog.compassoo_project.R.drawable.baseline_star_24),
                        contentDescription = null,
                        tint = TextColor1,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = "#1",
                        fontFamily = futurafamily,
                        fontSize = 12.sp,
                        color = TextColor2,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "Learn More..",
                        fontFamily = futurafamily,
                        fontSize = 12.sp,
                        color = color,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        //Save
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.BookmarkBorder,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}


@Composable
fun MidRow(color: Color, dropFun: () -> Unit, isExpanded: MutableState<Boolean>, course: MutableState<Course?>) {
    val rotationAngle = animateFloatAsState(targetValue = if (isExpanded.value) 180f else 0f, label = "")
    course.value?.let {
        Column {
            Box(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                    .clip(RoundedCornerShape(50))
                    .background(TextColor4)
                    .height(1.dp)
                    .animateContentSize()
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.weight(1f)) {
                    Icon(
                        painter = painterResource(id = com.molog.compassoo_project.R.drawable.baseline_book_24),
                        modifier = Modifier
                            .padding(end = 8.dp, top = 4.dp)
                            .size(18.dp),
                        tint = TextColor1,
                        contentDescription = null
                    )
                    Text(
                        text = it.courseTitle,
                        modifier = Modifier.widthIn(max = 250.dp), maxLines = 1,
                        fontFamily = futurafamily,
                        color = TextColor1,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Row {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            modifier = Modifier.size(24.dp),
                            tint = TextColor2,
                            contentDescription = null
                        )
                    }

                    IconButton(onClick = dropFun) {
                        Icon(
                            imageVector =
                            Icons.Default.ArrowDropDown,
                            modifier = Modifier
                                .size(24.dp)
                                .rotate(rotationAngle.value),
                            tint = TextColor2,
                            contentDescription = null
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                    .clip(RoundedCornerShape(50))
                    .background(TextColor4)
                    .height(1.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun LowerRow(color: Color, course: Course) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = "Deadline",
                fontFamily = futurafamily,
                color = TextColor2,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = course.importantDate,
                modifier = Modifier.widthIn(max = 80.dp), maxLines = 1,
                fontFamily = futurafamily,
                color = TextColor1,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Box(modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(50))
            .background(TextColor4)
            .height(50.dp)
            .width(1.dp)
        )
//AVG placements not provided
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = "Avg Placements",
                fontFamily = futurafamily,
                color = TextColor2,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = "$115000",
                fontFamily = futurafamily,
                modifier = Modifier.widthIn(max = 80.dp), maxLines = 1,
                color = color,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Box(modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(50))
            .background(TextColor4)
            .height(50.dp)
            .width(1.dp)
        )

        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = "Total Fee",
                fontFamily = futurafamily,
                color = TextColor2,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = course.fees.inr,
                modifier = Modifier.widthIn(max = 80.dp), maxLines = 1,
                fontFamily = futurafamily,
                color = TextColor2,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun LowerRow2(color: Color, course: Course) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //Column1
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Time Span",
                fontFamily = futurafamily,
                color = TextColor2,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = course.duration,
                modifier = Modifier.widthIn(max = 80.dp), maxLines = 1,
                fontFamily = futurafamily,
                color = TextColor2,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

        //Devider1
        Box(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
                .clip(RoundedCornerShape(50))
                .background(TextColor4)
                .height(50.dp)
                .width(1.dp)
        )

        var examString = ""
        course.examScores.forEachIndexed { index, examScore ->
            examString +=   if (index == course.examScores.size - 1) examScore.examName
                            else (examScore.examName + ", ")
        }

        //Column2
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.width(200.dp)
        ) {
            Text(
                text = "Exams Accepted",
                fontFamily = futurafamily,
                color = TextColor2,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = examString ,
                modifier = Modifier.widthIn(max = 190.dp), maxLines = 1,
                fontFamily = futurafamily,
                color = color,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ExpandableContent(color: Color, courseList: List<Course?>, curCourse: MutableState<Course?>) {
    val pagerState = rememberPagerState()
    val len = courseList.size
    Column {
        Column {
            HorizontalPager(
                count = courseList.size,
                state = pagerState,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp), // Padding for smooth scrolling
                itemSpacing = 8.dp // Space between items
            ) { page ->
                courseList[page]?.let { course ->
                    course?.let {
                        curCourse.value = it
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .wrapContentHeight()
                        ) {
                            LowerRow(color = color, course = it)

                            //Divider
                            Box(
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(TextColor4)
                                    .height(1.dp)
                                    .fillMaxWidth()
                            )

                            LowerRow2(color = color, course = it)

                            Spacer(modifier = Modifier.size(8.dp))
                        //Apply Now Button
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
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
                                        text = "Apply Now",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                                        fontFamily = futurafamily
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.size(8.dp))

            CarouselIndicator(len = pagerState.pageCount, cur = pagerState.currentPage)
        }
    }
}

@Composable
fun CarouselIndicator(len:Int, cur:Int) {
//Carousel Indicator
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        for(i in 0 until len){
            val indicatorColor = animateColorAsState(
                targetValue = if (i == cur) TextColor2 else Color.Transparent
            )
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .padding(4.dp)
                    .border(
                        width = 1.dp,
                        color = TextColor2,
                        shape = CircleShape
                    )
                    .background(
                        color = indicatorColor.value,
                        shape = CircleShape
                    )
            )
        }
    }
}
