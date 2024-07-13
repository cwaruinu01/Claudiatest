package net.ezra.ui.products

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import net.ezra.navigation.ROUTE_DASHBOARD
import net.ezra.navigation.ROUTE_VIEW_PROD
import java.util.UUID

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddJobpostScreen(navController: NavController, onProductAdded: () -> Unit) {
    var jobName by remember { mutableStateOf("") }
    var jobDescription by remember { mutableStateOf("") }
    var jobSalary by remember { mutableStateOf("") }
    var jobImageUri by remember { mutableStateOf<Uri?>(null) }
    var jobSocialmedia by remember { mutableStateOf("") }
    var jobEmail by remember { mutableStateOf("") }
    var jobCompany by remember { mutableStateOf("") }


    // Track if fields are empty
    var jobNameError by remember { mutableStateOf(false) }
    var jobDescriptionError by remember { mutableStateOf(false) }
    var jobSalaryError by remember { mutableStateOf(false) }
    var jobImageError by remember { mutableStateOf(false) }
    var jobSocialmediaError by remember { mutableStateOf(false) }
    var jobEmailError by remember { mutableStateOf(false) }
    var jobCompanyError by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            jobImageUri = it
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Add Jobpost", fontSize = 30.sp, color = Color.White)
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xfffcf3e0))
            ) {
                item {
                    if (jobImageUri != null) {
                        // Display selected image
                        Image(
                            painter = rememberImagePainter(jobImageUri), // Using rememberImagePainter with Uri
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    } else {
                        // Display placeholder if no image selected
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No Image Selected", modifier = Modifier.padding(8.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { launcher.launch("image/*") }) {
                        Text("Select Image")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        shape = RoundedCornerShape(20.dp),
                        value = jobName,
                        onValueChange = { jobName = it },
                        label = { Text("Post") },
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(100.dp))
                    OutlinedTextField(
                        shape = RoundedCornerShape(20.dp),
                        value = jobDescription,
                        onValueChange = { jobDescription = it },
                        label = { Text(" Description") },
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        shape = RoundedCornerShape(20.dp),
                        value = jobSalary,
                        onValueChange = { jobSalary = it },
                        label = { Text("Salary") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions(onDone = { /* Handle Done action */ }),
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        shape = RoundedCornerShape(20.dp),
                        value = jobEmail,
                        onValueChange = { jobEmail = it },
                        label = { Text(" email") },
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = jobCompany,
                        shape = RoundedCornerShape(20.dp),
                        onValueChange = { jobCompany = it },
                        label = { Text(" company") },
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    OutlinedTextField(
                        shape = RoundedCornerShape(20.dp),
                        value = jobSocialmedia,
                        onValueChange = { jobSocialmedia = it },
                        label = { Text(" social media") },
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    if (jobNameError) {
                        Text("post is required", color = Color.Red)
                    }
                    if (jobDescriptionError) {
                        Text(" Description is required", color = Color.Red)
                    }
                    if (jobSalaryError) {
                        Text("Salary is required", color = Color.Red)
                    }
                    if (jobImageError) {
                        Text(" Image is required", color = Color.Red)
                    }
                    if (jobSocialmediaError){
                        Text(text = "Social media is required", color = Color.Red)
                    }
                    if (jobCompanyError){
                        Text(text = "Company is required", color = Color.Red)
                    }
                    if (jobEmailError){
                        Text(text = "Email is required", color = Color.Red)
                    }

                    // Button to add product
                    Button(
                        onClick = {
                            // Reset error flags
                            jobNameError = jobName.isBlank()
                            jobDescriptionError = jobDescription.isBlank()
                            jobSalaryError = jobSalary.isBlank()
                            jobSocialmediaError = jobSocialmedia.isBlank()
                            jobEmailError = jobEmail.isBlank()
                            jobCompanyError = jobCompany.isBlank()

                            jobImageError = jobImageUri == null

                            // Add product if all fields are filled
                            if (!jobNameError && !jobDescriptionError && !jobSalaryError && !jobImageError && ! jobSocialmediaError && ! jobEmailError && ! jobCompanyError) {
                                addProductToFirestore(
                                    navController,
                                    onProductAdded,
                                    jobName,
                                    jobDescription,
                                    jobSalary.toDouble(),
                                    jobImageUri,
                                    jobCompany,
                                    jobSocialmedia,
                                    jobEmail
                                )
                            }
                        },
                        modifier = Modifier,
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(2.dp, Color(0XFFb22e6f)),
                        colors = androidx.compose.material.ButtonDefaults.buttonColors(Color(0XFFfcf3e0)),

                    ) {
                        Text("Add Job")
                    }
                }
            }
        }
    )
}

