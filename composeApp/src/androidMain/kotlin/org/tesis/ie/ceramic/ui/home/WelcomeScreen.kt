package org.tesis.ie.ceramic.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.tesis.ie.ceramic.R

@Composable
fun WelcomeScreen(
    onHomeClick:()-> Unit
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(
                Color.Black.copy(alpha = 0.4f),
                blendMode = BlendMode.Darken
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "AyniNet Logo",
                modifier = Modifier
                    .width(250.dp)
                    .height(150.dp)
                    .padding(vertical = 16.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 32.dp) // Inner padding from screen edges
                    .clip(RoundedCornerShape(12.dp)), // Slightly rounded corners for the card
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.1f)) // Semi-transparent white background
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.welcome_title1),
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.welcome_title2),
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.welcome_title3),
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.welcome_title4),
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onHomeClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF606C38))
            ) {
                Text(
                    text = "Comenzar",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
