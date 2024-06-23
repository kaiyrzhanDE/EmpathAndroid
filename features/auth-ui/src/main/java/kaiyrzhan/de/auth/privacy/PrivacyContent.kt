package kaiyrzhan.de.auth.privacy

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kaiyrzhan.de.core.animations.VerticalExpandCollapseAnimation
import kaiyrzhan.de.core.components.ToolbarText
import kaiyrzhan.de.core.preview.PreviewTheme
import kaiyrzhan.de.core.preview.Previews
import kaiyrzhan.de.feature_auth.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PrivacyContent(component: PrivacyComponent) {
    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { ToolbarText(text = stringResource(id = R.string.privacy)) },
                navigationIcon = {
                    IconButton(onClick = component::onBackClicked) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_backward),
                            contentDescription = stringResource(id = R.string.back),
                            tint = Color.Unspecified,
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item { Spacer(modifier = Modifier.height(20.dp)) }
            items(10) { index -> ExpandableCard(index, scope, lazyListState) }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ExpandableCard(
    index: Int = 1,
    scope: CoroutineScope,
    lazyListState: LazyListState,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val onExpandClicked = {
        isExpanded = !isExpanded
    }
    Column(
        modifier = Modifier
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(shape = RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
                .clickable(onClick = onExpandClicked)
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "$index. Общие положения",
                style = MaterialTheme.typography.titleSmall,
            )
            IconButton(onClick = onExpandClicked) {
                Icon(
                    painter = painterResource(
                        id = if (isExpanded) R.drawable.ic_arrow_up
                        else R.drawable.ic_arrow_down
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
        if (isExpanded) {
            HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(horizontal = 20.dp))
        }
        VerticalExpandCollapseAnimation(isVisible = isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                repeat(3) {
                    Text(
                        text = "1.1. Настоящее Пользовательское Соглашение (далее – «Соглашение») регулирует отношения между пользователями (далее – «Пользователь») и владельцем мессенджера (далее – «Администрация»).",
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Previews
@Composable
private fun Preview() = PreviewTheme {
    PrivacyContent(component = FakePrivacyComponent())
}


