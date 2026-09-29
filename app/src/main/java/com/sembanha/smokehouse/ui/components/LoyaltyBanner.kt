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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sembanha.smokehouse.ui.theme.*

@Composable
fun LoyaltyBanner(
    currentStamps: Int,
    totalStamps: Int = 8,
    onSeeRulesClick: () -> Unit
) {
    val progress = (currentStamps.toFloat() / totalStamps.toFloat()).coerceIn(0f, 1f)

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                GoldPrimary.copy(alpha = 0.4f),
                RoundedCornerShape(16.dp)
            )
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFF2D1A10), Color(0xFF1A130F))
                ),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(FlamePrimary.copy(alpha = 0.25f))
                        .border(1.dp, FlamePrimary, RoundedCornerShape(20.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "🔥 ESPECIAL DO FOGO",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black,
                        color = FlameBright,
                        letterSpacing = 0.5.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(GoldPrimary.copy(alpha = 0.15f))
                        .border(1.dp, GoldPrimary.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "$currentStamps / $totalStamps Selos",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldBright
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (currentStamps >= totalStamps) "Parabéns! Brinde Lendário Liberado!" else "Falta apenas ${totalStamps - currentStamps} selo para seu brinde!",
                fontSize = 16.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Fotografe seu brinde na mesa, compartilhe pós-conta e destrave uma Porção de Torresmo ou Pudim de Leite na Brasa cortesia da casa.",
                fontSize = 12.sp,
                lineHeight = 16.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = FlamePrimary,
                trackColor = Color.Black.copy(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${(progress * 100).toInt()}% Concluído • Quase Mestre",
                    fontSize = 11.sp,
                    color = TextMuted
                )

                Text(
                    text = "Ver Passaporte >",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = GoldBright,
                    modifier = Modifier.clickable { onSeeRulesClick() }
                )
            }
        }
    }
}
