package com.personal.integration.nci.api

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder

@Component
class ApiConnection(val restTemplate: RestTemplate,
                    val objectMapper: ObjectMapper) {

    val logger: Logger = LoggerFactory.getLogger(ApiConnection::class.java)


    final inline fun <reified T> nciGetRequest(endpoint: String, params: LinkedMultiValueMap<String, String>): JsonNode {

        System.out.println("Hello Testing")

        val entity = HttpEntity<String>(HttpHeaders())


        val uri = UriComponentsBuilder
            .fromPath("/$endpoint")
            .queryParams(params)
            .toUriString()


        System.out.println(uri)


        val response = try {

            restTemplate.exchange(uri, HttpMethod.GET, entity, JsonNode::class.java).body

        } catch (e: HttpClientErrorException) {
            logger.error(e.message, e)
            objectMapper.readValue<JsonNode>(e.responseBodyAsString)
        }

        return response
    }


}
