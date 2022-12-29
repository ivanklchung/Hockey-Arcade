package com.example.arcade;

public class PuckVelocity
{
    private int x, y;
    public PuckVelocity(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    // Generate Getter and Setter Modes
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
