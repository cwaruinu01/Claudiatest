package net.ezra.ui.auth



import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import net.ezra.R
import net.ezra.navigation.ROUTE_DASHBOARD
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_REGISTER
import net.ezra.navigation.ROUTE_VIEW_PROD

@Composable
fun LoginScreen(navController: NavController, onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    BackHandler {
        navController.popBackStack()

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffcf3e0)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(painter = painterResource(id = R.drawable.verdictvistalogo2removebgpreview), contentDescription =null )


        Text("Login",
            style = MaterialTheme.typography.h4,
            color=Color(0xffb22e6f))
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email",
                color = Color(0xffb22e6f)) },
            modifier = Modifier,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xffb22e6f),
                unfocusedBorderColor = Color(0xffb22e6f)
            ),
            shape = RoundedCornerShape(20.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password",
                color = Color(0xffb22e6f)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xffb22e6f),
                unfocusedBorderColor = Color(0xffb22e6f)
            ),
            shape = RoundedCornerShape(20.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(48.dp))
        } else {



            Button(
                colors = ButtonDefaults.buttonColors(Color(0xffb22e6f)),
                onClick = {
                    if (email.isBlank() || password.isBlank()) {
                        error = "Please fill in all fields"
                    } else {
                        isLoading = true
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                isLoading = false
                                if (task.isSuccessful) {
                                    navController.navigate(ROUTE_DASHBOARD)
                                } else {
                                    error = task.exception?.message ?: "Login failed"
                                }
                            }
                    }
                },
                modifier = Modifier,
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Login")
            }

            androidx.compose.material3.Text(
                modifier = Modifier

                    .clickable {
                        navController.navigate(ROUTE_REGISTER) {
                            popUpTo(ROUTE_LOGIN) { inclusive = true }
                        }
                    },
                text = "Don't have an account? Register",
                textAlign = TextAlign.Center,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
            )



        }

        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
