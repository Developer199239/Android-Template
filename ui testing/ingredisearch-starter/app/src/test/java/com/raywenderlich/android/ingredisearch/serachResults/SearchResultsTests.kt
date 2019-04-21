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

package com.raywenderlich.android.ingredisearch.serachResults

import com.nhaarman.mockitokotlin2.*
import com.raywenderlich.android.ingredisearch.common.Recipe
import com.raywenderlich.android.ingredisearch.repository.RecipeRepository
import com.raywenderlich.android.ingredisearch.repository.RepositoryCallback
import com.raywenderlich.android.ingredisearch.searchResults.SearchResultsPresenter
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SearchResultsTests {

  private lateinit var repository: RecipeRepository
  private lateinit var presenter: SearchResultsPresenter
  private lateinit var view: SearchResultsPresenter.View

  @Before
  fun setup() {
    repository = mock()
    view = mock()
    presenter = SearchResultsPresenter(repository)
    presenter.attachView(view)
  }

  @Test
  fun search_callsShowLoading() {
    presenter.search("eggs")

    verify(view).showLoading()
  }

  @Test
  fun search_callsGetRecipes() {
    presenter.search("eggs")

    verify(repository).getRecipes(eq("eggs"), any())
  }

  @Test
  fun search_withRepositoryHavingRecipes_callsShowRecipes() {
    val recipe = Recipe("id", "title", "imageUrl", "sourceUrl", false)
    val recipes = listOf(recipe)

    doAnswer {
      val callback: RepositoryCallback<List<Recipe>> = it.getArgument(1)
      callback.onSuccess(recipes)
    }.whenever(repository).getRecipes(eq("eggs"), any())

    presenter.search("eggs")

    verify(view).showRecipes(eq(recipes))
  }

  @Test
  fun addFavorite_shouldUpdateRecipeStatus() {
    val recipe = Recipe("id", "title", "imageUrl", "sourceUrl", false)

    presenter.addFavorite(recipe)

    Assert.assertTrue(recipe.isFavorited)
  }
}
