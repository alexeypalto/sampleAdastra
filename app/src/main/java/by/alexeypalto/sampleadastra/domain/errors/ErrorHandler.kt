package by.alexeypalto.sampleadastra.domain.errors

interface ErrorHandler {
    operator fun invoke(throwable: Throwable): Exception
}