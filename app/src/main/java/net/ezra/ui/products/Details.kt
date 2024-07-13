package net.ezra.ui.products

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.ezra.navigation.ROUTE_VIEW_PROD


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobpostDetailScreen(navController: NavController, productId: String) {

    var product by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(Unit) {
        fetchProduct(productId) { fetchedProduct ->
            product = fetchedProduct
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    // Display the product name if available
                    Text(
                        text = product?.name ?: "Details",
                        fontSize = 30.sp,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_VIEW_PROD)
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            "backIcon",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xffb22e6f),
                    titleContentColor = Color.White,
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xfffcf3e0)),
            ) {
                product?.let {
                    LazyColumn {
                        item {

                            Column(modifier = Modifier.padding(16.dp)) {
                                Image(
                                    painter = rememberAsyncImagePainter(it.imageUrl),
                                    contentDescription = null,
                                    modifier = Modifier.size(300.dp)
                                )
                                Text(text = it.name, style = MaterialTheme.typography.h5)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "salary: ${it.salary}", style = MaterialTheme.typography.subtitle1)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "Description")
                                Text(text = it.description, style = MaterialTheme.typography.body1)
                                Spacer(modifier = Modifier.height(8.dp))
                                androidx.compose.material3.Text(text = "Company")
                                Text(text = it.company, style = MaterialTheme.typography.body1)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "Email")
                                Text(text = it.email, style = MaterialTheme.typography.body1)
                                Spacer(modifier = Modifier.height(8.dp))
                                androidx.compose.material3.Text(text = "Social media")
                                Text(text = it.socialmedia, style = MaterialTheme.typography.body1)
                            }

                        }
                    }

                }
            }
        }
    )
}


suspend fun fetchProduct(productId: String): Product? {
    val db = FirebaseFirestore.getInstance()
    val productsCollection = db.collection("Jobpost")

    return try {
        val documentSnapshot = productsCollection.document(productId).get().await()
        if (documentSnapshot.exists()) {
            val productData = documentSnapshot.data ?: return null
            Product(
                id = productId,
                name = productData["name"] as String,
                // Add other product properties here
            )
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}




////noinspection UsingMaterialAndMaterial3Libraries
////noinspection UsingMaterialAndMaterial3Libraries
////noinspection UsingMaterialAndMaterial3Libraries
//import android.annotation.SuppressLint
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
////noinspection UsingMaterialAndMaterial3Libraries
//import androidx.compose.material.MaterialTheme
////noinspection UsingMaterialAndMaterial3Libraries
//import androidx.compose.material.Scaffold
////noinspection UsingMaterialAndMaterial3Libraries
//import androidx.compose.material.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.CenterAlignedTopAppBar
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import coil.compose.rememberAsyncImagePainter
//import net.ezra.navigation.ROUTE_VIEW_PROD
//
//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun jobpostDetailScreen(navController: NavController, jobpostId: String) {
//
//    var jobpost by remember { mutableStateOf<jobpost?>(null) }
//
//    LaunchedEffect(Unit) {
//        fetchjobpost(jobpostId) { fetchedjobpost ->
//            jobpost = fetchedjobpost
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = {
//                    // Display the product name if available
//                    Text(
//                        text = jobpost?.name ?:"",
//                        fontSize = 20.sp,
//                        color = Color.White
//                    )
//                },
//                navigationIcon = {
//                    IconButton(onClick = {
//                        navController.navigate(ROUTE_VIEW_PROD)
//                    }) {
//                        Icon(
//                            Icons.AutoMirrored.Filled.ArrowBack,
//                            "backIcon",
//                            tint = Color.White
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xffb22e6f),
//                    titleContentColor = Color.White,
//                )
//            )
//        },
//        content = {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color(0xFFfcf3e0)),
//            ) {
//                jobpost?.let {
//                    LazyColumn {
//                        item {
//
//                            Column(modifier = Modifier.padding(16.dp)) {
//                                Column(modifier = Modifier.fillMaxSize()) {
//
//
//                                    Image(
//                                        painter = rememberAsyncImagePainter(it.imageUrl),
//                                        contentDescription = null,
//                                        modifier = Modifier
//                                            .size(100.dp),
//                                    )
//
//
//                                }
//
//                                Spacer(modifier = Modifier.height(16.dp))
//
//                                Column(modifier = Modifier.fillMaxSize()) {
//                                    Text(text = "post")
//                                    Text(text = it.name, style = MaterialTheme.typography.h5)
//                                    Spacer(modifier = Modifier.height(8.dp))
//                                    Text(text = "salary")
//                                    Text(text = " ${it.salary}", style = MaterialTheme.typography.subtitle1)
//                                    Spacer(modifier = Modifier.height(8.dp))
//                                    Text(text ="description")
//                                    Text(text = it.description, style = MaterialTheme.typography.body1)
//                                    Spacer(modifier = Modifier.height(16.dp))
//
//                                }
//
//
//
//                                Spacer(modifier = Modifier.height(16.dp))
//
//
//                                //added the line below for google sign in button
////                                GoogleSignInButton {
////
////                                }
//                            }
//                            Column (modifier = Modifier
//                                .fillMaxSize()
//                                .background(Color(0xFFb22e6f))){
//                                Column(modifier = Modifier
//                                    .background(Color(0xFFb22e6f))
//                                    .fillMaxSize()) {
//                                    Text(text = "EMAIL")
//                                    Text(text = it.email, style = MaterialTheme.typography.h6, color = Color.White)
//                                }
//                                Column (modifier = Modifier
//                                    .background(Color(0xFFb22e6f))
//                                    .fillMaxSize()){
//                                    Text(text = "LINKEDIN")
//                                    Text(text = it.linkedIn, style = MaterialTheme.typography.h6, color = Color.White)
//                                }
//                                Column (modifier = Modifier
//                                    .background(Color(0xFFb22e6f))
//                                    .fillMaxSize()){
//                                    Text(text = "INSTAGRAM")
//                                    Text(text = it.instagram, style = MaterialTheme.typography.h6, color = Color.White)
//                                }
//                                Column (modifier = Modifier
//                                    .background(Color(0xFFb22e6f))
//                                    .fillMaxSize()){
//                                    Text(text = "X")
//                                    Text(text = it.x, style = MaterialTheme.typography.h6, color = Color.White)
//                                }
//                            }
//                        }
//                    }
//
//                }
//            }
//        }
//    )
//}
//
//
////Added the line below for google sign in button
////@Composable
////fun GoogleSignInButton(onClick: () -> Unit) {
////    Button(onClick = onClick) {
////        Text("Sign in with Google")
////    }
////}