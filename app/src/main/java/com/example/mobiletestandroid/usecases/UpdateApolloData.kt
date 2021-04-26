package com.example.mobiletestandroid.usecases

import com.example.mobiletestandroid.data.ApolloRepository
import com.example.mobiletestandroid.domain.ApolloEntity

class UpdateApolloData (private val apolloRepository: ApolloRepository){
    operator fun invoke(apollo: ApolloEntity) {
        apolloRepository.updateApollo(apollo)
    }
}