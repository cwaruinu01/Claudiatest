package com.jane.claudiatest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jane.claudiatest.ui.theme.ClaudiatestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Greeting() {
    val about= LocalContext.current
    val login= LocalContext.current
    val proxy= LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {


         Text(text = "WELCOME")
        //Buttons must never share variables.

        Button(
            onClick = { about.startActivity(Intent(about, AboutActivity::class.java)) },
            colors = ButtonDefaults.buttonColors(Color(0xFF01272B)),
        ) {
            Text(text = "About")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { login.startActivity(Intent(login, loginActivity::class.java)) },
            colors = ButtonDefaults.buttonColors(Color(0xFF01272B)),
        ) {
            Text(text = "Login")
        }
        Button(
            onClick = { login.startActivity(Intent(proxy, ProxyAssignmentActivity::class.java)) },
            colors = ButtonDefaults.buttonColors(Color(0xFF01272B)),
        ) {
            Text(text = "Proxy")
        }
    }
}
