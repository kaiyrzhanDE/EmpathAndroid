package kaiyrzhan.de.core.permission

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kaiyrzhan.de.core.preview.Previews
import kaiyrzhan.de.core.theme.ApplicationTheme
import kaiyrzhan.de.core_ui.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionDialog(
    iconsWithDescriptions: List<Pair<Int, Int>>,
    description: String,
    onAcceptRequest: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    require(iconsWithDescriptions.isNotEmpty()) { "IconsWithDescription must not be empty" }

    val scope = rememberCoroutineScope()
    val tooltipState = rememberTooltipState(isPersistent = true)

    val iconState: MutableState<Pair<Int, Int>> = remember {
        mutableStateOf(iconsWithDescriptions[0])
    }

    var currentIndex = 1
    LaunchedEffect(Unit) {
        scope.launch {
            iconsWithDescriptions.forEachIndexed { index, _ ->
                if (index != 0) {
                    delay(1000)
                    iconState.value = iconsWithDescriptions[currentIndex]
                    currentIndex = (currentIndex + 1) % iconsWithDescriptions.size
                }
            }
        }
    }

    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                        )
                    )
                    .background(color = MaterialTheme.colorScheme.onBackground),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Crossfade(targetState = iconState.value, label = stringResource(id = R.string.crossfade)) { state ->
                    TooltipBox(
                        modifier = Modifier.padding(40.dp),
                        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                        tooltip = {
                            PlainTooltip { Text(text = stringResource(id = state.second)) }
                        },
                        state = tooltipState,
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { scope.launch { tooltipState.show() } },
                            painter = painterResource(id = state.first),
                            contentDescription = stringResource(id = state.second),
                            tint = MaterialTheme.colorScheme.background,
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.padding(20.dp),
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                TextButton(onClick = onDismissRequest) {
                    Text(
                        text = stringResource(id = R.string.later),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                TextButton(onClick = onAcceptRequest) {
                    Text(
                        text = stringResource(id = R.string.settings),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}

@Previews
@Composable
private fun Preview() {
    ApplicationTheme {
        PermissionDialog(
            iconsWithDescriptions = listOf(
                R.drawable.ic_file_text to R.string.document,
                R.drawable.ic_file_audio to R.string.audio_file,
                R.drawable.ic_file_video to R.string.video_file,
                R.drawable.ic_file to R.string.file,
                R.drawable.ic_folder to R.string.folder,
            ),
            description = stringResource(id = R.string.description_for_files_permission),
            onAcceptRequest = { },
            onDismissRequest = { },

            )
    }
}