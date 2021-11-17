package ir_base.data

interface LinearData<out T> {
    val indices: List<Int>
    operator fun get(index: Int): T
}
