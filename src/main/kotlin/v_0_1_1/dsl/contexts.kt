package v_0_1_1.dsl

import v_0_1_1.ir.models.*
import v_0_1_1.ir.models.aes.*
import v_0_1_1.ir.models.scale.*

open class Context {
    var dataset: NamedData? = null

    var mappings: MutableMap<Aes, DataSource<Any>> = mutableMapOf()
    var settings: MutableMap<Aes, Any> = mutableMapOf()
    var scales: MutableMap<Aes, Scale> = mutableMapOf()

    infix fun<T: Any> PositionalAes.mapTo(dataSource: DataSource<T>): PositionalMapping<T> {
        mappings[this] = dataSource
        return PositionalMapping(
            this,
            dataSource
        )
    }

    infix fun<T: Any, R> NonPositionalAes<R>.mapTo(dataSource: DataSource<T>): NonPositionalMapping<R, T> {
        mappings[this] = dataSource
        return NonPositionalMapping(
            this,
            dataSource
        )
    }

    // TODO positional set
    infix fun<T> NonPositionalAes<T>.setTo(value: T) {
        settings[this] = value!! // TODO()
    }

    infix fun<T> PositionalMapping<T>.scaleContinuous(block: (ContinuousPositionalScale<T>.() -> Unit)) {
        scales[this.aes] = ContinuousPositionalScale<T>().apply(block)
    }

    infix fun<R, T> NonPositionalMapping<R, T>.scaleContinuous(block: (ContinuousNonPositionalScale<T, R>.() -> Unit)) {
        scales[this.aes] = ContinuousNonPositionalScale<T, R>().apply(block)
    }

    infix fun<T> PositionalMapping<T>.scaleCategorical(block: (CategoricalPositionalScale<T>.() -> Unit)) {
        scales[this.aes] = CategoricalPositionalScale<T>().apply(block)
    }

    infix fun<R, T> NonPositionalMapping<R, T>.scaleCategorical(block: (CategoricalNonPositionalScale<T, R>.() -> Unit)) {
        scales[this.aes] = CategoricalNonPositionalScale<T, R>().apply(block)
    }

    /*
    infix fun Aes.scale(scale: Scale) {
        scales[this] = scale
    }

     */

    // TODO more
    val x = X
    val y = Y

    val size = SIZE
    val color = COLOR

}

class LayerContext : Context() {
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
