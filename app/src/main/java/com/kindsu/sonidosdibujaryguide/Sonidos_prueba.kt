package com.kindsu.sonidosdibujaryguide

import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.media.ToneGenerator
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kindsu.sonidosdibujaryguide.databinding.ActivitySonidosPruebaBinding

class Sonidos_prueba : AppCompatActivity() {
    private lateinit var binding : ActivitySonidosPruebaBinding
    private var soundPool : SoundPool? = null //Manejar sonido
    private var audioManager : AudioManager? = null //Gestionar volumen
    private var streamType = AudioManager.STREAM_MUSIC //
    private var isLoaded = false // Verificar sonidos cargados
    private var sound1 = 0 //idPrimer audio
    private var sound2 = 0 //idSegundo audio
    private var volume: Float = 1.0f // Manejar volumen de los sonidos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySonidosPruebaBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tg = ToneGenerator(AudioManager.STREAM_NOTIFICATION, ToneGenerator.MAX_VOLUME) // Crear ToneGenerator para generar un tono de notificacion del sistema
        //Agregar el sonido de tg al boton pulsar
        binding.btnPulsar.setOnClickListener{
            tg.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT)
        }
        prepararSonidos()

        binding.btnDibujarAct.setOnClickListener{
            val intent = Intent(this, Dibujar_prueba::class.java)
            startActivity(intent)
        }
        binding.btnGlideAct.setOnClickListener{
            val intent = Intent(this, Glide_prueba::class.java)
            startActivity(intent)
        }
    }

    private fun prepararSonidos() {
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager?.let { audioManager ->
            val currentVolumeIndex = audioManager.getStreamVolume(streamType).toFloat()
            val maxVolumeIndex = audioManager.getStreamMaxVolume(streamType).toFloat()
            volume = currentVolumeIndex / maxVolumeIndex
            volumeControlStream = streamType
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            soundPool = SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(5)
                .build()
            soundPool!!.setOnLoadCompleteListener { soundPool, sampleId, status ->
                Log.d("Activity", "Se completo la carga: $soundPool, $sampleId, $status")
                isLoaded = true
            }
            sound1 = soundPool?.load(this, R.raw.navi, 1) ?: 0
            sound2 = soundPool?.load(this, R.raw.discord, 1) ?: 0

            binding.btnNavi.setOnClickListener {
                playSound(sound1)
            }
            binding.btnDiscord.setOnClickListener {
                playSound(sound2)
            }
        }

    }
    private fun playSound(sound: Int) {
        if(isLoaded) {
            val leftVolumn = volume
            val rightVolumn = volume
            val priority = 1
            val loop = 0
            val rate = 1f
            soundPool!!.play(sound, leftVolumn, rightVolumn, priority, loop, rate)
        }
    }
}