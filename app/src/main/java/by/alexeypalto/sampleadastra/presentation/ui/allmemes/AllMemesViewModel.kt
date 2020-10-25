package by.alexeypalto.sampleadastra.presentation.ui.allmemes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import by.alexeypalto.sampleadastra.domain.state.Result
import by.alexeypalto.sampleadastra.presentation.base.delegates.ViewModelRecyclerDelegate
import by.alexeypalto.sampleadastra.domain.errors.ErrorHandler
import by.alexeypalto.sampleadastra.domain.models.Meme
import by.alexeypalto.sampleadastra.domain.uscases.AddMemeToFavoriteUseCase
import by.alexeypalto.sampleadastra.domain.uscases.GetMemesUseCase
import by.alexeypalto.sampleadastra.domain.uscases.RemoveMemeFromFavoriteUseCase
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewState
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewStateMapper
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

const val DEFAULT_MEMES_COUNT = 30

class AllMemesViewModel @Inject constructor(
    private val getMemesUseCase: GetMemesUseCase,
    private val addMemeToFavoriteUseCase: AddMemeToFavoriteUseCase,
    private val removeMemeFromFavoriteUseCase: RemoveMemeFromFavoriteUseCase,
    private val memesViewStateMapper: MemeViewStateMapper,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    var count: Int = DEFAULT_MEMES_COUNT

    val recyclerDelegate by lazy { ViewModelRecyclerDelegate(viewModelScope, ::loadData) }

    fun setMemesCount(count: Int) {
        this.count = count
    }

    private suspend fun loadData(): Result<List<MemeViewState>>{
        return withContext(viewModelScope.coroutineContext) {
            try {
                val result = getMemesUseCase(count)
                Result.Success(result.map(memesViewStateMapper::invoke))
            } catch(e: Exception) {
                Timber.e(e)
                Result.Error(errorHandler(e))
            }
        }
    }

    fun addMemeToFavorite(memeViewState: MemeViewState) {
        viewModelScope.launch {
            try {
                addMemeToFavoriteUseCase.invoke(convertVSToModel(memeViewState))
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun removeMemeFromFavorite(id: Int) {
        viewModelScope.launch {
            try {
                removeMemeFromFavoriteUseCase.invoke(id)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun convertVSToModel(viewState: MemeViewState): Meme {
        return Meme(
            id = viewState.id,
            url = viewState.url,
            createdAt = viewState.createdAt
        )
    }
}