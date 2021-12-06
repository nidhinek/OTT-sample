package com.nidhinek.ottminiframe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nidhinek.ottminiframe.data.ImdbRepository
import com.nidhinek.ottminiframe.data.ImdbSearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.simplifiedcoding.data.network.Resource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ImdbRepository) : ViewModel() {
    val title: MutableLiveData<String> = MutableLiveData()
    private val _searchResponse: MutableLiveData<Resource<ImdbSearchResponse>> = MutableLiveData()
    val searchResponse: LiveData<Resource<ImdbSearchResponse>>
        get() = _searchResponse

    fun getMovies(query: String) = viewModelScope.launch {
        _searchResponse.value = Resource.Loading
        _searchResponse.value = repository.searchMovies(query)
    }

    fun setToolbarTitle(titleHeader: String) {
        title.value = titleHeader
    }
}
