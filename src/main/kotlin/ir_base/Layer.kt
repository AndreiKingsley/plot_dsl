package ir_base

import ir_base.scale.Scale

interface Layer {
    //val data: JointData
    //val data: Map<String, Any>?

    val aesBindings: Map<Aes, BindingParameter>

    val geom: Geom


    // dataset
    val data: Map<String, Any>

    // constant values e.g. color, size
    val styles: Map<String, Any>

    val dataScales: Map<String, Scale<Any, Any>>

    val stylesScales: Map<String, Scale<Any, Any>>


}
