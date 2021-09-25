package tictactoe

import kotlin.math.abs

fun main() {
    var isXgo = true
    var ch = "X"
    var str = "_________"
    draw(str)

    do {
        while (true) {

            print("Enter the coordinates: ")
            val digs = readLine()!!
            if (!digs[0].isDigit() || digs.split(" ").size != 2) {
                println("You should enter numbers!")
                continue
            }
            val (row, col) = digs.split(" ")
            if (row.toInt() > 3 || col.toInt() > 3) {
                println("Coordinates should be from 1 to 3!")
                continue
            }
            val k =  3 * (row.toInt() - 1) + col.toInt() - 1
            if (str[k] == '_') {
                str = str.replaceRange(k..k, ch)
                isXgo = !isXgo
                ch = if (isXgo) "X" else "O"
                break
            }
            else {
                println("This cell is occupied! Choose another one!")
            }
        }
        draw(str)
    } while (analyze(str))
}

fun draw(s: String) {
    var k = 0
    println("---------")
    for (i in 1..3) {
        print("| ")
        for (j in 1..3) {
            print("${s[k]} ")
            k++
        }
        println("|")
    }
    println("---------")
}

fun analyze(s: String): Boolean {
    var xWin = false
    var oWin = false
    val xNum = s.filter { it == 'X' }.count()
    val oNum = s.filter { it == 'O' }.count()
    if (xNum >= 3) xWin =
            s.matches("XXX......".toRegex()) ||
            s.matches("...XXX...".toRegex()) ||
            s.matches("......XXX".toRegex()) ||
            s.matches("X..X..X..".toRegex()) ||
            s.matches(".X..X..X.".toRegex()) ||
            s.matches("..X..X..X".toRegex()) ||
            s.matches("X...X...X".toRegex()) ||
            s.matches("..X.X.X..".toRegex())
    if (oNum >= 3) oWin =
            s.matches("OOO......".toRegex()) ||
            s.matches("...OOO...".toRegex()) ||
            s.matches("......OOO".toRegex()) ||
            s.matches("O..O..O..".toRegex()) ||
            s.matches(".O..O..O.".toRegex()) ||
            s.matches("..O..O..O".toRegex()) ||
            s.matches("O...O...O".toRegex()) ||
            s.matches("..O.O.O..".toRegex())
    if (abs(xNum - oNum) > 1 || xWin && oWin)
        println("Impossible")
    else if (xWin) println("X wins")
    else if (oWin) println("O wins")
    else if (xNum + oNum == 9)
        println("Draw")
    else
        return true
    return false
}