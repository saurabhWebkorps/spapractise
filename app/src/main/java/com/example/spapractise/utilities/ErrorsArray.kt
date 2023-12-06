package com.example.spapractise.utilities

import android.os.Parcel
import android.os.Parcelable


    class ErrorsArray() : Parcelable {

        lateinit var errors: Array<String?>


        constructor(parcel: Parcel) : this() {
            errors = readStringArray(parcel,errors)
        }

        private fun readStringArray(parcel: Parcel, errors: Array<String?>): Array<String?> {

            val N: Int =parcel.readInt()
            if (N == errors.size) {
                for (i in 0 until N) {
                    errors[i] = parcel.readString()
                }
                return errors
            } else {
                throw RuntimeException("bad array lengths")
            }
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeStringArray(errors);
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ErrorsArray> {
            override fun createFromParcel(parcel: Parcel): ErrorsArray {
                return ErrorsArray(parcel)
            }

            override fun newArray(size: Int): Array<ErrorsArray?> {
                return arrayOfNulls(size)
            }
        }


    }


