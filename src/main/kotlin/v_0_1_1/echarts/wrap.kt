package v_0_1_1.echarts

import v_0_1_1.ir.models.*
import v_0_1_1.ir.models.scale.*

fun wrapData(data: NamedData): Pair<List<List<String>>, Map<String, Int>> {
    val header = data.keys.toList()
    val size = data.values.first().size
    val idToDim = header.mapIndexed { index, s -> s to index }.toMap()

    val source = mutableListOf<List<String>>()
    source.add(header)
    for (i in 0 until size) {
        source.add(
            header.map { data[it]!![i].toString() }
        )
    }

    return source to idToDim
}


fun Geom.toType(): String {
    return when (this) {
        Geom.POINT -> "scatter"
        Geom.BAR -> "bar"
        Geom.LINE -> "line"
        else -> TODO()
    }
}

fun Scale.toVisualMap(aes: Aes, dim: Int): VisualMap {
    return when (this) {
        is ScaleCategorical -> {
            val categoriesString = categories.map { value -> value.toString() }
            val valuesString = values.map { value -> value.toString() }
            val inRange = when (aes.name) {
                "color" -> InRange(color = valuesString)
                "fill" -> InRange(color = valuesString)
                "size" -> InRange(symbolSize = valuesString)
                else -> InRange() // TODO
            }
            VisualMap(
                show = false, // TODO
                dimension = dim,
                categories = categoriesString,
                inRange = inRange,
            )
        }
        is ScaleContinuous -> {
            val min = domainLimits?.first.toString()
            val max = domainLimits?.second.toString()
            val valuesString = listOf(range?.first.toString(), range?.second.toString())
            val inRange = when (aes.name) {
                "color" -> InRange(color = valuesString)
                "fill" -> InRange(color = valuesString)
                "size" -> InRange(symbolSize = valuesString)
                else -> InRange() // TODO
            }
            VisualMap(
                show = false, // TODO
                dimension = dim,
                min = min,
                max = max,
                inRange = inRange,
            )
        }
        else -> {
            TODO()
        }
    }
}

fun Scale.toAxis(): Axis {
    return when (this) {
        is ScaleCategorical -> {
            Axis(
                type = "category",
                data = if (categories.isEmpty()){
                    null
                } else {
                    categories.map { value -> value.toString() }
                }
            )
        }
        is ScaleContinuous -> {
            Axis(
                type = "value",
                min = domainLimits?.first?.toString(),
                max = domainLimits?.second?.toString(),
            )
        }
        else -> {
            TODO()
        }
    }
}

fun Layer.toSeries(): Series{
    // TODO STYLE
    return Series(
        type = geom!!.toType(),
        encode = XYEncode(
            x = mappings[Aes("x")]!!,
            y = mappings[Aes("y")]!!
        ),
    )
}

fun Plot.toOption(): Option {
    val (source, idToDim) = wrapData(dataset!!.toMap())
    // TODO!!!

    val visualMap = mutableListOf<VisualMap>()
    var xAxis = Axis("value")
    var yAxis = Axis("value")

    layers.forEach { layer ->
        layer.scales.forEach { (aes, scale) ->
            if (aes.name == "x"){
                xAxis = scale.toAxis()
            } else if (aes.name == "y") {
                yAxis = scale.toAxis()
            } else {
                visualMap.add(scale.toVisualMap(aes, idToDim[layer.mappings[aes]!!]!!)) // TODO
            }
        }
    }

    return Option(
        dataset = Dataset(source),
        xAxis = xAxis,
        yAxis = yAxis,
        visualMap = visualMap,
        series = layers.map { it.toSeries() }
    )
}