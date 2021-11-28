package v_0_1_1.echarts

import kotlinx.serialization.Serializable

@Serializable
data class Option(
    val dataset: Dataset,
    val xAxis: Axis,
    val yAxis: Axis,
    val visualMap: List<VisualMap>? = null,
    val series: List<Series>
)

@Serializable
data class Dataset(
    // TODO val source: List<List<@Contextual Any>>
    val source: List<List<String>>
)

@Serializable
data class Axis(
    val type: String,

    val min: String? = null,
    val max: String? = null,

    val data: List<String>? = null,
)

@Serializable
data class Series(
  //  val name: String,
    val type: String,
    val encode: XYEncode,
    val itemStyle: ItemStyle? = null,
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

@Serializable
data class VisualMap(
    val show: Boolean = false,
    val dimension: Int,

    val min: String? = null,
    val max: String? = null,

    val categories: List<String>? = null,

    val inRange: InRange
)

@Serializable
data class InRange(
    val symbolSize: List<String>? = null,
    val color: List<String>? = null
)