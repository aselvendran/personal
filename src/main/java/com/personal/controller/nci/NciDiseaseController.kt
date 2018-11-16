package com.personal.controller.nci

import okhttp3.OkHttpClient
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@RestController
@RequestMapping("/api/athavantest")
class NciDiseaseController {

    var Logger = LoggerFactory.getLogger(NciDiseaseController::class.java)

    @GetMapping
    fun getAll(@RequestParam(defaultValue = "0") page: Long): String {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
//
//        redditApi = retrofit.create(Any)


        val client = OkHttpClient
            .Builder()
            .build()

        val retrofit2 = Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
            .client(client)
            .build()

        System.out.println("AthavanTestingValues:  ")

        return retrofit2.toString()


    }


}
