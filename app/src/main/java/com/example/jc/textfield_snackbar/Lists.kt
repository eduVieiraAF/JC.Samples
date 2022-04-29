package com.example.jc.textfield_snackbar

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jc.ui.theme.JCTheme

@Composable
fun SimpleList() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        for (i in 1..12) {
            Text(
                "Simple List\nItem → $i.",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp)
            )
        }
    }
}

@Composable
fun LazyColumnList() {
    LazyColumn {
        items(5000) {
            Text(
                "LazyList\n Item → $it.",
                color = MaterialTheme.colors.secondaryVariant,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp)
            )
        }
    }
}

@Composable
fun ItemsIndexedSample() {
    LazyColumn {
        itemsIndexed(listOf("This", "is", "Jetpack", "Compose")
        ){ index, string ->
            Text(
                string,
                color = MaterialTheme.colors.primary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp)
            )
        }
    }
}

@Preview("Simple List", showBackground = true)
@Composable
fun ShowList() {
    JCTheme {
        SimpleList()
    }
}

@Preview("Dark Theme -> LazyColumn", uiMode = UI_MODE_NIGHT_YES)
@Preview("LazyColumn List", showBackground = true)
@Composable
fun ShowLazyColumnList() {
    JCTheme {
        LazyColumnList()
    }
}

@Preview("Dark Theme → itemsIndexed", uiMode = UI_MODE_NIGHT_YES)
@Preview("LazyColumn List", showBackground = true)
@Composable
fun ShowLazyColumnItemsIndexed() {
    JCTheme {
        ItemsIndexedSample()
    }
}