package by.alexeypalto.sampleadastra.remote.errors

import by.alexeypalto.sampleadastra.domain.errors.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {

    override fun invoke(throwable: Throwable): Exception {
        return when (throwable) {
            is IOException -> Exception("IO error")
            is HttpException -> Exception("server error")
            else -> Exception("something went wrong")
        }
    }
}