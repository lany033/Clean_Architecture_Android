package com.example.kotlincoroutines1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

@Composable
fun Screen(viewModel: MainViewModel = viewModel()){
    var a by remember { mutableStateOf("") }
    var b by remember { mutableStateOf("") }

    Calculator(
        a = a,
        onAChanged = {
            a = it
        },
        b = b,
        onBChanged = {
            b = it
        },
        result = viewModel.resultState,
        onButtonClick = {
            viewModel.add(a, b)
        }
    )
}

@Composable
fun Calculator(
    a: String,
    onAChanged: (String) -> Unit,
    b: String,
    onBChanged: (String) -> Unit,
    result: String,
    onButtonClick: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = a,
            onValueChange = onAChanged,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("a") }
        )
        OutlinedTextField(
            value = b,
            onValueChange = onBChanged,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("b") }
        )
        Text(text = result)
        Button(onClick = onButtonClick) {
            Text(text = "Calculate")
        }
    }
}
