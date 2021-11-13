package models

interface AbstractDataSource<T, Ind> {
    fun get(ind: Ind): T
}
