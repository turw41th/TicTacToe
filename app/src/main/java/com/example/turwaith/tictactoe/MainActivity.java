package com.example.turwaith.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    /*
    Cross = 0
    Circle = 1
    Empty = 2
     */

    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    Button playAgainButton;
    TextView winnerTextView;
    ImageView activePlayerView;

    public void appear(View view){

        ImageView counter = (ImageView) view;


        Log.i("Tag", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());





        if (gameState[tappedCounter] == 2 && gameActive){

            gameState[tappedCounter] = activePlayer;

            
            gameState[tappedCounter] = activePlayer;
            if(activePlayer == 0){
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;
                activePlayerView.setImageResource(R.drawable.circle);
            } else if(activePlayer == 1){
                counter.setImageResource(R.drawable.circle);
                activePlayer = 0;
                activePlayerView.setImageResource(R.drawable.cross);
            }

            
            for(int[] winningPos:winningPos){
                if(gameState[winningPos[0]]==gameState[winningPos[1]]&&gameState[winningPos[1]]==gameState[winningPos[2]]&&gameState[winningPos[0]] !=2){
                    String winner = "";
                    gameActive=false;
                    if(activePlayer==0){
                        winner = "Circles have won!";
                    }else if(activePlayer==1){
                        winner = "Crosses have won!";
                    }

                    //Toast.makeText(this, "" + winner, Toast.LENGTH_SHORT).show();



                    winnerTextView.setText(winner);

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                    activePlayerView.setImageDrawable(null);
                }else{

                    if (gameActive) {

                        boolean unentschieden = true;

                        for (int gameStatNow : gameState) {
                            if (gameStatNow == 2) unentschieden = false;
                        }

                        if (unentschieden) {

                            gameActive = false;
                            for(int counterState:gameState){
                                if(counterState==2) gameActive = true;
                            }
                            if(!gameActive){
                                winnerTextView.setText("Draw!");

                                playAgainButton.setVisibility(View.VISIBLE);
                                winnerTextView.setVisibility(View.VISIBLE);
                                activePlayerView.setImageDrawable(null);

                        }


                    }

                    }
                }
            }


        }




    }



    public void playAgain(View view){


        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }

        for(int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;

        activePlayerView.setImageResource(R.drawable.cross);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgainButton = findViewById(R.id.playAgainButton);
        winnerTextView = findViewById(R.id.winnerTextView);
        activePlayerView = findViewById(R.id.activePlayerView);

        activePlayerView.setImageResource(R.drawable.cross);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
    }
}
