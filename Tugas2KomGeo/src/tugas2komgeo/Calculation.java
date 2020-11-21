/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas2komgeo;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Kelas ini berfungsi untuk melakukan operasi perhitungan
 * @author Kelvin Adrian Darmawan / 2017730043
 * @author Ivan Hardja / 2017730002
 */
public class Calculation {
    
    /**
     * Menghitung jarak antara 2 titik
     * @param p1 titik awal
     * @param p2 titik akhir
     * @return jarak titik p1 ke titik p2
     */
    public double dist(Point p1,Point p2){ //mencari jarak antara 2 titik
        double xx = Math.pow(p1.x-p2.x,2); //nilai kuadrat kordinat x point 1 dikurang kordinat x point 2
        double yy = Math.pow(p1.y-p2.y,2); //nilai kuadrat kordinat y point 1 dikurang kordinat x point 2
    
        double res = Math.sqrt(xx+yy); //mencari akar nilai pertambahan xx dan yy
        return res;
    }
    
    /**
     * Menentukan posisi titik terhadap segmen garis
     * @param p titik mulai segmen garis
     * @param q titik akhir segmen garis
     * @param r titik yang ingin dikethui posisinya
     * @return angka positif bila di kanan segmen garis, negatif bila di kiri garis,
     * dan 0 bila lurus
     */
    public double pos(Point p, Point q, Point r){ //mencari posisi titik pada garis
        Point pq = new Point(q.x-p.x,q.y-p.y); //vektor pq di isi kordinat q dikurang kordinat p
        Point qr = new Point(r.x-q.x,r.y-q.y); //vektor pq di isi kordinat q dikurang kordinat p
   	
        double res = cross(pq,qr); //cross product pq dan qr
    
        return res; //positif=kanan, negatif=kiri, 0=lurus
    }
  
    /**
     * Menghitung cross product pada titik p dan titik q
     * @param p titik p
     * @param q titik q
     * @return cross product
     */
    public double cross(Point p,Point q){ //menghitung cross product
        double res = p.x*q.y-p.y*q.x; //hitung cross product
        return  res;//cross product
    }
  
    /**
     * Menghitung dot product pada titik p dengan titik q
     * @param p titik p
     * @param q titik q
     * @return dot product
     */
    public double dot(Point p,Point q){ //menghitung dot product
        double res = p.x*q.x+p.y*q.y; //hitung dot product
        return res;//dot product
    }
    
    /**
     * Mengecek apakah kedua garis ini berpotongan atau tidak
     * @param p titik awal garis 1
     * @param q titik akhir garis 1
     * @param r titik awal garis 2
     * @param s titik akhir garis 2
     * @return bernilai benar bila kedua garis saling berpotongan, bernilai
     * false bila kedua garis tidak saling berpotongan
     */
    public boolean intersect(Point p,Point q,Point r,Point s){//mengecek apabila kedua buah garis berpotongan atau tidak
        double posisi_p = pos(r, p, q); // mencari posisi titik r pada pq
        double posisi_q = pos(s, p, q); // mencari posisi titik s pada pq
        return (posisi_p >= 0 && posisi_q <= 0) || (posisi_p <= 0 && posisi_q >= 0); // melihat apakah kedua garis berpotongan atau tidak
        //mengembalikan nilai true saat diketahui bahwa kedua garis saling berpotongan
        //kalau tidak berpotongan
        //mengembalikan nilai false
    } 
    
