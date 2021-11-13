package ir_base.scale

import ir_base.Aes

interface Scale<RawValue, GeomValue> {

    // TODO where grid?

    val aes: Aes?

    val guideSettings: GuideSettings

    val limits: Pair<GeomValue?, GeomValue?>?

    val transform: ((RawValue) -> GeomValue)?
}

interface PositionalScale<RawValue, GeomValue>: Scale<RawValue, GeomValue> {

    // TODO where grid?

    override val aes: Aes?

    override val guideSettings: GuideSettings

    override val limits: Pair<GeomValue?, GeomValue?>?

    override val transform: ((RawValue) -> GeomValue)?
}
