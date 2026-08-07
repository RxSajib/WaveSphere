package com.zenbyte.studio.presentation.viewmodel.utils

import android.content.Context
import android.telephony.TelephonyManager

object Extras {

    fun getSimCountry(context: Context): String {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.simCountryIso
    }
}