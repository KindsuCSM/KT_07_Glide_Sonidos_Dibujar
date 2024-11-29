package com.kindsu.sonidosdibujaryguide

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.kindsu.sonidosdibujaryguide.databinding.ActivityGlidePruebaBinding

class Glide_prueba : AppCompatActivity() {
    /*Para usar Glide hay que poner en dependencies de build.gradle.kt
    * implementation ("com.github.bumptech.glide:glide:4.16.0")
    *   annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")*/

    /*Hay que darle permisos a la aplicación para acceder a internet, nos vamos al manifest y ponemos:
    *   "<uses-permission android:name="android.permission.INTERNET"/>"*/

    private lateinit var binding : ActivityGlidePruebaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGlidePruebaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Añadir imagenes a los ImageView, tiene que ser el enlace directo a la imagen
        Glide.with(this)
            .load("https://static.wikia.nocookie.net/gatopedia/images/2/2e/El_gatoo.png/revision/latest?cb=20230103150310&path-prefix=es")
            .into(binding.ivImagen)
        Glide.with(this)
            .load("https://preview.redd.it/meme-de-gato-v0-krd7xsbs9dvc1.jpeg?auto=webp&s=87ba53c53f3727203c5fee4ac781815cfe95858a")
            .into(binding.ivImagen2)


        binding.btnDibujarAct.setOnClickListener{
            val intent = Intent(this, Dibujar_prueba::class.java)
            startActivity(intent)
        }
        binding.btnSonidosAct.setOnClickListener{
            val intent = Intent(this, Sonidos_prueba::class.java)
            startActivity(intent)
        }
    }
}