package com.example.shuhan.chess;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;
import java.nio.Buffer;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private GameBoard game;
    private  Button gameButtons[][];
    private TextView infoDisplay;
    private boolean gameEnd = false;
    private boolean whiteTurn = true;
    private boolean pieceSelected = false;
    private int startX;
    private int startY;
    private String gameName;
    private AlertDialog.Builder dialogBuilder;
    private Context temp;
    private String fl = "";
    private String[] token;
    private boolean c = false;
    private String files = "";
    private boolean load = false;
    private int x1=-1;
    private int y1=-1;
    private int x2=-1;
    private int y2=-1;
    private Drawable sDrawable = null;
    private Drawable eDrawable = null;
    //private Context context=getApplicationContext();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        gameButtons = new Button[game.getBoardWidth()][game.getBoardHeight()];

        gameButtons[0][0] = (Button) findViewById(R.id.a1);
        gameButtons[0][1] = (Button) findViewById(R.id.a2);
        gameButtons[0][2] = (Button) findViewById(R.id.a3);
        gameButtons[0][3] = (Button) findViewById(R.id.a4);
        gameButtons[0][4] = (Button) findViewById(R.id.a5);
        gameButtons[0][5] = (Button) findViewById(R.id.a6);
        gameButtons[0][6] = (Button) findViewById(R.id.a7);
        gameButtons[0][7] = (Button) findViewById(R.id.a8);

        gameButtons[1][0] = (Button) findViewById(R.id.b1);
        gameButtons[1][1] = (Button) findViewById(R.id.b2);
        gameButtons[1][2] = (Button) findViewById(R.id.b3);
        gameButtons[1][3] = (Button) findViewById(R.id.b4);
        gameButtons[1][4] = (Button) findViewById(R.id.b5);
        gameButtons[1][5] = (Button) findViewById(R.id.b6);
        gameButtons[1][6] = (Button) findViewById(R.id.b7);
        gameButtons[1][7] = (Button) findViewById(R.id.b8);

        gameButtons[2][0] = (Button) findViewById(R.id.c1);
        gameButtons[2][1] = (Button) findViewById(R.id.c2);
        gameButtons[2][2] = (Button) findViewById(R.id.c3);
        gameButtons[2][3] = (Button) findViewById(R.id.c4);
        gameButtons[2][4] = (Button) findViewById(R.id.c5);
        gameButtons[2][5] = (Button) findViewById(R.id.c6);
        gameButtons[2][6] = (Button) findViewById(R.id.c7);
        gameButtons[2][7] = (Button) findViewById(R.id.c8);

        gameButtons[3][0] = (Button) findViewById(R.id.d1);
        gameButtons[3][1] = (Button) findViewById(R.id.d2);
        gameButtons[3][2] = (Button) findViewById(R.id.d3);
        gameButtons[3][3] = (Button) findViewById(R.id.d4);
        gameButtons[3][4] = (Button) findViewById(R.id.d5);
        gameButtons[3][5] = (Button) findViewById(R.id.d6);
        gameButtons[3][6] = (Button) findViewById(R.id.d7);
        gameButtons[3][7] = (Button) findViewById(R.id.d8);

        gameButtons[4][0] = (Button) findViewById(R.id.e1);
        gameButtons[4][1] = (Button) findViewById(R.id.e2);
        gameButtons[4][2] = (Button) findViewById(R.id.e3);
        gameButtons[4][3] = (Button) findViewById(R.id.e4);
        gameButtons[4][4] = (Button) findViewById(R.id.e5);
        gameButtons[4][5] = (Button) findViewById(R.id.e6);
        gameButtons[4][6] = (Button) findViewById(R.id.e7);
        gameButtons[4][7] = (Button) findViewById(R.id.e8);

        gameButtons[5][0] = (Button) findViewById(R.id.f1);
        gameButtons[5][1] = (Button) findViewById(R.id.f2);
        gameButtons[5][2] = (Button) findViewById(R.id.f3);
        gameButtons[5][3] = (Button) findViewById(R.id.f4);
        gameButtons[5][4] = (Button) findViewById(R.id.f5);
        gameButtons[5][5] = (Button) findViewById(R.id.f6);
        gameButtons[5][6] = (Button) findViewById(R.id.f7);
        gameButtons[5][7] = (Button) findViewById(R.id.f8);

        gameButtons[6][0] = (Button) findViewById(R.id.g1);
        gameButtons[6][1] = (Button) findViewById(R.id.g2);
        gameButtons[6][2] = (Button) findViewById(R.id.g3);
        gameButtons[6][3] = (Button) findViewById(R.id.g4);
        gameButtons[6][4] = (Button) findViewById(R.id.g5);
        gameButtons[6][5] = (Button) findViewById(R.id.g6);
        gameButtons[6][6] = (Button) findViewById(R.id.g7);
        gameButtons[6][7] = (Button) findViewById(R.id.g8);

        gameButtons[7][0] = (Button) findViewById(R.id.h1);
        gameButtons[7][1] = (Button) findViewById(R.id.h2);
        gameButtons[7][2] = (Button) findViewById(R.id.h3);
        gameButtons[7][3] = (Button) findViewById(R.id.h4);
        gameButtons[7][4] = (Button) findViewById(R.id.h5);
        gameButtons[7][5] = (Button) findViewById(R.id.h6);
        gameButtons[7][6] = (Button) findViewById(R.id.h7);
        gameButtons[7][7] = (Button) findViewById(R.id.h8);

        infoDisplay = (TextView) findViewById(R.id.textView17);

        game = new GameBoard();

        startNewGame();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.newGame:
                startNewGame();
                break;
            case R.id.saveGame:
                saveGame();
                break;
            case R.id.loadGame:
                loadGame();
                break;
            case R.id.exitGame:
                MainActivity.this.finish();
                break;
            case R.id.drawB:
                draw();
                break;
            case R.id.resignB:
                resign();
                break;
            case R.id.undoB:
                undo();
                break;
        }
        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    private void undo(){
        if(x1!=-1&&x2!=-1&&y1!=-1&&y2!=-1&&sDrawable!=null&&eDrawable!=null){
            gameButtons[x1][y1].setForeground(sDrawable);
            gameButtons[x2][y2].setForeground(eDrawable);
            game.getBoard()[x1][y1].setType(game.startT);
            game.getBoard()[x1][y1].setColor(game.startC);
            game.getBoard()[x2][y2].setType(game.endT);
            game.getBoard()[x2][y2].setColor(game.endC);
        }


    }

    private void draw(){
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Draw?");
        dialogBuilder.setMessage("Do you agree to draw?");
        dialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which){
                infoDisplay.setText("Draw!!!");
                saveGame();
            }
        });
        dialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                Toast.makeText(getApplicationContext(),"Canceled", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialogSaveGame = dialogBuilder.create();
        dialogSaveGame.show();
    }

    private void resign(){
        if(whiteTurn){
            infoDisplay.setText("Black's Win!!!");
        }
        else{
            infoDisplay.setText("White's Win!!!");
        }
    }



    private void saveGame(){

        dialogBuilder = new AlertDialog.Builder(this);
        final EditText txtInput = new EditText(this);
        gameName = "";
        dialogBuilder.setTitle("Would you like to save the game?");
        dialogBuilder.setMessage("Please give a name to this game: ");
        dialogBuilder.setView(txtInput);
        dialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which){
                gameName+=txtInput.getText().toString();
                System.out.println(gameName);
                String fileName = gameName;
                File file;
                FileOutputStream outputStream = null;
                try{
                    outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                    outputStream.write(game.getSteps().getBytes());
                    outputStream.close();
                    if(files.equals("")){
                        files = gameName;
                    }
                    else {
                        files = files + " " + gameName;
                    }
                    System.out.println("Files is "+files);
                }catch(Exception e){
                    e.printStackTrace();
                }
                try{
                    String cacheName = gameName+"Cache";
                    file = new File(getCacheDir(),cacheName);
                    outputStream = new FileOutputStream(file);
                    outputStream.write(game.getSteps().getBytes());

                    outputStream.close();
                }catch(Exception e){
                    e.printStackTrace();
                }



            }
        });
        dialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
               Toast.makeText(getApplicationContext(),"Canceled", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialogSaveGame = dialogBuilder.create();
        dialogSaveGame.show();
//        return context;

    }

    private void loadGame(){

        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Load Game");


        if(!this.files.equals("")) {
            System.out.println("In Loading Game: files are :"+ files);
            final String[] files = this.files.split(" ");
            dialogBuilder.setSingleChoiceItems(files, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int selected = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                    fl = files[selected];
                    System.out.println("you select "+fl);
                    BufferedReader input = null;
                    File file;

                    try{
                        String cachename = fl+"Cache";
                        file = new File(getCacheDir(),cachename);
                        input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                        String line;
                        StringBuffer buffer = new StringBuffer();
                        while((line = input.readLine())!=null){
                            buffer.append(line);
                        }
                        System.out.println("------------------------------------------");
                        System.out.println("string is : "+buffer.toString());
                        String steps = buffer.toString();
                        token = steps.split(";");

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    load = true;
                    dialog.cancel();
                    playback(token);
                }
            });

        }



