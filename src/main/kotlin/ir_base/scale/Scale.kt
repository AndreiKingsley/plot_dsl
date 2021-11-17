package ir_base.scale

import ir_base.Aes
import ir_base.ID

interface Scale<RawValue, GeomValue> {
    val id: ID

    // coordinate system pointer
    val coordID: ID?

    // mark of physical meaning
    // TODO как связана с GeomValue????
    val aes: Aes?

    // settings for guide TODO
    val guideSettings: GuideSettings?

    // values out of this limits will not be displayed
    val rawValuesLimits: Pair<RawValue?, RawValue?>?

    // transform range limits
    val geomValuesLimits: Pair<GeomValue?, GeomValue?>?

    val transform: ((RawValue) -> GeomValue)?
}

/*
interface PositionalScale<RawValue, GeomValue>: Scale<RawValue, GeomValue> {

    // TODO where grid?

    override val aes: Aes?

    override val guideSettings: GuideSettings

    override val limits: Pair<GeomValue?, GeomValue?>?

    override val transform: ((RawValue) -> GeomValue)?
}


 */
