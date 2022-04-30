package com.example.jc.samples

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jc.ui.theme.JCTheme


@Composable
fun SimpleAnimation() {
    var sizeState by remember { mutableStateOf(200.dp) }
    val size by animateDpAsState(
        targetValue = sizeState,
        animationSpec =  tween(
            durationMillis = 500,
            delayMillis = 150,
            easing = FastOutLinearInEasing
        )
        /* more in animationSpec →

       → spring(Spring.DampingRatioHighBouncy)

       → keyframes {
            durationMillis = 5000
            sizeState at 0 with LinearEasing
            sizeState * 1.5f at 1000 with FastOutLinearInEasing
            sizeState * 2f at 5000
        }

         */
    )
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = MaterialTheme.colors.error,
        targetValue = MaterialTheme.colors.secondaryVariant,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    Box(
        modifier = Modifier
            .size(size)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                sizeState += 100.dp
            },
            colors = ButtonDefaults
                .buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
        ) {
            Text("Click to expand")
        }
    }
}

@Preview("DarkTheme → Animation", uiMode = UI_MODE_NIGHT_YES)
@Preview("LightTheme → Animation", showBackground = true)
@Composable
fun AnimationPreview() {
    JCTheme {
        SimpleAnimation()
    }
}
