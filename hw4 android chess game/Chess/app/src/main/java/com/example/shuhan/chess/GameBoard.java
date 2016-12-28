package com.example.shuhan.chess;

/**
 * Created by Shuhan on 2016/4/27.
 */
public class GameBoard {

    private Piece gameBoard[][];
    private final static int BOARD_Width = 8;
    private final static int BOARD_HEIGHT =8;

    public static int getBoardWidth(){
        return BOARD_Width;
    }
    public static int getBoardHeight(){
        return BOARD_HEIGHT;
    }
    private boolean bk_move =false;
    private boolean wk_move =false;
    private boolean brr_move =false;
    private boolean blr_move =false;
    private boolean wrr_move =false;
    private boolean wlr_move =false;
    public String startT="Empty";
    public String startC="Empty";
    public String endT="Empty";
    public String endC="Empty";
    private String steps="";


    public GameBoard(){
        //init array of game pieces.
        gameBoard = new Piece[BOARD_Width][BOARD_HEIGHT];
        clearBoard();

    }

    public String getSteps(){
        return steps;
    }

    public Piece[][] getBoard(){
        return gameBoard;
    }

    public void clearBoard(){
        //reset the board to its initial state
        for(int i =0;i<BOARD_Width;i++){
            for(int j=0;j<BOARD_HEIGHT;j++){
                if(j==0){
                    if(i==0||i==7)
                        gameBoard[i][j] = new Piece("W","R");
                    else if (i==1||i==6)
                        gameBoard[i][j] = new Piece("W","K");
                    else if(i==2||i==5)
                        gameBoard[i][j] = new Piece("W","B");
                    else if(i==3)
                        gameBoard[i][j] = new Piece("W","Q");
                    else
                        gameBoard[i][j] = new Piece("W","King");
                }
                else if(j==1)
                    gameBoard[i][j] = new Piece("W","P");
                else if(j==6)
                    gameBoard[i][j] = new Piece("B","P");
                else if(j==7){
                    if(i==0||i==7)
                        gameBoard[i][j] = new Piece("B","R");
                    else if (i==1||i==6)
                        gameBoard[i][j] = new Piece("B","K");
                    else if(i==2||i==5)
                        gameBoard[i][j] = new Piece("B","B");
                    else if(i==3)
                        gameBoard[i][j] = new Piece("B","Q");
                    else
                        gameBoard[i][j] = new Piece("B","King");
                }
                else{
                    gameBoard[i][j] = new Piece("Empty","Empty");
                }
            }
        }

    }

