// File Path: app/src/main/java/com/sirlasirliputeri/absenssp/AbsenApp.kt
package com.sirlasirliputeri.absenssp

import android.app.Application

class AbsenApp : Application() {

    companion object {
        // Dipakai SoundHelper buat akses Context tanpa harus ubah puluhan
        // titik pemanggilan playSukses()/playError() di seluruh app.
        lateinit var instance: AbsenApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