//        dialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //c=true;
//
//                dialog.cancel();
//                startNewGame();
//                c=true;
//                //playback(token);
//
//
//            }
//        });
//
//
//        dialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
//            @Override
//            public void onClick(DialogInterface dialog, int which){
//                Toast.makeText(getApplicationContext(),"Canceled", Toast.LENGTH_SHORT).show();
//            }
//        });

        AlertDialog alert = dialogBuilder.create();
        alert.show();



        c = false;

//        if(c){
//            startNewGame();
//            for(int i=0; i<token.length; i++){
//                String[] step = token[i].split("\\s");
//                System.out.println("step is: "+step[0]+" "+step[1]+" "+step[2]+" "+step[3]);
//                setMove(Integer.parseInt(step[0]),Integer.parseInt(step[1]),Integer.parseInt(step[2]),Integer.parseInt(step[3]));
//                try {
//                    Thread.sleep(3000);                 //2000 milliseconds is one second.
//                } catch(InterruptedException ex) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        }
        /*System.out.println("---------------------------------------");
        System.out.println("url is "+context.getFilesDir());
        String url = context.getFilesDir()+"/lol";
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(url), "UTF8"));
        System.out.println("next is "+ in.next());
        while(in.hasNextLine()) {
            System.out.println("next line is "+in.nextLine());
        }
        */
