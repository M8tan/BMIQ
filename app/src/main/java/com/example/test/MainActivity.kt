package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BmiCalculatorApp()
                }
            }
        }
    }
}

@Composable
fun BmiCalculatorApp() {
    // ---- State variables ----
    var gender by remember { mutableStateOf("Male") }
    var age by remember { mutableStateOf(25f) }
    var weight by remember { mutableStateOf(70f) }
    var height by remember { mutableStateOf(170f) }
    var bmiResult by remember { mutableStateOf<Float?>(null) }

    // ---- Layout ----
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "BMI Calculator",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // GENDER SELECTOR
        GenderSelector(selectedGender = gender, onGenderChange = { gender = it })
        Spacer(Modifier.height(24.dp))

        // AGE SLIDER
        Text("Age: ${age.toInt()} years", fontSize = 18.sp)
        Slider(value = age, onValueChange = { age = it }, valueRange = 10f..100f)
        Spacer(Modifier.height(16.dp))

        // WEIGHT SLIDER
        Text("Weight: ${weight.toInt()} kg", fontSize = 18.sp)
        Slider(value = weight, onValueChange = { weight = it }, valueRange = 30f..200f)
        Spacer(Modifier.height(16.dp))

        // HEIGHT SLIDER
        Text("Height: ${height.toInt()} cm", fontSize = 18.sp)
        Slider(value = height, onValueChange = { height = it }, valueRange = 100f..220f)
        Spacer(Modifier.height(24.dp))

        // CALCULATE BUTTON
        Button(onClick = {
            val hMeters = height / 100
            val bmi = weight / (hMeters * hMeters)
            bmiResult = bmi
        }) {
            Text("Calculate BMI")
        }

        // RESULT DISPLAY
        bmiResult?.let { bmi ->
            Spacer(Modifier.height(24.dp))
            val category = when {
                bmi < 18.5 -> "Underweight"
                bmi < 25 -> "Normal"
                bmi < 30 -> "Overweight"
                else -> "Obese"
            }

            Text(
                text = "Your BMI: %.2f".format(bmi),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Which is: $category",
                fontSize = 20.sp,
                color = when (category) {
                    "Underweight" -> MaterialTheme.colorScheme.secondary
                    "Normal" -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.error
                }
            )
        }
    }
}

@Composable
fun GenderSelector(selectedGender: String, onGenderChange: (String) -> Unit) {
    val options = listOf("Male", "Female")

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Gender", fontSize = 18.sp, fontWeight = FontWeight.Medium)
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { gender ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    RadioButton(
                        selected = (selectedGender == gender),
                        onClick = { onGenderChange(gender) }
                    )
                    Text(gender)
                }
            }
        }
    }
}
