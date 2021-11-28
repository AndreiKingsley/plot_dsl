package v_0_1_1.ir.models

import v_0_1_1.ir.models.scale.Scale

class Layer(
    val data: NamedData?,
    var geom: Geom? = null,
    val mappings: Map<Aes, String>,
    val settings: Map<Aes, Any>,
    val scales: Map<Aes, Scale>
) {
    // TODO: GeomValues Type???
}
