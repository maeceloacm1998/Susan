package com.app.core.utils

import kotlin.random.Random

object Utils {
    fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis()
    }

    fun generateRandomId(): Int {
        return Random.nextInt()
    }
}