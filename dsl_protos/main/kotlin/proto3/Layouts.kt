package proto3

class Layouts {
    var title: String? = null
    val xAxis = AxisLayout()
    val yAxis = AxisLayout()
}

enum class LineStyle {
    SOLID, DOT;
}

class AxisLayout {
    var enable = true

    var title: String? = null
    var style: LineStyle = LineStyle.SOLID
}

inline operator fun AxisLayout.invoke(body: AxisLayout.() -> Unit) = apply(body)
