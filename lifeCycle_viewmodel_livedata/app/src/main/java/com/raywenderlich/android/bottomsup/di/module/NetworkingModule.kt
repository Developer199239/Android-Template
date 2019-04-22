/*
 * Copyright (c) 2018 Razeware LLC
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
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.bottomsup.di.module

import com.raywenderlich.android.bottomsup.api.BreweryApiService
import com.raywenderlich.android.bottomsup.common.BASE_URL
import com.raywenderlich.android.bottomsup.di.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@ApplicationScope
class NetworkingModule {

  @Provides
  fun provideBaseUrl(): String = BASE_URL

  @Provides
  fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
  }

  @Provides
  fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
      OkHttpClient.Builder()
          .addInterceptor(loggingInterceptor)
          .build()

  @Provides
  fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

  @Provides
  fun provideRetrofitClient(client: OkHttpClient,
                            baseUrl: String,
                            converter: GsonConverterFactory): Retrofit =
      Retrofit.Builder()
          .client(client)
          .addConverterFactory(converter)
          .baseUrl(baseUrl)
          .build()

  @Provides
  fun provideBreweryApiService(retrofit: Retrofit): BreweryApiService =
      retrofit.create(BreweryApiService::class.java)
}