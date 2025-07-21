package com.online.coin.domain.repository

import com.online.coin.data.api.ServiceResponse
import com.online.coin.data.model.CoinDetails
import com.online.coin.data.model.CoinList
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoins(): Flow<ServiceResponse<CoinList>>

    suspend fun getCoinDetails(coinId: String): Flow<ServiceResponse<CoinDetails>>

}