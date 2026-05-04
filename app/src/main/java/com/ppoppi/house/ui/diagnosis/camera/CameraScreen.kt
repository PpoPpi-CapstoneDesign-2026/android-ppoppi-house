package com.ppoppi.house.ui.diagnosis.camera

import android.Manifest
import android.app.Activity
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ppoppi.house.R
import com.ppoppi.house.ui.diagnosis.camera.component.CameraGridOverlay
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray300
import com.ppoppi.house.ui.theme.Gray400
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.White
import com.ppoppi.house.ui.util.getStyledText
import com.ppoppi.house.ui.util.noRippleClickable
import com.yalantis.ucrop.UCrop
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    onBackClick: () -> Unit,
    onCaptured: (Uri) -> Unit,
    name: String,
) {
    val context = LocalContext.current
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)
    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }

    val cropLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { UCrop.getOutput(it) }?.let(onCaptured)
            }
        }
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri ?: return@rememberLauncherForActivityResult
            val destFile =
                File(context.cacheDir, "images")
                    .also { it.mkdirs() }
                    .let { File(it, "cropped_${System.currentTimeMillis()}.jpg") }
            cropLauncher.launch(
                UCrop
                    .of(uri, Uri.fromFile(destFile))
                    .withAspectRatio(1f, 1f)
                    .getIntent(context),
            )
        }

    Scaffold(
        topBar = { CameraTopAppBar(onBackClick) },
        containerColor = White,
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 22.dp),
        ) {
            Text(
                text =
                    stringResource(R.string.diagnosis_camera_hint, name).getStyledText(
                        style = PpoPpiTheme.typography.title2,
                        stringResource(R.string.diagnosis_camera_hint_highlight),
                    ),
                style = PpoPpiTheme.typography.body2,
                color = Black,
                modifier = Modifier.padding(top = 20.dp),
            )
            CameraView(
                modifier =
                    Modifier
                        .padding(top = 26.dp)
                        .padding(horizontal = 14.dp)
                        .clip(RoundedCornerShape(13.dp))
                        .background(color = Gray400, shape = RoundedCornerShape(13.dp)),
                cameraPermission = cameraPermission,
                onImageCaptureReady = { imageCapture = it },
            )
            Text(
                text = stringResource(R.string.diagnosis_camera_description),
                style = PpoPpiTheme.typography.body3,
                color = Gray300,
                modifier = Modifier.padding(top = 38.dp, start = 14.dp),
            )
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = 40.dp),
            ) {
                Box(
                    modifier =
                        Modifier
                            .size(60.dp)
                            .align(Alignment.BottomCenter)
                            .noRippleClickable(
                                onClick = {
                                    takePhoto(
                                        context = context,
                                        imageCapture = imageCapture,
                                        onSuccess = { file ->
                                            val uri =
                                                FileProvider.getUriForFile(
                                                    context,
                                                    "${context.packageName}.fileprovider",
                                                    file,
                                                )
                                            onCaptured(uri)
                                        },
                                        onError = {},
                                    )
                                },
                            ).border(
                                width = 1.dp,
                                color = Primary400,
                                shape = RoundedCornerShape(100.dp),
                            ),
                ) {
                    Box(
                        modifier =
                            Modifier
                                .size(50.dp)
                                .align(Alignment.Center)
                                .clip(CircleShape)
                                .background(Primary400),
                    )
                }

                IconButton(
                    onClick = { galleryLauncher.launch("image/*") },
                    modifier =
                        Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 14.dp)
                            .size(48.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.PhotoLibrary,
                        contentDescription = null,
                        tint = Primary400,
                        modifier = Modifier.size(32.dp),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraView(
    cameraPermission: PermissionState,
    onImageCaptureReady: (ImageCapture) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .aspectRatio(1f),
    ) {
        when {
            cameraPermission.status.isGranted -> {
                CameraPreview(
                    modifier = Modifier.fillMaxSize(),
                    onImageCaptureReady = onImageCaptureReady,
                )
                CameraGridOverlay(modifier = Modifier.fillMaxSize())
            }

            else -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Button(onClick = { cameraPermission.launchPermissionRequest() }) {
                        Text("카메라 권한 허용")
                    }
                }
            }
        }
    }
}

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    onImageCaptureReady: (ImageCapture) -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val previewView =
        remember {
            PreviewView(context).apply {
                scaleType = PreviewView.ScaleType.FILL_CENTER
            }
        }

    LaunchedEffect(Unit) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview =
                androidx.camera.core.Preview.Builder().build().also {
                    it.surfaceProvider = previewView.surfaceProvider
                }

            val imageCapture =
                ImageCapture
                    .Builder()
                    .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                    .build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview,
                    imageCapture,
                )
                onImageCaptureReady(imageCapture)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(context))
    }

    AndroidView(
        factory = { previewView },
        modifier = modifier,
    )
}

@Composable
@Preview(showBackground = true)
private fun CameraScreenPreview() {
    PpoPpiTheme {
        CameraScreen(onBackClick = {}, onCaptured = {}, name = "뽀삐")
    }
}

fun takePhoto(
    context: Context,
    imageCapture: ImageCapture?,
    onSuccess: (File) -> Unit,
    onError: (String) -> Unit,
) {
    imageCapture ?: run {
        onError("카메라가 준비되지 않았습니다")
        return
    }

    val imagesDir = File(context.cacheDir, "images").also { it.mkdirs() }
    val photoFile =
        File(
            imagesDir,
            SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.KOREA)
                .format(System.currentTimeMillis()) + ".jpg",
        )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                onSuccess(photoFile)
            }

            override fun onError(exception: ImageCaptureException) {
                onError(exception.message ?: "알 수 없는 오류")
            }
        },
    )
}
