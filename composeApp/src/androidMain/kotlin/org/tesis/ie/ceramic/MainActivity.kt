package org.tesis.ie.ceramic

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.tesis.ie.ceramic.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val navHostController = rememberNavController()
                NavHost(navHostController,startDestination = AppNavGraph.Dest.Root.route){
                    listOf(
                        AppNavGraph,
                    ).forEach{
                        it.build(
                            navHostController = navHostController,
                            navGraphBuilder = this
                        )
                    }
                }
            }
        }
        /*if(!hasCameraPermission()) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA), 0
            )
        }
        setContent {
            MaterialTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Mi App") }
                        )
                    },
                    content = { paddingValues ->
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
                                            text = it.name,
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
                )
            }
        }*/
    }

    private fun hasCameraPermission() = ContextCompat.checkSelfPermission(
        this, Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}