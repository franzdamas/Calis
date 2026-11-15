package com.example.util

import kotlin.random.Random

object IdGenerator {
    private const val HEX_CHARS = "0123456789ABCDEF"

    private fun generateRandomHex(length: Int): String {
        val sb = StringBuilder(length)
        for (i in 0 until length) {
            sb.append(HEX_CHARS[Random.nextInt(HEX_CHARS.length)])
        }
        return sb.toString()
    }

    /**
     * TID Format: TCXXXXX (5 Random Hexadecimal Characters)
     */
    fun generateTID(): String = "TC${generateRandomHex(5)}"

    /**
     * SID Format: STXXXXX (5 Random Hexadecimal Characters)
     */
    fun generateSID(): String = "ST${generateRandomHex(5)}"

    /**
     * CID Format: CLSXXXXX (5 Random Hexadecimal Characters)
     */
    fun generateCID(): String = "CLS${generateRandomHex(5)}"

    /**
     * TKID Format: TKXXXXX (5 Random Hexadecimal Characters)
     */
    fun generateTKID(): String = "TK${generateRandomHex(5)}"
}
