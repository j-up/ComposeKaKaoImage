package com.image.jm.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.image.jm.domain.usecase.FetchImageWithBookmarkCheckUseCase
import com.image.jm.domain.usecase.GetBookmarkUseCase
import com.image.jm.domain.usecase.UpdateBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchImageUseCase: FetchImageWithBookmarkCheckUseCase,
    private val updateBookmarkUseCase: UpdateBookmarkUseCase,
    getBookmarkUseCase: GetBookmarkUseCase,
) : ViewModel() {

    private val _queryFlow = MutableStateFlow("")

    val homeStateFlow: StateFlow<HomeState> = _queryFlow
        .filter(String::isNotEmpty)
        .debounce(1000L)
        .combine(
            getBookmarkUseCase()
        ) { query, _ ->
            query
        }.flatMapLatest { query ->
            fetchImageUseCase.invoke(query = query)
                .map { data ->

                    when (val result = data.getOrNull()) {
                        null -> {
                            HomeState.OnFail
                        }

                        else -> {
                            HomeState.OnImageListLoad(query, result)
                        }
                    }
                }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(TIME_OUT),
            HomeState.OnClear
        )

    fun setQuery(query: String) = viewModelScope.launch(Dispatchers.IO) {
        _queryFlow.emit(query)
    }

    fun updateBookmark(thumbnail: String, bookmark: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            updateBookmarkUseCase(thumbnail, bookmark)
        }

    companion object {
        private const val TIME_OUT = 5000L
    }
}