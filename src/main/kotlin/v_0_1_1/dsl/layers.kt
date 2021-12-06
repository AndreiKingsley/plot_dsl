package v_0_1_1.dsl

import v_0_1_1.ir.models.Geom

fun PlotContext.points(block: LayerContext.() -> Unit) {
    // TODO dataset
    layers.add(LayerContext().apply { copyFrom(this@points) }.apply(block).toLayer(Geom.POINT))
}
