package com.jane.claudiatest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jane.claudiatest.ui.theme.ClaudiatestTheme

class HomeworkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            page1()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun page1 (){
    Column {
        Column(modifier = Modifier
            .background(Color(0xFFCE003A))
            .fillMaxWidth()
        ) {
            Column(modifier = Modifier
                ) {
                Row {
                    Row(horizontalArrangement = Arrangement.Start) {
                        Text(text = "New York City")
                    }
                    Row(horizontalArrangement = Arrangement.End) {
                        Text(text = "Notification")
                    }
                }
                Row {
                    Row {
                        // SearchBar(
                        //  query = ,
                        // onQueryChange = ,
                        //onSearch = ,
                        // active = ,
                        // onActiveChange =
                        // ) { }
                    }
                    Row (horizontalArrangement = Arrangement.End){
                        Text(text = "Icon")
                    }

                }
            }
        }
        Column (modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
        ){
            LazyColumn (modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()){
                item {
                    Row(modifier = Modifier
                        .fillMaxWidth()) {
                        Column {
                            Text(text = "#SpecialForYou")
                        }
                        Column(modifier = Modifier,
                            horizontalAlignment = End
                            ) {
                            TextButton(onClick = { /*TODO*/ }) {
                                Text(text = "See All")
                            }
                        }
                    }
                }
                item {
                    LazyRow (modifier = Modifier
                        .fillMaxWidth()){
                        item {
                            Row {
                                Row {
                                    Image(painter = painterResource(id = R.drawable.watch), contentDescription = "watch")
                                    Spacer(modifier = Modifier
                                        .width(10.dp))
                                }
                                Row {
                                    Image(painter = painterResource(id = R.drawable.watch), contentDescription = "watch")
                                    Spacer(modifier = Modifier
                                        .width(10.dp))
                                }
                                Row {
                                    Image(painter = painterResource(id = R.drawable.watch), contentDescription = "watch")
                                    Spacer(modifier = Modifier
                                        .width(10.dp))
                                }

                            }
                        }
                    }
                }
                item {
                    Row {
                        Text(text = "Category")
                        TextButton(onClick = { /*TODO*/ }) {
                            Text(text = "See All")
                        }
                    }
                }
                item {
                    Column {
                        Row {
                            IconButton(onClick = { /*TODO*/ }) {

                            }
                            IconButton(onClick = { /*TODO*/ }) {

                            }
                            IconButton(onClick = { /*TODO*/ }) {

                            }
                            IconButton(onClick = { /*TODO*/ }) {

                            }
                        }
                    }
                }
                item {
                    Row {
                    }
                }
            }
        }

    }
}