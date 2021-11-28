package v_0_1_1

import jetbrains.letsPlot.*
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomBar
import jetbrains.letsPlot.geom.geomErrorBar
import jetbrains.letsPlot.label.xlab
import jetbrains.letsPlot.label.ylab
import jetbrains.letsPlot.scale.scaleColorManual
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import v_0_1_0.echarts.wrapLayer
import v_0_1_1.dsl.layer
import v_0_1_1.dsl.plot
import v_0_1_1.dsl.scaleContinuous
import v_0_1_1.echarts.toOption
import v_0_1_1.ir.models.Geom
import v_0_1_1.letsplot.toPlot
import java.io.File

fun main() {
    val plot = plot {
        dataset = mapOf(
            "name" to arrayOf("Biba", "Boba", "Boba", "Biba"),
            "iq" to arrayOf(12, 13, 76, 88),
            "year" to arrayOf(2011, 2012, 2021, 2021),
        )

        //x mapTo "name" //  doesnt work for echarts
        x mapTo "name" scaleCategorical {}

        val commonYScale = scaleContinuous {
            domainLimits = 0 to 100
        }

        size setTo 1

        layer {
            geom = Geom.BAR
            y mapTo "iq" scale(commonYScale)
            fill mapTo "year" scaleCategorical {}

            width setTo 0.1
        }

        layer {
            geom = Geom.LINE
            y mapTo "iq" scale(commonYScale)

            size setTo 3
            color setTo "#ff00ff"
        }

        /*
        layer {
            geom = line {
                size = ..
                color = ...
            }
        }

         */


    }

    val json = Json {
        explicitNulls = false
        encodeDefaults = true
    }
    File("echarts/chart.json").writeText(json.encodeToString(plot.toOption()))

   // ggsave(plot.toPlot(), "kek.png")

    /*
    ggsave(
        letsPlot(mapOf(
            "name" to arrayOf("Biba", "Boba", "Boba", "Biba"),
            "iq" to arrayOf(12, 13, 76, 88),
            "year" to arrayOf(2011, 2012, 2021, 2021),
        )) + geomBar(position = Pos.dodge) {
            x = "name"
            y = "iq"
            fill = "year"
        },
        "kek2.png"
    )

     */
/* TODO kek behavior
    val data = mapOf(
        "supp" to listOf("OJ", "OJ", "OJ", "VC", "VC", "VC"),
        "dose" to listOf(0.5, 1.0, 2.0, 0.5, 1.0, 2.0),
        "length" to listOf(13.23, 22.70, 26.06, 7.98, 16.77, 26.14),
        "len_min" to listOf(11.83, 21.2, 24.50, 4.24, 15.26, 23.35),
        "len_max" to listOf(15.63, 24.9, 27.11, 10.72, 19.28, 28.93)
    )

    val p1 = letsPlot(data) {x="dose"}


    ggsave(p1 +
            geomBar(stat= Stat.identity, position=Pos.dodge, color="black") {y="length"; fill="supp"},
        "kek4.png")


    val dataset = mapOf(
        "name" to arrayOf("Biba", "Boba"),
        "iq" to arrayOf(1, 10),
    )



    ggsave(letsPlot(dataset) + geomBar {x = "name"; y = "iq"}, "kek.png")

     */
}