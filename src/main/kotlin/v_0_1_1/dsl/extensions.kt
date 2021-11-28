package v_0_1_1.dsl

import v_0_1_1.ir.models.*

fun PlotContext.toPlot(): Plot {
    return Plot(dataset, layers)
}

fun plot(block: PlotContext.() -> Unit): Plot {
    return PlotContext().apply(block).toPlot()
}

fun PlotContext.layer(block: LayerContext.() -> Unit) {
    // TODO dataset
    layers.add(LayerContext().apply { copyFrom(this@layer) }.apply(block).toLayer())
}
/*
fun Plot.points(block: LayerContext.() -> Unit) {
    layers.add(LayerContext().apply(block).apply { geom = Geom.POINTS }.toLayer())
}

 */

fun LayerContext.toLayer(): Layer {
    return Layer(dataset, geom, mappings, settings, scales)
}