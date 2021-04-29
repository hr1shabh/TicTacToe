package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var player = true
    var count = 0
    var boardx=Array(3){IntArray(3)}
    lateinit var board:Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button1=findViewById<Button>(R.id.button1)
        var button2=findViewById<Button>(R.id.button2)
        var button3=findViewById<Button>(R.id.button3)
        var button4=findViewById<Button>(R.id.button4)
        var button5=findViewById<Button>(R.id.button5)
        var button6=findViewById<Button>(R.id.button6)
        var button7=findViewById<Button>(R.id.button7)
        var button8=findViewById<Button>(R.id.button8)
        var button9=findViewById<Button>(R.id.button9)
        board = arrayOf(
                arrayOf(button1,button2,button3),
                arrayOf(button4,button5,button6),
                arrayOf(button7,button8,button9)
        )


for(i in board){
    for(button in i){
        button.setOnClickListener(this)
    }
}
        val reset=findViewById<Button>(R.id.reset)
        initialize()
        reset.setOnClickListener{
         player=true
            count=0
            initialize()
        }

    }
    private fun initialize() {
        for(i in 0 ..2){
            for(j in 0..2 ){
                boardx[i][j]=-1
                board[i][j].isEnabled=true
                board[i][j].text=""
            }
        }

    }


    override fun onClick(v: View) {
        when(v.id){
            R.id.button1 ->{
                updatevalue(row=0,col=0,Player=player)
            }
            R.id.button2 ->{
                updatevalue(row=0,col=1,Player=player)
            }
            R.id.button3 ->{
                updatevalue(row=0,col=2,Player=player)
            }
            R.id.button4 ->{
                updatevalue(row=1,col=0,Player=player)
            }
            R.id.button5 ->{
                updatevalue(row=1,col=1,Player=player)
            }
            R.id.button6 ->{
                updatevalue(row=1,col=2,Player=player)
            }
            R.id.button7 ->{
                updatevalue(row=2,col=0,Player=player)
            }
            R.id.button8 ->{
                updatevalue(row=2,col=1,Player=player)
            }
            R.id.button9 ->{
                updatevalue(row=2,col=2,Player=player)
            }
        }
        count++;
        player=!player
        if(player) updateDisplay("Player X turn")
        else updateDisplay("Player 0 turn")

        if(count==9) updateDisplay("Game Draw!")

        checkwinner()
    }

    private fun checkwinner() {
        // 1.case : Horizontal
      for(i in 0 .. 2){
     if(boardx[i][0]==boardx[i][1] && boardx[i][0]==boardx[i][2]){
         if(boardx[i][0]==1){
             updateDisplay("Player X wins")
             break
         }
         else if(boardx[i][0]==0){
             updateDisplay("Player O wins")
             break
         }
     }
      }
        //2. case : vertical
        for(i in 0 .. 2){
            if(boardx[0][i]==boardx[1][i] && boardx[0][i]==boardx[2][i]){
                if(boardx[0][i]==1){
                    updateDisplay("Player X wins")
                    break
                }
                else if(boardx[0][i]==0){
                    updateDisplay("Player O wins")
                    break
                }
            }

        }
        //3. case : diagonals
        if(boardx[0][0]==boardx[2][2] && boardx[2][2]==boardx[1][1]){
            if(boardx[0][0]==1){
                updateDisplay("Player X wins")
            }
            else if(boardx[0][0]==0){
                updateDisplay("Player O wins")

        }
    }
        if(boardx[0][2]==boardx[1][1] && boardx[1][1]==boardx[2][0]){
            if(boardx[0][2]==1){
                updateDisplay("Player X wins")
            }
            else if(boardx[0][2]==0){
                updateDisplay("Player O wins")

            }
        }
    }

    private fun updateDisplay(text: String) {
        var lol=findViewById<TextView>(R.id.lol)
        lol.text=text
        if(text.contains("wins")){
            disableButtons()
        }
    }

    private fun disableButtons() {
     for(i in 0 .. 2){
         for(j in 0 .. 2){
             board[i][j].isEnabled=false
         }
     }
    }

    private fun updatevalue(row: Int, col: Int, Player: Boolean) {
 val text=if(player) "X" else "0"
val value=if(player) 1 else 0
board[row][col].apply {
    isEnabled=false
    setText(text)
}
boardx[row][col]=value

    }


}
