// File Path: app/src/main/java/com/sirlasirliputeri/absenssp/util/SoundHelper.kt
package com.sirlasirliputeri.absenssp.util

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import com.sirlasirliputeri.absenssp.AbsenApp
import java.util.Locale

/**
 * Audio Feedback System berbasis ToneGenerator (tanpa file audio eksternal, hemat ukuran
 * APK). Memakai kombinasi 2 nada DTMF pendek untuk menghasilkan bunyi "chime" modern --
 * mirip nada konfirmasi pembayaran contactless -- dan bukan lagi bunyi "beep" mesin lama.
 *
 * Kalau Mode Meme aktif (lihat PrefsHelper.isMemeMode), bunyi chime diganti suara TTS
 * (Text-to-Speech) bawaan Android yang teriak kata-kata meme Indo -- masih tanpa file
 * audio eksternal sama sekali, cuma "disuruh ngomong" pakai suara sistem HP.
 */
object SoundHelper {

    private val handler = Handler(Looper.getMainLooper())

    private val fraseSukses = listOf("Anjay, mantap!", "Wih gaskan!", "Sikat, absen sukses!", "Mantap jiwa!")
    private val fraseSudahAbsen = listOf("Santuy, udah kecatet!", "Woy udah absen tadi!", "Yah, jangan diulang-ulang!")
    private val fraseError = listOf("Waduh, error!", "Yah gagal coy!", "Aduh, ditolak nih!")

    // Inisialisasi TTS lazy -- baru dibuat pas Mode Meme pertama kali dipakai.
    private var tts: TextToSpeech? = null
    private var ttsReady = false

    private fun pastikanTtsSiap() {
        if (tts != null) return
        tts = TextToSpeech(AbsenApp.instance.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale("id", "ID")
                ttsReady = true
            }
        }
    }

    /** Ngomong satu frase meme dengan nada/kecepatan tertentu, lalu lepas fokus audio singkat. */
    private fun ucapkanMeme(frase: String, pitch: Float, speechRate: Float) {
        pastikanTtsSiap()
        val engine = tts ?: return
        if (!ttsReady) return // kalau TTS belum siap (jarang, hanya sesaat setelah app buka), diam saja -- tidak crash
        engine.setPitch(pitch)
        engine.setSpeechRate(speechRate)
        engine.speak(frase, TextToSpeech.QUEUE_FLUSH, null, "absen_meme_utt")
    }

    /** Sukses: 2 nada naik cepat, ceria dan singkat -- atau teriakan "anjay mantap" ala meme. */
    fun playSukses() {
        val context = AbsenApp.instance.applicationContext
        if (PrefsHelper.isMemeMode(context)) {
            ucapkanMeme(fraseSukses.random(), pitch = 1.35f, speechRate = 1.15f)
            return
        }
        val tg = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 90)
        tg.startTone(ToneGenerator.TONE_DTMF_6, 70)
        handler.postDelayed({
            tg.startTone(ToneGenerator.TONE_DTMF_9, 110)
            handler.postDelayed({ tg.release() }, 160)
        }, 80)
    }

    /** Sudah absen: 1 nada datar, netral -- atau sindiran santai ala meme. */
    fun playSudahAbsen() {
        val context = AbsenApp.instance.applicationContext
        if (PrefsHelper.isMemeMode(context)) {
            ucapkanMeme(fraseSudahAbsen.random(), pitch = 1.0f, speechRate = 1.0f)
            return
        }
        val tg = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 75)
        tg.startTone(ToneGenerator.TONE_DTMF_7, 150)
        handler.postDelayed({ tg.release() }, 230)
    }

    /** Error / tidak dikenal: 2 nada turun -- atau teriakan "waduh error" ala meme. */
    fun playError() {
        val context = AbsenApp.instance.applicationContext
        if (PrefsHelper.isMemeMode(context)) {
            ucapkanMeme(fraseError.random(), pitch = 0.75f, speechRate = 0.95f)
            return
        }
        val tg = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 95)
        tg.startTone(ToneGenerator.TONE_DTMF_4, 110)
        handler.postDelayed({
            tg.startTone(ToneGenerator.TONE_DTMF_1, 160)
            handler.postDelayed({ tg.release() }, 240)
        }, 120)
    }
}
