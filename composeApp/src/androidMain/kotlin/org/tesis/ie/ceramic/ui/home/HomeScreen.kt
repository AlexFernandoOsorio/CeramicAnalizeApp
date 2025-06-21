package org.tesis.ie.ceramic.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.tesis.ie.ceramic.R

data class Category(@DrawableRes val iconResId: Int, val name: String, val backgroundColor: Color)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (
    onSearchClick:()-> Unit,
    onInvestigationClick:()-> Unit,
    onInformationClick:()-> Unit,
    onListCategoriesClick:()-> Unit
) {
    Scaffold(
    topBar = {
        TopAppBar(
            title = { Text("Inicio", color = Color.White) },
            navigationIcon = {
                IconButton(onClick = { /* Handle menu click */ }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF606C38)) // Dark blue color
        )
    }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .paint(
                    painter = painterResource(id = R.drawable.background),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(
                        Color.Black.copy(alpha = 0.4f),
                        blendMode = BlendMode.Darken
                    )
                )
        ) {

            Spacer(modifier = Modifier.height(60.dp))

            val categories = listOf(
                Category(R.drawable.ic_camera, stringResource(R.string.home_title1), Color(0xFF606C38)), // Green
                Category(R.drawable.ic_categories, stringResource(R.string.home_title2), Color(0xFF606C38)), // Brown
                Category(R.drawable.ic_investigation, stringResource(R.string.home_title3), Color(0xFF606C38)), // Red
                Category(R.drawable.ic_contact, stringResource(R.string.home_title4), Color(0xFF606C38)), // Dark Red
                Category(R.drawable.ic_manual, stringResource(R.string.home_title5), Color(0xFF606C38)), // Blue
                Category(R.drawable.ic_info, stringResource(R.string.home_title6), Color(0xFF606C38)), // Purple
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(categories) { category ->
                    CategoryItem(
                        category = category,
                        onCategoryClick = {selectedCategory ->
                            if(selectedCategory.name == "Investigación"){
                                onInvestigationClick()
                            }
                            if (selectedCategory.name == "Información"){
                                onInformationClick()
                            }
                            if (selectedCategory.name == "Lista de categorías"){
                                onListCategoriesClick()
                            }
                            if(selectedCategory.name == "Reconocimiento"){
                                onSearchClick()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ActionButton(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector, containerColor: Color,modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        modifier = Modifier
            .height(48.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text, color = Color.White, fontSize = 14.sp)
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    onCategoryClick: (Category) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(IntrinsicSize.Max)
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(category.backgroundColor)
                .wrapContentSize(Alignment.Center)
                .clickable {
                    onCategoryClick(category)
                }
        ) {
            Icon(
                painter = painterResource(id = category.iconResId),
                contentDescription = category.name,
                modifier = Modifier.size(40.dp),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category.name,
            fontSize = 13.sp,
            color = Color.White,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            lineHeight = 16.sp // Adjust line height for multi-line text if needed
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    HomeScreen(
        onSearchClick = {},
        onInvestigationClick = {},
        onInformationClick = {},
        onListCategoriesClick = {}
    )
}
