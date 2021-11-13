package proto2

class XYBindings<T_X, T_Y> {
    lateinit var x: Binding<T_X>
    lateinit var y: Binding<T_Y>
}

class AesBindings<T_X, T_Y, T_SIZE, T_COLOR> {
    var x: Binding<T_X>? = null
    var y: Binding<T_Y>? = null
    //var color: Binding<T_SIZE>? = null
    //var size: Binding<T_COLOR>? = null
}
