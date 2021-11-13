package models

interface Layer {
    var dataBinding: BindingSet?
    var geom: Geom?
    var stat: Stat?
    var position: Position?
}

class SimpleLayer(): Layer {
    override var dataBinding: BindingSet? = null
    override var geom: Geom? = null
    override var stat: Stat? = null
    override var position: Position? = null
}


