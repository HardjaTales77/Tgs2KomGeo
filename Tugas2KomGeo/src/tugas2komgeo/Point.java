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
    
    public boolean biggerX(Point p){//mengecek apakah nilai kordinat x lebih besar dari titik masukan
        return this.x>p.x;//kembalikan true apabila nilai kordinat x lebih besar
    }
    
    public boolean equivalent(Point p){//mengecek apakah sama dengan titik masukan
        return (this.x==p.x && this.y==p.y);//kembalikan true apabila nilai kordinat x dan y sama dengan masukan
    }
    
    @Override
    /**
     * Membandingkan dua titik berdasarkan koordinat x nya
     * Keluaran mengkiuti syarat compareTo milik java
     */
    public int compareTo(Point o) {
        return (int)(this.x-o.x);
    }
    
    /**
     * Mengeluarkan koordinat x dan y titik dalam bentuk string
     * @return x dan y dalam bentuk string
     */
    public String print(){
        return "["+x+","+y+"] ";
    }
}
