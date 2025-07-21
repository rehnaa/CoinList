package com.online.coin.data.repository

import com.online.coin.data.api.ServiceResponse
import com.online.coin.data.model.CoinDetails
import com.online.coin.data.model.CoinList
import com.online.coin.domain.repository.CoinRepository
import com.online.coin.utils.TestData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Fake repository used to test the repository using Hilt module.
 * Its a fake class for the actual repository used in the app,
 * the actual replaced with this fake in the hilt module.
 */
class FakeCoinRepository (
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CoinRepository {
    private var fakeCoinList : ServiceResponse<CoinList> = ServiceResponse.Success(TestData.coinList)
    private var fakeCoinDetails = CoinDetails.default

    fun setFakeCoinList(fakeList: ServiceResponse<CoinList>) {
        fakeCoinList = fakeList
    }

    override suspend fun getCoins(): Flow<ServiceResponse<CoinList>> {
        return flow {
            emit(fakeCoinList)
        }.flowOn(defaultDispatcher)
    }

    override suspend fun getCoinDetails(coinId: String): Flow<ServiceResponse<CoinDetails>> {
        return if (coinId == fakeCoinDetails.id) {
            flow {
                emit(ServiceResponse.Success(fakeCoinDetails))
            }.flowOn(defaultDispatcher)
        } else {
            flow {
                emit(ServiceResponse.Error(0, "Coin not found"))
            }.flowOn(defaultDispatcher)
        }
    }
}
