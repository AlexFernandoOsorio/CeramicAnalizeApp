package org.tesis.ie.ceramic.ui.investigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.tesis.ie.ceramic.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvestigationScreen(
    onBackClick : () -> Unit,
    selected: String? = null
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Investigación", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Menu", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF606C38)) // Dark blue color
            )
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .paint(
                    painter = painterResource(id = R.drawable.background),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(
                        Color.Black.copy(alpha = 0.4f),
                        blendMode = BlendMode.Darken
                    )
                )
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            InvestigationCard(
                title = "Paper",
                description = "EVALUACIÓN Y APLICACIÓN DE REDES NEURONALES CONVOLUCIONALES EN LA CLASIFICACIÓN DE CERÁMICA INCA",
                iconRes = R.drawable.ic_paper,
                isSelected = selected == "paper"
            )

            Spacer(modifier = Modifier.height(32.dp))

            InvestigationCard(
                title = "Tesis",
                description = "EVALUACIÓN Y APLICACIÓN DE REDES NEURONALES CONVOLUCIONALES EN LA CLASIFICACIÓN DE CERÁMICA INCA",
                iconRes = R.drawable.ic_bookmark,
                isSelected = selected == "tesis"
            )
        }
    }
}

@Composable
fun InvestigationCard(title: String, description: String, iconRes: Int, isSelected: Boolean) {
    val backgroundColor = if (isSelected) Color(0xFF5C6E36) else Color(0x80FEFAE0).copy(alpha = 0.8f)
    val contentColor = if (isSelected) Color.White else Color(0xFF5C6E36)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(10.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier
                .width(120.dp)
                .height(118.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = contentColor,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                color = contentColor,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    InvestigationScreen(
        onBackClick = {}
    )
}
