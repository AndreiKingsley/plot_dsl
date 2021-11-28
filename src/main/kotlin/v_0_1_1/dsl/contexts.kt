package v_0_1_1.dsl

import v_0_1_1.ir.models.*
import v_0_1_1.ir.models.scale.*

open class Context {
    var dataset: NamedData? = null

    var mappings: MutableMap<Aes, String> = mutableMapOf()
    var settings: MutableMap<Aes, Any> = mutableMapOf()
    var scales: MutableMap<Aes, Scale> = mutableMapOf()

    infix fun Aes.mapTo(id: String): Aes {
        mappings[this] = id
        return this
    }

    infix fun Aes.setTo(value: Any): Aes {
        settings[this] = value
        return this
    }

    infix fun Aes.scaleContinuous(block: ScaleContinuous.() -> Unit) {
        scales[this] = ScaleContinuous().apply(block)
    }

    infix fun Aes.scaleCategorical(block: ScaleCategorical.() -> Unit) {
        scales[this] = ScaleCategorical().apply(block)
    }

    infix fun Aes.scale(scale: Scale) {
        scales[this] = scale
    }

    // TODO more
    val x = Aes("x")
    val y = Aes("y")

    val size = Aes("size")
    val color = Aes("color")
    val fill = Aes("fill")

    val width = Aes("width")
}

class LayerContext : Context() {
    var geom: Geom? = null

    fun copyFrom(other: Context) {
        dataset = other.dataset?.toMutableMap()
        mappings = other.mappings.toMutableMap()
        settings = other.settings.toMutableMap()
        scales = other.scales.toMutableMap()
    }
}

class PlotContext : Context() {
    val layers: MutableList<Layer> = mutableListOf()
}
