package by.alexeypalto.sampleadastra.local.database.mapper

interface Mapper<M, E> {
    fun mapToEntity(model: M): E
    fun mapToModel(entity: E): M
}