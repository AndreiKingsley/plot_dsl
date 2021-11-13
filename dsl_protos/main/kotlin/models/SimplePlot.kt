package models

class SimplePlot {
    var activeBinding: BindingSet? = null
    val layers: MutableList<Layer> = mutableListOf()
}

inline fun plot(body: SimplePlot.() -> Unit): SimplePlot{
    return SimplePlot().apply(body)
}

inline fun SimplePlot.bind(body: SimpleBindingSet.() -> Unit): SimpleBindingSet {
    val bindingSet = SimpleBindingSet().apply(body)
    activeBinding = bindingSet
    return bindingSet
}

inline fun SimplePlot.layer(body: SimpleLayer.() -> Unit): SimpleLayer {
    val newLayer = SimpleLayer().apply(body)
    layers.add(newLayer)
    return newLayer
}

inline fun SimplePlot.points(body: SimpleLayer.() -> Unit): SimpleLayer {
    val newLayer = SimpleLayer().apply {
        dataBinding = activeBinding
    }

    newLayer.apply(body).apply {
        geom = Geom.POINT // TODO
    }
    layers.add(newLayer)
    return newLayer
}