package com.example.domain.usecases

import com.example.domain.entities.SourcesItemDTO
import com.example.domain.repos.SourcesRepository
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val sourcesRepository: SourcesRepository
){
   suspend operator fun invoke(category: String): List<SourcesItemDTO> = sourcesRepository.getSources(category)
}