package v_0_1_0

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import v_0_1_0.echarts.wrapLayer
import v_0_1_0.ir.*
import v_0_1_0.letsplot.LayerWrapper
import v_0_1_0.letsplot.toPlot
import java.io.File

fun main(){
    val dataset = mutableMapOf<String, List<Any>>(
        "gdp" to listOf("x", "x", "x", "y", "y", "y"),
        "life exp." to listOf(77, 77.4, 68, 81.8, 81.7, 76.9),

        "population" to listOf(17096869, 27662440, 1154605773, 23968973, 35939927, 1376048943),
        "year" to listOf(1990, 1990, 1990, 2015, 2015, 2015)
    )

    val layer = points {
        data = dataset

        map {
            "x" to "gdp"
            "y" to "life exp."

            "size" to "population"
            "color" to "year"
        }

        set {
            "alpha" to 0.5
        }

        scaleContinuous {
            aes = "size"

            range = 7 to 17
            domainLimits = 10000000 to 1500000000
        }

        scaleCategorical {
            aes = "color"

            categories = listOf(1990, 2015)
            values = listOf("red", "green")
        }
    }

    /*
    var p2 = letsPlot(dataset)
    //p2 += LayerWrapper(layer)

    p2 += geomPoint {
        x = "gdp"
        y = "life exp."

        size = "population"
        color = "year"
    } + scaleColorManual(values = listOf("red", "green"),  format = "4d", breaks = listOf(1990, 2015)) + scaleSize(range = 7 to 17, limits = 10000000 to 1500000000) + scaleXContinuous(limits = 0 to 50000) +
            scaleYContinuous(limits = 0 to 100)



    ggsave(p2, "my-plot.png")
    */

    val plot = layer.toPlot()
    ggsave(plot, "plot.png")

    val json = Json {
        explicitNulls = false
        encodeDefaults = true
    }
    File("echarts/chart.json").writeText(json.encodeToString(wrapLayer(layer)))
}

inline fun points(function: SimpleLayer.() -> Unit): SimpleLayer {
    return SimpleLayer().apply(function).apply {
        geom = Point
    }
}

class MapContext<T> {
    val innerMap: MutableMap<String, T> = mutableMapOf()

    infix fun String.to(value: T){
        innerMap[this] = value
    }
}

inline fun SimpleLayer.map(block: MapContext<String>.() -> Unit) {
    mappings = MapContext<String>().apply(block).innerMap
}

inline fun SimpleLayer.set(block: MapContext<Any>.() -> Unit) {
    settings = MapContext<Any>().apply(block).innerMap
}

inline fun SimpleLayer.scaleCategorical(block: CategoricalScale.() -> Unit) {
    scales.add(CategoricalScale().apply(block))
}

inline fun SimpleLayer.scaleContinuous(block: ContinuousScale.() -> Unit) {
    scales.add(ContinuousScale().apply(block))
}
