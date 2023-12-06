package com.example.spapractise.ui.auth.dataclass

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("country_code")
    val country_code: String?=null,

    @SerializedName("country_chars")
    val country_chars: String?=null,

    @SerializedName("email")
    val email: String?=null,

    @SerializedName("first_name")
    val first_name: String?=null,

    @SerializedName("last_name")
    val last_name: String?=null,

    @SerializedName("mobile_number")
    val mobile_number: String?=null,

    @SerializedName("password")
    val password: String?=null,

    @SerializedName("password_confirmation")
    val password_confirmation: String?=null,

    @SerializedName("otp_code")
    val otp_code: String?=null,

    @SerializedName("business_id_card")
    val business_id_card: String?=null,

    @SerializedName("about_me")
    val about_me: String?=null,

    @SerializedName("dob")
    val dob: String?=null,

    ): Parcelable
