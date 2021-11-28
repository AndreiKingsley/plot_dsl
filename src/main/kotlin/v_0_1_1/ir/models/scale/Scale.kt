package v_0_1_1.ir.models.scale

import v_0_1_1.ir.models.Aes

interface Scale {
    //var aes: Aes
}

class ScaleContinuous : Scale {
   // override var aes: Aes = Aes("")
    // TODO val guide

    var trans: Trans = Trans.Identity

    // TODO GEOMTYPE
    var domainLimits: Pair<Any, Any>? = null
    var range: Pair<Any, Any>? = null
}


class ScaleCategorical : Scale {
   // override var aes: Aes = Aes("")
    // TODO val guide

    var categories: List<Any> = listOf()
    var values: List<Any> = listOf()
}

