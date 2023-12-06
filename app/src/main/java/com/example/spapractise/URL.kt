package com.example.spapractise

object URL {
    //const val BASE_URL = "http://139.99.8.202:3000/"

    //    const val BASE_URL = "https://spa-native.herokuapp.com/"
    const val BASE_URL = "https://admin.mytipsdubai.com/"
    const val TERMS_AND_CONDITIONS = "https://www.mytipsdubai.com/terms-and-conditions/"
    //https://www.mytipsdubai.com/about-us/
    const val SUPPORT_MAIL = "contact@mytipsdubai.com"
    const val USER = "/users"
    const val V2_USER = "api/v2/users"
    const val LOGIN = "$USER/login"
    const val REGISTER = "$USER/registration"
    const val V2_REGISTER = "$V2_USER/registration"
    const val USER_PROFILE = "$USER/profile"
    const val UPDATE_PHONE = "$USER/update_mobile_number"
    const val RESET_PASSWORD = "$USER/reset_password"
    const val TRANSACTION = "transactions"
    const val VERSION_MANAGER = "/version_manager"

    const val BANK_ACCOUNTS = "/bank_accounts"
    const val DELETE_BANK_ACCOUNTS = "/bank_accounts/"
    const val USER_UPDATE = "$USER/update"
    const val GRAPH_DATA = "$USER/graph_data"
    const val V2_USER_UPDATE = "$V2_USER/update"
    const val QR_CODES = "/qr_codes"
    const val WALLET = "$USER/wallet"
    const val WITHDRAWL_REQUESTS = "$BASE_URL/withdrawal_requests"
}