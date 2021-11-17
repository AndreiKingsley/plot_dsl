package ir_base

import ir_base.scale.Coord
import ir_base.scale.Scale

interface PlotSpec {
    val layers: List<Layer>

    val scales: Map<ID, Scale<Any, Any>>

    val coords: Map<ID, Coord>

    //val layoutParameters: TODO (name, size, etc.)
}