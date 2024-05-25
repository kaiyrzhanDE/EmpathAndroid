//package kaiyrzhan.de.core.image_picker
//
//import android.Manifest
//import android.Manifest.permission
//import android.os.Build
//import android.util.Log
//import android.widget.Toast
//import androidx.compose.animation.animateContentSize
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.itemsIndexed
//import androidx.compose.foundation.lazy.grid.rememberLazyGridState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.BottomSheetScaffold
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.FloatingActionButton
//import androidx.compose.material3.FloatingActionButtonDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.ModalBottomSheet
//import androidx.compose.material3.SheetState
//import androidx.compose.material3.SheetValue
//import androidx.compose.material3.rememberModalBottomSheetState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.Lifecycle
//import com.google.accompanist.permissions.ExperimentalPermissionsApi
//import com.google.accompanist.permissions.rememberMultiplePermissionsState
//import kaiyrzhan.de.core.image_picker.components.CameraCard
//import kaiyrzhan.de.core.image_picker.components.ImageCard
//import kaiyrzhan.de.core.image_picker.model.ActionType
//import kaiyrzhan.de.core.image_picker.model.Image
//import kaiyrzhan.de.core.preview.Previews
//import kaiyrzhan.de.core.theme.SlateGray
//import kaiyrzhan.de.core.components.CircleLoading
//import kaiyrzhan.de.core.effects.ComposeLifecycleEffect
//import kaiyrzhan.de.core.effects.SingleActionEffect
//import kaiyrzhan.de.core.extensions.modifiers.scaleAnimation
//import kaiyrzhan.de.core.permission.PermissionDialog
//import kaiyrzhan.de.core.permission.PermissionUtils
//import kaiyrzhan.de.core.permission.PermissionUtils.findActivity
//import kaiyrzhan.de.core.permission.rememberMultiplePermissions
//import kaiyrzhan.de.core.permission.rememberPermissionState
//import kaiyrzhan.de.core.preview.PreviewTheme
//import kaiyrzhan.de.core_ui.R
//
///**
// * Composable function multiple/single picker images from gallery
// *
// * @param sheetState - The state of the bottom sheet.
// * @param isMultiplePicker Multiple/single picker mode.
// * @param maxSelectedImages The max number of images that can be selected.
// * @param onSendRequest Executes when the user clicks to send selected images.
// * @param onDismissRequest Executes when the user clicks outside of the bottom sheet.
// */
//@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
//@Composable
//fun ImagePickerBottomSheet(
//    sheetState: SheetState,
//    isMultiplePicker: Boolean,
//    maxSelectedImages: Int = 9,
//    onSendRequest: (List<Image>) -> Unit,
//    onDismissRequest: () -> Unit,
//) {
//    require(maxSelectedImages in -1..9) { "maxSelectedImages must be between 1 and 9." }
//
//    val viewModel: ImagePickerViewModel = hiltViewModel()
//    val screenState = viewModel.screenStateFlow.collectAsState()
//
//    val context = LocalContext.current
//    val lazyGridState = rememberLazyGridState()
//    // NEED REFACTOR AND STRUCTING
////    val permissionDialogState = rememberSaveable { mutableStateOf(false) }
//    val permissionsState = rememberMultiplePermissionsState(
//        permissions = PermissionUtils.mediaPermissions,
//        onPermissionsResult = { permissionsResult ->
//            permissionsResult.keys.forEach{ key ->
//                Log.d("veildc","key: $key, value: ${permissionsResult[key]}")
//            }
//
//        }
//    )
//
////    LaunchedEffect(permissionsState) {
////        if (!permissionsState.allPermissionsGranted) {
////            if (permissionsState.shouldShowRationale) {
////                permissionDialogState.value = true
////            } else {
////                permissionsState.launchMultiplePermissionRequest()
////            }
////        } else {
////            viewModel.getImages(context, isMultiplePicker, maxSelectedImages)
////        }
////    }
//
//    ComposeLifecycleEffect { _, event ->
//        when (event) {
//            Lifecycle.Event.ON_CREATE -> {
//                viewModel.getImages(
//                    context = context,
//                    isMultiplePicker = isMultiplePicker,
//                    maxSelectedImages = maxSelectedImages,
//                    hasCameraPermission = permissionsState.allPermissionsGranted,
//                    hasImagePermission = permissionsState.allPermissionsGranted,
//                )
//            }
//            else -> Unit
//        }
//    }
//
//    SingleActionEffect(sideEffectFlow = viewModel.actionsFlow) { action ->
//        when (action) {
//            is ImagePickerAction.OnSendRequest -> onSendRequest(action.selectedImages)
//        }
//    }
//
//    ModalBottomSheet(
//        modifier = Modifier
//            .fillMaxSize()
//            .animateContentSize(),
//        sheetState = sheetState,
//        onDismissRequest = onDismissRequest,
//        dragHandle = null,
//        shape = RoundedCornerShape(
//            topStart = if (sheetState.currentValue == SheetValue.PartiallyExpanded) 10.dp else 0.dp,
//            topEnd = if (sheetState.currentValue == SheetValue.PartiallyExpanded) 10.dp else 0.dp
//        )
//    ) {
//        Box(
//            modifier = Modifier
//                .padding(top = 5.dp)
//                .width(40.dp)
//                .height(3.dp)
//                .background(color = SlateGray, shape = RoundedCornerShape(10.dp))
//                .align(Alignment.CenterHorizontally)
//        )
//
////        if (permissionDialogState.value) {
////            PermissionDialog(
////                iconsWithDescriptions = listOf(
////                    R.drawable.ic_file_text to R.string.document,
////                    R.drawable.ic_file_audio to R.string.audio_file,
////                    R.drawable.ic_file_video to R.string.video_file,
////                    R.drawable.ic_file to R.string.file,
////                    R.drawable.ic_folder to R.string.folder,
////                ),
////                description = stringResource(id = R.string.description_for_files_permission),
////                onDismissRequest = { permissionDialogState.value = false },
////                onAcceptRequest = { PermissionUtils.openApplicationSettings(context) },
////            )
////        }
//
//        when (val state = screenState.value) {
//            is ImagePickerState.None -> {}
//            is ImagePickerState.Loading -> {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(20.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircleLoading(
//                        isVisible = true,
//                        modifier = Modifier.size(60.dp)
//                    )
//                }
//            }
//
//            is ImagePickerState.ImagePicker -> {
//                Box(
//                    modifier = Modifier
//                        .width(40.dp)
//                        .padding(top = 5.dp)
//                        .align(Alignment.CenterHorizontally)
//                )
//                Box {
//                    LazyVerticalGrid(
//                        modifier = Modifier
//                            .padding(5.dp)
//                            .fillMaxWidth(),
//                        columns = GridCells.Fixed(3),
//                        state = lazyGridState,
//                        verticalArrangement = Arrangement.spacedBy(5.dp),
//                        horizontalArrangement = Arrangement.spacedBy(5.dp),
//                    ) {
//                        itemsIndexed(
//                            items = state.images,
//                            key = { _, image -> image.id },
//                        ) { index, image ->
//                            when (image.actionType) {
//                                ActionType.CAMERA -> {
//                                    CameraCard(
//                                        onCameraClicked = {
//                                            //TODO take photo and make it selected
//                                        }
//                                    )
//                                }
//
//                                ActionType.IMAGE -> {
//                                    ImageCard(
//                                        index = index,
//                                        image = image,
//                                        isMultiplePicker = isMultiplePicker,
//                                        onImageSelected = {
//                                            viewModel.onEvent(
//                                                ImagePickerEvent.OnImageSelected(image)
//                                            )
//                                        }
//                                    )
//                                }
//
//                                ActionType.IMAGE_PERMISSION -> {
//                                    //TODO camera permission card and image permission card
//                                }
//
//                                ActionType.CAMERA_PERMISSION -> {
//                                    //TODO camera permission card and image permission card
//                                }
//                            }
//                        }
//                    }
//                    FloatingActionButton(
//                        modifier = Modifier
//                            .align(Alignment.BottomEnd)
//                            .padding(20.dp)
//                            .scaleAnimation(
//                                isVisible = state.selectedImagesCount > 0,
//                                from = 0f,
//                                to = 1f
//                            ),
//                        shape = RoundedCornerShape(100.dp),
//                        elevation = FloatingActionButtonDefaults.elevation(10.dp),
//                        onClick = { viewModel.onEvent(ImagePickerEvent.OnSendClicked) }
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.ic_send),
//                            contentDescription = stringResource(id = R.string.send),
//                            tint = MaterialTheme.colorScheme.background,
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Previews
//@Composable
//private fun Preview() {
//    PreviewTheme {
//        ImagePickerBottomSheet(
//            sheetState = rememberModalBottomSheetState(),
//            isMultiplePicker = false,
//            onDismissRequest = {},
//            onSendRequest = {},
//            maxSelectedImages = 9,
//        )
//    }
//}
//
//
