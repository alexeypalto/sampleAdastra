package by.alexeypalto.sampleadastra.presentation.viewstate

interface Mapper<M, VS> {
    operator fun invoke(entity: M): VS
}