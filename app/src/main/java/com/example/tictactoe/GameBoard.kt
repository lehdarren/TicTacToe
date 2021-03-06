package com.example.tictactoe

class GameBoard() {
    private var board: Array<IntArray> = arrayOf(
            intArrayOf(0,0,0),
            intArrayOf(0,0,0),
            intArrayOf(0,0,0)
    )

    fun getBoard(): Array<IntArray> {
        return board
    }

    fun setBoard(row: Int, col: Int, value: Int) {
        board[row][col] = value
    }
}