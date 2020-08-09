package io.victor.tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.winner_activity.*

class WinnerActivity : AppCompatActivity(R.layout.winner_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val winner = intent.getStringExtra(winnerKey)!!
        textViewWinner.text = getString(R.string.player_won).replace("$$", winner)

        resetGame.setOnClickListener {
            val intent = Intent(this, InitialScreenActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
