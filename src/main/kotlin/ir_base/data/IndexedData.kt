package ir_base.data

interface IndexedData<out T, Index> {
    val indices: List<Index>
    operator fun get(index: Index): T
}
