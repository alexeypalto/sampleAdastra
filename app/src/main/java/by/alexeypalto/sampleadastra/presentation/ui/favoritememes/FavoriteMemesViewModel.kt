package by.alexeypalto.sampleadastra.presentation.ui.favoritememes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import by.alexeypalto.sampleadastra.domain.errors.ErrorHandler
import by.alexeypalto.sampleadastra.domain.state.Result
import by.alexeypalto.sampleadastra.domain.uscases.GetFavoriteMemesUseCase
import by.alexeypalto.sampleadastra.presentation.base.delegates.ViewModelRecyclerDelegate
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewState
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewStateMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class FavoriteMemesViewModel(
    private val getFavoriteMemesUseCase: GetFavoriteMemesUseCase,
    private val memesViewStateMapper: MemeViewStateMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val errorHandler: ErrorHandler
) : ViewModel()  {

    val recyclerDelegate by lazy { ViewModelRecyclerDelegate(viewModelScope, ::loadData) }

    private suspend fun loadData(): Result<List<MemeViewState>> {
        return withContext(dispatcher) {
            try {
                val result = getFavoriteMemesUseCase()
                Result.Success(result.map(memesViewStateMapper::mapToViewState))
            } catch (e: Exception) {
                Timber.e(e)
                Result.Error(errorHandler(e))
            }
        }
    }

    fun refresh() {
        recyclerDelegate.refresh()
    }

    class Factory(
        private val getFavoriteMemesUseCase: GetFavoriteMemesUseCase,
        private val memesViewStateMapper: MemeViewStateMapper,
        private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO,
        private val errorHandler: ErrorHandler

    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FavoriteMemesViewModel(
                getFavoriteMemesUseCase,
                memesViewStateMapper,
                dispatcherIO,
                errorHandler
            ) as T
        }
    }
}