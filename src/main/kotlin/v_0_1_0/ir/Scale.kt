package v_0_1_0.ir

interface Scale {
    var aes: String?
    var showLegend: Boolean
}

class CategoricalScale: Scale {
    override var aes: String? = null
    override var showLegend: Boolean = false

    var categories = listOf<Any>()
    var values = listOf<Any>()
}

class ContinuousScale: Scale {
    override var aes: String? = null
    override var showLegend: Boolean = false

    var domainLimits: Pair<Number, Number>? = null
    var range: Pair<Number, Number>? = null
}
