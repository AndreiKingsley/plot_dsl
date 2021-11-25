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

fun wrapLayer(layer:SimpleLayer): Option {
    val (source, idToDim) = wrapData(layer.data!!.toMap())
    return Option(
        dataset = Dataset(source),
        xAxis = Axis("value"),
        yAxis = Axis("value"),
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