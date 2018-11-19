package com.personal.integration.nci.api

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder

@Component
class AuthApi(private val restTemplate: RestTemplate,
              private val objectMapper: ObjectMapper) {

    val logger: Logger = LoggerFactory.getLogger(AuthApi::class.java)

    @Value("")
    val username: String? = null

    @Value("")
    val password: String? = null


    fun getAuthToken(): JsonNode {
        val jf: JsonNodeFactory = JsonNodeFactory.instance;
        val postBody: ObjectNode = jf.objectNode();

        postBody
                .putObject("auth")
                .put("username", username)
                .put("password", password);

        logger.debug("Sending to Appnexus these credentials: {}", postBody.toString());

        val response: JsonNode = restTemplate
                .postForObject(
                        "/auth",
                        postBody.toString(),
                        ObjectNode::class.java
                )
                .get("response")


        logger.debug("Token crend: {}", restTemplate
                .postForObject(
                        "/auth",
                        postBody.toString(),
                        ObjectNode::class.java
                ))


        logger.debug("Appnexus responded to getAuthToken with {}", response.toString());

        return response
    }


    fun getEndpoint(url: String) {


//        val headers = ttdUtil.getDefaultHeader(authApi)
        val jf = JsonNodeFactory.instance
        val postBody = jf.objectNode()

        postBody
                .put("PageSize", 100)
                .put("PageStartIndex", 0)


//        val entity = HttpEntity(postBody, headers)
        val entity = HttpEntity(postBody)
        val uri = UriComponentsBuilder
                .fromPath("${url}/${url}")
                .toUriString()


        logger.debug("uri: {} ", uri)
        logger.debug("entity: {} ", entity)

        logger.debug("responsechcekc: {}", restTemplate.exchange(uri, HttpMethod.POST, entity, JsonNode::class.java))

        val response = try {

            restTemplate.exchange(uri, HttpMethod.POST, entity, JsonNode::class.java).body

        } catch (e: HttpClientErrorException) {
            logger.error(e.message, e)
            objectMapper.readValue<JsonNode>(e.responseBodyAsString)
        }


        logger.debug("responseData: {} ", response)


    }
}
