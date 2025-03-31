package com.joses.mali.landlord.domain

import kotlinx.serialization.Serializable

@Serializable
data class LandlordUser(
    val id: String,
    val apartmentName: String,
    val units: String,
    val tenants: String,
    val tillNumber: String,
    val phoneNumber: String,
)
