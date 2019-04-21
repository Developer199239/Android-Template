package com.raywenderlich.android.ingredisearch

import android.app.Application
import com.raywenderlich.android.ingredisearch.repository.RecipeRepository
import com.raywenderlich.android.ingredisearch.repository.RecipeRepositoryImpl
import com.raywenderlich.android.ingredisearch.search.SearchPresenter
import com.raywenderlich.android.ingredisearch.searchResults.SearchResultsPresenter

open class IngredisearchApp : Application() {

  open fun getRecipeRepository(): RecipeRepository {
    return RecipeRepositoryImpl.getRepository(this)
  }

  fun getSearchResultsPresenter() = SearchResultsPresenter(getRecipeRepository())

  fun getSearchPresenter() = SearchPresenter(getRecipeRepository())
}