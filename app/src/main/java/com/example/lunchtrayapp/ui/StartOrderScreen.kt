package com.example.lunchtrayapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lunchtrayapp.R
import com.example.lunchtrayapp.ui.theme.LunchTrayAppTheme

@Composable
fun StartOrderScreen(
    onNextButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(
            modifier = Modifier
                .widthIn(min = 250.dp),
            onClick = { onNextButtonClicked() }
        ) {
            Text(
                text = stringResource(id = R.string.start_order),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartOrderScreenPreview(modifier: Modifier = Modifier) {
    LunchTrayAppTheme {
        StartOrderScreen()
    }
}