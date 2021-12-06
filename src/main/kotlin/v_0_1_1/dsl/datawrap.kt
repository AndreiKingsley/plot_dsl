package v_0_1_1.dsl

import v_0_1_1.ir.models.aes.Aes
import v_0_1_1.ir.models.aes.NonPositionalAes
import v_0_1_1.ir.models.aes.PositionalAes

// todo
class DataSource<out T>(val id: String)

class PositionalMapping<T>(
    val aes: PositionalAes,
    val source: DataSource<T>
)

class NonPositionalMapping<R, T>(
    val aes: NonPositionalAes<R>,
    val source: DataSource<T>
)
