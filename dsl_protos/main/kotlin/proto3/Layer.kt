package proto3

class SimpleLayer() {
    var dataBinding: SimpleBindings? = null
    var scale: SimpleScales = SimpleScales()
    var geomKind: GeomKind? = null
}

inline fun SimpleLayer.bind(
    body: SimpleBindings.() -> Unit
): SimpleBindings {
    val bindingSet = SimpleBindings().apply(body)
    dataBinding = bindingSet
    return bindingSet
}

inline fun SimpleLayer.scale(body: SimpleScales.() -> Unit) {
    val scales = if (scale == null) {
        SimpleScales()
    } else {
        scale
    }
    this.scale = scales
}
