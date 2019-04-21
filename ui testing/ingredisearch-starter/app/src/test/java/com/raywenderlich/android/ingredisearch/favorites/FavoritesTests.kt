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

package com.raywenderlich.android.ingredisearch.favorites

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.raywenderlich.android.ingredisearch.common.Recipe
import com.raywenderlich.android.ingredisearch.repository.RecipeRepository
import org.junit.Before
import org.junit.Test

class FavoritesTests {

  private lateinit var repository: RecipeRepository
  private lateinit var presenter: FavoritesPresenter
  private lateinit var view: FavoritesPresenter.View

  @Before
  fun setup() {
    repository = mock()
    view = mock()
    presenter = FavoritesPresenter(repository)
    presenter.attachView(view)
  }

  @Test
  fun loadFavorites_callsGetFavorites() {
    presenter.loadFavorites()

    verify(repository).getFavoriteRecipes()
  }

  @Test
  fun loadFavorites_withRepositoryHavingFavorites_callsShowRecipes() {
    val recipe = Recipe("id", "title", "imageUrl", "sourceUrl", false)
    val recipes = listOf(recipe)
    whenever(repository.getFavoriteRecipes()).thenReturn(recipes)

    presenter.loadFavorites()

    verify(view).showRecipes(eq(recipes))
  }

  @Test
  fun loadFavorites_withEmptyRepository_callsShowEmptyRecipes() {
    whenever(repository.getFavoriteRecipes()).thenReturn(emptyList())

    presenter.loadFavorites()

    verify(view).showEmptyRecipes()
  }

  @Test
  fun removeFavorite_callsRefreshRemovedFavorite() {
    val recipe = Recipe("id", "title", "imageUrl", "sourceUrl", false)
    val recipes = listOf(recipe)
    whenever(repository.getFavoriteRecipes()).thenReturn(recipes)

    presenter.removeFavorite(recipe)

    verify(view).refreshRemovedFavorite(eq(0))
  }

  @Test
  fun removeFavorite_callsRepositoryRemove() {
    val recipe = Recipe("id", "title", "imageUrl", "sourceUrl", false)
    val recipes = listOf(recipe)
    whenever(repository.getFavoriteRecipes()).thenReturn(recipes)

    presenter.removeFavorite(recipe)

    verify(repository).removeFavorite(eq(recipe))
  }
}
