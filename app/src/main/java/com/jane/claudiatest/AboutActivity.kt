package com.jane.claudiatest

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jane.claudiatest.ui.theme.ClaudiatestTheme

class AboutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            About()

        }

    }
}
@Preview(showBackground = true)
@Composable
fun About() {
    //Column---puts different texts on two separate lines (columns)to avoid overlapping.
      // It must start with a capital 'C'
    Column (
        modifier = Modifier//The Css of android
            .background(androidx.compose.ui.graphics.Color.Gray)
            .fillMaxSize()
            .padding(horizontal = 100.dp, vertical = 50.dp)
        //There is no margin in android
    ){
        Text(
            //styling/modifier for the text
            text = "This is the about screen.",
            fontSize =25.sp,
            color = androidx.compose.ui.graphics.Color.DarkGray,
            fontFamily = FontFamily.Cursive,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
        )
        //spaces between the first text and the second text
        Spacer(
            modifier = Modifier
            .height(10.dp)
        )
        Text(text = "I want to buy books",
            color = androidx.compose.ui.graphics.Color.DarkGray,
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic
            )

    }


}



