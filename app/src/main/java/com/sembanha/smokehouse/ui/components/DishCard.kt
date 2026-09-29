package com.sembanha.smokehouse.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sembanha.smokehouse.data.Dish
import com.sembanha.smokehouse.ui.theme.*

@Composable
fun DishCard(
    dish: Dish,
    onDishClick: () -> Unit,
    onAddClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = BgCard),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
            .clickable { onDishClick() }
    ) {
        Column {
            // Media Image with badges
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(Color.Black)
            ) {
                AsyncImage(
                    model = dish.imageAsset,
                    contentDescription = dish.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Badges top-left
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopStart),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    dish.badges.forEach { badge ->
                        val isRed = badge.contains("CLÁSSICO") || badge.contains("ASSINATURA")
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (isRed) Color(0xFFDC2626) else Color(0xFFB45309))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = badge,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            // Info Content
            Column(
                modifier = Modifier.padding(14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${dish.serving} • ${dish.weight}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = GoldBright
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = dish.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = dish.description,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(12.dp))

                Divider(color = BorderSubtle, thickness = 1.dp)

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "PREÇO INDIVIDUAL",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextMuted
                        )
                        Text(
                            text = "R$ %.2f".format(dish.price).replace('.', ','),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                    }

                    Button(
                        onClick = onAddClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = FlamePrimary
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "+ Pedir",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
