/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas2komgeo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Kelas ini berfungsi untuk melakukan operasi perhitungan
 * @author Kelvin Adrian Darmawan / 2017730043
 * @author Ivan Hardja / 2017730002
 */
public class Calculation {
    
    private Point sOne, sTwo;//titik global untuk menyimpan pada class Line salah satu titik terdekat atau terjauh
    
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
    private boolean isIn(Point[]p, Point t){//Mengecek apakah titik berada dalam polygon atau tidak
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
        LinkedList<Point> temp = new LinkedList<>();//Linkedlist untuk menyimpan hasil sementara
        Point [] shell = new Point[4];//array titik yang berisi titik yang membentuk segiempat : titik dengan nilai kordinat x terbesar dan terkecil serta titik dengan nilai kordinat y terbesar dan terkecil
        double yMax=Double.MIN_VALUE;//variabel menyimpan nilai kordinat y terbesar
        double yMin=Double.MAX_VALUE;//variabel menyimpan nilai kordinat y terkecil
        shell[0]=p[0];//array sudah di sort berdasarkan nilai kordinat x maka pada posisi array ke 0 adalah titik dengan nilai kordinat x terkecil
        shell[1]=p[p.length-1];//array sudah di sort berdasarkan nilai kordinat x maka pada posisi array ke p.length-1 adalah titik dengan nilai kordinat x terbesar
        for (int i = 1; i < p.length-1; i++) {//untuk semua titik pada array dari posisi ke 1 ke posisi ke titik sebelum p.length-1
            if(p[i].y>yMax){//cek apakah nilai kordinat y titik ke i lebih besar dari yMax
                yMax=p[i].y;//jika iya ganti nilai yMax dengan nilai kordinat y titik ke i
                shell[2]=p[i];//masukkan ke array titik shell titik ke i
            }
            if(p[i].y<yMin){//cek apakah nilai kordinat y titik ke i lebih kecil dari yMax
                yMin=p[i].y;//jika iya ganti nilai yMax dengan nilai kordinat y titik ke i
                shell[3]=p[i];//masukkan ke array titik shell titik ke i
            }
        }
        
        for (int i = 0; i < 4; i++) {//untuk semua titik pada shell
            temp.add(shell[i]);//tambahkan dalam linkedList temp
        }
        
        for (int i = 0; i < p.length; i++) {//untuk semua titik pada p
            if(!temp.contains(p[i])){//cek apakah titik sudah ada dalam linkedlist temp
                if(!this.isIn(shell, p[i])){//jika titik tidak ada dalam linkedlist cek jika titik berada di dalam polygon yang terbentuk dari array shell
                    temp.add(p[i]);//jika tidak maka tambahkan ke linkedlist temp
                }
            }
        }
        
        Point[] output = new Point[temp.size()];//array titik hasil berukuran besar dari linkedlist temp
        for (int i = 0; i < output.length; i++) {//untuk semua titik yang bisa dimasukkan dalam array output
            output[i] = temp.pop();//masukkan ke dalam array output titik dari linkedlist temp
        }
        return output;//kembalikan array output
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
    
    public Point[] incrementaSweeping(Point[] p){
        Arrays.sort(p);
        ArrayList<Point> l = new ArrayList<>();// untuk menampung convex hull saat ini
        if(p.length<=4){
            return p;
        }
        else{
            System.out.println("else");
            l.add(p[0]);            
            int bawah, atas, nbawah, natas;
            boolean isSAtas = false;
            //segitiga bawah
            if(p[0].y>p[1].y){
                nbawah = 1;
                natas = 0;
            }
            else{//segitiga atas
                nbawah = 0;
                natas = 1;
                isSAtas = true;
            }
            int right = 2;
            bawah = right;
            atas = right;
            for (int i = 3; i < p.length; i++) {
                //cek bawah
                while(this.ccw(p[i], l.get(bawah), l.get(nbawah))<0){ //selama masih belok kiri
                    bawah = nbawah;
                    if(nbawah!=0){
                        nbawah--;
                    }
                }
                //hasil: p[i]-->bawah-->nbawah
                //cek atas
                while(this.ccw(p[i], l.get(atas), l.get(natas))>0){ //selama masih belok kanan
                    atas = natas;
                    if(i==3){
                        natas--;
                    }
                    else{
                        natas++;
                    }
                }
                if(i>3){
                    //buang titik di dalam polygon baru
                    for (int j = bawah+1; j < atas; j++) {
                        l.remove(j);
                    }
                }
                else{
                    //bikin segitiga jadi ccw di ArrayList
                    if(!isSAtas){
                        l.add(p[2]);
                        l.add(p[1]);
                    }
                    else{
                        l.add(p[1]);
                        l.add(p[2]);
                    }
                }
                this.addMiddle(l, p[i], bawah+1);
            }
            return p;
        }
//        else{
//            System.out.println("else");
//            l.add(p[0]);
//            l.add(p[1]);
//            l.add(p[2]);
//            int bawah, atas, nbawah, natas;
//            if(p[0].y>p[1].y){
//                nbawah = 1;
//                natas = 0;
//            }
//            else{
//                nbawah = 0;
//                natas = 1;
//            }
//            int right = 2;
//            bawah = right;
//            atas = right;
//            for (int i = 3; i < p.length; i++) {
//                if(i==3){
//                    if(this.ccw(p[i], l.get(right), l.get(nbawah))<0){
//                        //belok kanan
//                        Point temp = l.get(natas);
//                        l.remove(natas);
//                        l.add(p[i]);
//                        l.add(temp);
//                    }
//                    else if(this.ccw(p[i], l.get(right), l.get(nbawah))>0){
//                        Point temp = l.get(right);
//                        l.remove(right);
//                        l.add(p[i]);
//                        l.add(temp);
//                    }
//                    bawah = nbawah;
//                    atas = natas;
//                }
//                else{
//                    while(this.ccw(p[i], l.get(bawah), l.get(nbawah))>0){
//                        if(nbawah!=0){
//                            bawah = nbawah;
//                            nbawah--;
//                        }
//                        else{
//                            break;
//                        }
//                    }
//                    while(this.ccw(p[i], l.get(atas), l.get(natas))<0){
//                        if(natas!=0){
//                            atas = natas;
//                            if(natas==l.size()-1){
//                                natas = 0;
//                            }
//                            else{
//                                natas++;
//                            }
//                        }
//                        else{
//                            break;
//                        }
//                    }
//                    //bawah, baru, atas
//                    for (int j = bawah+1; j < atas; j++) {
//                        l.remove(j);                        
//                    }
//                    this.addMiddle(l, p[i], bawah+1);
//                }
//            }            
//            Point[] arr = new Point[l.size()];
//            for (int i = 0; i < p.length; i++) {
//                arr[i] = l.get(i);            
//            }
//            return arr;
//        }
    }
    
    /**
     * Metode ini memasukkan sebuah titik ke dalam arraylist (di sela-sela titik lain)
     *
     * @param l arraylist dari titik dimana titik baru mau dimasukkan
     * @param p titik baru yang mau dimasukkan
     * @param index adalah posisi index letak mau dimasukkan titik
     * @return hasil arraylist
     */
    public ArrayList<Point> addMiddle(ArrayList<Point> l, Point p, int index){
        Point temp = l.get(index);//variabel titik yang menyimpan secara sementara titik awal yang berada di posisi index
        l.add(index, p);//masukkan ke dalam arraylist titik p pada posisi index
        for (int i = index+1; i < l.size(); i++) {//untuk setiap titik dari index+1 ke besar ukuran arraylist
            Point temp2 = l.get(i);//variabel sementara kedua yang menyimpan titik sementara di posisi ke i
            l.add(i, temp);//masukkan titik temp ke posisi i pada arraylist
            temp = temp2;//update temp dengan titik dari temp2
        }
        return l;//kembalikan hasil arraylist yang sudah ditambah titik
    }
    
    /**
     * Metode ini memanggil metode lain untuk mencari pasangan titik dengan jarak terjauh
     *
     * @param p array sebuah titik
     * @return variabel Line hasil
     */
    private Line longest(Point[] p){
        Point[] temp = this.incrementaSweeping(this.aklToussant(p));//variabel array titik ini menyimpan hasil convex hull dari proses metode incrementalSweeping dan akl-toussaint     
        Line hasil = this.rotatingCaliper(temp);//variabel line yang menyimpan hasil perhitungan rotating caliper
        return hasil;
    }
    
    /**
     * Metode ini mencari index dari kedua titik terjauh
     * @param p kumpulan titik
     * @return String yang berupa index dari kedua titik yang memiliki jarak terjauh
     */
    public String idxLongestPair(Point[] p){
        Point[] temp = p.clone();//buat sebuah array yang merupakan clone dari array p
        Line lp = this.longest(p);//panggil metode longest dengan masukan p
        Point a=lp.p;//menyimpan titik dari garis lp
        Point b=lp.q;//menyimpan titik dari garis lp
        String aS="";//variabel string yang menyimpan index titik pertama
        String bS="";//variabel string yang menyimpan index titik kedua
        for (int q = 0; q < p.length; q++) {//untuk setiap titik pada temp
            if(temp[q].equivalent(a)){//cek apabila titik temp ke q sama dengan titik a
                aS+=q+1;//jika iya, simpan index pada String aS ditambah dengan 1 karena index hasil dari 1 sampai n
            }else if(temp[q].equivalent(b)){//cek apabila titik temp ke q sama dengan titik b
                bS+=q+1;//jika iya, simpan index pada String aS ditambah dengan 1 karena index hasil dari 1 sampai n
            }
        }
        return aS+" "+bS;//kembalikan kedua String
    }
    
    /**
     * Metode ini menghitung luas dari segitiga yang terbentuk dari 3 buah titik
     *
     * @param p titik 1
     * @param q titik 2
     * @param r titik 3
     * @return hasil luas segitiga
     */
    private double luasSegitiga(Point p, Point q, Point r) {
        double res = 0.0;//variabel tempat hasil perhitungan disimpan
        res+=(p.x+q.x)*(p.y+q.y);//res ditambah dengan pertambahan kordinat x dari titik p dan q dikalikan dengan pertambahan kordinat y nya
        res+=(q.x+r.x)*(q.y+r.y);//res ditambah dengan pertambahan kordinat x dari titik q dan r dikalikan dengan pertambahan kordinat y nya
        res+=(r.x+p.x)*(r.y+p.x);//res ditambah dengan pertambahan kordinat x dari titik p dan r dikalikan dengan pertambahan kordinat y nya
        return Math.abs(res / 2.0);
    }
    
    /**
     * Metode ini menggunakan algoritma Rotating Calipers untuk mencari 2 titik dengan jarak terjauh
     *
     * @param p kumpulan titik
     * @return variabel Line yang terbuat dari 2 titik dengan jarak terjauh
     */
    private Line rotatingCaliper(Point[] p){
        int n = p.length;//variabel ini menyimpan banyaknya titik p
        if(n==1){//jika banyak titik hanya 1
            return null;//kembalikan null
        }
        if(n == 2){//jika banyak titik hanya 2
            return new Line(p[0], p[1], this.dist(p[0],p[1]));//kembalikan garis yang terbentuk dari kedua titik tersebut
        }
        int k = 1;//variabel berfungsi sebagai counter/index
        while(this.luasSegitiga(p[n-1], p[0], p[(k+1)%n])>this.luasSegitiga(p[n-1], p[0], p[k])){//selama luas segitiga dari titik p[n-1],p[n],p[(k+1)%n] lebih besar dari luas segitiga p[n-1], p[0], p[k]
            k++;//variabel k bertambah
        }
        double res = 0;//variabel ini menyimpan hasil dengan jarak terjauh
        for (int i = 0, j = k; i<=k && j < n; i++) {//untuk semua titik i lebih kecil dari k dan untuk semua titik dari k lebih kecil dari n
            double temp = this.dist(p[i], p[j]);//varibel sementara menyimpan jarak dari titik ke i dan j
            if(temp>res){//cek jika nilai temp lebih besar dari res
                res = temp;//jika iya maka ganti nilai res menjadi temp
                this.sOne=p[i];//simpan titik ke i pada global variabel sOne
                this.sTwo=p[j];//simpan titik ke j pada global variabel sTwo
            }
            while(j<n && this.luasSegitiga(p[i], p[(i+1)%n], p[(j+1)%n])>
                    this.luasSegitiga(p[i], p[(i+1)%n], p[j])){//selama nilai j lebih kecil n dan luas segitiga titik p[i], p[(i+1)%n], p[(j+1)%n] lebih besar dari luas segitiga titik p[i], p[(i+1)%n], p[j]
                temp = this.dist(p[i], p[(j+1)%n]);//isi temp dengan jarak dari titik p[i], p[(j+1)%n]
                if(temp>res){//cek apakah nilai temp lebih besar dari res
                    res = temp;//jika iya ganti nilai res menjadi temp
                    this.sOne=p[i];//simpan titik ke i pada global variabel sOne
                    this.sTwo=p[(j+1)%n];//simpan titik ke (j+1)%n pada global variabel sOne
                }
                j++;//nilai j bertambah
            }
        }
        Line hasil = new Line(this.sOne, this.sTwo, res);//variabel hasil menyimpan garis yang terbentuk dari variabel global sOne, sTwo dan res
        return hasil;//kembalikan hasil
    }
    
    /**
     * Metode bruteforce untuk mencari jarak terpendek pada index yang sudah ditentukan
     * @param p kumpulan titik
     * @param start index awal
     * @param end index akhir
     * @return garis yang terbentuk dari dua pasangan titik terdekat
     */
    private Line shortBF(Point[] p, int start, int end){
        double min_val = Double.MAX_VALUE;//menyimpan jarak paling kecil
        for (int i = start; i < end; i++) {//untuk setiap titik dari index start ke index end suatu array
            for (int j = i+1; j < end; j++) {//untuk setiap titik dari sesudah i ke index end suatu array
                double temp = this.dist(p[i], p[j]);//variabel yang menyimpan hasil perhitungan jarak 2 buah titik
                if (temp<min_val) {//cek apakah nilai temp lebih kecil dari min_val
                    min_val = temp;//jika lebih kecil maka nilai min_val adalah temp
                    this.sOne = p[i];//nilai variabel global sOne adalah titik ke i yang membuat garis
                    this.sTwo = p[j];//nilai variabel global sTwo adalah titik ke j yang membuat garis
                }
            }
        }
        Line hasil = new Line(this.sOne,this.sTwo,min_val);//buat sebuah garis yang terbentuk dari kedua titik dengan jarak terdekat
        return hasil;//kembalikan hasil
    }
    
    /**
     * Mencari jarak terkecil dari hasil perhitungan jarak sebelumnya
     * @param s kumpulan titik yang berada di dalam jangkauan jarak sebelumnya dari garis pembagi
     * @param d jarak terpendek yang sudah dihitung
     * @return garis yang terbentuk dari dua pasangan titik terdekat
     */
    private Line stripClosest(Point[] s, double d){
        double min_val = d;//menyimpan jarak terkecil yang sudah terhitung
        for (int i = 0; i < s.length; i++) {//untuk setiap titik pada array
            for(int j=i+1;j<s.length;j++){//untuk setiap titik pada array sesudah i
                double temp = this.dist(s[i], s[j]);//variabel menyimpan jarak yang dihitung
                if (temp<min_val) {//cek apakah nilai temp lebih kecil dari min_val
                    min_val = temp;//jika lebih kecil maka nilai min_val adalah temp
                    this.sOne = s[i];//variabel global sOne menyimpan titik ke i
                    this.sTwo = s[j];//variabel global sTwo menyimpan titik ke j
                }
            }
        }
        Line hasil = new Line(this.sOne,this.sTwo,min_val);//buat sebuah garis yang terbentuk dari kedua titik dengan jarak terdekat
        return hasil;//kembalikan hasil
    }
    
    /**
     * Membandingkan dua segmen garis mana jarak yang lebih pendek
     * @param l1 garis yang terbentuk dari dua titik
     * @param l2 garis yang terbentuk dari dua titik
     * @return garis terpendek
     */
    private Line smaller(Line l1, Line l2){
        if(l1.distance<l2.distance){//cek apakah jarak garis l1 lebih kecil dari l2
            return l1;//jika iya kembalikan garis l1
        }
        else{//jika tidak
            return l2;//kembalikan garis l2
        }
    }
    
    /**
     * Metode rekursif devide and conquer untuk mencari titik dengan jarak terdekat
     * @param p kumpulan titik
     * @param q kumpulan titik
     * @param start index dimulai
     * @param n index akhir
     * param p dan q merupakan array titik yang sama
     * @return garis yang terbentuk dari dua pasangan titik terdekat
     */
    private Line closestR(Point[] p, Point[] q, int start, int n){
        if (n<=3) {//jika banyak titik lebih kecil dari 3
            return shortBF(p, start, n);//panggil metode shortBF
        }
        int mid = n/2-1;//hitung index tengah dari array
        Point midPoint = p[mid];//variabel yang menyimpan titik dengan posisi index di tengah
        
        Line left = closestR(p, q, start, mid);//panggil secara rekursif untuk garis di bagian kiri, dengan array dimulai dari start ke mid
        Line right = closestR(p, q, mid, n-1);//panggil secara rekursif untuk garis di bagian kanan, dari mid ke n-1
        
        Line temp = this.smaller(left,right);//variabel menyimpan garis yang lebih kecil dari garis bagian kiri dan bagian kanan
        
        int count = 0;//variabel yang menghitung banyak titik yang berada di dekat garis pembagi kedua bagian kiri dan kanan
        for (int i = 0; i < q.length; i++) {//untuk setiap titik di q
            if(Math.abs(q[i].x) - Math.abs(midPoint.x)<temp.distance){//hitung untuk titik q ke i jika nilai kordinat x nya ketika dikurangi dengan kordinat x titik midPoint lebih kecil dari jarak garis terdekat atau tidak
                count++;//jika iya variabel count bertambah
            }
        }
        if(count==0){//jika variabel count nilai masih 0 maka sudah tidak ada garis dengan jarak yang lebih dekat
            return temp;//kembalikan garis temp
        }
        else{//jika variabel count nilai lebih dari 0
            Point[] strip = new Point[count];//buat sebuah array titik berukuran count
            int j = 0;//variabel ini menyimpan index untuk array strip
            for (int i = 0; i < q.length; i++) {//untuk setiap titik pada array q
                if(Math.abs(q[i].x) - Math.abs(midPoint.x)<temp.distance){//hitung untuk titik q ke i jika nilai kordinat x nya ketika dikurangi dengan kordinat x titik midPoint lebih kecil dari jarak garis terdekat atau tidak
                    strip[j] = q[i];//maka untuk strip ke j berisi titik dari q ke i
                    j++;//nilai j bertambah
                }
            }
            Line temp2 = this.smaller(temp, this.stripClosest(strip, temp.distance));//cek garis yang lebih pendek dari temp atau hasil garis dari metode stripClosest
            return temp2;//kembalikan temp2
        }
    }
    
    /**
     * Memanggil metode rekursif untuk mencari pasangan titik terdekat
     * @param p kumpulan titik
     * @return garis yang terbentuk dari dua pasangan titik terdekat
     */
    private Line closestPair(Point[] p){
        Arrays.sort(p);//urutkan array p berdasarkan kordinat x
        Point[] q = p;//buat array q yang merupakan array p
        return this.closestR(p, q, 0, p.length);//panggil metode rekursif sebagai output
    }
    
    /**
     * Metode ini mencari index dari kedua titik terdekat
     * @param p kumpulan titik
     * @return String yang berupa index dari kedua titik yang memiliki jarak terdekat
     */
    public String idxClosestPair(Point[] p){
        Point[] temp = p.clone();//buat sebuah array yang merupakan clone dari array p
        Line cp = this.closestPair(p);//panggil metode closestPair dengan masukan p
        Point a=cp.p;//menyimpan titik dari garis cp
        Point b=cp.q;//menyimpan titik dari garis cp
        String aS="";//variabel string yang menyimpan index titik pertama
        String bS="";//variabel string yang menyimpan index titik kedua
        for (int q = 0; q < p.length; q++) {//untuk setiap titik pada temp
            if(temp[q].equivalent(a)){//cek apabila titik temp ke q sama dengan titik a
                aS+=q+1;//jika iya, simpan index pada String aS ditambah dengan 1 karena index hasil dari 1 sampai n
            }else if(temp[q].equivalent(b)){//cek apabila titik temp ke q sama dengan titik b
                bS+=q+1;//jika iya, simpan index pada String aS ditambah dengan 1 karena index hasil dari 1 sampai n
            }
        }
        return aS+" "+bS;//kembalikan kedua String
    }
}
