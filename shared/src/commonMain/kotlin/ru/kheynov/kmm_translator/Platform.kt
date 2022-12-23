package ru.kheynov.kmm_translator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform