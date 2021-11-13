package proto2

class SimplePlot {
    var activeXYBinding: XYBindings<Any, Any>? = null
    //var activeBinding: AesBindings<T_X, T_Y, T_SIZE, T_COLOR>? = null
    //val layers: MutableList<Layer> = mutableListOf()
}

inline fun plot(body: SimplePlot.() -> Unit): SimplePlot{
    return SimplePlot().apply(body)
}

inline fun SimplePlot.bind(
    body: XYBindings<Any, Any>.() -> Unit
): XYBindings<Any, Any> {
    val bindingSet = XYBindings<Any, Any>().apply(body)
    activeXYBinding = bindingSet
    return bindingSet
}


/*
inline fun<T_X, T_Y, T_SIZE, T_COLOR> plot(body: SimplePlot<T_X, T_Y, T_SIZE, T_COLOR>.() -> Unit): SimplePlot<T_X, T_Y, T_SIZE, T_COLOR>{
    return SimplePlot<T_X, T_Y, T_SIZE, T_COLOR>().apply(body)
}

inline fun<T_X, T_Y, T_SIZE, T_COLOR> SimplePlot<T_X, T_Y, T_SIZE, T_COLOR>.bind(
    body: AesBindings<T_X, T_Y, T_SIZE, T_COLOR>.() -> Unit
): AesBindings<T_X, T_Y, T_SIZE, T_COLOR> {
    val bindingSet = AesBindings<T_X, T_Y, T_SIZE, T_COLOR>().apply(body)
    activeBinding = bindingSet
    return bindingSet
}

 */

/*
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

 */