//        File file;
//        String line = null;
//        try{
           // file = File.createTempFile("oil",null,context.getCacheDir());
            //FileReader fl = new FileReader(context.getCacheDir());
          //  BufferedReader read = new BufferedReader(fl);
            //while(( line=read.readLine())!=null){

            //}
//        }catch(IOException e){
//            e.printStackTrace();
//        }

    }

    private void playback(String[] tk){
        //System.out.println("Get Called----------");
        //System.out.println("Token is; "+tk);
        String[] steps = tk;
        startNewGame();
        //System.out.println("Here LOL");
        //System.out.println("Here Steps is: "+steps);
        if(steps != null) {
            System.out.println("WOW");
            for (int i = 0; i < steps.length; i++) {
                String[] step = steps[i].split("\\s");
                System.out.println("step is: " + step[0] + " " + step[1] + " " + step[2] + " " + step[3]);
                setMove(Integer.parseInt(step[0]), Integer.parseInt(step[1]), Integer.parseInt(step[2]), Integer.parseInt(step[3]));
                try {
                    Thread.sleep(2000);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
           }
        }
    }


    private void startNewGame(){
        game.clearBoard();
        infoDisplay.setText("White's Turn");
        whiteTurn = true;
        gameEnd = false;

        for(int i =0;i<game.getBoardWidth();i++){
            for(int j=0;j<game.getBoardHeight();j++){
                if(j==0){
                    if(i==0||i==7)
                        gameButtons[i][j].setForeground(getResources().getDrawable(R.drawable.wr));
                    else if (i==1||i==6)
                        gameButtons[i][j].setForeground(getResources().getDrawable(R.drawable.wk));
                    else if(i==2||i==5)
                        gameButtons[i][j].setForeground(getResources().getDrawable(R.drawable.wb));
                    else if(i==3)
                        gameButtons[i][j].setForeground(getResources().getDrawable(R.drawable.wq));
                    else
                        gameButtons[i][j].setForeground(getResources().getDrawable(R.drawable.wking));
                }
                else if(j==1)
                    gameButtons[i][j].setForeground(getResources().getDrawable(R.drawable.wp));
                else if(j==6)
                    gameButtons[i][j].setForeground(getResources().getDrawable(R.drawable.bp));
                else if(j==7){
                    if(i==0||i==7)
                        gameButtons[i][j].setForeground(getResources().getDrawable(R.drawable.br));
                    else if (i==1||i==6)
                        gameButtons[i][j].setForeground(getResources().getDrawable(R.drawable.bk));
                    else if(i==2||i==5)
                        gameButtons[i][j].setForeground(getResources().getDrawable(R.drawable.bb));
                    else if(i==3)
                        gameButtons[i][j].setForeground(getResources().getDrawable(R.drawable.bq));
                    else
                        gameButtons[i][j].setForeground(getResources().getDrawable(R.drawable.bking));
                }
                else{
                    gameButtons[i][j].setForeground(null);
                }

                if(load == false) {
                    gameButtons[i][j].setText("");
                    gameButtons[i][j].setOnClickListener(new ButtonClickListener(i, j));
                }
            }
        }
    }


    private void setMove(int sx, int sy, int ex, int ey){

        if (game.getBoard()[ex][ey].getColor().equals("W")) {
            if (game.getBoard()[ex][ey].getType().equals("P"))
                eDrawable = getResources().getDrawable(R.drawable.wp);
            if (game.getBoard()[ex][ey].getType().equals("R"))
                eDrawable = getResources().getDrawable(R.drawable.wr);
            if (game.getBoard()[ex][ey].getType().equals("K"))
                eDrawable = getResources().getDrawable(R.drawable.wk);
            if (game.getBoard()[ex][ey].getType().equals("B"))
                eDrawable = getResources().getDrawable(R.drawable.wb);
            if (game.getBoard()[ex][ey].getType().equals("Q"))
                eDrawable = getResources().getDrawable(R.drawable.wq);
            if (game.getBoard()[ex][ey].getType().equals("King"))
                eDrawable = getResources().getDrawable(R.drawable.wking);
        } else if (game.getBoard()[ex][ey].getColor().equals("B")) {
            if (game.getBoard()[ex][ey].getType().equals("P"))
                eDrawable = getResources().getDrawable(R.drawable.bp);
            if (game.getBoard()[ex][ey].getType().equals("R"))
                eDrawable = getResources().getDrawable(R.drawable.br);
            if (game.getBoard()[ex][ey].getType().equals("K"))
                eDrawable = getResources().getDrawable(R.drawable.bk);
            if (game.getBoard()[ex][ey].getType().equals("B"))
                eDrawable = getResources().getDrawable(R.drawable.bb);
            if (game.getBoard()[ex][ey].getType().equals("Q"))
                eDrawable = getResources().getDrawable(R.drawable.bq);
            if (game.getBoard()[ex][ey].getType().equals("King"))
                eDrawable = getResources().getDrawable(R.drawable.bking);
        } else {
            for(int k = 0;k<10;k++) {
                System.out.println("NULL!!!!!!!!!");
            }
            eDrawable = null;
        }
        int result = game.move(sx,sy,ex,ey,whiteTurn);

        if(result == 4){
            infoDisplay.setText("Illegal Move!!!");
            eDrawable = null;
        }
        if(result != 4) {

            if(game.getBoard()[ex][ey].getType().equals("King")&&Math.abs(sx-ex)==2){
                if(sx>ex){
                    String co = game.getBoard()[ex][ey].getColor();
                    if(co.equals("W")) {
                        //gameButtons[sx][sy].setForeground(null);
                        //gameButtons[ex][ey].setForeground(sDrawable);
                        gameButtons[sx - 4][sy].setForeground(null);
                        gameButtons[sx - 1][sy].setForeground(getResources().getDrawable(R.drawable.wr));
                    }
                    else{
                        gameButtons[sx - 4][sy].setForeground(null);
                        gameButtons[sx - 1][sy].setForeground(getResources().getDrawable(R.drawable.br));
                    }

                }
                else{
                    String co = game.getBoard()[ex][ey].getColor();
                    if(co.equals("W")) {
                        //gameButtons[sx][sy].setForeground(null);
                        //gameButtons[ex][ey].setForeground(sDrawable);
                        gameButtons[sx + 3][sy].setForeground(null);
                        gameButtons[sx +1 ][sy].setForeground(getResources().getDrawable(R.drawable.wr));
                    }
                    else{
                        gameButtons[sx + 3 ][sy].setForeground(null);
                        gameButtons[sx + 1][sy].setForeground(getResources().getDrawable(R.drawable.br));
                    }
                }
            }

              //start location drawable
            //Drawable eDrawable = null;  //end location drawable
            if (game.getBoard()[ex][ey].getColor().equals("W")) {
                if (game.getBoard()[ex][ey].getType().equals("P"))
                    sDrawable = getResources().getDrawable(R.drawable.wp);
                if (game.getBoard()[ex][ey].getType().equals("R"))
                    sDrawable = getResources().getDrawable(R.drawable.wr);
                if (game.getBoard()[ex][ey].getType().equals("K"))
                    sDrawable = getResources().getDrawable(R.drawable.wk);
                if (game.getBoard()[ex][ey].getType().equals("B"))
                    sDrawable = getResources().getDrawable(R.drawable.wb);
                if (game.getBoard()[ex][ey].getType().equals("Q"))
                    sDrawable = getResources().getDrawable(R.drawable.wq);
                if (game.getBoard()[ex][ey].getType().equals("King"))
                    sDrawable = getResources().getDrawable(R.drawable.wking);
            } else if (game.getBoard()[ex][ey].getColor().equals("B")) {
                if (game.getBoard()[ex][ey].getType().equals("P"))
                    sDrawable = getResources().getDrawable(R.drawable.bp);
                if (game.getBoard()[ex][ey].getType().equals("R"))
                    sDrawable = getResources().getDrawable(R.drawable.br);
                if (game.getBoard()[ex][ey].getType().equals("K"))
                    sDrawable = getResources().getDrawable(R.drawable.bk);
                if (game.getBoard()[ex][ey].getType().equals("B"))
                    sDrawable = getResources().getDrawable(R.drawable.bb);
                if (game.getBoard()[ex][ey].getType().equals("Q"))
                    sDrawable = getResources().getDrawable(R.drawable.bq);
                if (game.getBoard()[ex][ey].getType().equals("King"))
                    sDrawable = getResources().getDrawable(R.drawable.bking);
            } else {
                for(int k = 0;k<10;k++) {
                    System.out.println("NULL!!!!!!!!!");
                }
                sDrawable = null;
            }

            gameButtons[sx][sy].setForeground(null);
            gameButtons[ex][ey].setForeground(sDrawable);

            if (result == 1){
                infoDisplay.setText("White Wins!!!");
                saveGame();
                gameEnd = true;
            }
            if (result == 2){
                infoDisplay.setText("Black Wins!!!");
                saveGame();
                gameEnd = true;
            }
            if (result == 3){
                System.out.println("This Step Works Successfully<------------");
                if(whiteTurn == true){
                    whiteTurn = false;
                    infoDisplay.setText("Black's Turn");

                    for(int i =0;i<8;i++){
                        for(int j=0;j<8;j++){
                            if(game.getBoard()[i][j].getType().equals("King")){
                                if(game.getBoard()[i][j].getColor().equals("B")){
                                    if(game.checkMate(i,j,"B")){
                                        infoDisplay.setText("Black is checkmated, Black's Turn");
                                    }
                                    break;
                                }

                            }
                        }
                    }

                }
                else{
                    whiteTurn = true;
                    infoDisplay.setText("White's Turn");


                    for(int i =0;i<8;i++){
                        for(int j=0;j<8;j++){
                            if(game.getBoard()[i][j].getType().equals("King")){
                                if(game.getBoard()[i][j].getColor().equals("W")){
                                    if(game.checkMate(i,j,"W")){
                                        infoDisplay.setText("White is checkmated, White's Turn");
                                    }
                                    break;
                                }

                            }
                        }
                    }
                }
                x1=sx;
                y1=sy;
                x2=ex;
                y2=ey;


            }
        }

        pieceSelected = false;

    }



    private class ButtonClickListener implements View.OnClickListener{

        int x; // location on X
        int y; // location on Y

        public ButtonClickListener(int x, int y){
            this.x = x;
            this.y = y;
        }

        public void onClick(View view){
            if (!gameEnd){
                if(!pieceSelected){
                    if((game.getBoard()[x][y].getColor().equals("W")&&whiteTurn)||(game.getBoard()[x][y].getColor().equals("B")&&!whiteTurn)) {
                        if (!game.getBoard()[x][y].getColor().equals("Empty")) {
                            if ((x == y) || (x + 2 == y) || (x + 4 == y) || (x + 6 == y) || (y + 2 == x) || (y + 4 == x) || (y + 6 == x)) {
                                gameButtons[x][y].setBackground(getResources().getDrawable(R.drawable.brown_selected));
                                startX = x;
                                startY = y;
                                pieceSelected = true;
                            } else {
                                gameButtons[x][y].setBackground(getResources().getDrawable(R.drawable.white_selected));
                                startX = x;
                                startY = y;
                                pieceSelected = true;
                            }
                        }
                    }
                }
                else if(pieceSelected){

                        for(int m =0; m<3;m++) {
                            System.out.println("Start X: " + startX + " Start Y: " + startY + "\nEnd X: " + x + " End Y: " + y);
                        }
                        setMove(startX,startY,x,y);
                        pieceSelected = false;
                        //x = startX;
                        //y = startY;
                    if((startX== startY)||(startX+2==startY)||(startX+4==startY)||(startX+6==startY)||(startY+2==startX)||(startY+4==startX)||(startY+6==startX)){
                        gameButtons[startX][startY].setBackground(getResources().getDrawable(R.drawable.brown));
                    }
                    else{
                        gameButtons[startX][startY].setBackground(getResources().getDrawable(R.drawable.white));
                    }
                }
            }
        }

    }

}
