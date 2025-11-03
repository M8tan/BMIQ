package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                CounterApp()
            }
        }
    }
}

@Composable
fun CounterApp() {
    // State variable to keep track of the counter
    var count by remember { mutableStateOf(0) }

    // Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Counter: $count",
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = { count++ },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Increase")
        }

        Button(
            onClick = { count = 0 },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Reset")
        }
    }
}
