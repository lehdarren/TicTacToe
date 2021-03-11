package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlin.random.Random

class AIActivity : AppCompatActivity() {

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
    private lateinit var playerButton: Button
    private lateinit var winnerText: TextView

    private var currentPlayer = 1
    private var board: GameBoard = GameBoard()
    private var TAG = "Doc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai)

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
        playerButton = findViewById(R.id.player_button)


        topLeft.setOnClickListener{
            board.setBoard(0, 0, currentPlayer)

            buttonPress(topLeft)
            topLeft.isEnabled = false

            if (checkWinner()) {
                endGame()
            } else {
                switchPlayer()
                aiMove()
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
                aiMove()
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
                aiMove()
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
                aiMove()
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
                aiMove()
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
                aiMove()
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
                aiMove()
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
                aiMove()
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
                aiMove()
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

        playerButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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

    private fun aiMove() {
        val boardArray =  board.getBoard()

        Log.d(TAG, "aiMove: retrieving board")

        var found = false

        while (!found) {
            val randRow = Random.nextInt(3)
            Log.d(TAG, "aiMove: $randRow")
            val randCol = Random.nextInt(3)
            Log.d(TAG, "aiMove: $randCol")

            if (boardArray[randRow][randCol] == 0) {
                found = true
                val location = randRow.toString() + randCol.toString()

                Log.d(TAG, "location: $location")
                board.setBoard(randRow, randCol, currentPlayer)

                if (location == "00") {
                    buttonPress(topLeft)
                    topLeft.isEnabled = false
                } else if (location == "01") {
                    buttonPress(topMiddle)
                    topMiddle.isEnabled = false
                } else if (location == "02") {
                    buttonPress(topRight)
                    topRight.isEnabled = false
                } else if (location == "10") {
                    buttonPress(middleLeft)
                    middleLeft.isEnabled = false
                } else if (location == "11") {
                    buttonPress(center)
                    center.isEnabled = false
                } else if (location == "12") {
                    buttonPress(middleRight)
                    middleRight.isEnabled = false
                } else if (location == "20") {
                    buttonPress(bottomLeft)
                    bottomLeft.isEnabled = false
                } else if (location == "21") {
                    buttonPress(bottomMiddle)
                    bottomMiddle.isEnabled = false
                } else if (location == "22") {
                    buttonPress(bottomRight)
                    bottomRight.isEnabled = false
                }

                if (checkWinner()) {
                    endGame()
                }
            }
        }

        if (winnerText.text != resources.getString(R.string.player2_win) &&
            winnerText.text != resources.getString(R.string.player1_win)) {

            switchPlayer()
        }
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
                currentPlayer = 1
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
