package com.jane.claudiatest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jane.claudiatest.ui.theme.ClaudiatestTheme

class loginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           Login()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Login(){
    var name by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var password by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .fillMaxWidth()
        .fillMaxHeight()
    )
    {
        // Place your background image here
        Image(
            painter = painterResource(id = R.drawable.starryloginpage), // Replace with your image resource
            contentDescription = "Background image",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .fillMaxSize()
                .alpha(0.5f)
        )
        // Rest of your UI content here
        Column(modifier = Modifier
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,)
        {
            // Your other composables
            OutlinedTextField(
                value =name,
                onValueChange ={name=it},
                label = { Text(text = "Name")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                )
            OutlinedTextField(
                value = password,
                onValueChange ={name=it},
                label = { Text(text = "Email")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Button(onClick = { /*TODO*/ },
                colors = ButtonDefaults.outlinedButtonColors(Color(0xff01272B))) {
                Text(text = "log in")
            }
            Text(text = "Not a member,")
            Text(text = "Sign up",
                modifier = Modifier
                    .clickable {  })

        }
    }

}