package v_0_1_1.letsplot

import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.*
import v_0_1_1.ir.models.*
import v_0_1_1.ir.models.scale.*

class LayerWrapper(val layer: Layer) :
    jetbrains.letsPlot.intern.layer.LayerBase(
        data = layer.data,
        mapping = Options(layer.mappings.map { (aes, id) -> aes.name to id }.toMap()),
        geom = layer.geom.toLPGeom(),
        stat = Stat.identity,
        position = Pos.dodge, // TODO
        showLegend = true,
    ) {
    override fun seal() = Options(layer.settings.map { (aes, id) -> aes.name to id }.toMap())
}

fun Geom?.toLPGeom(): jetbrains.letsPlot.intern.layer.GeomOptions {
    return when(this!!){
        Geom.POINT -> jetbrains.letsPlot.Geom.point() // TODO
        Geom.BAR -> jetbrains.letsPlot.Geom.bar()
        Geom.LINE -> jetbrains.letsPlot.Geom.line()
        else -> TODO()
    }
}

fun Scale.wrap(aes: Aes): jetbrains.letsPlot.intern.Scale {
    return when (this) {
        is ScaleCategorical -> {
            when (aes.name) {
                "x" -> scaleXDiscrete(limits = categories) // TODO values -> incorrect
                "y" -> scaleYDiscrete(limits = categories)

                "size" -> scaleSizeManual(values = values.map { it as Double }, breaks = categories)
                "color" -> scaleColorManual(values = values, breaks = categories)
                "fill" -> scaleFillDiscrete() // TODO Discrete and Manual
                else -> TODO()
            }
        }
        is ScaleContinuous -> {
            when (aes.name) {
                "x" -> scaleXContinuous(limits = domainLimits.toLP()) // TODO range -> incorrect
                "y" -> scaleYContinuous(limits = domainLimits.toLP())

                "size" -> scaleSize(range = range.toLP(), limits = domainLimits.toLP())
                "color" -> scaleColorContinuous(
                    low = range?.first.toString(),
                    high = range?.first.toString(),
                    limits = domainLimits.toLP()
                ) // TODO
                else -> TODO()
            }
        }
        else -> TODO()
    }
}

fun Pair<Any, Any>?.toLP() = this?.let { (it.first as Number) to (it.second as Number) }

fun Plot.toPlot(): jetbrains.letsPlot.intern.Plot {
    // TODO
    return layers.fold(letsPlot(dataset)) { plot, layer ->
        var buffer = plot + LayerWrapper(layer)
        layer.scales.forEach { (aes, scale) -> buffer += scale.wrap(aes) }
        buffer
    }
}
