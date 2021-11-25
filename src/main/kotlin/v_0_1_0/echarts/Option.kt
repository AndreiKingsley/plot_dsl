package v_0_1_0.echarts

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Option(
    val dataset: Dataset,
    val xAxis: Axis,
    val yAxis: Axis,
    val series: List<Series>
)

@Serializable
data class Dataset(
    // TODO val source: List<List<@Contextual Any>>
    val source: List<List<String>>
)

@Serializable
data class Axis(
    val type: String
)

@Serializable
data class Series(
    val name: String,
    val type: String,
    val encode: XYEncode,
    val itemStyle: ItemStyle,
)

@Serializable
data class XYEncode(
    val x: String,
    val y: String
)

@Serializable
data class ItemStyle(
    val opacity: Double,
)