    /**
     * Mencari posisi titik pada polygon (di luar atau di dalam polygon).
     * Dilakukan dengan cara menggambar garis horizontal dari titik yang ingin
     * dicek lurus ke kanan dan menghitung perpotongannya. Bila perpotongannya
     * ganjil, maka berada di dalam polygon. Bila genap, maka titik berada di
     * luar polygon.
     *
     * @param p urutan titik pembentuk polygon
     * @param t titik yang ingin dicek posisinya
     * @return bernilai false jika titik berada di luar polygon. Bernilai true
     * jika titik berada di dalam polygon.
     */
    public boolean isIn(Point[]p,Point t){//Mengecek apakah titik berada dalam polygon atau tidak
        boolean cek;//untuk mengecek apabila garis berpotongan atau tidak
        int counter=0;//untuk mengecek berapa kali berpotongan
        int n=p.length;//banyak titik yang ada
        int j=n-1;//untuk posisi terakhir titik yang ada pada array titik
        double nX=0;//x untuk titik yang baru membuat garis dengan titik t 
        for(int i=0;i<n;i++){//untuk setiap titik yang ada pada array
            if(p[i].x>nX){//jika nilai x titik p ke i lebih besar dari nX
                nX=p[i].x;//nilai nX yang baru adalah nilai x dari p[i]
            }
        }
        Point nP=new Point(nX+1,t.y);//titik baru untuk membuat garis dengan titik t dengan nilai x adalah nX+1 dengan nilai y sama dengan titik t
        
        for(int i=0;i<n;i++){//untuk setiap titik pada array p
            cek=this.intersect(p[i], p[j], t, nP);//isi cek dengan metode intersect untuk lihat berpotongan atau tidak
            j=i;//nilai j menjadi i
            if(cek){//tergantung nilai cek bila true
                counter++;//nilai counter yang menghitung garis berpotongan bertambah
            }
        }
        if(counter%2==0){//cek apakah counter genap atau ganjil, apabila genap
            return false;//kembalikan false karena berada di luar polygon
        }else{//apabila ganjil
            return true;//kembalikan true karena berada di dalam polygon
        }
    }
    
    /**
     * asumsi nilai kordinat xMin, yMin, xMax, dan yMax dimiliki oleh titik yang berbeda
     * @param p merupakan array dari kelas Point(titik)
     * @return  
     */
    public Point[] aklToussant(Point [] p){
        LinkedList<Point> temp = new LinkedList<>();
        // 0=xmax 1=xmin 2=ymax 3=ymin
        Point [] shell = new Point[4];
        double xMax=Double.MIN_VALUE;
        double yMax=Double.MIN_VALUE;
        double yMin=Double.MAX_VALUE;
        double xMin=Double.MAX_VALUE;
        
//        int iXMax =-1;
//        int iYMax=-1;
//        int iYMin=-1;
//        int iXMin=-1;
        
        for (int i = 0; i < p.length; i++) {
            if(p[i].x>xMax){
                xMax=p[i].x;
//                iXMax=i;
                shell[0]=p[i];
            }
            if(p[i].x<xMin){
                xMin=p[i].x;
//                iXMin=i;
                shell[1]=p[i];
            }
            if(p[i].y>yMax){
                yMax=p[i].y;
//                iYMax=i;
                shell[2]=p[i];
            }
            if(p[i].y<yMin){
                yMin=p[i].y;
//                iYMin=i;
                shell[3]=p[i];
            }
        }
        
        
        
        for (int i = 0; i < p.length; i++) {
            if(p[i].equals(shell[0])||p[i].equals(shell[1])||p[i].equals(shell[2])||p[i].equals(shell[3])){
                temp.add(p[i]);
            }else{
                if(!this.isIn(shell, p[i])){
                    temp.add(p[i]);
                }
            }
        }
        Point[] output = new Point[temp.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = temp.pop();
        }
        return output;
    }
    
    /**
     * Posisi sebuah titik terhadap sebuah segmen garis (kiri, kanan, atau
     * colinear)
     *
     * @param p titik yang ingin dicek
     * @param q titik mulai pembentuk segmen garis
     * @param r titik akhir pembentuk segmen garis
     * @return jika nilainya <0 di kiri
     * >0 di kanan 0 kolinear atau segaris
     */
    public double ccw(Point p, Point q, Point r) {
        Point pq = new Point(q.x - p.x, q.y - p.y); //pq berisikan koordinat q-koordinat p
        Point qr = new Point(r.x - q.x, r.y - q.y); //qr berisikan koordinat r-koordinat q
        return cross(pq, qr); //pq X qr
    }
    
    public void incrementaSweeping(Point[] p){
        Arrays.sort(p);
        LinkedList<Point> l = new LinkedList<>();// untuk menampung convex hull saat ini
        l.add(p[0]);
        l.add(p[1]);
        l.add(p[2]);
        for (int i = 3; i < p.length; i++) {
            Point pl = p[i-2];
            Point pr = p[i-1];
            
        }
    }
    
    /**
     * Menghitung luas polygon, baik convex maupun concave
     *
     * @param polygon urutan titik pembentuk polygon
     * @return luas polygon
     */
    public double luasPolygon(Point[] polygon) {
        double res = 0.0;
        int j = polygon.length - 1;
        for (int i = 0; i < polygon.length; i++) {
            res += (polygon[j].x + polygon[i].x) * (polygon[j].y - polygon[i].y);
            j = i;
        }
        return Math.abs(res / 2.0);
    }
}
