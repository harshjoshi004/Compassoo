package com.molog.compassoo_project.ui.theme

import android.graphics.drawable.PictureDrawable
import android.widget.ImageView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.caverock.androidsvg.SVG

@Composable
// Fits in Center, does not fill maximum background area
fun SvgImage(assetName: String, modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                setLayerType(ImageView.LAYER_TYPE_SOFTWARE, null) // For complex SVGs
                scaleType = ImageView.ScaleType.FIT_CENTER
                try {
                    // Load the SVG from assets
                    val svg = SVG.getFromAsset(context.assets, assetName)
                    // Create a PictureDrawable from the SVG
                    val drawable = PictureDrawable(svg.renderToPicture())
                    // Set the PictureDrawable as the content for the ImageView
                    setImageDrawable(drawable)
                } catch (e: Exception) {
                    // Handle exceptions (e.g., file not found)
                    println("SVG Renderer: ${e.message}")
                    e.printStackTrace() // You might want to log the error or display a placeholder
                }
            }
        },
        modifier = modifier
    )
}

@Composable
// Crops into the center, fills all the background area possible
fun SvgImageBackground(assetName: String, modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                setLayerType(ImageView.LAYER_TYPE_SOFTWARE, null) // For complex SVGs
                scaleType = ImageView.ScaleType.CENTER_CROP
                try {
                    // Load the SVG from assets
                    val svg = SVG.getFromAsset(context.assets, assetName)
                    // Create a PictureDrawable from the SVG
                    val drawable = PictureDrawable(svg.renderToPicture())
                    // Set the PictureDrawable as the content for the ImageView
                    setImageDrawable(drawable)
                } catch (e: Exception) {
                    // Handle exceptions (e.g., file not found)
                    println("SVG Renderer: ${e.message}")
                    e.printStackTrace() // You might want to log the error or display a placeholder
                }
            }
        },
        modifier = modifier
    )
}