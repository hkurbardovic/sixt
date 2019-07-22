package com.hariskurbardovic.sixt.network.connectivity

import java.io.IOException

class NoConnectivityException(private val errorMessage: String) : IOException() {

    override fun getLocalizedMessage(): String {
        return errorMessage
    }
}