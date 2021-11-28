package v_0_1_0.letsplot

import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.scaleColorContinuous
import jetbrains.letsPlot.scale.scaleColorManual
import jetbrains.letsPlot.scale.scaleSize
import jetbrains.letsPlot.scale.scaleSizeManual
import v_0_1_0.ir.*

class LayerWrapper(val layer: SimpleLayer) :
    jetbrains.letsPlot.intern.layer.LayerBase(
        data = layer.data,
        mapping = Options(layer.mappings),
        geom= layer.geom.toLPGeom(),
        stat = Stat.identity,
        position = Pos.identity,
        showLegend = true,
    ) {
    override fun seal() = Options(layer.settings)
}

fun Geom?.toLPGeom(): jetbrains.letsPlot.intern.layer.GeomOptions = jetbrains.letsPlot.Geom.point() // TODO

fun Scale.wrap(): jetbrains.letsPlot.intern.Scale {
    return when(this){
        is CategoricalScale -> {
            when(aes) {
                "size" -> scaleSizeManual(values = values.map { it as Double }, breaks = categories)
                "color" -> scaleColorManual(values=values, breaks = categories)
                else -> TODO()
            }
        }
        is ContinuousScale -> {
            when(aes) {
                "size" -> scaleSize(range = range, limits = domainLimits)
                "color" -> scaleColorContinuous() // TODO
                else -> TODO()
            }
        }
        else -> TODO()
    }
}

fun SimpleLayer.toPlot(): jetbrains.letsPlot.intern.Plot {
    var plot = letsPlot() + LayerWrapper(this)
    scales.forEach { plot += it.wrap() }
    return plot
}
