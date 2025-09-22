package com.example.birthdayapp.utils

import com.plusmobileapps.konnectivity.Konnectivity
import java.net.Inet4Address
import java.net.NetworkInterface

class DeviceInfoProvider {

    fun isDeviceOnline(): Boolean {
        return Konnectivity().isConnected
    }

    fun getLocalIpAddress(): String? {
        try {
            val netWorkInterfaces = NetworkInterface.getNetworkInterfaces()
            for (networkInterface in netWorkInterfaces) {
                val addresses = networkInterface.inetAddresses
                for (address in addresses) {
                    if (!address.isLoopbackAddress && address is Inet4Address) {
                        return address.hostAddress
                    }
                }
            }
            return null
        } catch (ex: Exception) {
            return null
        }
    }


}