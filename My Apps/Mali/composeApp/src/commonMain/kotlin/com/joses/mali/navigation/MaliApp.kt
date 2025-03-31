package com.joses.mali.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.joses.mali.landlord.repository.LandlordRepository
import com.joses.mali.landlord.ui.AllTenants
import com.joses.mali.landlord.ui.AllTenantsRoute
import com.joses.mali.landlord.ui.ApartmentPayments
import com.joses.mali.landlord.ui.ApartmentPaymentsRoute
import com.joses.mali.landlord.ui.LandlordsScreen
import com.joses.mali.landlord.ui.LandlordsScreenRoute
import com.joses.mali.landlord.ui.PastTenants
import com.joses.mali.landlord.ui.PastTenantsRoute
import com.joses.mali.landlord.ui.UnitPaymentsHistory
import com.joses.mali.landlord.ui.UnitPaymentsHistoryUi
import com.joses.mali.tenants.domain.UserRepository
import com.joses.mali.tenants.network.MpesaStkPushClient
import com.joses.mali.tenants.ui.LoginPage
import com.joses.mali.tenants.ui.LoginPageUi
import com.joses.mali.tenants.ui.PaymentHistory
import com.joses.mali.tenants.ui.PaymentHistoryUi
import com.joses.mali.tenants.ui.RentStatus
import com.joses.mali.tenants.ui.RentStatusUi
import com.joses.mali.tenants.ui.TenantSearchScreen
import com.joses.mali.ui.SelectionScreen
import com.joses.mali.tenants.ui.TenantsScreen
import com.joses.mali.tenants.ui.TenantsScreen2
import com.joses.mali.tenants.ui.UpcomingBill
import com.joses.mali.tenants.ui.UpcomingBillUi

@Composable
fun MaliApp(
    mpesaStkPushClient: MpesaStkPushClient,
    userRepository: UserRepository,
    onGeneratePdf: () -> Unit,
    generateApartmentPaymentReport: () -> Unit,
    onGeneratePastTenantPdf: () -> Unit,
    generateTenantPdf: () -> Unit
) {

    val navController = rememberNavController()
    val startDestinationString = LoginPageUi
    val landlordRepository = remember { LandlordRepository() }

    NavHost(
        navController = navController,
        startDestination = startDestinationString,
        Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {

        composable<LoginPageUi> {
            LoginPage(
                navController = navController
            )
        }

        composable<SelectionScreen> {
            val args = it.toRoute<SelectionScreen>()
            SelectionScreen(
                navController = navController,
                id = args.id,
                name = args.name,
                profilePicUrl = args.profilePicUrl,
            )
        }

        composable<LandlordsScreenRoute> {
            val args = it.toRoute<LandlordsScreenRoute>()
            LandlordsScreen(
                navController = navController,
                name = args.name
            )
        }

        composable<AllTenantsRoute> {
            val args = it.toRoute<AllTenantsRoute>()
            AllTenants(
                navController = navController,
                apartmentId = args.apartmentId,
                generateTenantPdf = { generateTenantPdf() }
            )
        }

        composable<ApartmentPaymentsRoute> {
            val args = it.toRoute<ApartmentPaymentsRoute>()
            ApartmentPayments(
                navController = navController,
                apartmentId = args.apartmentId,
                generateApartmentPaymentReport = { generateApartmentPaymentReport() }
            )
        }

        composable<PastTenantsRoute> {
            val args = it.toRoute<PastTenantsRoute>()
            PastTenants(
                navController = navController,
                apartmentId = args.apartmentId,
                onGeneratePastTenantPdf = { onGeneratePastTenantPdf() }
            )
        }

        composable<UnitPaymentsHistoryUi> {
            val args = it.toRoute<UnitPaymentsHistoryUi>()
            UnitPaymentsHistory(
                navController = navController,
                tenantId = args.tenantId
            )
        }

        composable<TenantsScreen2> {
            TenantSearchScreen(
                apartmentId = "",
                landlordRepository = landlordRepository,
                navController = navController
            )
//            TenantsScreen(
//                navController = navController,
//                mpesaStkPushClient = mpesaStkPushClient,
//                repository = userRepository,
//                onGeneratePdf = { onGeneratePdf() }
//            )
        }

        composable<TenantsScreen> {
//            TenantSearchScreen(
//                apartmentId = "",
//                landlordRepository = landlordRepository,
//                onTenantSelected = {
//                    navController.navigate(
//                        route = TenantsScreen
//                    )
//                },
//            )

            TenantsScreen(
                navController = navController,
                mpesaStkPushClient = mpesaStkPushClient,
                repository = userRepository,
                onGeneratePdf = { onGeneratePdf() }
            )
        }

        composable<PaymentHistoryUi> {
            val args = it.toRoute<PaymentHistoryUi>()
            PaymentHistory(
                navController = navController,
                tenantId = args.tenantId
            )
        }

        composable<RentStatusUi> {
            val args = it.toRoute<RentStatusUi>()
            RentStatus(
                navController = navController,
                tenantId = args.tenantId
            )
        }

        composable<UpcomingBillUi> {
            val args = it.toRoute<UpcomingBillUi>()
            UpcomingBill(
                navController = navController,
                tenantId = args.tenantId
            )
        }

    }

}
