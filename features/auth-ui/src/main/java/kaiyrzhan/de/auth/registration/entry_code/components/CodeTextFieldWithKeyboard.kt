package kaiyrzhan.de.auth.registration.entry_code.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kaiyrzhan.de.core.theme.Mustard
import kaiyrzhan.de.core.theme.Peach
import kaiyrzhan.de.core.preview.PreviewTheme
import kaiyrzhan.de.core.preview.Previews
import kaiyrzhan.de.feature_auth.R

internal enum class KeyTypes {
    NUMBER,
    REMOVE,
    ENTER,
    INVISIBLE,
}

@Composable
internal fun ColumnScope.CodeTextFieldWithKeyboard(
    length: Int,
    code: String,
    modifier: Modifier = Modifier,
    onNumberClicked: (String) -> Unit,
    onRemoveClicked: () -> Unit,
    onEnterClicked: () -> Unit,
) {
    val empty = "0"
    val actions = listOf(
        Pair(KeyTypes.NUMBER, "1"),
        Pair(KeyTypes.NUMBER, "2"),
        Pair(KeyTypes.NUMBER, "3"),
        Pair(KeyTypes.REMOVE, empty),
        Pair(KeyTypes.NUMBER, "4"),
        Pair(KeyTypes.NUMBER, "5"),
        Pair(KeyTypes.NUMBER, "6"),
        Pair(KeyTypes.ENTER, empty),
        Pair(KeyTypes.NUMBER, "7"),
        Pair(KeyTypes.NUMBER, "8"),
        Pair(KeyTypes.NUMBER, "9"),
        Pair(KeyTypes.INVISIBLE, empty),
        Pair(KeyTypes.INVISIBLE, empty),
        Pair(KeyTypes.NUMBER, "0"),
        Pair(KeyTypes.INVISIBLE, empty),
        Pair(KeyTypes.INVISIBLE, empty),
    )
    CodeTextField(
        length = length,
        code = code,
        modifier = modifier
    )
    Spacer(modifier = Modifier.weight(0.75f))
    NumberKeyboard(
        actions = actions,
        code = code,
        length = length,
        onNumberClicked = onNumberClicked,
        onRemoveClicked = onRemoveClicked,
        onEnterClicked = onEnterClicked
    )
}

@Composable
private fun CodeTextField(length: Int, code: String, modifier: Modifier) {
    val updatedCode = when {
        code.length < length -> code.plus("_")
        else -> code
    }
    LazyVerticalGrid(columns = GridCells.Fixed(length)) {
        items(length) { index ->
            val currentChar = updatedCode.getOrNull(index)
            Row(
                modifier = modifier
                    .padding(5.dp)
                    .height(50.dp)
                    .border(
                        width = 1.dp,
                        color = if (currentChar != null) Peach else MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(8.dp),
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                if (currentChar != null) {
                    Text(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight(align = Alignment.CenterVertically),
                        text = currentChar.toString(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.displaySmall,
                        color = Peach,
                    )
                }
            }
        }
    }

}


@Composable
private fun NumberKeyboard(
    actions: List<Pair<KeyTypes, String>>,
    code: String,
    length: Int,
    onNumberClicked: (String) -> Unit,
    onRemoveClicked: () -> Unit,
    onEnterClicked: () -> Unit,
) {
    LazyVerticalGrid(columns = GridCells.Fixed(4)) {
        items(actions) { (keyType, value) ->
            when (keyType) {
                KeyTypes.NUMBER ->
                    Key(
                        text = value,
                        onClick = {
                            if (code.length < length) {
                                onNumberClicked(code.plus(value))
                            }
                        },
                    )

                KeyTypes.INVISIBLE ->
                    Key(
                        text = value,
                        isVisible = false,
                        enabled = false,
                    )

                KeyTypes.REMOVE ->
                    Key(
                        painter = painterResource(id = R.drawable.ic_backspace),
                        enabled = code.isNotEmpty(),
                        onClick = onRemoveClicked,
                    )

                KeyTypes.ENTER ->
                    Key(
                        painter = painterResource(id = R.drawable.ic_arrow_forward),
                        enabled = code.length == length,
                        onClick = onEnterClicked,
                    )

            }
        }
    }

}

@Composable
private fun Key(
    text: String,
    isVisible: Boolean = true,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    OutlinedButton(
        modifier = Modifier
            .height(50.dp)
            .padding(2.dp)
            .alpha(if (isVisible) 1f else 0f),
        onClick = { onClick?.let { function -> function() } },
        shape = RoundedCornerShape(8.dp),
        enabled = enabled,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
private fun Key(
    painter: Painter,
    isVisible: Boolean = true,
    enabled: Boolean = true,
    contentDescription: String? = null,
    onClick: (() -> Unit)? = null,
) {
    OutlinedButton(
        modifier = Modifier
            .height(50.dp)
            .padding(2.dp)
            .alpha(if (isVisible) 1f else 0f),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Mustard,
        ),
        enabled = enabled,
        onClick = { onClick?.let { function -> function() } },
        border = BorderStroke(1.dp, Mustard),
        shape = RoundedCornerShape(8.dp),
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription
        )
    }
}

@Previews
@Composable
private fun Preview() {
    PreviewTheme {
        Column {
            CodeTextFieldWithKeyboard(
                onNumberClicked = {},
                onRemoveClicked = {},
                onEnterClicked = {},
                code = "",
                length = 6,
            )
        }
    }
}
