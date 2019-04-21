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

package com.raywenderlich.android.ingredisearch.recentIngredients

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.android.ingredisearch.R
import com.raywenderlich.android.ingredisearch.common.ChildActivity
import com.raywenderlich.android.ingredisearch.repository.RecipeRepositoryImpl
import kotlinx.android.synthetic.main.activity_recent_ingredients.*

fun Context.recentIngredientsIntent(): Intent {
  return Intent(this, RecentIngredientsActivity::class.java)
}

class RecentIngredientsActivity : ChildActivity(), RecentIngredientsPresenter.View {

  private val presenter by lazy {
    RecentIngredientsPresenter(RecipeRepositoryImpl.getRepository(this))
  }

  companion object {
    const val EXTRA_INGREDIENTS_SELECTED = "EXTRA_INGREDIENTS_SELECTED"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_recent_ingredients)

    presenter.attachView(this)

    presenter.loadRecentIngredients()

    okButton.setOnClickListener {
      presenter.confirmSelectedIngredients()
    }
  }

  override fun onDestroy() {
    presenter.detachView()
    super.onDestroy()
  }

  // region RecentIngredientsPresenter.View methods
  override fun showRecentIngredients(list: List<String>) {
    ingredients.layoutManager = LinearLayoutManager(this)
    ingredients.adapter = RecentIngredientsAdapter(list,
        object : RecentIngredientsAdapter.Listener {
          override fun onSelectIngredient(ingredient: String) {
            presenter.selectIngredient(ingredient)
          }

          override fun onUnselectIngredient(ingredient: String) {
            presenter.unselectIngredient(ingredient)
          }
        })
  }

  override fun sendSelectedIngredients(list: List<String>) {
    val data = Intent()
    data.putStringArrayListExtra(EXTRA_INGREDIENTS_SELECTED, ArrayList(list))
    setResult(RESULT_OK, data)
    finish()
  }
  // endregion
}
