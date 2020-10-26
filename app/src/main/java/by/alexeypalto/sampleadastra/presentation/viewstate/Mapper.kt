package by.alexeypalto.sampleadastra.presentation.viewstate

interface Mapper<M, VS> {
    fun mapToViewState(entity: M): VS
    fun mapToModel(vs: VS): M
}