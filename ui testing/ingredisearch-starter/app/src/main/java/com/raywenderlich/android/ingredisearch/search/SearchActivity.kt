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

package com.raywenderlich.android.ingredisearch.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import com.raywenderlich.android.ingredisearch.IngredisearchApp
import com.raywenderlich.android.ingredisearch.R
import com.raywenderlich.android.ingredisearch.common.ChildActivity
import com.raywenderlich.android.ingredisearch.recentIngredients.RecentIngredientsActivity
import com.raywenderlich.android.ingredisearch.recentIngredients.recentIngredientsIntent
import com.raywenderlich.android.ingredisearch.repository.RecipeRepositoryImpl
import com.raywenderlich.android.ingredisearch.searchResults.searchResultsIntent
import kotlinx.android.synthetic.main.activity_search.*

private const val SELECT_INGREDIENTS_REQUEST = 322

class SearchActivity : ChildActivity(), SearchPresenter.View {

  private val presenter by lazy {
    (application as IngredisearchApp).getSearchPresenter()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search)

    presenter.attachView(this)

    searchButton.setOnClickListener {
      val query = ingredients.text.toString()
      presenter.search(query)
    }
  }

  override fun onResume() {
    super.onResume()
    presenter.loadRecentIngredients()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == SELECT_INGREDIENTS_REQUEST && resultCode == RESULT_OK) {
      data?.let {
        val selected = it.getStringArrayListExtra(
            RecentIngredientsActivity.EXTRA_INGREDIENTS_SELECTED
        )
        ingredients.setText(selected.joinToString(","))
      }
    }
  }

  override fun onDestroy() {
    presenter.detachView()
    super.onDestroy()
  }

  // region SearchPresenter.View methods
  override fun showQueryRequiredMessage() {
    // Hide keyboard
    val view = this.currentFocus
    if (view != null) {
      val imm
          = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    Snackbar
        .make(searchButton, getString(R.string.search_query_required), Snackbar.LENGTH_LONG)
        .show()
  }

  override fun showSearchResults(query: String) {
    startActivity(searchResultsIntent(query))
  }

  override fun hideRecentIngredients() {
    recentButton.visibility = View.GONE
  }

  override fun showRecentIngredients() {
    recentButton.visibility = View.VISIBLE
    recentButton.setOnClickListener {
      startActivityForResult(recentIngredientsIntent(), SELECT_INGREDIENTS_REQUEST)
    }
  }
  // endregion
}
