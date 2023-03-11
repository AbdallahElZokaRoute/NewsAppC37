package com.route.domain.usecases

import com.route.domain.entity.SourcesItemDTO
import com.route.domain.repos.SourcesRepository
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val sourcesRepository: SourcesRepository
) {
    suspend operator fun invoke(category: String): List<SourcesItemDTO> =
        sourcesRepository.getSources(category)

}
