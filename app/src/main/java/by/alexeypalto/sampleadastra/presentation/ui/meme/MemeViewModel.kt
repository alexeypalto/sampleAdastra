package by.alexeypalto.sampleadastra.presentation.ui.meme

import androidx.lifecycle.*
import by.alexeypalto.sampleadastra.domain.uscases.AddMemeToFavoriteUseCase
import by.alexeypalto.sampleadastra.domain.uscases.IsMemeExistUseCase
import by.alexeypalto.sampleadastra.domain.uscases.RemoveMemeFromFavoriteUseCase
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewState
import by.alexeypalto.sampleadastra.presentation.viewstate.MemeViewStateMapper
import kotlinx.coroutines.launch
import timber.log.Timber

class MemeViewModel(
    private val addMemeToFavoriteUseCase: AddMemeToFavoriteUseCase,
    private val removeMemeFromFavoriteUseCase: RemoveMemeFromFavoriteUseCase,
    private val isMemeExistUseCase: IsMemeExistUseCase,
    private val mapperViewState: MemeViewStateMapper
) : ViewModel() {

    val isFavoriteMemeLiveData: LiveData<Boolean>
        get() = _isFavoriteMemeLiveData
    private val _isFavoriteMemeLiveData = MutableLiveData<Boolean>()

    fun checkIsMemeExists(id: String) {
        viewModelScope.launch {
            try {
                val isExist = isMemeExistUseCase(id)
                _isFavoriteMemeLiveData.postValue(isExist)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun actionDB(viewState: MemeViewState) {
        viewModelScope.launch {
            try {
                val isFavorite = _isFavoriteMemeLiveData.value ?: false
                if (isFavorite) removeMemeFromFavoriteUseCase(viewState.id)
                else addMemeToFavoriteUseCase(mapperViewState.mapToModel(viewState))

                _isFavoriteMemeLiveData.value = !isFavorite
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

    }

    class Factory(
        private val addMemeToFavoriteUseCase: AddMemeToFavoriteUseCase,
        private val removeMemeFromFavoriteUseCase: RemoveMemeFromFavoriteUseCase,
        private val isMemeExistUseCase: IsMemeExistUseCase,
        private val mapperViewState: MemeViewStateMapper
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MemeViewModel(addMemeToFavoriteUseCase,removeMemeFromFavoriteUseCase, isMemeExistUseCase, mapperViewState) as T
        }
    }
}