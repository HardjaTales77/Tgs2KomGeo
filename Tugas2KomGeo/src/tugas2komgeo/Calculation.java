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
    
    private Point sOne;
    private Point sTwo;
    
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
     * Menghitung cross product pada titik p dan titik q
     * @param p titik p
     * @param q titik q
     * @return cross product
     */
    private double cross(Point p,Point q){ //menghitung cross product
        double res = p.x*q.y-p.y*q.x; //hitung cross product
        return  res;//cross product
    }
  
    /**
     * Menghitung dot product pada titik p dengan titik q
     * @param p titik p
     * @param q titik q
     * @return dot product
     */
    private double dot(Point p,Point q){ //menghitung dot product
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
    private boolean intersect(Point p,Point q,Point r,Point s){//mengecek apabila kedua buah garis berpotongan atau tidak
        double posisi_p = ccw(r, p, q); // mencari posisi titik r pada pq
        double posisi_q = ccw(s, p, q); // mencari posisi titik s pada pq
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
    private boolean isIn(Point[]p,Point t){//Mengecek apakah titik berada dalam polygon atau tidak
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
    private Point[] aklToussant(Point [] p){
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
    private double ccw(Point p, Point q, Point r) {
        Point pq = new Point(q.x - p.x, q.y - p.y); //pq berisikan koordinat q-koordinat p
        Point qr = new Point(r.x - q.x, r.y - q.y); //qr berisikan koordinat r-koordinat q
        return cross(pq, qr); //pq X qr
    }
    
    private void incrementaSweeping(Point[] p){
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
    
    private double luasSegitiga(Point p, Point q, Point r) {
        double res = 0.0;
        res+=(p.x+q.x)*(p.y+q.y);
        res+=(q.x+r.x)*(q.y+r.y);
        res+=(r.x+p.x)*(r.y+p.x);
        return Math.abs(res / 2.0);
    }
    
    public void rotatingCaliper(Point[] p){
        int idxp = p.length-2;
        int idxnextp = 0;
        int idxq = 0;
        int idxnextq = 1;
        while(luasSegitiga(p[idxp],p[idxnextp],p[idxnextq])>luasSegitiga(p[idxp],p[idxnextp],p[idxp])){
            idxq = idxnextq;
            idxnextq++;
        }
        
    }
    
    /**
     * Metode bruteforce untuk mencari jarak terpendek pada index yang sudah ditentukan
     * @param p kumpulan titik
     * @param start index awal
     * @param end index akhir
     * @return garis yang terbentuk dari dua pasangan titik terdekat
     */
    private Line shortBF(Point[] p, int start, int end){
        Point[] res = new Point[2];
        double min_val = Double.MAX_VALUE;
        for (int i = start; i < end; i++) {
            for (int j = i+1; j < end; j++) {
                double temp = this.dist(p[i], p[j]);
                if (temp<min_val) {
                    min_val = temp;
                    this.sOne = p[i];
                    this.sTwo = p[j];
                }
            }
        }
        Line hasil = new Line(this.sOne,this.sTwo,min_val);
        return hasil;
    }
    
    /**
     * Mencari jarak terkecil dari hasil perhitungan jarak sebelumnya
     * @param s kumpulan titik yang berada di dalam jangkauan jarak sebelumnya dari garis pembagi
     * @param d jarak terpendek yang sudah dihitung
     * @return garis yang terbentuk dari dua pasangan titik terdekat
     */
    private Line stripClosest(Point[] s, double d){
        Point[] res = new Point[2];
        double min_val = d;
        for (int i = 0; i < s.length; i++) {
            for(int j=i+1;j<s.length;j++){
                double temp = this.dist(s[i], s[j]);
                if (temp<min_val) {
                    min_val = temp;
                    this.sOne = s[i];
                    this.sTwo = s[j];
                }
            }
        }
        Line hasil = new Line(this.sOne,this.sTwo,min_val);
        return hasil;
    }
    
    /**
     * Membandingkan dua segmen garis mana jarak yang lebih pendek
     * @param l1 garis yang terbentuk dari dua titik
     * @param l2 garis yang terbentuk dari dua titik
     * @return garis terpendek
     */
    private Line smaller(Line l1, Line l2){
        if(l1.distance<l2.distance){
            return l1;
        }
        else{
            return l2;
        }
    }
    
    /**
     * Metode rekursif devide and conquer untuk mencari titik dengan jarak terdekat
     * @param p kumpulan titik
     * @param q kumpulan titik
     * @param start index dimulai
     * @param n index akhir
     * @return garis yang terbentuk dari dua pasangan titik terdekat
     */
    private Line closestR(Point[] p, Point[] q, int start, int n){
        if (n<=3) {
            return shortBF(p, start, n);
        }
        int mid = n/2-1;
        Point midPoint = p[mid];
        
        Line left = closestR(p, q, start, mid);
        Line right = closestR(p, q, mid, n-1);
        
        Line temp = this.smaller(left,right);
        
        int count = 0;
        for (int i = 0; i < q.length; i++) {
            if(Math.abs(q[i].x) - Math.abs(midPoint.x)<temp.distance){
                count++;
            }
        }
        if(count==0){
            return temp;
        }
        else{
            Point[] strip = new Point[count];
            int j = 0;
            for (int i = 0; i < q.length; i++) {
                if(Math.abs(q[i].x) - Math.abs(midPoint.x)<temp.distance){
                    strip[j] = q[i];
                    j++;
                }
            }
            Line temp2 = this.smaller(temp, this.stripClosest(strip, temp.distance));
            return temp2;
        }
    }
    
    /**
     * Memanggil metode rekursif untuk mencari pasangan titik terdekat
     * @param p kumpulan titik
     * @return garis yang terbentuk dari dua pasangan titik terdekat
     */
    private Line closestPair(Point[] p){
        Arrays.sort(p);
        Point[] q = p;
        return this.closestR(p, q, 0, p.length);
    }
    
    public String idxClosestPair(Point[] p){
        Point[] temp = p.clone();
        Line cp = this.closestPair(p);
        Point a=cp.p;
        Point b=cp.q;
        String aS="";
        String bS="";
        for (int q = 0; q < p.length; q++) {
            if(temp[q].equivalent(a)){
                aS+=q+1;
            }else if(temp[q].equivalent(b)){
                bS+=q+1;
            }
        }
        return aS+" "+bS;
    }
}
