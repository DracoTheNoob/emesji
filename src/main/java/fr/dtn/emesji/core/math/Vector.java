package fr.dtn.emesji.core.math;

public class Vector {
    private double x, y;

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void translate(double x, double y){
        this.x += x;
        this.y += y;
    }

    public void translate(Vector vector){
        this.x += vector.getX();
        this.y += vector.getY();
    }

    public double distanceFrom(Vector v){
        return Math.sqrt(Math.pow(v.getX() - x, 2) + Math.pow(v.getY() - y, 2));
    }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    @Override public String toString() { return "(" + x + ";" + y + ")"; }
    public Vector copy(){ return new Vector(x, y); }
}