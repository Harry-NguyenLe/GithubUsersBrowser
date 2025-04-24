package com.tymex.interview.core.utils

fun Int?.formatCountAbbreviated(threshold: Int = 100): String {
    val count = this?.orZero()
    var result = ""
    count?.let {
        if (count < threshold) {
            result = this.toString()
        } else {
            val lowerBoundary = (count / 100) * 100
            result = "${lowerBoundary}+"
        }
    }
    return result
}

fun Int?.orZero(): Int = this ?: 0