package com.example.ceramicanalizeapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform