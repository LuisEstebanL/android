package com.sembanha.smokehouse.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sembanha.smokehouse.data.MockData
import com.sembanha.smokehouse.ui.components.DishCard
import com.sembanha.smokehouse.ui.components.LoyaltyBanner
import com.sembanha.smokehouse.ui.theme.*
import com.sembanha.smokehouse.viewmodel.RestaurantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    viewModel: RestaurantViewModel,
    onNavigateToPassport: () -> Unit,
    onNavigateToBill: () -> Unit
) {
    val filteredDishes = MockData.dishes.filter { dish ->
        val matchesCat = viewModel.selectedCategory == "todos" ||
                dish.category == viewModel.selectedCategory ||
                (viewModel.selectedCategory == "favoritos" && dish.badges.any { it.contains("ASSINATURA") || it.contains("CLÁSSICO") })

        val q = viewModel.searchQuery.trim().lowercase()
        val matchesSearch = q.isEmpty() ||
                dish.name.lowercase().contains(q) ||
                dish.description.lowercase().contains(q)

        matchesCat && matchesSearch
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 90.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Loyalty Progress Banner
            item {
                LoyaltyBanner(
                    currentStamps = viewModel.stampsCount,
                    totalStamps = 8,
                    onSeeRulesClick = onNavigateToPassport
                )
            }

            // Search Bar
            item {
                OutlinedTextField(
                    value = viewModel.searchQuery,
                    onValueChange = { viewModel.updateSearch(it) },
                    placeholder = {
                        Text(
                            text = "Buscar picanha, costela, torresmo...",
                            fontSize = 13.sp,
                            color = TextMuted
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar",
                            tint = TextMuted,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = BgCard,
                        unfocusedContainerColor = BgCard,
                        focusedBorderColor = FlamePrimary,
                        unfocusedBorderColor = BorderSubtle,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    singleLine = true
                )
            }

            // Category Chips Row
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(MockData.categories) { cat ->
                        val isSelected = viewModel.selectedCategory == cat.id
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(if (isSelected) FlamePrimary else BgCard)
                                .border(
                                    1.dp,
                                    if (isSelected) FlameBright else BorderSubtle,
                                    RoundedCornerShape(20.dp)
                                )
                                .clickable { viewModel.selectCategory(cat.id) }
                                .padding(horizontal = 14.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = cat.icon, fontSize = 12.sp)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = cat.label,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                                    color = if (isSelected) Color.White else TextSecondary
                                )
                            }
                        }
                    }
                }
            }

            // Section Title
            item {
                Column {
                    Text(
                        text = "TRADIÇÃO DO FOGO & BRASA",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black,
                        color = FlameBright,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Mais Pedidos da Casa",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Dishes
            items(filteredDishes) { dish ->
                DishCard(
                    dish = dish,
                    onDishClick = { viewModel.openDishDetail(dish) },
                    onAddClick = { viewModel.openDishDetail(dish) }
                )
            }
        }

        // Floating Table Pill (Bottom of screen)
        Surface(
            color = BgCard.copy(alpha = 0.95f),
            shape = RoundedCornerShape(30.dp),
            shadowElevation = 8.dp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth()
                .border(1.dp, BorderHighlight, RoundedCornerShape(30.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(SuccessGreen)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Mesa 14 Ativa",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Pedindo em Galera",
                            fontSize = 10.sp,
                            color = GoldBright
                        )
                    }
                }

                Button(
                    onClick = onNavigateToBill,
                    colors = ButtonDefaults.buttonColors(containerColor = FlamePrimary),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text(
                        text = "Comanda",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
