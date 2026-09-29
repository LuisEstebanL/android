package com.sembanha.smokehouse.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sembanha.smokehouse.data.Dish
import com.sembanha.smokehouse.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishDetailSheet(
    dish: Dish,
    onDismiss: () -> Unit,
    onAddToCart: (side: String?, sauces: List<String>, notes: String, qty: Int) -> Unit
) {
    var selectedSide by remember { mutableStateOf(dish.acompanhamentos.firstOrNull()?.name) }
    val selectedSauces = remember { mutableStateListOf<String>() }
    var notes by remember { mutableStateOf("") }
    var qty by remember { mutableIntStateOf(1) }

    val basePrice = dish.price
    val saucesPrice = selectedSauces.sumOf { sName ->
        dish.molhos.find { it.name == sName }?.price ?: 0.0
    }
    val totalPrice = (basePrice + saucesPrice) * qty

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = BgCard,
        contentColor = Color.White,
        dragHandle = null,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            // Hero Image with Close Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Black)
            ) {
                AsyncImage(
                    model = dish.imageAsset,
                    contentDescription = dish.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopEnd)
                        .size(36.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color.Black.copy(alpha = 0.7f))
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Fechar",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Info Content
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = dish.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "R$ %.2f".format(dish.price).replace('.', ','),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        color = GoldBright
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = dish.description,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = TextSecondary
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Mandatory Side Dish Section
                if (dish.acompanhamentos.isNotEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "ACOMPANHAMENTO",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            letterSpacing = 0.5.sp
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(FlamePrimary.copy(alpha = 0.2f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "OBRIGATÓRIO · ESCOLHA 1",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = FlameBright
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        dish.acompanhamentos.forEach { side ->
                            val isSelected = selectedSide == side.name
                            Surface(
                                color = if (isSelected) FlamePrimary.copy(alpha = 0.1f) else BgSurface,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        1.dp,
                                        if (isSelected) FlamePrimary else BorderSubtle,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable { selectedSide = side.name }
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        RadioButton(
                                            selected = isSelected,
                                            onClick = { selectedSide = side.name },
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = FlamePrimary,
                                                unselectedColor = TextMuted
                                            )
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Column {
                                            Text(
                                                text = side.name,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.White
                                            )
                                            Text(
                                                text = side.desc,
                                                fontSize = 11.sp,
                                                color = TextMuted
                                            )
                                        }
                                    }

                                    Text(
                                        text = "Incluso",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = GoldBright
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Optional Sauces Section
                if (dish.molhos.isNotEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "MOLHO ADICIONAL",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = "OPCIONAL · ATÉ 2",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextMuted
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        dish.molhos.forEach { sauce ->
                            val isSelected = selectedSauces.contains(sauce.name)
                            Surface(
                                color = if (isSelected) FlamePrimary.copy(alpha = 0.1f) else BgSurface,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        1.dp,
                                        if (isSelected) FlamePrimary else BorderSubtle,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable {
                                        if (isSelected) {
                                            selectedSauces.remove(sauce.name)
                                        } else {
                                            if (selectedSauces.size < 2) selectedSauces.add(sauce.name)
                                        }
                                    }
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Checkbox(
                                            checked = isSelected,
                                            onCheckedChange = { checked ->
                                                if (checked) {
                                                    if (selectedSauces.size < 2) selectedSauces.add(sauce.name)
                                                } else {
                                                    selectedSauces.remove(sauce.name)
                                                }
                                            },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = FlamePrimary,
                                                uncheckedColor = TextMuted
                                            )
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = sauce.name,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.White
                                        )
                                    }

                                    Text(
                                        text = if (sauce.price > 0) "+ R$ %.2f".format(sauce.price).replace('.', ',') else "Grátis",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = GoldBright
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Kitchen Notes
                Text(
                    text = "OBSERVAÇÕES DA COZINHA",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    placeholder = {
                        Text(
                            text = "Ex: Ponto da carne, molho à parte, tirar cebola...",
                            fontSize = 12.sp,
                            color = TextMuted
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = BgSurface,
                        unfocusedContainerColor = BgSurface,
                        focusedBorderColor = FlamePrimary,
                        unfocusedBorderColor = BorderSubtle,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Bottom Footer CTA: Qty and Add Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Qty Stepper
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(BgSurface)
                            .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                            .padding(horizontal = 6.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { if (qty > 1) qty-- },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Text(text = "−", fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Text(
                            text = "$qty",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        IconButton(
                            onClick = { qty++ },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Text(text = "+", fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }

                    // Add Button
                    Button(
                        onClick = {
                            onAddToCart(selectedSide, selectedSauces.toList(), notes, qty)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = FlamePrimary),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp)
                    ) {
                        Text(
                            text = "ADICIONAR · R$ %.2f".format(totalPrice).replace('.', ','),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
