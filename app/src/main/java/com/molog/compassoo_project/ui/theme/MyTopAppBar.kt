package com.molog.compassoo_project.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.molog.compassoo_project.MainAppViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    navAction: ()->Unit,
    prScore:Float,
    actionFun: ()->Unit,
    colorDark: Color,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title, color = TextColor2, fontFamily = futurafamily) },
        navigationIcon = {
            IconButton(onClick = navAction) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null, tint = TextColor2)
            }
        },
        modifier = Modifier.shadow(elevation = 1.dp),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
        actions = {
            IconButton(onClick = actionFun) {
                Box(modifier = Modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
                    ProgressRing(progress = prScore, color = colorDark)
                    Text(text = "${(prScore*100).toInt()}",
                        fontFamily = futurafamily,
                        color = TextColor1,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar(){
    Scaffold(
        topBar = {
            MyTopAppBar(title = "Education", {}, 0.8f, {}, Color.Magenta)
        }
    ) {innerPadding->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize())
    }
}

@Composable
fun ProgressRing(progress: Float, color: Color) {
    val strokeWidth = 4.dp // Thickness of the ring in dp

    val colorStopsForProgressRing = arrayOf(
        0f to Color.Transparent,
        1f to color
    )

    Canvas(modifier = Modifier.size(30.dp)
    ) { // Set the size as needed
        val canvasSize = size.minDimension // Get the smaller dimension to keep it a circle
        val strokeWidthPx = strokeWidth.toPx() // Convert strokeWidth to pixels
        val topLeftOffset = Offset(strokeWidthPx / 2, strokeWidthPx / 2)
        val arcSize = Size(canvasSize - strokeWidthPx, canvasSize - strokeWidthPx)

        // Draw progress arc
        drawArc(
            brush = Brush.sweepGradient(
                colorStops = colorStopsForProgressRing,
                center = Offset(size.width / 2, size.height / 2)
            ),
            startAngle = 0f, // Starting angle (top of the circle)
            sweepAngle = -360f*progress, // Sweep angle proportional to progress
            useCenter = false,
            topLeft = topLeftOffset,
            size = arcSize,
            style = Stroke(width = strokeWidthPx)
        )
    }
}