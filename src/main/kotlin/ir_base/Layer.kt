package ir_base

import ir_base.data.*
import ir_base.scale.*

interface Layer {
    // dataset
    val data: NamedData

    // style attributes e.g. color, size, ... Todo is it AES?
    val style: Map<String, Any>

    // geom flag
    val geom: Geom

    //scales
    val dataScales: List<Pair<String, Scale<Any, Any>>>
    val stylesScales: List<Pair<String, Scale<Any, Any>>>

}
