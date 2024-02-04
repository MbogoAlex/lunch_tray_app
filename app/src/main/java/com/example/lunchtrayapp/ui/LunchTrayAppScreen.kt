package com.example.lunchtrayapp.ui

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtrayapp.R
import com.example.lunchtrayapp.datasource.Datasource

enum class LunchTrayAppNavigation(@StringRes val appBarTitle: Int) {
    START(R.string.lunch_tray),
    ENTREES(R.string.choose_entry),
    SIDE_DISH(R.string.choose_side_dish),
    ACCOMPANIMENT(R.string.choose_accompaniment),
    CHECKOUT(R.string.order_checkout),
}

@Composable
fun LunchTrayAppScreen(

    lunchTrayViewModel: LunchTrayViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = LunchTrayAppNavigation.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayAppNavigation.START.name
    )

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                lunchTrayAppNavigation = currentScreen,
                onBackButtonClicked = { navController.navigateUp() },
                showNavigationIcon = navController.previousBackStackEntry != null,
            )
        }
    ) {
        val lunchTrayUiState by lunchTrayViewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = LunchTrayAppNavigation.START.name,
            modifier = Modifier.padding(it)
        ) {
            composable(LunchTrayAppNavigation.START.name) {
                StartOrderScreen(
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayAppNavigation.ENTREES.name)
                    }
                )
            }
            composable(LunchTrayAppNavigation.ENTREES.name) {
                ChooseFoodItemScreen(
                    foodItems = Datasource.entrees,
                    onCancelButtonClicked = {
                        navController.popBackStack(LunchTrayAppNavigation.START.name, false)
                    },
                    onNextButtonClicked = {foodName, price ->
                        lunchTrayViewModel.addEntry(foodName, price)
                        navController.navigate(LunchTrayAppNavigation.SIDE_DISH.name)
                    }
                )
            }
            composable(LunchTrayAppNavigation.SIDE_DISH.name){
                ChooseFoodItemScreen(
                    foodItems = Datasource.sideDishes,
                    onCancelButtonClicked = {

                        navController.popBackStack(LunchTrayAppNavigation.START.name, false)
                    },
                    onNextButtonClicked = { foodName, price ->
                        lunchTrayViewModel.addSideDish(foodName, price)
                        navController.navigate(LunchTrayAppNavigation.ACCOMPANIMENT.name)
                    }
                )
            }
            composable(LunchTrayAppNavigation.ACCOMPANIMENT.name) {
                ChooseFoodItemScreen(
                    foodItems = Datasource.accompaniments,
                    onCancelButtonClicked = {

                        navController.popBackStack(LunchTrayAppNavigation.START.name, false)
                    },
                    onNextButtonClicked = { foodName, price ->
                        lunchTrayViewModel.addAccompaniment(foodName, price)
                        navController.navigate(LunchTrayAppNavigation.CHECKOUT.name)
                    }
                )
            }
            composable(LunchTrayAppNavigation.CHECKOUT.name) {
                val context = LocalContext.current
                OrderSummary(
                    lunchTrayUiState = lunchTrayUiState,
                    onShareButtonClicked = { subject, summary ->
                                           shareOrderSummary(subject, summary, context)
                    },
                    onCancelButtonClicked = {
                        navController.popBackStack(LunchTrayAppNavigation.START.name, false)
                    }
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    showNavigationIcon: Boolean,
    lunchTrayAppNavigation: LunchTrayAppNavigation,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
        Text(text = stringResource(id = lunchTrayAppNavigation.appBarTitle))
    },
    navigationIcon = {
        if (showNavigationIcon){
            IconButton(onClick = { onBackButtonClicked() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.previous_screen)
                )
            }
        }
    }
        )
}

private fun shareOrderSummary(subject: String, summary: String, context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }



    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.order_summary)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun LunchTrayAppScreenPreview() {
    LunchTrayAppScreen()
}