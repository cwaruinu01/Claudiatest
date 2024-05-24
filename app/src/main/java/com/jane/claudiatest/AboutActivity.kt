//images are always stored in the res directory inside the drawable folder
//images should be small in size in terms of bytes
package com.jane.claudiatest

//import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
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
    //mutable means changeable.
    //Remember is

    var name by remember {
          mutableStateOf(TextFieldValue(""))
       }

    var email by remember {
        mutableStateOf(TextFieldValue(""))
    }

    //Column---puts different texts on two separate lines (columns)to avoid overlapping.
      // It must start with a capital 'C'
    //The contents inside the column are inside the call88ibraces
    //

    Column (
        modifier = Modifier//The Css of android
            .background(Color(0xFF01454B))
            .fillMaxSize()
            .padding(10.dp),
        //There is no margin in android
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
//alignments and also arrangements are not part of modifiers so they are separate using commas
    )
    {
        //
        TextField(
            value = name,
            onValueChange ={name=it}
        )
        Spacer(
            modifier = Modifier
            .height(10.dp)
        )
        // OutlineTextField is more styled compared to TextField
        //labels act as placeholders
        OutlinedTextField(
            value = email,
            onValueChange ={name=it},
            label = { Text(text = "EMAIL")}
        )
        Text(
            //styling/modifier for the text
            text = "This is the about screen.",
            fontSize =25.sp,
            color = Color(0xff330026),
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
            color = Color(0xff1a0033),
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .background(Color(0x684660B8))
            )
        Spacer(modifier = Modifier
            .height(10.dp))
        Text(text = "My name is Jane Doe",
            color = Color(0xff262626),
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .background(Color(0xAD026349))
            )
        Spacer(
            modifier=Modifier
                .height(10.dp)
        )
        //modifiers can't be used for the general button
        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color
                (0xFF01272B)
            )
        )

        {
            Text(
                text = "Our services",
                color = Color
                    (0xFF027CD8)
            )
        }
        //adding images from drawable.
        //    Image: This is a composable function from Jetpack Compose for displaying images.
        //    painterResource: This part specifies that the image should be loaded from a resource provided by the Android application. The id parameter references the resource using an identifier like R.drawable.recordingbooth. This identifier likely corresponds to an image file stored within the application's drawable resources.
        //    contentDescription: This attribute provides a textual description of the image content, which is crucial for accessibility purposes, especially for screen reader users.
        //
        //While the code itself cannot directly display an image here, it suggests that the corresponding image resource (identified by R.drawable.recordingbooth) depicts a recording booth. Recording booths are typically small, soundproofed spaces used for isolating a singer or musician during a recording session. They often contain microphones and other equipment for capturing audio.


        Image(
            painter = painterResource
            (id = R.drawable.recordingbooth),
            contentDescription = "recording booth"
        )

    }


}



