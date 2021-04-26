package com.example.mobiletestandroid.usecases

import com.example.mobiletestandroid.data.ApolloRepository
import com.example.mobiletestandroid.data.ResponseApolloData
import com.example.mobiletestandroid.domain.ApolloEntity

class GetApolloData(private val apolloRepository: ApolloRepository) {
    operator fun invoke(): Pair<MutableList<ApolloEntity>, ResponseApolloData> = apolloRepository.getApolloList()
}