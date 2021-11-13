package models

sealed interface Binding

// TODO discuss index
class Mapping<T, Index>: Binding {
    var mapping: ((Index) -> T?)? = null // move to constructor?
}

fun<T> values(list: List<T>): Mapping<T, Int>{
    val mapping = Mapping<T, Int>()
    mapping.mapping = { i -> list.getOrNull(i)}
    return mapping
}

class Assigment<T>: Binding {
    var value: T? = null // move to constructor?
}

fun<T> const(value: T): Assigment<T>{
    val assigment = Assigment<T>()
    assigment.value = value
    return assigment
}
