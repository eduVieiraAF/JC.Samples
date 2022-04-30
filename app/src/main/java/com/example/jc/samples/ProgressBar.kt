package com.example.jc.samples

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jc.ui.theme.JCTheme

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = MaterialTheme.colors.secondaryVariant,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 2000,
    aniDelay: Int = 0
) {
    var animaitonPlayed by remember { mutableStateOf(false) }
    val curPercent = animateFloatAsState(
        targetValue = if (animaitonPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = aniDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animaitonPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(radius * 2f)
        ) {
            Canvas(modifier = Modifier.size(radius * 2f)) {
                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = 360 * curPercent.value,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }
            Text(
                text = ("${(curPercent.value * number).toInt()}%"),
                color = MaterialTheme.colors.secondaryVariant,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview("Dark", uiMode = UI_MODE_NIGHT_YES)
@Preview("Light", showBackground = true)
@Composable
fun PBPreview() {
    JCTheme {
        CircularProgressBar(percentage = 0.78f, number = 100)
    }
}