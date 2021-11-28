package v_0_1_0.echarts

import v_0_1_0.ir.*

fun wrapData(data: NamedData): Pair<List<List<String>>, Map<String, Int>>{
    val header = data.keys.toList()
    val size = data.values.first().size
    val idToDim = header.mapIndexed { index, s -> s to index }.toMap()

    val source = mutableListOf<List<String>>()
    source.add(header)
    for (i in 0 until size){
        source.add(
            header.map { data[it]!![i].toString() }
        )
    }

    return source to idToDim
}


fun Geom.toType(): String{
    return "scatter" // TODO
}

fun List<Scale>.toVisualMap(aesToDim: Map<String, Int>): List<VisualMap>{
    return map {
        when (it) {
            is CategoricalScale -> with(it) {
                val dimension = aesToDim[aes]!! // TODO
                val categoriesString = categories.map { value -> value.toString() }
                val valuesString = values.map { value -> value.toString() }
                val inRange = when (aes) {
                    "color" -> InRange(color = valuesString)
                    "size" -> InRange(symbolSize = valuesString)
                    else -> InRange() // TODO
                }
                VisualMap(
                    show = showLegend,
                    dimension = dimension,
                    categories = categoriesString,
                    inRange = inRange,
                )
            }
            is ContinuousScale -> with(it) {
                val dimension = aesToDim[aes]!! // TODO
                val min = domainLimits?.first.toString()
                val max = domainLimits?.second.toString()
                val valuesString = listOf(range?.first.toString(), range?.second    .toString())
                val inRange = when (aes) {
                    "color" -> InRange(color = valuesString)
                    "size" -> InRange(symbolSize = valuesString)
                    else -> InRange() // TODO
                }
                VisualMap(
                    show = showLegend,
                    dimension = dimension,
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
}

fun wrapLayer(layer:SimpleLayer): Option {
    val (source, idToDim) = wrapData(layer.data!!.toMap())
    val aesToDim = layer.scales.map { it -> it.aes!! to idToDim[layer.mappings[it.aes]!!]!!}.toMap()
    return Option(
        dataset = Dataset(source),
        xAxis = Axis("value"),
        yAxis = Axis("value"),
        visualMap = layer.scales.toVisualMap(aesToDim),
        series = listOf(Series(
            name = "data",
            type = layer.geom!!.toType(),
            encode = XYEncode(
                x = layer.mappings["x"]!!,
                y = layer.mappings["y"]!!
            ),
            itemStyle = ItemStyle(
                opacity = layer.settings["alpha"]!! as Double
            )
        ))
    )
}