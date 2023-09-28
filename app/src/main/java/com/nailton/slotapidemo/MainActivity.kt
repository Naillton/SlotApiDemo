package com.nailton.slotapidemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nailton.slotapidemo.ui.theme.SlotApiDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlotApiDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    mainScreen()
                }
            }
        }
    }
}

@Composable
private fun mainScreen() {
    var linearSelected by rememberSaveable { mutableStateOf(true) }
    var imageSelected by rememberSaveable { mutableStateOf(true) }

    val onLinearClick = { it: Boolean ->
        linearSelected = it
    }

    val onTitleClick = {it: Boolean ->
        imageSelected = it
    }

    screenContent(
        linearSelected = linearSelected,
        imageSelected = imageSelected,
        linearClick = onLinearClick,
        titleClick = onTitleClick,
        titleContent = { titleContent(imageSelected) },
        progressContent = { progressContent(linearSelected)}
        )
}

@Composable
private fun screenContent(
    linearSelected: Boolean,
    imageSelected: Boolean,
    linearClick: (Boolean) -> Unit,
    titleClick: (Boolean) -> Unit,
    titleContent: @Composable () -> Unit,
    progressContent: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        titleContent()
        progressContent()
        checkBoxes(
            linearSelected = linearSelected,
            imageSelected = imageSelected,
            linearClick = linearClick,
            titleClick = titleClick
        )

    }
}

@Composable
private fun checkBoxes(
    linearSelected: Boolean,
    imageSelected: Boolean,
    linearClick: (Boolean) -> Unit,
    titleClick: (Boolean) -> Unit
) {
    Row(
        Modifier
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = linearSelected, onCheckedChange = linearClick )
        Text("Linear Progress")
        Spacer(modifier = Modifier.padding(20.dp))
        Checkbox(checked = imageSelected, onCheckedChange = titleClick )
        Text("Image Selected")
    }
}

@Composable
private fun titleImage(drawing: Int) {
    Image(
        painter = painterResource(id = drawing),
        contentDescription = "title image")
}

@Composable
private fun titleContent(imageSelected: Boolean) {
    if (imageSelected) {
        titleImage(drawing = R.drawable.baseline_cloud_download_24)
    } else {
        Text("Downloading",
            style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(30.dp))
    }
}

@Composable
private fun progressContent(linearSelected: Boolean) {
    if (linearSelected) {
        LinearProgressIndicator(Modifier.height(40.dp))
    } else {
        CircularProgressIndicator(Modifier.size(200.dp), strokeWidth = 18.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SlotApiDemoTheme {
        mainScreen()
    }
}