package v_0_1_1.ir.models

import v_0_1_1.dsl.DataSource
import v_0_1_1.ir.models.aes.Aes
import v_0_1_1.ir.models.scale.Scale

class Layer(
    val data: NamedData?,
    var geom: Geom,
    val mappings: Map<Aes, DataSource<Any>>,
    val settings: Map<Aes, Any>,
    val scales: Map<Aes, Scale>
) {
    // TODO: GeomValues Type???
}
