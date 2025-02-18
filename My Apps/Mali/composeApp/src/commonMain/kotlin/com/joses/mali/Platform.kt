package com.joses.mali

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform