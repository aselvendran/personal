package com.personal.controller.nci

import com.fasterxml.jackson.databind.JsonNode
import com.personal.integration.nci.NciService
import org.slf4j.LoggerFactory
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest


@RestController
@RequestMapping("/api/athavantest")
class NciDiseaseController(
    private val nciService: NciService
) {

    var Logger = LoggerFactory.getLogger(NciDiseaseController::class.java)

    @GetMapping("/{diseaseId}")
    fun getAll(@PathVariable diseaseId: String, webRequest: WebRequest): JsonNode {


        val params = webRequest.parameterMap

        val parameterIterated = LinkedMultiValueMap<String, String>()

        for ((key, value) in params) {
            parameterIterated.add(key, value[0])
        }


        return nciService.getAllDisease("diseases", parameterIterated)


    }


}
