package kaiyrzhan.de.auth.registration.optional_info

import android.graphics.Path.Op
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kaiyrzhan.de.auth.components.CustomTextField
import kaiyrzhan.de.auth.registration.optional_info.model.OptionalState
import kaiyrzhan.de.core.components.ToolbarText
import kaiyrzhan.de.core.image_picker.model.Image
import kaiyrzhan.de.core.preview.PreviewTheme
import kaiyrzhan.de.core.preview.Previews
import kaiyrzhan.de.core.theme.Gradient
import kaiyrzhan.de.core.theme.Mustard
import kaiyrzhan.de.feature_auth.R
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionalContent(component: OptionalComponent) {
    val screenState = component.screenStateFlow.collectAsState()
    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = component::onUserImageSelected
    )

    fun launchPhotoPicker() {
        singlePhotoPicker.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }

    val scrollState = rememberScrollState()
    when (val state = screenState.value) {
        is OptionalState.None -> Unit
        is OptionalState.Loading -> Unit
        is OptionalState.CreateAccount -> {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { ToolbarText(text = stringResource(id = R.string.optional)) },
                        actions = {
                            IconButton(onClick = component::onSkipClicked) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_skip_next),
                                    contentDescription = stringResource(id = R.string.skip),
                                    tint = Color.Unspecified,
                                )
                            }
                        }
                    )
                },
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(paddingValues)
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    UserImage(
                        image = state.image ?: Uri.EMPTY,
                        onSelectImageClicked = ::launchPhotoPicker
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.option_info_description),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TitledTextField(
                        title = stringResource(id = R.string.first_name),
                        value = state.firstName.orEmpty(),
                        onValueChange = component::onFirstNameChanged,
                        placeholder = stringResource(id = R.string.enter_first_name),
                        painter = painterResource(id = R.drawable.ic_id_card),
                        contentDescription = stringResource(id = R.string.first_name)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TitledTextField(
                        title = stringResource(id = R.string.last_name),
                        value = state.lastName.orEmpty(),
                        onValueChange = component::onLastNameChanged,
                        placeholder = stringResource(id = R.string.enter_last_name),
                        painter = painterResource(id = R.drawable.ic_id_card),
                        contentDescription = stringResource(id = R.string.last_name)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TitledTextField(
                        title = stringResource(id = R.string.date_of_birth),
                        value = "", //TODO(calendar dialog)
                        onValueChange = component::onLastNameChanged,
                        placeholder = stringResource(id = R.string.date_placeholder),
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = stringResource(id = R.string.date_of_birth)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TitledTextField(
                        title = stringResource(id = R.string.gender),
                        value = "", //TODO(calendar dialog)
                        onValueChange = component::onLastNameChanged,
                        painter = painterResource(id = R.drawable.ic_wc),
                        contentDescription = stringResource(id = R.string.gender)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = component::onSaveClicked,
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Text(
                            text = stringResource(id = R.string.save).uppercase(),
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }

            }

        }
    }
}

@Composable
private fun ColumnScope.TitledTextField(
    title: String,
    value: String,
    onValueChange: (String?) -> Unit,
    placeholder: String? = null,
    painter: Painter,
    contentDescription: String,
) {
    Text(
        modifier = Modifier.align(Alignment.Start),
        text = title,
        style = MaterialTheme.typography.bodyMedium,
    )
    Spacer(modifier = Modifier.height(8.dp))
    CustomTextField(
        value = value,
        onValueChange = { newValue -> onValueChange(newValue) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            if (placeholder != null) {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        trailingIcon = {
            Icon(painter = painter, contentDescription = contentDescription)
        }
    )
}

@Composable
private fun UserImage(
    image: Uri,
    onSelectImageClicked: () -> Unit,
) {
    PreviewTheme {
        Box(modifier = Modifier.size(120.dp)) {
            if (image != Uri.EMPTY) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = CircleShape,
                        ),
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            } else {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = CircleShape,
                        ),
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = null
                )
            }
            Card(
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.BottomEnd),
                shape = CircleShape,
                border = BorderStroke(width = 1.dp, color = Mustard),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                IconButton(
                    modifier = Modifier.fillMaxSize(),
                    onClick = onSelectImageClicked
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_photo_camera),
                        contentDescription = stringResource(id = R.string.photo),
                        tint = Mustard,
                    )
                }
            }
        }
    }
}


@Previews
@Composable
private fun Preview() {
    PreviewTheme {
        OptionalContent(FakeOptionalComponent())
    }
}