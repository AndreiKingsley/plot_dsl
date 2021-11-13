package letsplot

import ir_base.*
import jetbrains.letsPlot.Pos.identity
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.intern.layer.GeomOptions
import jetbrains.letsPlot.intern.layer.LayerBase
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.intern.layer.StatOptions

class LayerWrapper(val layer: Layer) :
    jetbrains.letsPlot.intern.layer.LayerBase(
        data = layer.data,
        mapping = layer.mappingOptions(),
        geom= layer.geom.toLPGeom(),
        stat = Stat.identity,
        position = identity,
        showLegend = true,
    ) {
    override fun seal() = layer.assignmentOptions()
}

fun Geom.toLPGeom(): jetbrains.letsPlot.intern.layer.GeomOptions = jetbrains.letsPlot.Geom.point() // TODO

fun Layer.mappingOptions() = Options(
    aesBindings
        .filter { it.value is MappingParameter }
        .map {
                (key, value) -> key.name to "dim${(value as MappingParameter).dimension}"
        }.toMap()
)

fun Layer.assignmentOptions() = Options(
    aesBindings
        .filter { it.value is AssigmentParameter<*> }
        .map {
                (key, value) -> key.name to (value as AssigmentParameter<*>).value
        }.toMap()
)
