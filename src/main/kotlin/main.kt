
/*
import ir_base.*
import ir_base.data.*
import jetbrains.datalore.base.json.Arr
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomDensity
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.lets_plot
import letsplot.LayerWrapper
import kotlin.reflect.KType

/*
class TestLayer(
    override val data: Map<String, Any>?,
): Layer{
    override val aesBindings: Map<Aes, BindingParameter> = mapOf(
        Aes.X to MappingParameter(0),
        Aes.Y to MappingParameter(1),

        Aes.COLOR to AssigmentParameter("red"),
        Aes.SIZE to AssigmentParameter(1.5)
    )
    override val geom: Geom = Point
}


 */


data class A(var a: Int)
data class B(val b: A)

fun main(){

   val f = mapOf(1 to A(1), 2 to A(2))

    f[1]?.a = 2

    println(f[1])

    /*
    val rand = java.util.Random()
    val data = mapOf (
        "dim0" to (1..15).toList(),
        "dim1" to (1..15).map { it * it }
    )

    var p = letsPlot(data)
    p += geomPoint(color="red", size=1.5) {x="dim0"; y="dim1"}
    p + ggsize(700, 350)

    ggsave(p, "lets-plot.png")


    val myLayer = TestLayer(data)
    val lpLayer = LayerWrapper(myLayer)

    var p2 = letsPlot()
    p2 += lpLayer
    p2 + ggsize(700, 350)

    ggsave(p2, "my-plot.png")

        */
}

 */

fun main(){

}