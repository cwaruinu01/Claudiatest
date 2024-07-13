package net.ezra.ui.products

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import net.ezra.navigation.ROUTE_DASHBOARD

data class Product(
    var id: String = "",
    val name: String = "",
    val description: String ="",
    val salary: Double = 0.0,
    var imageUrl: String = "",
    var socialmedia:String="",
    var email : String="",
    var company :String=""
)



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobPostListScreen(navController: NavController, products: List<Product>) {
    var isLoading by remember { mutableStateOf(true) }
    var productList by remember { mutableStateOf(emptyList<Product>()) }
    var displayedProductCount by remember { mutableStateOf(1) }
    var progress by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        fetchProducts { fetchedProducts ->
            productList = fetchedProducts
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Jobs",fontSize = 30.sp, color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_DASHBOARD)
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
                    .background(Color(0xfffcf3e0))
            ) {
                if (isLoading) {
                    // Progress indicator
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(progress = progress / 100f)
                        Text(text = "Loading jobs... $progress%", fontSize = 20.sp)
                    }
                } else {
                    if (productList.isEmpty()) {
                        // No products found
                        Text(text = "No Jobs found")
                    } else {
                        // Products list
                        LazyVerticalGrid(columns = GridCells.Fixed(1)) {
                            items(productList.take(displayedProductCount)) { product ->
                                JobpostListItem(product) {
                                    navController.navigate("productDetail/${product.id}")
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        // Load More Button
                        if (displayedProductCount < productList.size) {
                            Button(
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffb22e6f)),
                                onClick = { displayedProductCount += 6 },
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Text(text = "More jobs")
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun JobpostListItem(product: Product, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(product.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            // Product Image
            Image(
                painter = rememberImagePainter(product.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Product Details
            Column {
                Text(text = product.name)
                Text(text = "Price: ${product.salary}")
            }
        }
    }
}

private suspend fun fetchProducts(onSuccess: (List<Product>) -> Unit) {
    val firestore = Firebase.firestore
    val snapshot = firestore.collection("Jobpost").get().await()
    val productList = snapshot.documents.mapNotNull { doc ->
        val product = doc.toObject<Product>()
        product?.id = doc.id
        product
    }
    onSuccess(productList)
}

suspend fun fetchProduct(productId: String, onSuccess: (Product?) -> Unit) {
    val firestore = Firebase.firestore
    val docRef = firestore.collection("Jobpost").document(productId)
    val snapshot = docRef.get().await()
    val product = snapshot.toObject<Product>()
    onSuccess(product)
}




//import android.annotation.SuppressLint
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
////import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
////noinspection UsingMaterialAndMaterial3Libraries
//import androidx.compose.material.Button
////noinspection UsingMaterialAndMaterial3Libraries
//import androidx.compose.material.ButtonDefaults
////noinspection UsingMaterialAndMaterial3Libraries
//import androidx.compose.material.Card
////noinspection UsingMaterialAndMaterial3Libraries
//import androidx.compose.material.CircularProgressIndicator
////noinspection UsingMaterialAndMaterial3Libraries
//import androidx.compose.material.Scaffold
////noinspection UsingMaterialAndMaterial3Libraries,UsingMaterialAndMaterial3Libraries
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
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import coil.compose.rememberAsyncImagePainter
////import coil.compose.rememberImagePainter
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.firestore.ktx.toObject
//import com.google.firebase.ktx.Firebase
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.tasks.await
//import net.ezra.navigation.ROUTE_DASHBOARD
////import net.ezra.navigation.ROUTE_HOME
////import net.ezra.navigation.ROUTE_HOME2
//
//data class jobpost(
//    var id: String = "",
//    val name: String = "",
//    val description: String ="",
//    val salary: Double = 0.0,
//    var imageUrl: String = "",
//    var email: String = "",
//    var linkedIn: String = "",
//    var instagram: String = "",
//    var x: String = "",
//    var company: String = "",
//
//    )
//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun jobpostListScreen(navController: NavController, jobpost: List<jobpost>) {
//    var isLoading by remember { mutableStateOf(true) }
//    var jobpostList by remember { mutableStateOf(emptyList<jobpost>()) }
//    var displayedjobpostCount by remember { mutableStateOf(12) }
//    var progress by remember { mutableStateOf(0) }
//
//    LaunchedEffect(Unit) {
//        fetchjobpost(
//            onSuccess = { fetchedjobpost ->
//                jobpostList = fetchedjobpost
//                isLoading = false
//            },
//            onError = {
//                isLoading = false
//                // Handle the error, e.g., show a toast or a message
//            }
//        )
//
//        // Simulate progress for loading state
//        while (isLoading) {
//            delay(50)
//            progress = (progress + 1) % 101
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text(text = "Our Jobs") },
//                navigationIcon = {
//                    IconButton(onClick = { navController.navigate(ROUTE_DASHBOARD) }) {
//                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xfffcf3e0))
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xffb22e6f),
//                    titleContentColor = Color.White,
//                )
//            )
//        }
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xfffCf3E0))
//        ) {
//            if (isLoading) {
//                // Progress indicator
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircularProgressIndicator(progress = progress / 100f)
//                    Text(text = "Loading... $progress%", fontSize = 20.sp)
//                }
//            } else {
//                if (jobpostList.isEmpty()) {
//                    // No products found
//                    Text(text = "No jobs found", modifier = Modifier.align(Alignment.CenterHorizontally))
//                } else {
//                    // Products list
//                    LazyVerticalGrid(columns = GridCells.Fixed(1)) {
//                        items(jobpostList.take(displayedjobpostCount)) { jobpost ->
//                            jobpostListItem(jobpost) {
//                                navController.navigate("jobpostDetail/${jobpost.id}")
//                            }
//                        }
//                    }
//                    Spacer(modifier = Modifier.height(16.dp))
//                    // Load More Button
//                    if (displayedjobpostCount < jobpostList.size) {
//                        Button(
//                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffb22e6f)),
//                            onClick = { displayedjobpostCount += 1 },
//                            modifier = Modifier.align(Alignment.CenterHorizontally)
//                        ) {
//                            Text(text = "Load More")
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun jobpostListItem(jobpost: jobpost, onItemClick: (String) -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .clickable { onItemClick(jobpost.id) }
//            .background(Color.White)
//    ) {
//        Row(
//            // verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween,
//            modifier = Modifier.padding(16.dp)
//        ) {
//            // Product Image
//
//            Image(
//                painter = rememberAsyncImagePainter(jobpost.imageUrl),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(60.dp)
//            )
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            // Product Details
//            Column {
//                Text(text = jobpost.name)
//                Text(text = " ${jobpost.salary}")
//            }
//
//
//        }
//    }
//}
//
//private suspend fun fetchjobpost(onSuccess: (List<jobpost>) -> Unit, onError: () -> Unit) {
//    val firestore = Firebase.firestore
//    try {
//        val snapshot = firestore.collection("jobpost").get().await()
//        val jobpostList = snapshot.documents.mapNotNull { doc ->
//            val jobpost = doc.toObject<jobpost>()
//            jobpost?.id = doc.id
//            jobpost
//        }
//        onSuccess(jobpostList)
//    } catch (e: Exception) {
//        onError()
//    }
//}
//
//suspend fun fetchjobpost(jobpostId: String, onSuccess: (jobpost?) -> Unit) {
//    val firestore = Firebase.firestore
//    val docRef = firestore.collection("jobpost").document(jobpostId)
//    val snapshot = docRef.get().await()
//    val jobpost = snapshot.toObject<jobpost>()
//    onSuccess(jobpost)
//}