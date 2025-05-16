package org.tesis.ie.ceramic

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform