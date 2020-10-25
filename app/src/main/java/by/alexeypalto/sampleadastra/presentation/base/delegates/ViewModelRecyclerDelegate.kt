package by.alexeypalto.sampleadastra.presentation.base.delegates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import by.alexeypalto.sampleadastra.domain.state.Result

class ViewModelRecyclerDelegate<VS> private constructor(
    private val scope: CoroutineScope,
    private inline val suspendRequest: (suspend () -> Result<List<VS>>)?,
    private inline val flowRequest: (() -> Flow<Result<List<VS>>>)?
) {

    constructor(scope: CoroutineScope, request: suspend () -> Result<List<VS>>) : this(scope, request, null)
    constructor(scope: CoroutineScope, request: () -> Flow<Result<List<VS>>>) : this(scope, null, request)

    private val _itemsList by lazy { MutableLiveData<List<VS>>() }
    val itemsList: LiveData<List<VS>> = _itemsList

    private val _refreshStateLiveData by lazy { MutableLiveData<Result<Unit>>() }
    val refreshStateLiveData: LiveData<Result<Unit>> = _refreshStateLiveData

    private var job: Job? = null

    init {
        loadData()
    }

    fun refresh() {
        loadData()
    }

    private fun loadData() {
        job?.cancel()
        job = scope.launch {
            _refreshStateLiveData.postValue(Result.Loading)
            suspendRequest?.let { updateResult(it()) }
            flowRequest?.let { it() }?.collect { updateResult(it) }
        }
    }

    private fun updateResult(result: Result<List<VS>>) {
        when (result) {
            is Result.Success -> {
                _itemsList.postValue(result.data)
                _refreshStateLiveData.postValue(Result.Success(Unit))
            }
            is Result.Error -> {
                _refreshStateLiveData.postValue(result)
            }
            else -> { }
        }
    }

}