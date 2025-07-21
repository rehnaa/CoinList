package com.online.coin.di

import com.online.coin.domain.repository.CoinRepository
import com.online.coin.domain.usecases.CoinDetailsUseCase
import com.online.coin.domain.usecases.CoinListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
/**
 * Module used to provide the Usecases dependencies needed for hilt
 */
@Module
@InstallIn(SingletonComponent::class)
object UsecasesModule {

    @Provides
    fun provideCoinListUseCases(coinRepository: CoinRepository): CoinListUseCase{
        return CoinListUseCase(coinRepository)
    }
    @Provides
    fun provideCoinDetailsUseCases(coinRepository: CoinRepository): CoinDetailsUseCase{
        return CoinDetailsUseCase(coinRepository)
    }

}