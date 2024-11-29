package com.kindsu.sonidosdibujaryguide

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kindsu.sonidosdibujaryguide.databinding.ActivityDibujarPruebaBinding

class Dibujar_prueba : AppCompatActivity() {
    private lateinit var binding: ActivityDibujarPruebaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDibujarPruebaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fondo = Lienzo(this)
        binding.main.addView(fondo) //main es el id del layout principal del xml
    }

    class Lienzo(context : Context) : View(context) {
        override fun onDraw (canvas : Canvas){
            canvas.drawRGB(173, 216, 230) //Color del fondo de la actividad
            //Pintar lineas rojas
            val anchoPantalla = getWidth()
            val pincelLinea = Paint()

            pincelLinea.setARGB(255, 165, 0, 0) // Color de la linea
            canvas.drawLine(0f, 200f, anchoPantalla.toFloat(), 200f, pincelLinea) //Pintar la linea
            pincelLinea.setStrokeWidth(20f) //Añadirle grosor a la linea
            canvas.drawLine(0f, 250f, anchoPantalla.toFloat(), 250f, pincelLinea)


            //Pintar los rectangulos azules
            val pincelRectangulo = Paint()
            pincelRectangulo.setARGB(255, 0, 100, 255) //(alpha(controla la opacidad), red, green, blue)
            canvas.drawRect(10f, 10f, (anchoPantalla - 10).toFloat(), 40f, pincelRectangulo) //Pintar rectangulo relleno
            pincelRectangulo.setStyle(Paint.Style.STROKE) //Pintar lineas exteriores del rectangulo(finas)
            canvas.drawRect(10f, 60f, (anchoPantalla - 10).toFloat(), 90f, pincelRectangulo)
            pincelRectangulo.setStrokeWidth(3f) // Pintar lineas exteriores del rectangulo((3px de grosor)
            canvas.drawRect(10f, 110f, (anchoPantalla - 10).toFloat(), 140f, pincelRectangulo)
        }

    }
}