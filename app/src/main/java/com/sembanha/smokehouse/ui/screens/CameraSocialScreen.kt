package com.sembanha.smokehouse.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.sembanha.smokehouse.ui.theme.*
import com.sembanha.smokehouse.viewmodel.RestaurantViewModel

@Composable
fun CameraSocialScreen(
    viewModel: RestaurantViewModel,
    onNavigateToPassport: () -> Unit
) {
    var selectedFrame by remember { mutableStateOf("fogo-brasa") }
    var isPhotoCaptured by remember { mutableStateOf(false) }

    val frames = listOf(
        "fogo-brasa" to "🔥 Fogo & Brasa",
        "vintage-steak" to "🥩 Vintage Steak",
        "noite-epica" to "🍻 Noite Épica",
        "gold" to "⭐ Mestre Pitmaster"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 90.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Status Top Banner
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = FlamePrimary.copy(alpha = 0.15f)),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, FlamePrimary, RoundedCornerShape(12.dp))
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "CONTA PAGA · MESA 14 • 8º SELO 8/8",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    color = FlameBright
                )
                Text(
                    text = "Registre a mesa e libere seu brinde lendário!",
                    fontSize = 11.sp,
                    color = TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Viewfinder Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .border(
                    3.dp,
                    if (selectedFrame == "fogo-brasa") FlamePrimary else GoldPrimary,
                    RoundedCornerShape(16.dp)
                )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Viewfinder image
                AsyncImage(
                    model = "file:///android_asset/images/toast-friends.jpg",
                    contentDescription = "Mesa 14 Câmera",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Top Badge Overlay
                Box(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopStart)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Black.copy(alpha = 0.75f))
                        .border(1.dp, GoldBright, RoundedCornerShape(20.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = when (selectedFrame) {
                            "fogo-brasa" -> "🔥 FOGO & BRASA OFICIAL"
                            "vintage-steak" -> "🥩 VINTAGE STEAKHOUSE"
                            "noite-epica" -> "🍻 NOITE ÉPICA DE RIBS"
                            else -> "⭐ 8º SELO PITMASTER"
                        },
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black,
                        color = GoldBright
                    )
                }

                // Bottom Info Overlay
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(Color.Black.copy(alpha = 0.8f))
                        .padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Mesa 14 · Noite Épica na Brasa",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "Salão Principal • Sem-Banha BBQ",
                                fontSize = 10.sp,
                                color = GoldBright
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(FlamePrimary)
                                .border(1.dp, GoldBright, RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "8º SELO",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Shutter Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.showToast("Temporizador de 3s ativado!") },
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(BgCard)
            ) {
                Text(text = "⏱", fontSize = 16.sp)
            }

            // Big Shutter Button
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(4.dp, FlamePrimary, CircleShape)
                    .clickable {
                        isPhotoCaptured = true
                        viewModel.showToast("📸 Foto capturada!")
                    },
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(FlamePrimary)
                )
            }

            IconButton(
                onClick = { viewModel.showToast("Alternando câmera!") },
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(BgCard)
            ) {
                Text(text = "🔄", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Frame Filters Selector
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(frames) { (key, label) ->
                val isSelected = selectedFrame == key
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isSelected) FlamePrimary.copy(alpha = 0.25f) else BgCard)
                        .border(
                            1.dp,
                            if (isSelected) FlamePrimary else BorderSubtle,
                            RoundedCornerShape(8.dp)
                        )
                        .clickable { selectedFrame = key }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else TextSecondary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Action Buttons
        Button(
            onClick = {
                viewModel.saveCapturedPhoto(
                    title = "Noite Épica de Ribs & Chopp",
                    tag = "#Mesa14 #8Selos"
                )
                onNavigateToPassport()
            },
            colors = ButtonDefaults.buttonColors(containerColor = FlamePrimary),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = "Salvar Foto & Carimbar Selo Final",
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        TextButton(
            onClick = {
                val lastIndex = viewModel.stamps.indexOfLast { it.number == 8 }
                if (lastIndex >= 0) {
                    viewModel.stamps[lastIndex] = viewModel.stamps[lastIndex].copy(completed = true)
                }
                viewModel.showToast("8º Selo carimbado!")
                onNavigateToPassport()
            }
        ) {
            Text(
                text = "Carimbar sem foto (Continuar)",
                fontSize = 12.sp,
                color = TextMuted
            )
        }
    }
}
