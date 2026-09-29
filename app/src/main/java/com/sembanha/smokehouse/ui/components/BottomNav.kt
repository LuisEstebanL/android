package com.sembanha.smokehouse.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sembanha.smokehouse.ui.theme.*

data class NavItemData(
    val id: String,
    val label: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)

@Composable
fun BottomNav(
    activeTab: String,
    cartCount: Int,
    onTabSelect: (String) -> Unit
) {
    val items = listOf(
        NavItemData("menu", "Cardápio", Icons.Default.RestaurantMenu),
        NavItemData("bill", "Comanda", Icons.Default.ReceiptLong, badgeCount = cartCount),
        NavItemData("camera", "Câmera", Icons.Default.CameraAlt),
        NavItemData("passport", "Selos", Icons.Default.MilitaryTech)
    )

    Surface(
        color = BgDarkest.copy(alpha = 0.98f),
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        tonalElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val isSelected = activeTab == item.id
                val tintColor = if (isSelected) FlamePrimary else TextMuted

                Column(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onTabSelect(item.id) }
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = tintColor,
                            modifier = Modifier.size(24.dp)
                        )
                        if (item.badgeCount > 0) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .offset(x = 6.dp, y = (-4).dp)
                                    .size(16.dp)
                                    .clip(CircleShape)
                                    .background(FlamePrimary),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${item.badgeCount}",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = item.label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = tintColor
                    )
                }
            }
        }
    }
}
