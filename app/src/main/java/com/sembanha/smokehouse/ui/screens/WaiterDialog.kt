package com.sembanha.smokehouse.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sembanha.smokehouse.ui.theme.*

@Composable
fun WaiterDialog(
    onDismiss: () -> Unit,
    onSelectReason: (String) -> Unit
) {
    val reasons = listOf(
        "🧊 Mais Gelo e Copos",
        "🍴 Pratos / Talheres Limpos",
        "🔥 Dúvidas sobre Cortes ou Preparo",
        "💳 Trazer Maquininha de Cartão"
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = BgCard,
        shape = RoundedCornerShape(16.dp),
        title = {
            Text(
                text = "Chamar Garçom",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Mesa 14 · Atendimento pelo Garçom Lucas. Como podemos ajudar?",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                reasons.forEach { reason ->
                    Surface(
                        color = BgSurface,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                            .clickable { onSelectReason(reason) }
                    ) {
                        Text(
                            text = reason,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancelar", color = TextMuted)
            }
        }
    )
}
