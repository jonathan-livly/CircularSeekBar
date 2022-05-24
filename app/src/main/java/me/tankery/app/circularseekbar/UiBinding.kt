package me.tankery.app.circularseekbar

import android.graphics.Paint.Cap
import android.support.annotation.ColorInt

/**
 * Example class for data binding preferred fields
 */
data class UiBinding(
    val progress: Int? = null,
    @ColorInt val pointerFillColor: Int? = null,
    val pointerInnerBorderWidthPixels: Float? = null,
    val isNegativeEnabled: Boolean? = null,
    val circleStyle: Cap? = null,
    val circleStrokeWidthPixels: Float? = null,
    val pointerStrokeWidthPixels: Float? = null,
    val pointerHaloWidthPixels: Float? = null
)
