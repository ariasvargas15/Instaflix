package com.bsav.home.data.local.di

import com.bsav.home.data.local.datasource.ProgramLocalDataSource
import com.bsav.home.data.local.datasource.ProgramLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Provides
    @Reusable
    fun provideProgramLocalDataSource(programLocalDataSourceImpl: ProgramLocalDataSourceImpl): ProgramLocalDataSource = programLocalDataSourceImpl
}
