package com.joses.mali.landlord.repository

import com.joses.mali.landlord.domain.AddTenant
import com.joses.mali.landlord.domain.LandlordDataRepository
import com.joses.mali.landlord.domain.LandlordUser
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.flow.flow

class LandlordRepository : LandlordDataRepository {

    private val firestore = Firebase.firestore

    override fun getApartments() = flow {
        firestore.collection("Apartments").snapshots.collect { querySnapshot ->
            val users = querySnapshot.documents.map { documentSnapshot ->
                documentSnapshot.data<LandlordUser>()
            }
            emit(users)
        }
    }

    override fun getApartmentById(id: String) = flow {
        firestore.collection("Apartments").document(id).snapshots.collect { documentSnapshot ->
            emit(documentSnapshot.data<LandlordUser>())
        }
    }

    override suspend fun addApartment(user: LandlordUser) {
        val userId = generateRandomStringId()
        firestore.collection("Apartments")
            .document(userId)
            .set(user.copy(id = userId))
    }

    override suspend fun updateApartment(user: LandlordUser) {
        firestore.collection("Apartments").document(user.id).set(user)
    }

    override suspend fun deleteApartment(user: LandlordUser) {
        firestore.collection("Apartments").document(user.id).delete()
    }

    // Function to add a tenant to an apartment
    override suspend fun addTenant(
        apartmentId: String,
        tenant: AddTenant
    ) {
        val tenantId = generateRandomStringId() // Generate unique ID for tenant
        firestore.collection("Apartments")
            .document(apartmentId)
            .collection("tenants") // Subcollection for tenants
            .document(tenantId)
            .set(tenant.copy(id = tenantId)) // Store with the generated ID
    }

    // Function to retrieve tenants for a specific apartment
    override fun getTenants(
        apartmentId: String
    ) = flow {
        firestore.collection("Apartments")
            .document(apartmentId)
            .collection("tenants")
            .snapshots.collect { querySnapshot ->
                val tenants = querySnapshot.documents.map { documentSnapshot ->
                    documentSnapshot.data<AddTenant>()
                }
                emit(tenants)
            }
    }

    override fun getPastTenants(
        apartmentId: String
    ) = flow {
        firestore.collection("Apartments")
            .document(apartmentId)
            .collection("PastTenants")
            .snapshots.collect { querySnapshot ->
                val tenants = querySnapshot.documents.map { documentSnapshot ->
                    documentSnapshot.data<AddTenant>()
                }
                emit(tenants)
            }
    }

    // **🔹 Update tenant details**
    override suspend fun updateTenant(
        apartmentId: String,
        tenantId: String,
        updatedTenant: AddTenant
    ) {
        try {
            val tenantRef = firestore.collection("Apartments")
                .document(apartmentId)
                .collection("tenants")
                .document(tenantId)

            val tenantSnapshot = tenantRef.get()

            if (tenantSnapshot.exists) {
                tenantRef.set(updatedTenant) // Update only if the tenant exists
            } else {
                throw Exception("Tenant with ID $tenantId does not exist.")
            }
        } catch (e: Exception) {
            throw Exception("Failed to update tenant: ${e.message}")
        }
    }

    // **🔹 Delete a tenant**
    override suspend fun deleteTenant(apartmentId: String, tenantId: String) {
        try {
            val firestore = Firebase.firestore

            val tenantRef = firestore.collection("Apartments")
                .document(apartmentId)
                .collection("tenants")
                .document(tenantId)

            val pastTenantsRef = firestore.collection("Apartments")
                .document(apartmentId)
                .collection("PastTenants")
                .document(tenantId)

            firestore.runTransaction<Boolean> {
                val tenantSnapshot = get(tenantRef)

                if (tenantSnapshot.exists) {
                    // ✅ Deserialize Firestore document correctly
                    val tenantData = tenantSnapshot.data<AddTenant>() ?: throw Exception("Data is null")

                    set(pastTenantsRef, tenantData) // ✅ Move to "PastTenants"
                    delete(tenantRef) // ✅ Delete from "tenants"
                }

                true
            }

            println("✅ Tenant moved to PastTenants successfully!")
        } catch (e: Exception) {
            throw Exception("❌ Failed to move tenant: ${e.message}")
        }
    }

    suspend fun searchAllTenants(query: String): List<AddTenant> {
        val firestore = Firebase.firestore

        // Perform a collection group query on all 'tenants' subcollections
        val tenantsQuery = firestore.collectionGroup("tenants")
            .where("name", equalTo = query) // Adjust field and condition as needed

        val tenants = tenantsQuery.get().documents.mapNotNull { it.data<AddTenant>() }

        return tenants
    }

    suspend fun searchTenants(apartmentId: String, query: String): List<AddTenant> {
        val firestore = Firebase.firestore

        // Reference to the tenants collection
        val tenantsRef = firestore
            .collection("Apartments")
            .document(apartmentId)
            .collection("tenants")

        // Query for tenants matching the exact name
        val matchingByName = tenantsRef
            .where { "name" equalTo query }
            .get()
            .documents
            .map { it.data<AddTenant>() }

        // Query for tenants matching the exact phone number
        val matchingByPhone = tenantsRef
            .where { "phoneNumber" equalTo query }
            .get()
            .documents
            .map { it.data<AddTenant>() }

        // Combine results and remove duplicates
        return (matchingByName + matchingByPhone).distinctBy { it.id }
    }

    private fun generateRandomStringId(length: Int = 20): String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

}