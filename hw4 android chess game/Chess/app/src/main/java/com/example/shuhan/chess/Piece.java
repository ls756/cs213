package com.example.shuhan.chess;

/**
 * Created by Shuhan on 2016/4/26.
 */

public class Piece {
    private String color;
    private String type;

    public Piece(String color, String type){
        this.color = color;
        this.type = type;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getColor(){
        return color;
    }
    public String getType(){
        return type;
    }

}
