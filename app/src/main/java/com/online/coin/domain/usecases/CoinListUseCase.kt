package com.online.coin.domain.usecases

import com.online.coin.data.api.ServiceResponse
import com.online.coin.data.model.CoinList
import com.online.coin.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoinListUseCase @Inject constructor(private val coinRepository: CoinRepository) {
    suspend operator fun invoke() : Flow<ServiceResponse<CoinList>> {

        val response = coinRepository.getCoins().map { coinListResponse ->
            if (coinListResponse is ServiceResponse.Success) {
                val coinList = coinListResponse.data
                // Sort the objects based on it's name
                val mergedCoinList = CoinList().apply {
                    addAll(coinList.sortedBy { it.name })
                }
                ServiceResponse.Success(mergedCoinList)
            } else {
                coinListResponse
            }
        }
        return response
    }

}