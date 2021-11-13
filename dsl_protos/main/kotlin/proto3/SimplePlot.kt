package proto3

class SimplePlot {
    var activeDataBinding: SimpleBindings = SimpleBindings()
    val layers: MutableList<SimpleLayer> = mutableListOf()

    var layoutsSettings = Layouts()
}

inline fun plot(body: SimplePlot.() -> Unit): SimplePlot {
    return SimplePlot().apply(body)
}

inline fun SimplePlot.bind(body: SimpleBindings.() -> Unit): SimpleBindings {
    val bindingSet = SimpleBindings().apply(body)
    activeDataBinding = bindingSet
    return bindingSet
}

inline fun SimplePlot.layouts(body: Layouts.() -> Unit) {
    val layouts = Layouts().apply(body)
    layoutsSettings = layouts
}

inline fun SimplePlot.layer(body: SimpleLayer.() -> Unit): SimpleLayer {
    val newLayer = SimpleLayer().apply(body)
    layers.add(newLayer)
    return newLayer
}


inline fun SimplePlot.points(body: SimpleLayer.() -> Unit): SimpleLayer {
    val newLayer = SimpleLayer().apply {
        dataBinding = activeDataBinding
    }

    newLayer.apply(body).apply {
        geomKind = GeomKind.POINT // TODO
    }
    layers.add(newLayer)
    return newLayer
}

inline fun SimplePlot.text(size: Float, body: SimpleLayer.() -> Unit): SimpleLayer {
    val newLayer = SimpleLayer().apply {
        dataBinding = activeDataBinding.apply {
            this.size assignTo size
        }
    }

    newLayer.apply(body).apply {
        geomKind = GeomKind.TEXT // TODO
    }
    layers.add(newLayer)
    return newLayer
}

