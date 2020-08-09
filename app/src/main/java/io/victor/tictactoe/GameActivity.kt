package io.victor.tictactoe

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.game_activity.*

val winnerKey = "winnerKey"

class GameActivity : AppCompatActivity(R.layout.game_activity) {
    private val board by lazy {
        listOf(
            casilla1, casilla2, casilla3,
            casilla4, casilla5, casilla6,
            casilla7, casilla8, casilla9
        )
    }

    private val username1 by lazy { intent.getStringExtra(userName1Key)!! }
    private val username2 by lazy { intent.getStringExtra(userName2Key)!! }
    private var isUser1Turn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeTitleCurrentUser()

        board.forEach { textView ->
            textView.setOnClickListener {
                val wasTheMoveAllowed = setSymbolOnBoardSection(textView)
                if (wasTheMoveAllowed) {
                    checkIfPlayerWon()
                    isUser1Turn = !isUser1Turn
                    changeTitleCurrentUser()
                }
            }
        }
    }

    private fun changeTitleCurrentUser() {
        if (isUser1Turn) {
            val currentUser = getString(R.string.player_is_playing).replace("$$", username1)
            currentUserName.text = currentUser
        } else {
            val currentUser = getString(R.string.player_is_playing).replace("$$", username2)
            currentUserName.text = currentUser
        }
    }

    private fun setSymbolOnBoardSection(textView: TextView) : Boolean {
        if (textView.text.isNotEmpty()) {
            return false
        }

        if (isUser1Turn) {
            val symbol = "X"
            textView.text = symbol
        } else {
            val symbol = "O"
            textView.text = symbol
        }

        return true
    }

    private fun checkIfPlayerWon() {
        val targetSymbol =  if (isUser1Turn) {
             "X"
        } else {
            "O"
        }

        when {
            casilla1.text == targetSymbol && casilla2.text == targetSymbol && casilla3.text == targetSymbol -> showWinner()
            casilla1.text == targetSymbol && casilla5.text == targetSymbol && casilla9.text == targetSymbol -> showWinner()
            casilla1.text == targetSymbol && casilla4.text == targetSymbol && casilla7.text == targetSymbol -> showWinner()
            casilla2.text == targetSymbol && casilla5.text == targetSymbol && casilla8.text == targetSymbol -> showWinner()
            casilla3.text == targetSymbol && casilla5.text == targetSymbol && casilla7.text == targetSymbol -> showWinner()
            casilla3.text == targetSymbol && casilla6.text == targetSymbol && casilla9.text == targetSymbol -> showWinner()
            casilla4.text == targetSymbol && casilla5.text == targetSymbol && casilla6.text == targetSymbol -> showWinner()
            casilla7.text == targetSymbol && casilla8.text == targetSymbol && casilla9.text == targetSymbol -> showWinner()
        }
    }


    private fun showWinner() {
        val username = if (isUser1Turn) {
            username1
        } else {
            username2
        }

        val intent = Intent(this, WinnerActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra(winnerKey, username)
        startActivity(intent)
    }
}
