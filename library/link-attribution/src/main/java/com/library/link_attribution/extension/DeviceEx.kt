package com.library.link_attribution.extension

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.provider.Settings
import java.net.Inet4Address
import java.net.NetworkInterface

fun Context?.getOsVersion(): String? {
    return Build.VERSION.RELEASE
}
fun Context?.getSdkVersion(): Int? {
    return Build.VERSION.SDK_INT
}

fun Context?.getDeviceModel(): String? {
    return Build.MODEL
}
fun Context?.getManufacturer(): String? {
    return Build.MANUFACTURER
}
fun Context?.getDeviceName(): String? {
    return Settings.Secure.getString(
        this?.contentResolver,
        "bluetooth_name"
    )
}