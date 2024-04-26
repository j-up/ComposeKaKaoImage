package com.image.jm.common.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.image.jm.R
import com.image.jm.theme.ResourceObject

@Composable
fun WarningText(
    @StringRes textId: Int,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
) {
    Text(
        text = stringResource(textId),
        modifier = modifier,
        textAlign = textAlign,
        style = ResourceObject.LocalTypography.current.bold
    )
}

@Composable
@Preview
private fun WarningTextPreView() {
    WarningText(
        textId = R.string.search_result_error,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    )
}