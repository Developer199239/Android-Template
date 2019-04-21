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

import android.content.SharedPreferences
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.*
import com.raywenderlich.android.ingredisearch.common.Recipe
import org.junit.Before
import org.junit.Test


class RepositoryTests {
  private lateinit var spyRepository: RecipeRepository
  private lateinit var sharedPreferences: SharedPreferences
  private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

  @Before
  fun setup() {
    sharedPreferences = mock()
    sharedPreferencesEditor = mock()
    whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)

    spyRepository = spy(RecipeRepositoryImpl(sharedPreferences))
  }

  @Test
  fun addFavorite_withEmptyRecipes_savesJsonRecipe() {
    doReturn(emptyList<Recipe>()).whenever(spyRepository).getFavoriteRecipes()

    val recipe = Recipe("id", "title", "imageUrl", "sourceUrl", false)
    spyRepository.addFavorite(recipe)

    inOrder(sharedPreferencesEditor) {
      val jsonString = Gson().toJson(listOf(recipe))
      verify(sharedPreferencesEditor).putString(any(), eq(jsonString))
      verify(sharedPreferencesEditor).apply()
    }
  }

  @Test
  fun saveRecentIngredients_withEmptyRecentIngredients_savesJsonList() {
    doReturn(emptyList<String>()).whenever(spyRepository).getRecentIngredients()

    val query = "Eggs, ham, CHEESE"

    spyRepository.saveRecentIngredients(query)
    inOrder(sharedPreferencesEditor) {
      val jsonString = Gson().toJson(listOf("cheese", "eggs", "ham"))
      verify(sharedPreferencesEditor).putString(any(), eq(jsonString))
      verify(sharedPreferencesEditor).apply()
    }
  }
}