    public int move(int sx, int sy, int ex, int ey, boolean whiteturn){    //start x, start y,end x, end y
        boolean legal = checkLegal(sx,sy,ex,ey, whiteturn);
        boolean Pawnc = false;

        //check kings not facing each other.
        int wx=-1,wy=-1,bx=-1,by=-1;
        int count =0;
        if(legal){
            for(int i =0;i<8;i++){
                for(int j =0;j<8;j++){
                    if(gameBoard[i][j].getType().equals("King")){
                        if(gameBoard[i][j].getColor().equals("W")){
                            wx=i;
                            wy=j;
                        }
                        else{
                            bx=i;
                            by=j;
                        }
                    }
                }
            }
            //System.out.println("White King at: "+wx+" "+wy);
            //System.out.println("Black King at: "+bx+" "+by);
            if(bx==wx&&!gameBoard[sx][sy].getType().equals("King")){
                if(by>wy){
                    for(int j = wy+1;j<by;j++){
                        //System.out.println("type is "+gameBoard[bx][j].getType());
                        if(!gameBoard[bx][j].getType().equals("Empty")){
                            count++;
                        }
                    }
                }
                else{
                    for(int j = by+1;j<wy;j++){
                        if(!gameBoard[bx][j].getType().equals("Empty")){
                            count++;
                        }
                    }
                }
            }

            if(count == 1&&bx == sx&&sx!=ex){
                legal = false;
            }


            if(sx==bx&&sy==by&&ex==wx){
                boolean empty = true;

                if(ey > wy){
                    for(int j=wy+1; j<ey; j++){
                        if(!gameBoard[ex][j].getType().equals("Empty")){
                            empty = false;
                            break;
                        }
                    }
                }
                else{
                    for(int j = ey+1;j<wy;j++){
                        if(!gameBoard[ex][j].getType().equals("Empty")){
                            empty = false;
                            break;
                        }
                    }
                }

                if(empty == true){
                    legal = false;
                    System.out.println("here2");
                }
            }

            if(sx==wx&&sy==wy&&ex==bx){
                boolean empty = true;

                if(ey > by){
                    for(int j=by+1; j<ey; j++){
                        if(!gameBoard[ex][j].getType().equals("Empty")){
                            empty = false;
                            break;
                        }
                    }
                }
                else{
                    for(int j = ey+1;j<by;j++){
                        if(!gameBoard[ex][j].getType().equals("Empty")){
                            empty = false;
                            break;
                        }
                    }
                }

                if(empty == true){
                    legal = false;
                    System.out.println("Here1");
                }
            }









        }




        if(legal) {
            startC=gameBoard[sx][sy].getColor();
            startT=gameBoard[sx][sy].getType();
            endC=gameBoard[ex][ey].getColor();
            endT=gameBoard[ex][ey].getType();



            //check for castling
            if(sx==4&&sy==0)
                wk_move = true;
            if(sx==4&&sy==7)
                bk_move = true;
            if(sx==0&&sy==0)
                wlr_move=true;
            if(sx==0&&sy==7)
                blr_move = true;
            if(sx==7&&sy==0)
                wrr_move = true;
            if(sx==7&&sy==7)
                brr_move = true;

            if(gameBoard[sx][sy].getType().equals("King")&&Math.abs(sx-ex)==2){
                if(sx>ex){
                    String co = gameBoard[sx][sy].getColor();
                    gameBoard[sx-4][sy].setColor("Empty");
                    gameBoard[sx-4][sy].setType("Empty");
                    gameBoard[sx-1][sy].setColor(co);
                    gameBoard[sx-1][sy].setType("R");

                }
                else{
                    String co = gameBoard[sx][sy].getColor();
                    gameBoard[sx+3][sy].setColor("Empty");
                    gameBoard[sx+3][sy].setType("Empty");
                    gameBoard[sx+1][sy].setColor(co);
                    gameBoard[sx+1][sy].setType("R");
                }
            }

            if(gameBoard[sx][sy].getType().equals("P") && (ey==0 || ey==7)){
                if(gameBoard[sx][sy].getColor().equals("W")) {
                    gameBoard[sx][sy].setColor("Empty");
                    gameBoard[sx][sy].setType("Empty");
                    gameBoard[ex][ey].setType("Q");
                    gameBoard[ex][ey].setColor("W");
                    Pawnc = true;
                }
                else{
                    gameBoard[sx][sy].setColor("Empty");
                    gameBoard[sx][sy].setType("Empty");
                    gameBoard[ex][ey].setType("Q");
                    gameBoard[ex][ey].setColor("B");
                    Pawnc =true;
                }
            }



            String type = gameBoard[sx][sy].getType();
            String color = gameBoard[sx][sy].getColor();
            String eType = gameBoard[ex][ey].getType();
            String eColor = gameBoard[ex][ey].getColor();

            if(Pawnc==false) {

                gameBoard[sx][sy].setColor("Empty");
                gameBoard[sx][sy].setType("Empty");
                gameBoard[ex][ey].setType(type);
                gameBoard[ex][ey].setColor(color);
            }

            if(eType.equals("King")){
                if(eColor.equals("B"))
                    return 1; //White wins
                else
                    return 2; //Black wins
            }
            else {
                steps = steps+sx+" "+sy+" "+ex+" "+ey+";";
                return 3; // move success
            }
        }
        else{
            System.out.println("Illegal move");
            return 4; //illegal move!!!
        }
    }

