package org.tesis.ie.ceramic.ui.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.tesis.ie.ceramic.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCategoriesScreen(
    onBackClick : () -> Unit
){
    val categorias = listOf(
        CategoriaData(R.drawable.categoria_jarra, "A - Jarra", "Jarra con cuello corto y asa lateral", false),
        CategoriaData(R.drawable.categoria_cantaro, "B - Cántaro", "Cántaro de base plana", true),
        CategoriaData(R.drawable.categoria_olla, "C - Olla con soporte", "Olla con soporte pedestal", false),
        CategoriaData(R.drawable.categoria_vaso, "D - Vaso con borde simple", "Vaso de borde expandido", false),
        CategoriaData(R.drawable.categoria_fusayola, "E - Fusayola con borde simple", "Vaso de borde expandido", false)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Categorías", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF606C38)) // Dark blue color
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp)
                .paint(
                    painter = painterResource(id = R.drawable.background),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(
                        Color.Black.copy(alpha = 0.4f),
                        blendMode = BlendMode.Darken
                    )
                )
        ) {
            items(categorias) { categoria ->
                CategoriaItem(
                    imageRes = categoria.imageRes,
                    title = categoria.title,
                    description = categoria.description,
                    isHighlighted = categoria.isHighlighted
                )
            }
        }
    }
}

@Composable
fun CategoriaItem(imageRes: Int, title: String, description: String, isHighlighted: Boolean) {
    val backgroundColor = if (isHighlighted) Color(0xFF5C6E36) else Color(0xFFF4F1E9)
    val textColor = if (isHighlighted) Color.White else Color(0xFF5C6E36)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .padding(12.dp),
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 16.dp)
                .width(100.dp)
                .height(120.dp)

        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = textColor
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (description.isNotEmpty()) {
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = textColor
                )
            }
        }
    }
}

data class CategoriaData(
    val imageRes: Int,
    val title: String,
    val description: String,
    val isHighlighted: Boolean
)