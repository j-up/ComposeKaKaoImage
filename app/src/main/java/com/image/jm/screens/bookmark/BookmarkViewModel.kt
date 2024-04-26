package com.image.jm.screens.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.image.jm.domain.usecase.GetBookmarkUseCase
import com.image.jm.domain.usecase.UpdateBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getBookmarkUseCase: GetBookmarkUseCase,
    private val updateBookmarkUseCase: UpdateBookmarkUseCase
) : ViewModel() {

    private val _bookmarkStateFlow: MutableStateFlow<BookmarkState> = MutableStateFlow(BookmarkState.OnClear)
    val bookmarkStateFlow: Flow<BookmarkState> = _bookmarkStateFlow.asStateFlow()

    init {
        viewModelScope.launch (Dispatchers.IO) {
            getBookmarkUseCase().collect { bookmarkSet ->
                _bookmarkStateFlow.emit(BookmarkState.OnBookmarkListLoad(bookmarkSet.toList()))
            }
        }
    }

    fun removeBookmark(thumbnail: String) {
        viewModelScope.launch (Dispatchers.IO) {
            updateBookmarkUseCase(thumbnail, false)
        }
    }
}