package com.tymex.interview.core.utils

/**
 * This event helper is used to prevent monkey click on back button
 * */
class DebounceEventHelper {

    private val now: Long
        get() = System.currentTimeMillis()
    private var lastEventTimeMs: Long = 0

    fun debounceEvent(event: () -> Unit) {
        if (now - lastEventTimeMs >= 500L) {
            event.invoke()
        }
        lastEventTimeMs = now
    }

    companion object {
        @Volatile
        private var instance: DebounceEventHelper? = null
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: DebounceEventHelper().also { instance = it }
            }
    }
}
