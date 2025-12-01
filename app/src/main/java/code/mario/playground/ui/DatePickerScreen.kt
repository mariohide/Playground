package code.mario.playground.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerScreen() {
    var showPicker by remember { mutableStateOf(false) }
    Column(Modifier
        .fillMaxSize()
        .systemBarsPadding()) {
        Button(onClick = { showPicker = true }) {
            Text(text = "show date picker")
        }
    }
    if (showPicker) {
        DatePickerDialog(
            onDismissRequest = { showPicker = false },
            confirmButton = { Text(text = "confirm") },
            content = {
                DatePicker(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    state = rememberDatePickerState()
                )
            }
        )
    }
}
