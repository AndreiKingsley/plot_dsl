package ir_base

import ir_base.scale.Coord
import ir_base.scale.Scale

interface PlotSpec {
    val layers: List<Layer>

    val scales: Map<String, Scale<Any, Any>>

    val posScaleToCoord: Map<String, Coord>

    //val layoutParameters: TODO (name, size, etc.)
}