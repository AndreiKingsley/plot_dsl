package v_0_1_1.ir.models.aes

sealed class Aes(val name: String)

class PositionalAes(name: String): Aes(name)

class NonPositionalAes<T>(name: String): Aes(name)
