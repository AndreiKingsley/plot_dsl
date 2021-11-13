package proto3

class Binding(val aes: Aes, var parameter: BindingParameter? = null) {
}

infix operator fun <T> Binding.invoke(body: () -> Data<T>) {
    parameter = MappingParameter(body.invoke())
}

infix fun <T> Binding.mapTo(data: Data<T>) {
    parameter = MappingParameter(data)
}

infix fun <T> Binding.assignTo(value: T) {
    parameter = AssigmentParameter(value)
}

fun <T> Binding.set(value: T) {
    parameter = AssigmentParameter(value)
}

class SimpleBindings {
    val x = Binding(Aes.X)
    val y = Binding(Aes.Y)

    val color = Binding(Aes.COLOR)
    val size = Binding(Aes.SIZE)

    val label = Binding(Aes.LABEL)
}
