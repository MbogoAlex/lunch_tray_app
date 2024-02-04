package com.example.lunchtrayapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchtrayapp.datasource.LunchTrayUiState
import com.example.lunchtrayapp.R
import com.example.lunchtrayapp.ui.components.FormatPrice
import com.example.lunchtrayapp.ui.theme.LunchTrayAppTheme

@Composable
fun OrderSummary(
    lunchTrayUiState: LunchTrayUiState,
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    onShareButtonClicked: (subject: String, summary: String) -> Unit = {subject, summary ->},
    modifier: Modifier = Modifier,
) {
    val orderSummary = stringResource(
        id = R.string.order_summary_details,
        lunchTrayUiState.entree,
        lunchTrayUiState.sideDish,
        lunchTrayUiState.accompaniment,
        lunchTrayUiState.subtotal,
        lunchTrayUiState.tax,
        lunchTrayUiState.total
    )

    val subject = stringResource(id = R.string.order_summary)
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(

        ) {
            Text(
                text = stringResource(id = R.string.order_summary),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .padding(
                        top = 10.dp
                    )
            ) {
                Text(text = stringResource(id = lunchTrayUiState.entree.first))
                Spacer(modifier = Modifier.weight(1f))
                FormatPrice(price = lunchTrayUiState.entree.second.toString())
            }
            Row(
                modifier = Modifier
                    .padding(
                        top = 10.dp
                    )
            ) {
                Text(text = stringResource(id = lunchTrayUiState.sideDish.first))
                Spacer(modifier = Modifier.weight(1f))
                FormatPrice(price = lunchTrayUiState.sideDish.second.toString())
            }

            Row(
                modifier = Modifier
                    .padding(
                        top = 10.dp
                    )
            ) {
                Text(text = stringResource(id = lunchTrayUiState.accompaniment.first))
                Spacer(modifier = Modifier.weight(1f))
                FormatPrice(price = lunchTrayUiState.accompaniment.second.toString())
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(thickness = 1.dp)
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.subtotal, lunchTrayUiState.subtotal))
            Text(text = stringResource(id = R.string.tax, lunchTrayUiState.tax))
            Text(
                text = stringResource(id = R.string.total, lunchTrayUiState.total),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = { onCancelButtonClicked() },
                modifier = modifier
                    .weight(1f)

            ) {
                Text(text = stringResource(id = R.string.cancel).toUpperCase())
            }
            Button(
                onClick = { onNextButtonClicked() },
                enabled = false,
                modifier = modifier
                    .weight(1f)
            ) {
                Text(text = stringResource(id = R.string.next).toUpperCase())

            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { onShareButtonClicked(subject, orderSummary) },
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.share).toUpperCase())

        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderSummaryPreview(
    modifier: Modifier = Modifier,
) {
    LunchTrayAppTheme {
        OrderSummary(lunchTrayUiState = LunchTrayUiState(
            Pair(R.string.cauliflower, 20.0),
            Pair(R.string.summer_salad, 15.0),
            Pair(R.string.lunch_roll, 10.0),
            45.0,
            0.84,
            45.84
            )
        )
    }
}