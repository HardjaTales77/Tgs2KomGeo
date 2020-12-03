/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas2komgeo;

/**
 * Kelas ini berfungsi untuk menyimpan 2 titik yang membentuk sebuah garis
 * @author Kelvin Adrian Darmawan / 2017730043
 * @author Ivan Hardja / 2017730002
 */
public class Line {
    Point p;//titik 1
    Point q;//titik 2
    double distance;//jarak dari titik 1 dan titik 2

    public Line(Point p, Point q, double distance) {//constructor dari kelas
        this.p = p;
        this.q = q;
        this.distance = distance;
    }
    
}
