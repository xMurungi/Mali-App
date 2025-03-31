package com.joses.mali.landlord.domain

import kotlinx.serialization.Serializable

@Serializable
data class AddTenant(
    val id: String,
    val name: String,
    val unitAllocated: String,
    val phoneNumber: String,
    val email: String
)
