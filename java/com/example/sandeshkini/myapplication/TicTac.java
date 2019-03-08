package com.example.sandeshkini.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TicTac extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;


    public void Dialog(String s) {

        AlertDialog alertDialog = new AlertDialog.Builder(
                TicTac.this ).create();

        // Setting Dialog Title
        alertDialog.setTitle( "congratulation" );

        // Setting Dialog Message
        alertDialog.setMessage( s );

        // Setting Icon to Dialog
        alertDialog.setIcon( R.drawable.common_google_signin_btn_icon_dark );

        // Setting OK Button
        alertDialog.setButton( AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText( getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT ).show();
            }
        } );
        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tic_tac );
        textViewPlayer1 = findViewById( R.id.text_view_p1 );
        textViewPlayer2 = findViewById( R.id.text_view_p2 );

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier( buttonID, "id", getPackageName() );
                buttons[i][j] = findViewById( resID );
                buttons[i][j].setOnClickListener( this );
            }
        }

        Button buttonReset = findViewById( R.id.button_reset );
        buttonReset.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();

            }
        } );
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals( "" )) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText( "X" );
        } else {
            ((Button) v).setText( "O" );
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals( field[i][1] )
                    && field[i][0].equals( field[i][2] )
                    && !field[i][0].equals( "" )) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals( field[1][i] )
                    && field[0][i].equals( field[2][i] )
                    && !field[0][i].equals( "" )) {
                return true;
            }
        }

        if (field[0][0].equals( field[1][1] )
                && field[0][0].equals( field[2][2] )
                && !field[0][0].equals( "" )) {
            return true;
        }

        if (field[0][2].equals( field[1][1] )
                && field[0][2].equals( field[2][0] )
                && !field[0][2].equals( "" )) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText( this, "Player 1 wins!", Toast.LENGTH_SHORT ).show();
        Dialog( "Player 1 wins!" );
        updatePointsText();
        resetBoard();

    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText( this, "Player 2 wins!", Toast.LENGTH_SHORT ).show();
        Dialog( "Player 2 wins!" );
        updatePointsText();
        resetBoard();

    }

    private void draw() {
        Toast.makeText( this, "Draw!", Toast.LENGTH_SHORT ).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText( "Player 1: " + player1Points );
        textViewPlayer2.setText( "Player 2: " + player2Points );
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText( "" );
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }


}
