package com.sembanha.smokehouse.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sembanha.smokehouse.ui.theme.*
import com.sembanha.smokehouse.viewmodel.RestaurantViewModel

@Composable
fun PassportScreen(
    viewModel: RestaurantViewModel
) {
    val context = LocalContext.current
    val couponCode = "#SEMBANHA-8SELOS-2025"

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Passport Pitmaster Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, GoldPrimary, RoundedCornerShape(16.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF2E1A0D), Color(0xFF140E0A))
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(GoldPrimary)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "⭐ PASSAPORTE COMPLETO",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            color = BgDarkest
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Parabéns, Pitmaster!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Você carimbou suas 8 visitas lendárias na brasa viva. Temporada 2025/2026: Nível Mestre Churrasqueiro.",
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // 8 Stamps in 4x2 Grid
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        viewModel.stamps.chunked(4).forEach { rowStamps ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                rowStamps.forEach { stamp ->
                                    val isDone = stamp.completed
                                    Surface(
                                        color = if (isDone) GoldPrimary.copy(alpha = 0.15f) else Color.Black.copy(alpha = 0.4f),
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier
                                            .weight(1f)
                                            .border(
                                                1.dp,
                                                if (isDone) GoldPrimary else BorderHighlight,
                                                RoundedCornerShape(10.dp)
                                            )
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(28.dp)
                                                    .clip(CircleShape)
                                                    .background(if (isDone) FlamePrimary else BgCard),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = if (isDone) "🔥" else "${stamp.number}",
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.White
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = stamp.date,
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.White
                                            )
                                            Text(
                                                text = stamp.table,
                                                fontSize = 8.sp,
                                                color = TextMuted
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Unlocked Reward Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = BgCard),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, GoldPrimary, RoundedCornerShape(14.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "BRINDE DESBLOQUEADO",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black,
                        color = GoldBright,
                        letterSpacing = 0.5.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Pudim de Leite Defumado na Brasa ou Tábua de Petiscos",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Pudim artesanal assado na lenha com calda de caramelo âmbar e raspas de limão caipira. Válido por 30 dias na sua próxima visita.",
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Coupon display box
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(BgSurface)
                            .border(1.dp, GoldPrimary, RoundedCornerShape(8.dp))
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "CÓDIGO DO CUPOM", fontSize = 9.sp, color = TextMuted, fontWeight = FontWeight.Bold)
                            Text(text = couponCode, fontSize = 13.sp, fontWeight = FontWeight.Black, color = GoldBright)
                        }

                        Button(
                            onClick = {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                clipboard.setPrimaryClip(ClipData.newPlainText("Cupom", couponCode))
                                viewModel.showToast("Cupom copiado com sucesso!")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary),
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                            modifier = Modifier.height(32.dp)
                        ) {
                            Text(text = "Copiar", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BgDarkest)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { viewModel.showToast("Garçom Lucas avisado para liberar seu brinde!") },
                            colors = ButtonDefaults.buttonColors(containerColor = FlamePrimary),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                        ) {
                            Text(text = "🍽 Resgatar com Garçom", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }

                        OutlinedButton(
                            onClick = { viewModel.showToast("Compartilhado com sucesso!") },
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextSecondary),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = androidx.compose.ui.graphics.SolidColor(BorderHighlight)
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.height(44.dp)
                        ) {
                            Text(text = "Compartilhar", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }

        // Mural das 8 Mesas Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Mural das 8 Mesas",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Momentos no braseiro",
                    fontSize = 11.sp,
                    color = TextMuted
                )
            }
        }

        // Mural Grid Items
        items(viewModel.mural) { memory ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = BgCard),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Black)
                    ) {
                        AsyncImage(
                            model = memory.imageAsset,
                            contentDescription = memory.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = memory.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = memory.subtitle,
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                        Text(
                            text = "${memory.date} • ${memory.tag}",
                            fontSize = 10.sp,
                            color = GoldBright
                        )
                    }
                }
            }
        }
    }
}
