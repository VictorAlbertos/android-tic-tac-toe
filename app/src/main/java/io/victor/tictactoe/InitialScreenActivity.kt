package io.victor.tictactoe

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.initial_screen_activity.*


val userName1Key = "userName1"
val userName2Key = "userName2"

class InitialScreenActivity : AppCompatActivity(R.layout.initial_screen_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        play.setOnClickListener {
            when {
                firstUserName.text.isEmpty() -> {
                    Toast.makeText(this, R.string.nombre_jugador_1_vacio, Toast.LENGTH_LONG).show()
                }
                secondUserName.text.isEmpty() -> {
                    Toast.makeText(this, R.string.nombre_jugador_2_vacio, Toast.LENGTH_LONG).show()
                }
                else -> {
                    val intent = Intent(this, GameActivity::class.java)
                    intent.putExtra(userName1Key, firstUserName.text.toString())
                    intent.putExtra(userName2Key, secondUserName.text.toString())
                    startActivity(intent)
                }
            }
        }
    }
}
