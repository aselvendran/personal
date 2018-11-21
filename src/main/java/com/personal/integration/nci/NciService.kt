package com.personal.integration.nci

import com.fasterxml.jackson.databind.JsonNode
import com.personal.integration.nci.api.ApiConnection
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import java.util.*

@Service
class NciService(
    private val authApi: ApiConnection
) {
    //
    fun getAllDisease(endpoint: String, parameterMap: LinkedMultiValueMap<String, String>): JsonNode {
        return authApi.nciGetRequest<JsonNode>(endpoint, parameterMap)
    }


}