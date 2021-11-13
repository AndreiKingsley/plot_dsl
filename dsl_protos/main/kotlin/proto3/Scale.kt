package proto3

class Scale(val aes: Aes) {
    var limits: Pair<Float, Float>? = null
    var min: Float? = null
    var max: Float? = null
}

inline operator fun Scale.invoke(body: Scale.() -> Unit){
    this.apply(body)
}

class SimpleScales {
    val x = Scale(Aes.X)
    val y = Scale(Aes.Y)

    val color = Scale(Aes.COLOR)
    val size = Scale(Aes.SIZE)
}

