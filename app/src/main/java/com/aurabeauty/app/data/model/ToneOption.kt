package com.aurabeauty.app.data.model

import java.io.Serializable

data class ToneOption(
    val name: String,
    val colorHex: String,
    var isSelected: Boolean = false
) : Serializable
