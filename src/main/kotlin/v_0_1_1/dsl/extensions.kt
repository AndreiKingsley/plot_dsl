package v_0_1_1.dsl

import v_0_1_1.ir.models.*
import v_0_1_1.ir.models.scale.guide.*

fun PlotContext.toPlot(): Plot {
    return Plot(dataset, layers)
}

fun plot(block: PlotContext.() -> Unit): Plot {
    return PlotContext().apply(block).toPlot()
}

fun LayerContext.toLayer(geom: Geom): Layer {
    return Layer(dataset, geom, mappings, settings, scales)
}

operator fun Axis.invoke(block: Axis.() -> Unit) {
    this.apply(block)
}

operator fun Guide.invoke(block: Guide.() -> Unit) {
    this.apply(block)
}
