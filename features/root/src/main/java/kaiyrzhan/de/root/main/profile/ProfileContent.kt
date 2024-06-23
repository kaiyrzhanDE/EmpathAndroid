package kaiyrzhan.de.root.main.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kaiyrzhan.de.core.components.ToolbarText
import kaiyrzhan.de.core.preview.PreviewTheme
import kaiyrzhan.de.core.preview.Previews
import kaiyrzhan.de.feature_auth.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(component: ProfileComponent) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { ToolbarText(text = "Profile") },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Profile")
        }
    }

}

@Previews
@Composable
private fun Preview() = PreviewTheme {
    ProfileContent(component = FakeProfileComponent())
}
