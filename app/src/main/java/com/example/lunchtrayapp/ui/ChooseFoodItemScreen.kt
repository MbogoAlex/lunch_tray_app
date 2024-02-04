package com.example.lunchtrayapp.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchtrayapp.R
import com.example.lunchtrayapp.datasource.Datasource
import com.example.lunchtrayapp.model.Tray
import com.example.lunchtrayapp.ui.components.FormatPrice
import com.example.lunchtrayapp.ui.theme.LunchTrayAppTheme

@Composable
fun ChooseFoodItemScreen(
    foodItems: List<Tray>,
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: (foodName: Int, price: Double) -> Unit = {foodName, price ->},
    modifier: Modifier = Modifier
) {
    var selectedEntry by rememberSaveable {
        mutableStateOf(0)
    }

    var foodName by rememberSaveable {
        mutableStateOf(0)
    }
    var price by rememberSaveable {
        mutableStateOf(0.00)
    }

    var nextButtonEnabled by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(ScrollState(initial = 0))
    ) {
        foodItems.forEach { item ->
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        bottom = 10.dp
                    )
            ) {
                RadioButton(
                    selected = selectedEntry == item.foodName,
                    onClick = {
                        selectedEntry = item.foodName
                        foodName = item.foodName
                        price = item.price
                        nextButtonEnabled = true
                    }
                )
                Column {
                    Text(
                        text = stringResource(id = item.foodName),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = stringResource(id = item.foodDesc),
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    FormatPrice(

                        price = item.price.toString(),
                        modifier = Modifier

                            .padding(
                                top = 10.dp,
                                bottom = 10.dp
                            )
                    )
                }

            }
            Divider(thickness = 1.dp)
        }
        Spacer(modifier = Modifier.height(20.dp))
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
                onClick = { onNextButtonClicked(foodName, price) },
                enabled = nextButtonEnabled,
                modifier = modifier
                    .weight(1f)
            ) {
                Text(text = stringResource(id = R.string.next).toUpperCase())

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChooseEntryScreenPreview(
    modifier: Modifier = Modifier
) {
    LunchTrayAppTheme {
        ChooseFoodItemScreen(
            foodItems = Datasource.entrees,

        )
    }
}
