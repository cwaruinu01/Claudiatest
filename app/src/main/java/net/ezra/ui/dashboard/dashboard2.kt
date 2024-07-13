package net.ezra.ui.dashboard

// Import necessary libraries
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

// MainActivity: Entry point of the app
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Setting the content view to our ProgressTracker composable
            ProgressTrackerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ProgressTracker()
                }
            }
        }
    }
}

// ProgressState: Data class to hold progress state
data class ProgressState(val currentStep: Int, val totalSteps: Int)

// ProgressViewModel: ViewModel to manage progress state
class ProgressViewModel : ViewModel() {
    // LiveData to hold the current state of progress
    private val _progressState = MutableLiveData(ProgressState(currentStep = 0, totalSteps = 10))
    val progressState: LiveData<ProgressState> = _progressState

    // Function to update progress
    fun updateProgress(step: Int) {
        _progressState.value = _progressState.value?.copy(currentStep = step)
    }
}

// ProgressTracker: Composable function to display the progress tracker
@Composable
fun ProgressTracker(viewModel: ProgressViewModel = viewModel()) {
    // Observe the progress state from the ViewModel
    val progressState by viewModel.progressState.observeAsState(ProgressState(0, 10))

    // Layout for the progress tracker
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display progress as text
        Text(text = "Progress: ${progressState.currentStep} / ${progressState.totalSteps}")

        // Display a linear progress indicator
        LinearProgressIndicator(
            progress = progressState.currentStep.toFloat() / progressState.totalSteps,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Button to advance the progress
        Button(onClick = {
            if (progressState.currentStep < progressState.totalSteps) {
                viewModel.updateProgress(progressState.currentStep + 1)
            }
        }) {
            Text("Advance Step")
        }
    }
}

// ProgressTrackerTheme: Theme for the app (optional, can be customized)
@Composable
fun ProgressTrackerTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        // Call the content composable function
        content()
    }
}