private fun addProductToFirestore(navController: NavController, onProductAdded: () -> Unit, jobName: String, jobDescription: String, jobSalary: Double, jobImageUri: Uri?,jobSocialmedia :String , jobEmail:String ,jobCompany:String) {
    if (jobName.isEmpty() || jobDescription.isEmpty() || jobSalary.isNaN() || jobImageUri == null || jobCompany.isEmpty() || jobEmail.isEmpty() || jobSocialmedia.isEmpty()) {
        // Validate input fields
        return
    }

    val productId = UUID.randomUUID().toString()

    val firestore = Firebase.firestore
    val productData = hashMapOf(
        "name" to jobName,
        "description" to jobDescription,
        "salary" to jobSalary,
        "imageUrl" to "",
        "email" to jobEmail,
        "company" to jobCompany,
        "socialmedia" to jobSocialmedia,
    )

    firestore.collection("Jobpost").document(productId)
        .set(productData)
        .addOnSuccessListener {
            uploadImageToStorage(productId, jobImageUri) { imageUrl ->
                firestore.collection("Jobpost").document(productId)
                    .update("imageUrl", imageUrl)
                    .addOnSuccessListener {
                        // Display toast message
                        Toast.makeText(
                            navController.context,
                            "Job added successfully!",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Navigate to another screen
                        navController.navigate(ROUTE_DASHBOARD)

                        // Invoke the onProductAdded callback
                        onProductAdded()
                    }
                    .addOnFailureListener { e ->
                        // Handle error updating product document
                    }
            }
        }
        .addOnFailureListener { e ->
            // Handle error adding product to Firestore
        }
}

private fun uploadImageToStorage(productId: String, imageUri: Uri?, onSuccess: (String) -> Unit) {
    if (imageUri == null) {
        onSuccess("")
        return
    }

    val storageRef = Firebase.storage.reference
    val imagesRef = storageRef.child("Jobpost/$productId.jpg")

    imagesRef.putFile(imageUri)
        .addOnSuccessListener { taskSnapshot ->
            imagesRef.downloadUrl
                .addOnSuccessListener { uri ->
                    onSuccess(uri.toString())
                }
                .addOnFailureListener {
                    // Handle failure to get download URL
                }
        }
        .addOnFailureListener {
            // Handle failure to upload image
        }
}





//
////import androidx.compose.foundation.clickable
////noinspection UsingMaterialAndMaterial3Libraries
////noinspection UsingMaterialAndMaterial3Libraries
////noinspection UsingMaterialAndMaterial3Libraries
////noinspection UsingMaterialAndMaterial3Libraries
////noinspection UsingMaterialAndMaterial3Libraries
////noinspection UsingMaterialAndMaterial3Libraries
////import coil.compose.rememberImagePainter
//import android.annotation.SuppressLint
//import android.net.Uri
//import android.widget.Toast
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.Button
//import androidx.compose.material.Icon
//import androidx.compose.material.IconButton
//import androidx.compose.material.OutlinedTextField
//import androidx.compose.material.Scaffold
//import androidx.compose.material.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.CenterAlignedTopAppBar
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import coil.compose.rememberAsyncImagePainter
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase
//import com.google.firebase.storage.ktx.storage
//import net.ezra.navigation.ROUTE_DASHBOARD
//import net.ezra.navigation.ROUTE_VIEW_PROD
//import java.util.UUID
//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddjobpostScreen(navController: NavController, onjobpostAdded: () -> Unit) {
//    var name by remember { mutableStateOf("") }
//    var description by remember { mutableStateOf("") }
//    var salary by remember { mutableStateOf("") }
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//    var email by remember { mutableStateOf("") }
//    var linkedIn by remember { mutableStateOf("") }
//    var instagram by remember { mutableStateOf("") }
//    var x by remember { mutableStateOf("") }
//    var company by remember { mutableStateOf("") }
//
//    // Track if fields are empty
//    var nameError by remember { mutableStateOf(false) }
//    var descriptionError by remember { mutableStateOf(false) }
//    var salaryError by remember { mutableStateOf(false) }
//    var imageError by remember { mutableStateOf(false) }
//    var emailError by remember { mutableStateOf(false) }
//    var linkedInError by remember { mutableStateOf(false) }
//    var instagramError by remember { mutableStateOf(false) }
//    var xError by remember { mutableStateOf(false) }
//    var companyError by remember { mutableStateOf(false) }
//
//    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
//        uri?.let {
//            imageUri = it
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = {
//                    Text(text = "Add jobpost", fontSize = 30.sp, color = Color.White)
//                },
//                navigationIcon = {
//                    IconButton(onClick = {
//                        navController.navigate(ROUTE_DASHBOARD)
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
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color(0xfffcf3e0))
//            ) {
//                item {
//                    if (imageUri != null) {
//                        // Display selected image
//                        Image(
//                            painter = rememberAsyncImagePainter(imageUri), // Using rememberImagePainter with Uri
//                            contentDescription = null,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(200.dp)
//                        )
//                    } else {
//                        // Display placeholder if no image selected
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(200.dp),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text("No Image Selected", modifier = Modifier.padding(8.dp))
//                        }
//                    }
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Button(
//                        onClick = { launcher.launch("image/*") },
//                        colors = androidx.compose.material.ButtonDefaults.buttonColors(Color(0XFFfcf3e0))
//                    ) {
//                        Text("Select Image",color = Color(0XFFb22e6f))
//
//                    }
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        OutlinedTextField(
//                            value = name,
//                            onValueChange = { name = it },
//                            label = { Text("post") },
//                            shape = RoundedCornerShape(20.dp)
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        OutlinedTextField(
//                            value = description,
//                            onValueChange = { description = it },
//                            label = { Text("Description") },
//                            shape = RoundedCornerShape(20.dp)
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        OutlinedTextField(
//                            value = salary,
//                            onValueChange = { salary = it },
//                            label = { Text("salary") },
//                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                            keyboardActions = KeyboardActions(onDone = { /* Handle Done action */ }),
//                            shape = RoundedCornerShape(20.dp)
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        OutlinedTextField(
//                            value = linkedIn,
//                            onValueChange = { linkedIn = it },
//                            label = { Text(text = "LinkedIn")},
//                            shape = RoundedCornerShape(20.dp)
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        OutlinedTextField(
//                            value = instagram,
//                            onValueChange = { instagram = it },
//                            label = { Text(text = "Instagram")},
//                            shape = RoundedCornerShape(20.dp)
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        OutlinedTextField(
//                            value = x,
//                            onValueChange = { x = it },
//                            label = { Text(text = "X")},
//                            shape = RoundedCornerShape(20.dp)
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        OutlinedTextField(
//                            value = email,
//                            onValueChange = { email = it },
//                            label = { Text(text = "Email")},
//                            shape = RoundedCornerShape(20.dp)
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        OutlinedTextField(
//                            value = company,
//                            onValueChange = { company = it },
//                            label = { Text(text = "Company")},
//                            shape = RoundedCornerShape(20.dp)
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    if (nameError) {
//                        Text("post is required", color = Color.Red)
//                    }
//                    if (descriptionError) {
//                        Text("Description is required", color = Color.Red)
//                    }
//                    if (salaryError) {
//                        Text("salary is required", color = Color.Red)
//                    }
//                    if (imageError) {
//                        Text("Image is required", color = Color.Red)
//                    }
//                    if (linkedInError) {
//                        Text("LinkedIn is required", color = Color.Red)
//                    }
//                    if (instagramError) {
//                        Text("Instagram is required", color = Color.Red)
//                    }
//                    if (xError) {
//                        Text("X is required", color = Color.Red)
//                    }
//                    if (emailError) {
//                        Text("Email is required", color = Color.Red)
//                    }
//                    if (companyError) {
//                        Text("Company is required", color = Color.Red)
//                    }
//
//                    // Button to add product
//                    Button(
//                        onClick = {
//
//                            navController.navigate(ROUTE_VIEW_PROD)
//                            // Reset error flags
//                            nameError = name.isBlank()
//                            descriptionError = description.isBlank()
//                            salaryError = salary.isBlank()
//                            imageError = imageUri == null
//                            linkedInError=linkedIn.isBlank()
//                            instagramError=instagram.isBlank()
//                            xError=x.isBlank()
//                            emailError=email.isBlank()
//                            companyError=company.isBlank()
//
//                            // Add product if all fields are filled
//                            if (!nameError && descriptionError && !salaryError && !imageError&& !linkedInError&& !instagramError&& !xError&& !emailError&& !companyError) {
//                                addjobpostToFirestore(
//                                    navController,
//                                    onjobpostAdded,
//                                    name,
//                                    description,
//                                    salary.toDouble(),
//                                    imageUri,
//                                    linkedIn,
//                                    instagram,
//                                    x,
//                                    email,
//                                    company
//                                )
//                            }
//                        },
//                        modifier = Modifier,
//                        shape = RoundedCornerShape(20.dp),
//                        border = BorderStroke(2.dp, Color(0XFFb22e6f)),
//                        colors = androidx.compose.material.ButtonDefaults.buttonColors(Color(0XFFfcf3e0))
//                    ) {
//                        Text("Add Jobpost", color = Color(0XFFb22e6f))
//                    }
//                }
//            }
//        }
//    )
//}
//
//private fun addjobpostToFirestore(navController: NavController, onjobpostAdded: () -> Unit, name: String, description: String, salary: Double, imageUri: Uri?,linkedIn: String, instagram: String, x: String, email: String, company: String) {
//    if (name.isEmpty() || description.isEmpty() || salary.isNaN() || imageUri == null || linkedIn.isEmpty() || instagram.isEmpty() || x.isEmpty() || email.isEmpty() || company.isEmpty()) {
//        // Validate input fields
//        return
//    }
//
//    val jobpostId = UUID.randomUUID().toString()
//
//    val firestore = Firebase.firestore
//    val jobpostData = hashMapOf(
//        "name" to name,
//        "description" to description,
//        "salary" to salary,
//        "imageUrl" to "",
//        "linkedIn" to linkedIn,
//        "instagram" to instagram,
//        "x" to x,
//        "email" to email,
//        "company" to company
//    )
//
//    firestore.collection("jobpost").document(jobpostId)
//        .set(jobpostData)
//        .addOnSuccessListener {
//            uploadImageToStorage(jobpostId, imageUri) { imageUrl ->
//                firestore.collection("jobpost").document(jobpostId)
//                    .update("imageUrl", imageUrl)
//                    .addOnSuccessListener {
//                        // Display toast message
//                        Toast.makeText(
//                            navController.context,
//                            "Job added successfully!",
//                            Toast.LENGTH_SHORT
//                        ).show()
//
//                        // Navigate to another screen
//                        navController.navigate(ROUTE_VIEW_PROD)
//
//                        // Invoke the onProductAdded callback
//                        onjobpostAdded()
//                    }
//                    .addOnFailureListener { e ->
//                        // Handle error updating product document
//                    }
//            }
//        }
//        .addOnFailureListener { e ->
//            // Handle error adding product to Firestore
//        }
//}
//
//private fun uploadImageToStorage(jobpostId: String, imageUri: Uri?, onSuccess: (String) -> Unit) {
//    if (imageUri == null) {
//        onSuccess("")
//        return
//    }
//
//    val storageRef = Firebase.storage.reference
//    val imagesRef = storageRef.child("jobpost/$jobpostId.jpg")
//
//    imagesRef.putFile(imageUri)
//        .addOnSuccessListener { taskSnapshot ->
//            imagesRef.downloadUrl
//                .addOnSuccessListener { uri ->
//                    onSuccess(uri.toString())
//                }
//                .addOnFailureListener {
//                    // Handle failure to get download URL
//                }
//        }
//        .addOnFailureListener {
//            // Handle failure to upload image
//        }
//}
