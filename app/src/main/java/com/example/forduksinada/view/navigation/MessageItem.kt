package com.example.forduksinada.view.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forduksinada.utils.playWritesFonts

@Composable
fun MessageItem(text: String, color: Color, background: Color) {
    Box(
        modifier = Modifier
            .padding(top = 50.dp)
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .blur(radius = 10.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
        ) {
            Box(
                modifier = Modifier
                    .background(background, shape = RoundedCornerShape(30.dp))
                    .matchParentSize()

            ) {}
        }
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 35.sp,
            fontFamily = playWritesFonts,
            text = text,
            color = color,
            textAlign = TextAlign.Center
        )

    }
}
