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

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.android.ingredisearch.R
import com.raywenderlich.android.ingredisearch.common.ChildActivity
import com.raywenderlich.android.ingredisearch.common.Recipe
import com.raywenderlich.android.ingredisearch.common.RecipeAdapter
import com.raywenderlich.android.ingredisearch.recipe.recipeIntent
import com.raywenderlich.android.ingredisearch.repository.RecipeRepositoryImpl
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.view_error.*
import kotlinx.android.synthetic.main.view_loading.*
import kotlinx.android.synthetic.main.view_noresults.*

class FavoritesActivity : ChildActivity(), FavoritesPresenter.View {

  private val presenter by lazy {
    FavoritesPresenter(RecipeRepositoryImpl.getRepository(this))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)

    presenter.attachView(this)

    presenter.loadFavorites()
  }

  override fun onDestroy() {
    presenter.detachView()
    super.onDestroy()
  }

  // region FavoritesPresenter.View methods
  override fun showEmptyRecipes() {
    loadingContainer.visibility = View.GONE
    errorContainer.visibility = View.GONE
    list.visibility = View.VISIBLE
    noresultsContainer.visibility = View.VISIBLE
    noresultsTitle.text = getString(R.string.nofavorites)
  }

  override fun showRecipes(recipes: List<Recipe>) {
    loadingContainer.visibility = View.GONE
    errorContainer.visibility = View.GONE
    list.visibility = View.VISIBLE
    noresultsContainer.visibility = View.GONE

    list.layoutManager = LinearLayoutManager(this)
    list.adapter = RecipeAdapter(recipes, object : RecipeAdapter.Listener {
      override fun onClickItem(item: Recipe) {
        startActivity(recipeIntent(item.sourceUrl))
      }

      override fun onAddFavorite(item: Recipe) {
        // no-op
      }

      override fun onRemoveFavorite(item: Recipe) {
        presenter.removeFavorite(item)
      }

    })
  }

  override fun refreshRemovedFavorite(recipeIndex: Int) {
    list.adapter?.let {
      (it as RecipeAdapter).removeItem(recipeIndex)
      it.notifyItemRemoved(recipeIndex)
      if (it.itemCount == 0) {
        showEmptyRecipes()
      }
    }
  }
  // endregion
}
