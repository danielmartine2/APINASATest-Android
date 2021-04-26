package com.example.mobiletestandroid.ui.di

import com.example.mobiletestandroid.Util
import com.example.mobiletestandroid.data.ApolloCloudSource
import com.example.mobiletestandroid.data.ApolloMapper
import com.example.mobiletestandroid.data.ApolloRepository
import com.example.mobiletestandroid.data.dataSource.Cloud.ApolloDataCloud
import com.example.mobiletestandroid.data.dataSource.Local.ApolloDataLocal
import com.example.mobiletestandroid.data.dataSource.Cloud.EndPoints
import com.example.mobiletestandroid.data.dataSource.Local.ApolloDao
import com.example.mobiletestandroid.ui.ApolloPresenter
import com.example.mobiletestandroid.usecases.GetApolloData
import com.example.mobiletestandroid.usecases.UpdateApolloData
import com.loopj.android.http.SyncHttpClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApolloModule {

    @Provides
    @Singleton
    fun provideUtil() = Util.instance

    @Provides
    fun provideEndPoint() =
        EndPoints()

    @Provides
    fun provideClient() = SyncHttpClient()

    @Provides
    fun provideApolloMapper() = ApolloMapper()

    @Provides
    @Singleton
    fun provideApolloDataCloud(client: SyncHttpClient, endPoints: EndPoints) =
        ApolloDataCloud(
            client,
            endPoints
        )

    @Provides
    @Singleton
    fun provideApolloDataLocal() =
        ApolloDataLocal()

    @Provides
    @Singleton
    fun provideApolloRepository(apolloDataLocal: ApolloDataLocal,
                                apolloCloudSource: ApolloDataCloud,
                                apolloMapper: ApolloMapper,
                                util: Util
    ) = ApolloRepository(apolloDataLocal, apolloCloudSource, apolloMapper, util)

    @Provides
    @Singleton
    fun provideGetApolloData(apolloRepository: ApolloRepository) = GetApolloData(apolloRepository)

    @Provides
    @Singleton
    fun provideGUpdateApolloData(apolloRepository: ApolloRepository) = UpdateApolloData(apolloRepository)

    @Provides
    @Singleton
    fun provideApolloPresenter(getApolloData: GetApolloData, updateApolloData: UpdateApolloData) =
        ApolloPresenter(getApolloData, updateApolloData)

}