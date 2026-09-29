package com.sembanha.smokehouse

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.sembanha.smokehouse.ui.components.BottomNav
import com.sembanha.smokehouse.ui.components.TopBar
import com.sembanha.smokehouse.ui.screens.*
import com.sembanha.smokehouse.ui.theme.BgBody
import com.sembanha.smokehouse.ui.theme.SemBanhaSmokehouseTheme
import com.sembanha.smokehouse.viewmodel.RestaurantViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SemBanhaSmokehouseTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: RestaurantViewModel) {
    val context = androidx.compose.ui.platform.LocalContext.current

    // Observe toast messages
    LaunchedEffect(viewModel.toastMessage) {
        viewModel.toastMessage?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            viewModel.clearToast()
        }
    }

    Scaffold(
        containerColor = BgBody,
        topBar = {
            TopBar(
                onCallWaiterClick = { viewModel.isWaiterDialogOpen = true }
            )
        },
        bottomBar = {
            BottomNav(
                activeTab = viewModel.activeTab,
                cartCount = viewModel.totalItemsCount,
                onTabSelect = { viewModel.activeTab = it }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(targetState = viewModel.activeTab, label = "TabTransition") { tab ->
                when (tab) {
                    "menu" -> MenuScreen(
                        viewModel = viewModel,
                        onNavigateToPassport = { viewModel.activeTab = "passport" },
                        onNavigateToBill = { viewModel.activeTab = "bill" }
                    )
                    "bill" -> BillScreen(
                        viewModel = viewModel,
                        onNavigateToCamera = { viewModel.activeTab = "camera" }
                    )
                    "camera" -> CameraSocialScreen(
                        viewModel = viewModel,
                        onNavigateToPassport = { viewModel.activeTab = "passport" }
                    )
                    "passport" -> PassportScreen(
                        viewModel = viewModel
                    )
                }
            }

            // Dish Customization BottomSheet
            viewModel.selectedDishForDetail?.let { dish ->
                DishDetailSheet(
                    dish = dish,
                    onDismiss = { viewModel.closeDishDetail() },
                    onAddToCart = { side, sauces, notes, qty ->
                        viewModel.addDishToOrder(dish, side, sauces, notes, qty)
                    }
                )
            }

            // Waiter Dialog
            if (viewModel.isWaiterDialogOpen) {
                WaiterDialog(
                    onDismiss = { viewModel.isWaiterDialogOpen = false },
                    onSelectReason = { reason ->
                        viewModel.isWaiterDialogOpen = false
                        viewModel.showToast("🔔 Garçom Lucas notificado ($reason)!")
                    }
                )
            }
        }
    }
}
