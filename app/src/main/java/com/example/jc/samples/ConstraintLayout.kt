package com.example.jc.samples

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.jc.ui.theme.JCTheme

@Composable
fun Boxes() {
    val constraints = ConstraintSet {
        val tealBox = createRefFor("teal_box")
        val purpleBox = createRefFor("purple_box")
        val guideline = createGuidelineFromTop(0.6f)

        constrain(tealBox) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(purpleBox) {
            top.linkTo(parent.top)
            start.linkTo(tealBox.end)
            end.linkTo(parent.end)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        createHorizontalChain(tealBox, purpleBox, chainStyle = ChainStyle.Packed)
    }
    androidx.constraintlayout.compose.ConstraintLayout(
        constraints, modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier
            .background(MaterialTheme.colors.secondaryVariant)
            .layoutId("teal_box")
        )

        Box(modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .layoutId("purple_box")
        )
    }
}

@Preview("DarkTheme → Boxes", uiMode = UI_MODE_NIGHT_YES)
@Preview("LightTheme → Boxes", showBackground = true)
@Composable
fun BoxesPreview(){
    JCTheme {
        Boxes()
    }
}