    public boolean checkLegal(int sx,int sy,int ex,int ey,boolean whiteturn){
        Piece start = gameBoard[sx][sy];
        Piece end = gameBoard[ex][ey];



        if(start.getColor().equals(end.getColor())) {
            return false;
        }


        if(start.getType().equals("R")){
            if(sx!=ex){
                if(sy!=ey){
                    return false;
                }
                else{
                    if(ex>sx) {
                        for (int i = sx + 1; i < ex; i++) {
                            if (!gameBoard[i][sy].getColor().equals("Empty")) {
                                return false;
                            }
                        }
                    }
                    else{
                        for (int i = ex+1; i < sx; i++) {
                            if (!gameBoard[i][sy].getColor().equals("Empty")) {
                                return false;
                            }
                        }
                    }

                }
            }
            else{
                if(ey>sy) {
                    for (int i = sy + 1; i < ey; i++) {
                        if (!gameBoard[sx][i].getColor().equals("Empty")) {
                            return false;
                        }
                    }
                }
                else{
                    for (int i = ey+1; i < sy; i++) {
                        if (!gameBoard[sx][i].getColor().equals("Empty")) {
                            return false;
                        }
                    }
                }
            }

        }// for rook



        if(start.getType().equals("K")){
            if(Math.abs(sx-ex)==1){
                if(Math.abs(sy-ey)!=2){
                    return false;
                }
            }
            else if(Math.abs(sy-ey)==1){
                if(Math.abs(sx-ex)!=2){
                    return false;
                }
            }
            else{
                return false;
            }
        }//check for knight


        if(start.getType().equals("B")){
            if(Math.abs(sx-ex)!=Math.abs(sy-ey)){
                return false;
            }

            if(sx>ex){
                if(sy>ey){
                    int i=ex+1;
                    int j=ey+1;
                    while(i<sx&&j<sy){
                        if(!gameBoard[i][j].getType().equals("Empty")){
                            return false;
                        }
                        i++;
                        j++;
                    }
                }
                else{
                    int i = ex+1;
                    int j = ey-1;
                    while(i<sx&&j>sy){
                        if(!gameBoard[i][j].getType().equals("Empty")){
                            return false;
                        }
                        i++;
                        j--;
                    }
                }
            }
            else{
                if(sy>ey){
                    int i=ex-1;
                    int j=ey+1;
                    while(i>sx&&j<sy){
                        if(!gameBoard[i][j].getType().equals("Empty")){
//                            System.out.print(gameBoard[i][j].getColor()+gameBoard[i][j].getType());
//                            System.out.println("Problem is here!!! i= "+i+"j= "+j);
//                            System.out.println("Problem is here!!! i= "+i+"j= "+j);
//                            System.out.println("Problem is here!!! i= "+i+"j= "+j);
                            return false;
                        }
                        i--;
                        j++;
                    }
                }
                else{
                    int i = ex-1;
                    int j = ey-1;
                    while(i>sx&&j>sy){
                        if(!gameBoard[i][j].getType().equals("Empty")){
                            return false;
                        }
                        i--;
                        j--;
                    }
                }
            }
        }//check Bishop


        if(start.getType().equals("Q")){
            if(sx!=ex){
                if(sy!=ey){
                    if(Math.abs(sx-ex)!=Math.abs(sy-ey)){
                        return false;
                    }
                    if(sx>ex){
                        if(sy>ey){
                            int i=ex+1;
                            int j=ey+1;
                            while(i<sx&&j<sy){
                                if(!gameBoard[i][j].getType().equals("Empty")){
                                    return false;
                                }
                                i++;
                                j++;
                            }
                        }
                        else{
                            int i = ex+1;
                            int j = ey-1;
                            while(i<sx&&j>sy){
                                if(!gameBoard[i][j].getType().equals("Empty")){
                                    return false;
                                }
                                i++;
                                j--;
                            }
                        }
                    }
                    else{
                        if(sy>ey){
                            int i=ex-1;
                            int j=ey+1;
                            while(i>sx&&j<sy){
                                if(!gameBoard[i][j].getType().equals("Empty")){
//                                    System.out.print(gameBoard[i][j].getColor()+gameBoard[i][j].getType());
//                                    System.out.println("Problem is here!!! i= "+i+"j= "+j);
//                                    System.out.println("Problem is here!!! i= "+i+"j= "+j);
//                                    System.out.println("Problem is here!!! i= "+i+"j= "+j);
                                    return false;
                                }
                                i--;
                                j++;
                            }
                        }
                        else{
                            int i = ex-1;
                            int j = ey-1;
                            while(i>sx&&j>sy){
                                if(!gameBoard[i][j].getType().equals("Empty")){
                                    return false;
                                }
                                i--;
                                j--;
                            }
                        }
                    }



                }
                else{
                    if(ex>sx) {
                        for (int i = sx + 1; i < ex; i++) {
                            if (!gameBoard[i][sy].getColor().equals("Empty")) {
                                return false;
                            }
                        }
                    }
                    else{
                        for (int i = ex+1; i < sx; i++) {
                            if (!gameBoard[i][sy].getColor().equals("Empty")) {
                                return false;
                            }
                        }
                    }
                }
            }
            else{
                if(ey>sy) {
                    for (int i = sy + 1; i < ey; i++) {
                        if (!gameBoard[sx][i].getColor().equals("Empty")) {
                            return false;
                        }
                    }
                }
                else{
                    for (int i = ey+1; i < sy; i++) {
                        if (!gameBoard[sx][i].getColor().equals("Empty")) {
                            return false;
                        }
                    }
                }
            }
        }//check Queen

        if(start.getType().equals("King")){
            if (Math.abs(sy - ey)>1){
                return false;
            }

            if(Math.abs(ex-sx)>2){
                return false;
            }

            if(Math.abs(ex-sx)==2){
                if(start.getColor().equals("W")){
                    if(ex>sx) {   // white go right
                        if (wk_move || wrr_move) {
                            return false;
                        }

                        if (!gameBoard[sx + 1][sy].getType().equals("Empty") || !gameBoard[sx + 2][sy].getType().equals("Empty")) {
                            return false;
                        }
                        if(checkMate(sx+1,sy,"W")||checkMate(sx+2,sy,"W")||checkMate(sx,sy,"W")){
                            return false;
                        }

                    }
                    else{       //white go left
                        if (wk_move || wlr_move) {
                            return false;
                        }

                        if (!gameBoard[sx - 1][sy].getType().equals("Empty") || !gameBoard[sx - 2][sy].getType().equals("Empty")||!gameBoard[sx - 3][sy].getType().equals("Empty")) {
                            return false;
                        }
                        if(checkMate(sx-1,sy,"W")||checkMate(sx-2,sy,"W")||checkMate(sx,sy,"W")){
                            return false;
                        }
                    }
                }
                else{
                    if(ex>sx) {      //Black go right
                        if (bk_move || brr_move) {
                            return false;
                        }

                        if (!gameBoard[sx + 1][sy].getType().equals("Empty") || !gameBoard[sx + 2][sy].getType().equals("Empty")) {
                            return false;
                        }

                        if(checkMate(sx+1,sy,"B")||checkMate(sx+2,sy,"B")||checkMate(sx,sy,"B")){
                            return false;
                        }
                    }
                    else{
                        if (bk_move || blr_move) {
                            return false;
                        }

                        if (!gameBoard[sx - 1][sy].getType().equals("Empty") || !gameBoard[sx - 2][sy].getType().equals("Empty")||!gameBoard[sx - 3][sy].getType().equals("Empty")) {
                            return false;
                        }

                        if(checkMate(sx-1,sy,"B")||checkMate(sx-2,sy,"B")||checkMate(sx,sy,"B")){
                            return false;
                        }
                    }
                }

            }

        }// check King

        if(start.getType().equals("P")){
            if(start.getColor().equals("W")){
                if(ey-sy != 2){
                    if(ey-sy != 1){
                        return false;
                    }
                    else{
                        if(sx == ex){
                            if(!gameBoard[ex][ey].getType().equals("Empty")){
                                return false;
                            }
                        }
                        else if(Math.abs(sx-ex) == 1){
                            if(gameBoard[ex][ey].getType().equals("Empty")){
                                return false;
                            }
                        }
                        else if(Math.abs(sx-ex) > 1){
                            return false;
                        }
                    }
                }
                else {
                    if (sx != ex) {
                        return false;
                    }
                    if (!gameBoard[ex][ey].getType().equals("Empty")) {
                        return false;
                    }
                    if (!gameBoard[ex][ey - 1].getType().equals("Empty")) {
                        return false;
                    }
                }
            }
            else { //check black color
                if (sy - ey != 2) {
                    if (sy - ey != 1) {
                        return false;
                    } else {
                        if (sx == ex) {
                            if (!gameBoard[ex][ey].getType().equals("Empty")) {
                                return false;
                            }
                        }
                        else if(Math.abs(ex-sx) == 1){
                            if(gameBoard[ex][ey].getType().equals("Empty")){
                                return false;
                            }
                        }
                        else if (Math.abs(ex - sx) > 1) {
                            return false;
                        }
                    }
                } else {
                    if (sx != ex) {
                        return false;
                    }
                    if (!gameBoard[ex][ey].getType().equals("Empty")) {
                        return false;
                    }
                    if (!gameBoard[ex][ey + 1].getType().equals("Empty")) {
                        return false;
                    }
                }
            }
        }

        /*if(whiteturn==true){
            if(start.getColor().equals("B"))
                return false;
        }
        else{
            if(start.getColor().equals("W"))
                return false;
        }
        */


        return true;
    }

