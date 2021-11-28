package v_0_1_1.ir.models

data class Geom(val name: String){
    companion object {
        val POINT = Geom("point")
        val BAR = Geom("bar")
        val LINE = Geom("line")
    }
}
