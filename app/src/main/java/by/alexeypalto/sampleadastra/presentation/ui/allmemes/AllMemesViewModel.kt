package by.alexeypalto.sampleadastra.presentation.ui.allmemes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import by.alexeypalto.sampleadastra.domain.errors.ErrorHandler
import by.alexeypalto.sampleadastra.domain.state.Result
import by.alexeypalto.sampleadastra.domain.uscases.GetMemesUseCase
import by.alexeypalto.sampleadastra.presentation.base.delegates.ViewModelRecyclerDelegate
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewState
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewStateMapper
import kotlinx.coroutines.withContext
import timber.log.Timber

const val DEFAULT_MEMES_COUNT = 30

class AllMemesViewModel(
    private val getMemesUseCase: GetMemesUseCase,
    private val memesViewStateMapper: MemeViewStateMapper,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private var count: Int = DEFAULT_MEMES_COUNT

    val recyclerDelegate by lazy { ViewModelRecyclerDelegate(viewModelScope, ::loadData) }

    fun setMemesCount(count: Int) {
        this.count = count
    }

    private suspend fun loadData(): Result<List<MemeViewState>> {
        return withContext(viewModelScope.coroutineContext) {
            try {
                val result = getMemesUseCase(count)
                Result.Success(result.map(memesViewStateMapper::mapToViewState))
            } catch (e: Exception) {
                Timber.e(e)
                Result.Error(errorHandler(e))
            }
        }
    }

    class Factory(
        private val getAllMemesUseCase: GetMemesUseCase,
        private val mapper: MemeViewStateMapper,
        private val errorHandler: ErrorHandler
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AllMemesViewModel(getAllMemesUseCase, mapper, errorHandler) as T
        }
    }
}