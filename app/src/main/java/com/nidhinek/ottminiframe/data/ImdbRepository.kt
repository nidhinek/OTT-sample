package com.nidhinek.ottminiframe.data

import com.nidhinek.ottminiframe.api.ImdbService
import net.simplifiedcoding.data.network.SafeApiCall
import javax.inject.Inject

class ImdbRepository @Inject constructor(private val service: ImdbService) : SafeApiCall {
    suspend fun searchMovies(
        query: String
    ) = safeApiCall {
        service.searchMovies(query)
    }
}
