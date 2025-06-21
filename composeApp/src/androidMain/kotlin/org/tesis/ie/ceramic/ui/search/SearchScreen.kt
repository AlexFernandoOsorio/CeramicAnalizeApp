package org.tesis.ie.ceramic.ui.search

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.tesis.ie.ceramic.data.TFLITECeramicModel
import org.tesis.ie.ceramic.domain.models.Classification

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onBackClick : () -> Unit
) {
    val currentContext = LocalContext.current
    val applicationContext = currentContext.applicationContext

    // 1. Estado para saber si tenemos el permiso
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                currentContext,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    // 2. Prepara el lanzador para la solicitud de permiso
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasCameraPermission = isGranted
            if (!isGranted) {
                // Opcional: Manejar el caso donde el permiso es denegado
                // Mostrar un mensaje al usuario, deshabilitar la funcionalidad, etc.
                // Por ejemplo, podrías mostrar un SnackBar o un diálogo.
            }
        }
    )

    // 3. (Opcional) Lanzar la solicitud de permiso cuando el composable entra en la composición
    //    si aún no se ha concedido. Esto es útil si la funcionalidad principal requiere el permiso.
    //    Si prefieres solicitarlo al hacer clic en un botón, puedes omitir este LaunchedEffect
    //    y llamar a permissionLauncher.launch() desde el onClick de un botón.
    LaunchedEffect(key1 = hasCameraPermission) { // Se relanza si hasCameraPermission cambia
        if (!hasCameraPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
    Scaffold(
    topBar = {
        TopAppBar(
            title = { Text("Clasificación ", color = Color.White) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Menu", tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF606C38)) // Dark blue color
        )
    }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            var classifications by remember {
                mutableStateOf(emptyList<Classification>())
            }
            val analyzer = remember {
                CeramicImageAnalyzer(
                    classifier = TFLITECeramicModel(
                        context = applicationContext
                    ),
                    onResults = {
                        classifications = it
                    }
                )
            }
            val controller = remember {
                LifecycleCameraController(applicationContext).apply {
                    setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
                    setImageAnalysisAnalyzer(
                        ContextCompat.getMainExecutor(applicationContext),
                        analyzer
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CameraPreview(controller, Modifier.fillMaxSize())

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                ) {
                    classifications.forEach {
                        Text(
                            text = it.name + " " + it.score*100,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .padding(8.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}
