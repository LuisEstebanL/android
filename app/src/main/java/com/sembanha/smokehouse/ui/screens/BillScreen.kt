package com.sembanha.smokehouse.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
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
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sembanha.smokehouse.data.PaymentMethod
import com.sembanha.smokehouse.ui.theme.*
import com.sembanha.smokehouse.viewmodel.RestaurantViewModel

@Composable
fun BillScreen(
    viewModel: RestaurantViewModel,
    onNavigateToCamera: () -> Unit
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Step 8 Promo Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2A150D)),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, FlamePrimary, RoundedCornerShape(14.dp))
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(FlamePrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "📸", fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "PASSO FINAL · FOTO DA MESA",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Black,
                            color = FlameBright,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = "Ganhe o 8º Selo & Brinde da Casa!",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Pague agora para destravar a Câmera Social e registre a foto com a galera para liberar sua cortesia!",
                            fontSize = 11.sp,
                            lineHeight = 15.sp,
                            color = TextSecondary
                        )
                    }
                }
            }
        }

        // Comanda Table 14 Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = BgCard),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Comanda da Mesa 14",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "Comanda Digital #4920 · Garçom Lucas",
                                fontSize = 11.sp,
                                color = TextMuted
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(SuccessGreen.copy(alpha = 0.15f))
                                .border(1.dp, SuccessGreen.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "ATIVA",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Black,
                                color = SuccessGreen
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Ordered Items List
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        viewModel.orders.forEach { item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(BgSurface)
                                    .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "${item.qty}x ${item.name}",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = item.tag,
                                        fontSize = 11.sp,
                                        color = TextMuted
                                    )
                                    Text(
                                        text = "🕒 ${item.deliveredTime}",
                                        fontSize = 10.sp,
                                        color = GoldBright
                                    )
                                }

                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        text = "R$ %.2f".format(item.totalPrice).replace('.', ','),
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Row(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(BgDarkest)
                                            .border(1.dp, BorderSubtle, RoundedCornerShape(6.dp)),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(24.dp)
                                                .clickable { viewModel.decrementQty(item.id) },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(text = "−", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                        }
                                        Text(
                                            text = "${item.qty}",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            modifier = Modifier.padding(horizontal = 6.dp)
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(24.dp)
                                                .clickable { viewModel.incrementQty(item.id) },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(text = "+", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Bill Splitting Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = BgCard),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "👥 Divisão da Conta",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items((1..6).toList()) { count ->
                            val isSelected = viewModel.splitCount == count
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (isSelected) FlamePrimary.copy(alpha = 0.2f) else BgSurface)
                                    .border(
                                        1.dp,
                                        if (isSelected) FlamePrimary else BorderSubtle,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable { viewModel.splitCount = count }
                                    .padding(horizontal = 14.dp, vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (count == 1) "1 Pessoa" else "$count Pessoas",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) FlameBright else TextSecondary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(BgSurface)
                            .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Valor estimado por pessoa:",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                        Text(
                            text = "R$ %.2f".format(viewModel.perPersonTotal).replace('.', ','),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            color = GoldBright
                        )
                    }
                }
            }
        }

        // Waiter Service Fee (Tip)
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Taxa de Serviço do Garçom",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "❤ Repasse 100% à equipe",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SuccessGreen
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf(
                        0 to "Sem taxa",
                        10 to "10%",
                        12 to "12% Padrão"
                    ).forEach { (tip, label) ->
                        val isSelected = viewModel.tipPercent == tip
                        val tipVal = viewModel.subtotal * (tip / 100.0)
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) GoldPrimary.copy(alpha = 0.15f) else BgCard
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .border(
                                    1.dp,
                                    if (isSelected) GoldPrimary else BorderSubtle,
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable { viewModel.tipPercent = tip }
                        ) {
                            Column(
                                modifier = Modifier.padding(vertical = 10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) GoldBright else TextSecondary
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = if (tip == 0) "R$ 0,00" else "+ R$ %.2f".format(tipVal).replace('.', ','),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }

        // Financial Summary Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = BgCard),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Subtotal dos Itens", fontSize = 13.sp, color = TextSecondary)
                        Text(text = "R$ %.2f".format(viewModel.subtotal).replace('.', ','), fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Taxa de Serviço (${viewModel.tipPercent}%)", fontSize = 13.sp, color = TextSecondary)
                        Text(text = "R$ %.2f".format(viewModel.tipAmount).replace('.', ','), fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }

                    if (viewModel.paymentMethod == PaymentMethod.PIX) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Desconto PIX (5% OFF)", fontSize = 13.sp, color = SuccessGreen)
                            Text(text = "- R$ %.2f".format(viewModel.discountAmount).replace('.', ','), fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SuccessGreen)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(color = BorderSubtle, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column {
                            Text(text = "TOTAL A PAGAR", fontSize = 11.sp, fontWeight = FontWeight.Black, color = Color.White)
                            Text(text = "MESA 14 LIVRE DE ESPERA", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = GoldBright)
                        }
                        Text(
                            text = "R$ %.2f".format(viewModel.finalTotal).replace('.', ','),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Black,
                            color = GoldBright
                        )
                    }
                }
            }
        }

        // Payment Methods Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = BgCard),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "Formas de Pagamento",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    PaymentMethod.values().forEach { method ->
                        val isSelected = viewModel.paymentMethod == method
                        Surface(
                            color = if (isSelected) FlamePrimary.copy(alpha = 0.1f) else BgSurface,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .border(
                                    1.dp,
                                    if (isSelected) FlamePrimary else BorderSubtle,
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable { viewModel.paymentMethod = method }
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = isSelected,
                                        onClick = { viewModel.paymentMethod = method },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = FlamePrimary,
                                            unselectedColor = TextMuted
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = method.label,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.White
                                            )
                                            if (method == PaymentMethod.PIX) {
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Box(
                                                    modifier = Modifier
                                                        .clip(RoundedCornerShape(10.dp))
                                                        .background(SuccessGreen.copy(alpha = 0.2f))
                                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                                ) {
                                                    Text(
                                                        text = "5% OFF",
                                                        fontSize = 9.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = SuccessGreen
                                                    )
                                                }
                                            }
                                        }
                                        Text(
                                            text = when (method) {
                                                PaymentMethod.PIX -> "QR Code na tela & Copia e Cola"
                                                PaymentMethod.CREDIT_CARD -> "Maquininha sem fio na mesa"
                                                PaymentMethod.DIGITAL_WALLET -> "Apple Pay • Google Pay"
                                            },
                                            fontSize = 10.sp,
                                            color = TextMuted
                                        )
                                    }
                                }

                                Icon(
                                    imageVector = when (method) {
                                        PaymentMethod.PIX -> Icons.Default.ElectricBolt
                                        PaymentMethod.CREDIT_CARD -> Icons.Default.CreditCard
                                        PaymentMethod.DIGITAL_WALLET -> Icons.Default.PhoneAndroid
                                    },
                                    contentDescription = null,
                                    tint = if (isSelected) FlamePrimary else TextMuted
                                )
                            }
                        }
                    }

                    // Expanded PIX options
                    if (viewModel.paymentMethod == PaymentMethod.PIX) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(BgDarkest)
                                .border(1.dp, FlamePrimary, RoundedCornerShape(8.dp))
                                .padding(12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "00020126580014BR.GOV.BCB.PIX0136sembanha-smokehouse-mesa14@bbq.com.br",
                                    fontSize = 10.sp,
                                    color = GoldBright,
                                    maxLines = 1
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedButton(
                                    onClick = {
                                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                        clipboard.setPrimaryClip(ClipData.newPlainText("PIX", "sembanha-smokehouse-mesa14@bbq.com.br"))
                                        viewModel.showToast("Chave PIX copiada!")
                                    },
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldBright),
                                    border = ButtonDefaults.outlinedButtonBorder.copy(
                                        brush = androidx.compose.ui.graphics.SolidColor(GoldPrimary)
                                    ),
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(text = "Copiar Chave PIX", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }
        }

        // Checkout Button CTA
        item {
            Button(
                onClick = {
                    viewModel.processPayment()
                    onNavigateToCamera()
                },
                colors = ButtonDefaults.buttonColors(containerColor = FlamePrimary),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Pagar R$ %.2f & Ativar Câmera".format(viewModel.finalTotal).replace('.', ','),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Segurança",
                            tint = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.size(11.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Pagamento Criptografado • Mesa 14 Liberada",
                            fontSize = 10.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }
    }
}
