package com.joses.mali.landlord.domain

import kotlinx.coroutines.flow.Flow

interface LandlordDataRepository {
    fun getApartments(): Flow<List<LandlordUser>>
    fun getApartmentById(id: String): Flow<LandlordUser?>
    suspend fun addApartment(user: LandlordUser)
    suspend fun updateApartment(user: LandlordUser)
    suspend fun deleteApartment(user: LandlordUser)
    suspend fun addTenant(apartmentId: String, tenant: AddTenant)
    fun getTenants(apartmentId: String): Flow<List<AddTenant>>
    fun getPastTenants(apartmentId: String): Flow<List<AddTenant>>
    suspend fun deleteTenant(apartmentId: String,  tenantId: String)
    suspend fun updateTenant(apartmentId: String,  tenantId: String,  updatedTenant: AddTenant)
}