    public boolean checkMate(int x, int y, String c){
        boolean lu = false;
        boolean ru = false;
        boolean ld = false;
        boolean rd = false;
        boolean l = false;
        boolean u = false;
        boolean r = false;
        boolean d = false;


        for(int i =1; i<8;i++){

            //check left up
            if(x-i>=0&&y+i<=7&&lu==false) {
                if (!gameBoard[x - i][y + i].getColor().equals( "Empty")){
                    if(gameBoard[x-i][y+i].getColor().equals(c)){
                        lu = true;
                    }
                    else{
                        if(gameBoard[x-i][y+i].getType().equals("B")||gameBoard[x-i][y+i].getType().equals("Q")){
                            return true;
                        }
                        else if(gameBoard[x-i][y+i].getType().equals("King")&&i==1){
                            return true;
                        }
                        else if(gameBoard[x-i][y+i].getType().equals("P")&&i==1){
                            if(c.equals("W")){
                                return true;
                            }
                        }
                        else {
                            lu = true;
                        }
                    }
                }
            }




            //check Right UP
            if(x+i<=7&&y+i<=7&&ru==false) {
                if (!gameBoard[x + i][y + i].getColor().equals( "Empty")){
                    if(gameBoard[x+i][y+i].getColor().equals(c)){
                        ru = true;
                    }
                    else{
                        if(gameBoard[x+i][y+i].getType().equals("B")||gameBoard[x+i][y+i].getType().equals("Q")){
                            return true;
                        }
                        else if(gameBoard[x+i][y+i].getType().equals("King")&&i==1){
                            return true;
                        }
                        else if(gameBoard[x+i][y+i].getType().equals("P")&&i==1){
                            if(c.equals("W")){
                                return true;
                            }
                        }
                        else {
                            ru = true;
                        }
                    }
                }
            }

            //check left down
            if(x-i>=0&&y-i>=0&&ld==false) {
                if (!gameBoard[x - i][y - i].getColor().equals( "Empty")){
                    if(gameBoard[x-i][y-i].getColor().equals(c)){
                        ld = true;
                    }
                    else{
                        if(gameBoard[x-i][y-i].getType().equals("B")||gameBoard[x-i][y-i].getType().equals("Q")){
                            return true;
                        }
                        else if(gameBoard[x-i][y-i].getType().equals("King")&&i==1){
                            return true;
                        }
                        else if(gameBoard[x-i][y-i].getType().equals("P")&&i==1){
                            if(c.equals("B")){
                                return true;
                            }
                        }
                        else {
                            ld = true;
                        }
                    }
                }
            }


            //check right down
            if(x+i<=7&&y-i>=0&&rd==false) {
                if (!gameBoard[x + i][y - i].getColor().equals( "Empty")){
                    if(gameBoard[x+i][y-i].getColor().equals(c)){
                        rd = true;
                    }
                    else{
                        if(gameBoard[x+i][y-i].getType().equals("B")||gameBoard[x+i][y-i].getType().equals("Q")){
                            return true;
                        }
                        else if(gameBoard[x+i][y-i].getType().equals("King")&&i==1){
                            return true;
                        }
                        else if(gameBoard[x+i][y-i].getType().equals("P")&&i==1){
                            if(c.equals("B")){
                                return true;
                            }
                        }
                        else {
                            rd = true;
                        }
                    }
                }
            }

            //check left
            if(x-i>=0&&l==false){
                if(!gameBoard[x-i][y].getType().equals("Empty")){
                    if(gameBoard[x-i][y].getColor().equals(c)){
                        l = true;
                    }
                    else{
                        if(gameBoard[x-i][y].getType().equals("R")&&gameBoard[x-i][y].getType().equals("Q")){
                            return true;
                        }
                        else if(gameBoard[x-i][y].getType().equals("King")&&i==1){
                            return true;
                        }
                        else{
                            l=true; //left safe
                        }
                    }
                }
            }

            //check right
            if(x+i<=7&&r==false){
                if(!gameBoard[x+i][y].getType().equals("Empty")){
                    if(gameBoard[x+i][y].getColor().equals(c)){
                        r = true;
                    }
                    else{
                        if(gameBoard[x+i][y].getType().equals("R")&&gameBoard[x+i][y].getType().equals("Q")){
                            return true;
                        }
                        else if(gameBoard[x+i][y].getType().equals("King")&&i==1){
                            return true;
                        }
                        else{
                            r=true; //right safe
                        }
                    }
                }
            }


            //check up
            if(y+i<=7&&u==false){
                if(!gameBoard[x][y+i].getType().equals("Empty")){
                    if(gameBoard[x][y+i].getColor().equals(c)){
                        u = true;
                    }
                    else{
                        if(gameBoard[x][y+i].getType().equals("R")&&gameBoard[x][y+i].getType().equals("Q")){
                            return true;
                        }
                        else if(gameBoard[x][y+i].getType().equals("King")){
                            return true;
                        }
                        else{
                            u=true; //up safe
                        }
                    }
                }
            }


            //check down
            if(y-i>=0&&d==false){
                if(!gameBoard[x][y-i].getType().equals("Empty")){
                    if(gameBoard[x][y-i].getColor().equals(c)){
                        d = true;
                    }
                    else{
                        if(gameBoard[x][y-i].getType().equals("R")&&gameBoard[x][y-i].getType().equals("Q")){
                            return true;
                        }
                        else if(gameBoard[x][y-i].getType().equals("King")){
                            return true;
                        }
                        else{
                            d=true; //down safe
                        }
                    }
                }
            }
        }

        //check horse
        if(x-1>=0){
            if(y+2<=7){
                if(gameBoard[x-1][y+2].getType().equals("K")&&!gameBoard[x-1][y+2].getColor().equals(c))
                    return true;
            }
            if(y-2>=0){
                if(gameBoard[x-1][y-2].getType().equals("K")&&!gameBoard[x-1][y-2].getColor().equals(c))
                    return true;
            }
        }
        if(x-2>=0){
            if(y+1<=7){
                if(gameBoard[x-2][y+1].getType().equals("K")&&!gameBoard[x-2][y+1].getColor().equals(c))
                    return true;
            }
            if(y-1>=0){
                if(gameBoard[x-2][y-1].getType().equals("K")&&!gameBoard[x-2][y-1].getColor().equals(c))
                    return true;
            }
        }

        if(x+1<=7){
            if(y+2<=7){
                if(gameBoard[x+1][y+2].getType().equals("K")&&!gameBoard[x+1][y+2].getColor().equals(c))
                    return true;
            }
            if(y-2>=0){
                if(gameBoard[x+1][y-2].getType().equals("K")&&!gameBoard[x+1][y-2].getColor().equals(c))
                    return true;
            }
        }

        if(x+2<=7){
            if(y+1<=7){
                if(gameBoard[x+2][y+1].getType().equals("K")&&!gameBoard[x+2][y+1].getColor().equals(c))
                    return true;
            }
            if(y-1>=0){
                if(gameBoard[x+2][y-1].getType().equals("K")&&!gameBoard[x+2][y-1].getColor().equals(c))
                    return true;
            }
        }

        return false;
    }
}
