package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var restartButton: Button
    private lateinit var topLeft: ImageButton
    private lateinit var topMiddle: ImageButton
    private lateinit var topRight: ImageButton
    private lateinit var middleLeft: ImageButton
    private lateinit var center: ImageButton
    private lateinit var middleRight: ImageButton
    private lateinit var bottomLeft: ImageButton
    private lateinit var bottomMiddle: ImageButton
    private lateinit var bottomRight: ImageButton
    private lateinit var winnerText: TextView

    private var currentPlayer = 1
    private var board: GameBoard = GameBoard()
    private var TAG = "Doc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topLeft = findViewById(R.id.topLeft)
        topMiddle = findViewById(R.id.topMiddle)
        topRight = findViewById(R.id.topRight)

        middleLeft = findViewById(R.id.middleLeft)
        center = findViewById(R.id.center)
        middleRight = findViewById(R.id.middleRight)

        bottomLeft = findViewById(R.id.bottomLeft)
        bottomMiddle = findViewById(R.id.bottomMiddle)
        bottomRight = findViewById(R.id.bottomRight)

        winnerText = findViewById(R.id.winner_text)

        restartButton = findViewById(R.id.restart_button)


        topLeft.setOnClickListener{
            board.setBoard(0, 0, currentPlayer)

            buttonPress(topLeft)
            topLeft.isEnabled = false

            if (checkWinner()) {
                endGame()
            } else {
                switchPlayer()
            }
        }

        topMiddle.setOnClickListener{
            board.setBoard(0, 1, currentPlayer)

            buttonPress(topMiddle)
            topMiddle.isEnabled = false

            if (checkWinner()) {
                endGame()
            } else {
                switchPlayer()
            }
        }

        topRight.setOnClickListener{
            board.setBoard(0, 2, currentPlayer)

            buttonPress(topRight)
            topRight.isEnabled = false

            if (checkWinner()) {
                endGame()
            } else {
                switchPlayer()
            }
        }

        middleLeft.setOnClickListener{
            board.setBoard(1, 0, currentPlayer)

            buttonPress(middleLeft)
            middleLeft.isEnabled = false

            if (checkWinner()) {
                endGame()
            } else {
                switchPlayer()
            }
        }

        center.setOnClickListener{
            board.setBoard(1, 1, currentPlayer)

            buttonPress(center)
            center.isEnabled = false

            if (checkWinner()) {
                endGame()
            } else {
                switchPlayer()
            }
        }

        middleRight.setOnClickListener {
            board.setBoard(1, 2, currentPlayer)

            buttonPress(middleRight)
            middleRight.isEnabled = false

            if (checkWinner()) {
                endGame()
            } else {
                switchPlayer()
            }
        }

        bottomLeft.setOnClickListener{
            board.setBoard(2, 0, currentPlayer)

            buttonPress(bottomLeft)
            bottomLeft.isEnabled = false

            if (checkWinner()) {
                endGame()
            } else {
                switchPlayer()
            }
        }

        bottomMiddle.setOnClickListener {
            board.setBoard(2, 1, currentPlayer)

            buttonPress(bottomMiddle)
            bottomMiddle.isEnabled = false

            if (checkWinner()) {
                endGame()
            } else {
                switchPlayer()
            }
        }

        bottomRight.setOnClickListener {
            board.setBoard(2, 2, currentPlayer)

            buttonPress(bottomRight)
            bottomRight.isEnabled = false

            if (checkWinner()) {
                endGame()
            } else {
                switchPlayer()
            }
        }

        restartButton.setOnClickListener{
            Log.d(TAG, "restart button please work")
            buttonReset(topLeft)
            buttonReset(topMiddle)
            buttonReset(topRight)
            buttonReset(middleLeft)
            buttonReset(center)
            buttonReset(middleRight)
            buttonReset(bottomLeft)
            buttonReset(bottomMiddle)
            buttonReset(bottomRight)

            board = GameBoard()

            winnerText.text = resources.getString(R.string.player1_turn)
        }
    }

    private fun checkWinner(): Boolean {
        val boardArray = board.getBoard()

        //checking rows
        for (i: Int in 0 until 3) {
            Log.d(TAG, "checkWinner: checking row " + i)

            if ((boardArray[i][0] == boardArray[i][1]) &&
                    (boardArray[i][0] == boardArray[i][2]) &&
                    (boardArray[i][0] != 0)) {

                return true
            }
        }

        //checking columns
        for (i: Int in 0 until 3) {
            Log.d(TAG, "checkWinner: checking column " + i)
            if ((boardArray[0][i] == boardArray[1][i]) &&
                    (boardArray[0][i] == boardArray[2][i])
                    && (boardArray[0][i] != 0)) {

                return true
            }
        }

        //check diagonals
        if ((boardArray[0][0] == boardArray[1][1]) &&
                        (boardArray[0][0] == boardArray[2][2])
                        && (boardArray[0][0] != 0)){
            Log.d(TAG, "checkWinner: check diagonal left to right")
            return true
        }

        if (((boardArray[0][2] == boardArray[1][1]) &&
                        (boardArray[0][2] == boardArray[2][0])) &&
                        (boardArray[0][2] != 0)){
            Log.d(TAG, "checkWinner: check diagonal right to left")
            return true
        }

        if (gameTied()) {
            return true
        }

        return false
    }

    private fun gameTied(): Boolean {
        if (!topLeft.isEnabled &&
            !topRight.isEnabled &&
            !topMiddle.isEnabled &&
            !middleLeft.isEnabled &&
            !center.isEnabled &&
            !middleRight.isEnabled &&
            !bottomLeft.isEnabled &&
            !bottomMiddle.isEnabled &&
            !bottomRight.isEnabled) {
            return true
        }

        return false
    }

    private fun endGame() {
        if (gameTied()) {
            winnerText.text = resources.getString(R.string.players_tied)
        } else {

            topLeft.isEnabled = false
            topMiddle.isEnabled = false
            topRight.isEnabled = false
            middleRight.isEnabled = false
            middleLeft.isEnabled = false
            center.isEnabled = false
            bottomLeft.isEnabled = false
            bottomRight.isEnabled = false
            bottomMiddle.isEnabled = false

            if (currentPlayer == 1) {
                winnerText.text = resources.getString(R.string.player1_win)
            } else {
                winnerText.text = resources.getString(R.string.player2_win)
            }
        }
    }

    private fun buttonPress(button: ImageButton) {

        if(currentPlayer == 1) {
            button.foreground = ContextCompat.getDrawable(button.context, R.drawable.x_square)
        } else {
            button.foreground = ContextCompat.getDrawable(button.context, R.drawable.o_square)
        }

        Log.d(TAG, "buttonPress: changed foreground")
    }

    private fun buttonReset(button: ImageButton) {
        Log.d(TAG, "buttonReset: we made it here")
        button.foreground = ContextCompat.getDrawable(button.context, R.drawable.empty_square)
        button.isEnabled = true
    }

    private fun switchPlayer() {
        if (currentPlayer == 1) {
            currentPlayer += 1
            winnerText.text = resources.getString(R.string.player2_turn)
        } else {
            currentPlayer -= 1
            winnerText.text = resources.getString(R.string.player1_turn)
        }

        Log.d(TAG, currentPlayer.toString())
    }
}