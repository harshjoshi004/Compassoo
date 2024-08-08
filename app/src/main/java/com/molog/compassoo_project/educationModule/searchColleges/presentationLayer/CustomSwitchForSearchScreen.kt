package com.molog.compassoo_project.educationModule.searchColleges.presentationLayer

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.molog.compassoo_project.ui.theme.TextColor2
import com.molog.compassoo_project.ui.theme.TextColor4
import com.molog.compassoo_project.ui.theme.futurafamily

@Composable
fun CustomSwitch(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    color: Color
) {
    val thumbSize = 13.dp
    val trackHeight = 12.dp
    val thumbOffset = (thumbSize - trackHeight) / 2
    val thumbRadius = thumbSize / 2
    val trackColor = if (isChecked) color else Color.Gray
    val thumbColor = Color.White
    val switchWidth = 30.dp
    val switchHeight = thumbSize

    // Animate the thumb position
    val animatedOffset by animateDpAsState(
        targetValue = if (isChecked) switchWidth - thumbSize else 0.dp,
        label = "Thumb Animation"
    )

    Box(
        modifier = modifier
            .padding(top = 4.dp,  bottom = 4.dp)
            .size(switchWidth, switchHeight)
            .background(trackColor, shape = RoundedCornerShape(switchHeight / 2))
            .pointerInput(Unit) {
                detectTapGestures {
                    onCheckedChange(!isChecked)
                }
            }
    ) {
        Box(
            modifier = Modifier
                .size(thumbSize)
                .offset(x = animatedOffset)
                .background(thumbColor, shape = RoundedCornerShape(thumbRadius))
                .border(0.5.dp, TextColor4, RoundedCornerShape(thumbRadius))
                .graphicsLayer {
                    translationX = animatedOffset.toPx()
                }
        )
    }
}

@Preview
@Composable
fun PreviewCustomSwitch() {
    var isChecked by remember { mutableStateOf(true) }

    Surface(color = Color.Red) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            //Sort Icon
            item {
                Column(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .pointerInput(Unit) {
                            detectTapGestures { _ ->
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(painter = painterResource(id = com.molog.compassoo_project.R.drawable.sorting), contentDescription = null,
                        modifier = Modifier
                            //.padding(4.dp)
                            .size(20.dp), tint = TextColor2)
                    Text(text = "Sort", fontSize = 12.sp, fontWeight = FontWeight.Light, fontFamily = futurafamily, color = TextColor2)
                }
            }
            //Enable Eligibility
            item {
                Column(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .pointerInput(Unit) {
                            detectTapGestures { _ ->
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomSwitch(isChecked = true, onCheckedChange = {}, color = Color(0xFF3C4B17))
                    Text(text = "Eligibility", fontSize = 12.sp, fontWeight = FontWeight.Light, fontFamily = futurafamily, color = TextColor2)
                }
            }
        }
    }
}
