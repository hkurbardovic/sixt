package com.hariskurbardovic.sixt.network.connectivity

import android.content.Context
import com.hariskurbardovic.sixt.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (ConnectivityUtils.isConnected(context)) {
            chain.proceed(chain.request())
        } else {
            throw NoConnectivityException(
                context.getString(R.string.no_internet_connection)
            )
        }
    }
}