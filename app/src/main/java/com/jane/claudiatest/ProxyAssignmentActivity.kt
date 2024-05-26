package com.jane.claudiatest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jane.claudiatest.ui.theme.ClaudiatestTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class ProxyAssignmentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            home()
        }
    }
}




@Preview(showBackground = true)
@Composable
fun home(){
    Column(modifier =
    Modifier
        .fillMaxSize()
        .background(Color(0xFFFFFFFF))
        .padding(vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = "Location")
             //SearchBar(query = , onQueryChange = , onSearch = , active = , onActiveChange = ) { }
        }

            LazyColumn {
                item {
                    Row {
                        Text(text = "#SpecialForYou")
                        TextButton(onClick = { /*TODO*/ })
                        {
                            Text(text = "See All")
                        }
                    }
                }
                item {
                    LazyRow {
                        item {
                            Column {
                                Row {
                                    Column(modifier = Modifier
                                        .padding(10.dp)
                                    )
                                    {
                                        Image(painter = painterResource(id = R.drawable.watch) , contentDescription ="watch" )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column (modifier = Modifier
                                        .padding(10.dp)
                                    )
                                    {
                                        Image(painter = painterResource(id = R.drawable.watch) , contentDescription ="watch" )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                   Column (modifier = Modifier
                                       .padding(10.dp)
                                       )
                                   {
                                       Image(painter = painterResource(id = R.drawable.watch), contentDescription ="watch")
                                   }
                                }
                            }
                        }
                    }
                }
                item {
                    Row {
                        Column (horizontalAlignment = Alignment.Start){
                            Text(text = "Category")
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            TextButton(onClick = { /*TODO*/ })
                            {
                                Text(text = "See All") 
                            }
                        }
                    }
                }
                item {
                    Row (){
                        IconButton(onClick = { /*TODO*/ }) {Icon(
                            bitmap = ImageBitmap.imageResource(id = R.drawable.watch) ,
                            contentDescription ="clothes"
                        )}
                        IconButton(onClick = { /* Your action here */ }) { Icon(
                            bitmap = ImageBitmap.imageResource(id = R.drawable.watch),
                            contentDescription ="electronics"
                        )}
                        IconButton(onClick = { /* Your action here */ }) {Icon(
                            bitmap = ImageBitmap.imageResource(id = R.drawable.watch),
                            contentDescription = "shoes"
                        )}
                        IconButton(onClick = { /* Your action here */ }) {Icon(
                            bitmap = ImageBitmap.imageResource(id = R.drawable.watch),
                            contentDescription ="watch"
                        )}

                    }
                }
                item {
                    Column {
                        Row {
                            Column (horizontalAlignment =Alignment.Start){
                                Text(text = "Flash Sale")
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(text = "1 day 10 hr remaining")
                            }
                        }
                        LazyRow {
                            item {
                                Row (horizontalArrangement = Arrangement.Center){
                                    Button(
                                        onClick = { /*TODO*/ },
                                        colors = ButtonDefaults.buttonColors(Color(0xFF0F92CE)),
                                        shape = RoundedCornerShape(50)
                                    ) {

                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Button(
                                        onClick = { /*TODO*/ },
                                        colors = ButtonDefaults.buttonColors(Color(0xFF146B86)),
                                        shape = RoundedCornerShape(50)
                                    ) {

                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Button(
                                        onClick = { /*TODO*/ },
                                        colors = ButtonDefaults.buttonColors(Color(0xFF0E5E83)),
                                        shape = RoundedCornerShape(50)
                                    ) {

                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Button(
                                        onClick = { /*TODO*/ },
                                        colors = ButtonDefaults.buttonColors(Color(0xFF094663)),
                                        shape = RoundedCornerShape(50)
                                    ) {

                                    }
                                }
                            }
                        }
                        LazyRow {
                            item {
                            }
                        }

                    }
                }

            }
            Row {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFF0F92CE)),
                   shape = RoundedCornerShape(50)
                ) {

                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFF146B86)),
                    shape = RoundedCornerShape(50)
                ) {

                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFF0E5E83)),
                    shape = RoundedCornerShape(50)
                ) {

                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFF094663)),
                    shape = RoundedCornerShape(50)
                ) {

                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFF014C5F)),
                    shape = RoundedCornerShape(50)
                ) {

                }

            }



    }
}