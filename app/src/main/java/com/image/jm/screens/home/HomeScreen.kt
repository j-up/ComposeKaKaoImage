package com.image.jm.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.image.jm.model.KakaoImage
import com.image.jm.R
import com.image.jm.common.component.WarningText
import com.image.jm.theme.ResourceObject
import com.image.jm.theme.ResourceObject.LocalTypography
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: State<HomeState>,
    onValueChange: (String) -> Unit,
    bookmarkClickListener: (KakaoImage) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        
        Text(
            text = stringResource(id = R.string.screen_search_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textAlign = TextAlign.Center,
            style = LocalTypography.current.bold
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputTextFiled(
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { input ->
                    onValueChange(input)
                })
        }

        when (val value = state.value) {
            HomeState.OnClear -> {

            }

            HomeState.OnFail -> {
                WarningText(
                    textId = R.string.search_result_error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
            }

            is HomeState.OnImageListLoad -> {
                if (value.list.isEmpty()) {
                    WarningText(
                        textId = R.string.search_result_empty,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    )
                } else {
                    KakaoMediaList(
                        state = value,
                        bookmarkClickListener = {
                            bookmarkClickListener(it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun InputTextFiled(modifier: Modifier = Modifier, onValueChange: (String) -> Unit) {
    var query by rememberSaveable { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = { input ->
            query = input
            onValueChange(query)
        },
        textStyle = LocalTypography.current.input,
        placeholder = {
            Text(
                text = stringResource(id = R.string.text_input_label),
                style = LocalTypography.current.input
            )
        },
        singleLine = true,
        modifier = modifier
    )

}

@Composable
fun KakaoMediaList(
    state: HomeState.OnImageListLoad,
    modifier: Modifier = Modifier,
    bookmarkClickListener: (KakaoImage) -> Unit,
) {
    val listState: LazyListState = rememberLazyListState()

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = modifier
    ) {
        items(items = state.list, key = { item -> item.thumbnail }) {
            KaKaoMediaItemRow(
                present = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                bookmarkClickListener = bookmarkClickListener
            )
        }
    }
}

@Composable
fun KaKaoMediaItemRow(
    present: KakaoImage,
    modifier: Modifier = Modifier,
    bookmarkClickListener: (KakaoImage) -> Unit,
) {
    var isBookmark by remember { mutableStateOf(present.isBookmark) }

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 2.dp,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .padding(8.dp)
        ) {

            KaKaoMediaItemImageContent(
                present = present,
                modifier = Modifier.align(Alignment.Center)
            )

            Image(
                painter = when (isBookmark) {
                    true -> painterResource(R.drawable.bookmark_on)
                    else -> painterResource(R.drawable.bookmark_off)
                },
                contentDescription = "bookmark",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(24.dp)
                    .clickable {
                        isBookmark = !isBookmark
                        bookmarkClickListener(present.copy(isBookmark = !present.isBookmark))
                    }
            )
        }
    }
}

@Composable
fun KaKaoMediaItemImageContent(present: KakaoImage, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        CoilImage(
            imageModel = { present.thumbnail },
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

        Text(text = present.dateToString(), modifier = Modifier.padding(top = 5.dp))
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(ResourceObject.LocalColors.current.background),
        state = remember {
            mutableStateOf(
                HomeState.OnImageListLoad(
                    query = "peach",
                    list = listOf(
                        KakaoImage(thumbnail = "1", dateTime = "2024-04-13T07:56:56.000+09:00", isBookmark = true),
                        KakaoImage(thumbnail = "2", dateTime = "2024-04-12T12:33:00.000+09:00", isBookmark = true),
                        KakaoImage(thumbnail = "3", dateTime = "2024-04-12T12:33:00.000+09:00", isBookmark = false),
                        KakaoImage(thumbnail = "4", dateTime = "2024-04-12T12:33:00.000+09:00", isBookmark = false),
                        KakaoImage(thumbnail = "5", dateTime = "2024-04-12T12:33:00.000+09:00", isBookmark = false)
                    )
                )
            )
        },
        onValueChange = { },
        bookmarkClickListener = { }
    )
}