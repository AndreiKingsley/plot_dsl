package models

// TODO Interface
interface BindingSet

class SimpleBindingSet : BindingSet {
    var x: Binding? = null
    var y: Binding? = null
    var color: Binding? = null
    var size: Binding? = null
}

inline fun bind(bindings: SimpleBindingSet.() -> Unit) = SimpleBindingSet().apply(bindings)