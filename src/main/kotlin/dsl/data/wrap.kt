package dsl.data

import ir_base.data.LinearData

class ArrayData<T>(private val array: Array<T>) : LinearData<T> {
    override val indices: List<Int> = (0..array.size).toList()

    override fun get(index: Int): T = array[index]

}

fun <T> Array<T>.toLD() = ArrayData(this)
