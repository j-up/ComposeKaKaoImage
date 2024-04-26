package com.image.jm.screens.bookmark

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.image.jm.R
import com.image.jm.common.component.WarningText
import com.image.jm.theme.ResourceObject
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    state: State<BookmarkState>,
    onBookmarkClickListener: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.screen_bookmark_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textAlign = TextAlign.Center,
            style = ResourceObject.LocalTypography.current.bold
        )

        when (val value = state.value) {
            is BookmarkState.OnBookmarkListLoad -> {
                if (value.list.isEmpty()) {
                    WarningText(
                        textId = R.string.bookmark_result_empty,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                    )
                } else {
                    KakaoMediaBookmarkList(
                        value,
                        onBookmarkClickListener = {
                            onBookmarkClickListener(it)
                        }
                    )
                }
            }

            BookmarkState.OnClear -> {

            }
        }
    }
}

@Composable
fun KakaoMediaBookmarkList(
    state: BookmarkState.OnBookmarkListLoad,
    modifier: Modifier = Modifier,
    onBookmarkClickListener: (String) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = modifier
    ) {
        items(items = state.list, key = { item -> item }) { itemPresent ->
            KaKaoMediaBookmarkItemRow(
                present = itemPresent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onBookmarkClickListener = { selectedItem ->
                    onBookmarkClickListener(selectedItem)
                }
            )
        }
    }
}

@Composable
fun KaKaoMediaBookmarkItemRow(
    present: String,
    modifier: Modifier = Modifier,
    onBookmarkClickListener: (String) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 2.dp,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .padding(8.dp),
        ) {
            KaKaoMediaBookmarkItemImage(
                thumbnail = present,
                modifier = Modifier
                    .align(Alignment.Center)
            )

            Image(
                painter = painterResource(R.drawable.bookmark_on),
                contentDescription = "bookmark",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(24.dp)
                    .clickable {
                        onBookmarkClickListener(present)
                    }
            )
        }
    }
}


@Composable
fun KaKaoMediaBookmarkItemImage(thumbnail: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CoilImage(
            imageModel = { thumbnail },
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally),
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            ),
            previewPlaceholder = R.drawable.peach,
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                        .aspectRatio(1f)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            failure = {
                Image(
                    painter = painterResource(R.drawable.error),
                    contentDescription = "error",
                    modifier = Modifier
                        .size(100.dp)
                )
            }
        )
    }
}

@Preview
@Composable
private fun BookmarkScreenPreview() {
    BookmarkScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(ResourceObject.LocalColors.current.background),
        state = remember {
            mutableStateOf(
                BookmarkState.OnBookmarkListLoad(
                    listOf("1", "2", "3", "4", "5")
                )
            )
        },
        onBookmarkClickListener = { }
    )
}
