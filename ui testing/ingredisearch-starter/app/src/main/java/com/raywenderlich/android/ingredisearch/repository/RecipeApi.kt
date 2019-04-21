/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.ingredisearch.repository

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.raywenderlich.android.ingredisearch.BuildConfig
import com.raywenderlich.android.ingredisearch.common.RecipesContainer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface RecipeApi {

  // 1 - Get your Food2Fork API key from http://food2fork.com/about/api
  // 2 - Create a keystore.properties file with the following content (including the quotes):
  //     FOOD2FORK_API_KEY="YOUR API KEY"

  @GET("search?key=" + BuildConfig.FOOD2FORK_API_KEY)
  fun search(@Query("q") query: String): Call<RecipesContainer>

  companion object Factory {
    fun create(): RecipeApi {
      val gson = GsonBuilder()
          .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
          .create()

      val client = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
          val interceptor = HttpLoggingInterceptor()
          interceptor.level = HttpLoggingInterceptor.Level.BODY
          addInterceptor(interceptor)
        }
      }.build()

      val retrofit = Retrofit.Builder()
          .baseUrl("http://food2fork.com/api/")
          .client(client)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build()

      return retrofit.create(RecipeApi::class.java)
    }
  }
}