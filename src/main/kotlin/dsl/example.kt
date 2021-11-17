package dsl

import dsl.data.toLD
import ir_base.Aes
import ir_base.Point
import ir_base.data.NamedData
import javax.naming.Name

fun main(){


    val dataset: NamedData = mapOf(
        "rate1" to arrayOf(0.1, 0.2, 3.4, 5.5).toLD(),
        "rate2" to arrayOf(23.4, 2.3, 12.3, 0.4).toLD(),
    )

    val dataset2: NamedData = mapOf(
        "city" to arrayOf("SPb", "Moscow", "Nizhniy", "Rostov-na-Donu").toLD(),
    )

    val mainCartesianCoords = coords()

    val plot = plot {
        data = dataset

        data.add {
            "population" to arrayOf(1231241, 214124, 123123, 875).toLD()
        }

        val commonX = scale {
            coords = mainCartesianCoords

            aes = Aes.X

            axis {
                major {
                    breaks = 10
                }
            }

            // TODO limits in DSL

            limits = -1.0 to 8.5
        }

        val commonY = scale {
            coords = mainCartesianCoords

            aes = Aes.Y

            transform = { value ->
                Position( value / 100.0)
            }
        }

        val colorFromName = scale {
            aes = Aes.COLOR

            guide = null

            transform = { colorName -> Color.fromName(colorName) }
        }

        layer {
            geom = Point

            apply {
                "rate1" to commonX
                "rate2" to commonY

                "population" to scale {
                    aes = Aes.SIZE

                    // TODO limits types in DSL
                    limits = 3 to 15
                }

                "color" to colorFromName
            }

            style {
                "color" to "red"
            }
        }

        layer {
            geom = Text

            data.add(dataset2)

            apply {
                "city" to scale {
                    aes = Aes.LABEL
                }

                "size" to to scale {
                    aes = Aes.SIZE
                }

                "color" to colorFromName
            }

            style {
                "size" to 3
                "color" to "grey"
            }
        }
    }
}