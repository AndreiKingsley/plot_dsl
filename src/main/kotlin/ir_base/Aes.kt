package ir_base

class Aes(val name: String) {
    companion object {
        val X = Aes("x")
        val Y = Aes("y")

        val COLOR = Aes("color")
        val SIZE = Aes("size")

        val LABEL = Aes("label")
    }

    override fun toString(): String = name
}