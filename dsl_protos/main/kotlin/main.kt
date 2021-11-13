import proto3.*

class SomeDataSource {
    val bulgRate: SomeData<Float> = SomeData()
    val murderRate: SomeData<Float> = SomeData()
    val population: SomeData<Int> = SomeData()
    val city: SomeData<String> = SomeData()
}

enum class Color {
    RED;
}


fun main() {

    val data = SomeDataSource()

    plot {


        bind {
            x mapTo data.bulgRate
            y { data.murderRate }
        }

        points {
            bind {
                color assignTo "red"
                // same:  color.set(Color.RED)

                size mapTo data.population
            }

            scale {
                size {
                    min = 2F
                    max = 15F
                }

                x {
                    limits = 0F to 12F
                }
            }

            scale.y.limits = 0F to 1250F
        }

        text(size = 2.5F) {
            bind {
                label mapTo data.city
            }
        }

        layouts {
            title = "Crime rate"
            xAxis {
                title = "Murders per 1,000 population"
                style = LineStyle.SOLID
            }
            yAxis.title = "Burglaries per 1,000 population"
        }
    }
}