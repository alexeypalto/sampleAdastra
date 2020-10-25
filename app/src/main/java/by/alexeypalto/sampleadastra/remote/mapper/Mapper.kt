package by.alexeypalto.sampleadastra.remote.mapper

interface Mapper<R, M> {
    operator fun invoke(response: R): M
}