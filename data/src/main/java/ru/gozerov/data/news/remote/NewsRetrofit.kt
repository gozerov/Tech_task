package ru.gozerov.data.news.remote

import retrofit2.http.GET
import ru.gozerov.data.news.models.DataNewsResponse

const val API_KEY = "6559a215113f4c32943f99c537a4b11f"
const val NEWS_BASE_URL = "https://newsapi.org"

interface NewsRetrofit {

    @GET("./v2/top-headlines?country=us&apiKey=$API_KEY")
    suspend fun fetchNews(): DataNewsResponse

}