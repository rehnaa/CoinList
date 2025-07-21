package com.online.coin.domain.usecases

import com.online.coin.data.api.ServiceResponse
import com.online.coin.data.model.CoinDetails
import com.online.coin.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinDetailsUseCase @Inject constructor (private val coinRepository: CoinRepository) {

    suspend operator fun invoke(coinId: String): Flow<ServiceResponse<CoinDetails>> {
        return coinRepository.getCoinDetails(coinId)
    }
}