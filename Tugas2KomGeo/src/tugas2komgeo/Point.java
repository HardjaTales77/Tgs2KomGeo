/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas2komgeo;

/**
 * Kelas ini merepresentasikan sebuah titik
 * @author Kelvin Adrian Darmawan / 2017730043
 * @author Ivan Hardja / 2017730002
 */
public class Point implements Comparable<Point>{
    double x; //kordinat x
    double y; //kordinat y
  
    public Point(double x, double y){
        this.x=x; //isi x
        this.y=y; //isi y
    }
    
    public boolean biggerX(Point p){
        return this.x>p.x;
    }
    
    public boolean equivalent(Point p){
        return (this.x==p.x && this.y==p.y);
    }
    
    @Override
    public int compareTo(Point o) {
        return (int)(this.x-o.x);
    }
}
