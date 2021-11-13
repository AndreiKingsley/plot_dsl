package proto2


class Binding<T> {
    enum class Type {
        MAPPING, ASSIGNMENT;
    }

    var type: Type? = null

    var mapping: Data<T>? = null

    var value: T? = null
}

 infix fun<T> Binding<T>.mapTo(data: Data<T>){
    type = Binding.Type.MAPPING
    this.mapping = data
}

 infix fun<T> Binding<T>.assignTo(value: T){
    type = Binding.Type.ASSIGNMENT
    this.value = value
}

 infix fun<T> Binding<T>.to(data: Data<T>){
    type = Binding.Type.MAPPING
    this.mapping = data
}

/*
infix fun<T> Binding<T>.to(value: T){
    type = Binding.Type.ASSIGNMENT
    this.value = value
}

 */

