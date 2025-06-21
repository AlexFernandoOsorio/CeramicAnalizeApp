package org.tesis.ie.ceramic.ui.information

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.tesis.ie.ceramic.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationScreen (
    onBackClick : () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fuentes de información", color = Color.White) },
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

            FuenteCard(
                icon = R.drawable.logo_ministerio,
                title = "Gabinete de Elementos Muestrales y Colecciones – Coordinación de Calificaciones e Intervenciones Arqueológicas, DDC-Cusco",
                description = "Este gabinete forma parte del trabajo técnico del Ministerio de Cultura en Cusco y se encarga de conservar y estudiar materiales arqueológicos procedentes de investigaciones en la región. Su labor permite proteger y comprender el patrimonio cultural del país. En el marco de este proyecto, el gabinete proporcionó acceso a datos e imágenes de piezas cerámicas clave para el análisis desarrollado."
            )

            Spacer(modifier = Modifier.height(16.dp))

            FuenteCard(
                icon = R.drawable.logo_qhapaq,
                title = "Equipo de Trabajo Qhapaq Ñan – DDC-Cusco",
                description = "El equipo Qhapaq Ñan trabaja en la investigación, conservación y difusión del gran sistema vial andino que forma parte del patrimonio mundial. Su labor en campo y archivo aporta una visión integral del pasado incaico. Su colaboración fue esencial para esta investigación, ya que compartieron información sobre cerámica asociada a sitios del Camino Inca, contribuyendo significativamente al enfoque del estudio."
            )

            Spacer(modifier = Modifier.height(16.dp))

            FuenteCard(
                icon = R.drawable.logo_larco,
                title = "Museo Larco, Lima – Perú",
                description = "El Museo Larco es uno de los espacios culturales más reconocidos del Perú, con una destacada colección de arte precolombino. A través de sus salas y dépositos, su centro de investigación pone piezas únicas de la cerámica inca y otras culturas ancestrales. Gracias a su generoso apoyo, esta institución brindó valiosa información visual y contextual que enriqueció el desarrollo de la presente investigación."
            )
        }
    }
}

@Composable
fun FuenteCard(icon: Int, title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF4F1E9).copy(alpha = 0.9f), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color(0xFF5C6E36)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = description,
            fontSize = 13.sp,
            color = Color(0xFF000000)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    InformationScreen(
        onBackClick = {}
    )
}
