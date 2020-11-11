/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas2komgeo;
/**
 *
 * @author Ivan Hardja / 2017730002
 */
public class Calculation {
    public Calculation(){}
    
    public double dist(Point p1,Point p2){ //mencari jarak antara 2 titik
        double xx = Math.pow(p1.x-p2.x,2); //nilai kuadrat kordinat x point 1 dikurang kordinat x point 2
        double yy = Math.pow(p1.y-p2.y,2); //nilai kuadrat kordinat y point 1 dikurang kordinat x point 2
    
        double res = Math.sqrt(xx+yy); //mencari akar nilai pertambahan xx dan yy
        return res;
    }
  
    public double pos(Point p, Point q, Point r){ //mencari posisi titik pada garis
        Point pq = new Point(q.x-p.x,q.y-p.y); //vektor pq di isi kordinat q dikurang kordinat p
        Point qr = new Point(r.x-q.x,r.y-q.y); //vektor pq di isi kordinat q dikurang kordinat p
   	
        double res = cross(pq,qr); //cross product pq dan qr
    
        return res; //positif=kanan, negatif=kiri, 0=lurus
    }
  
    public double cross(Point p,Point q){ //menghitung cross product
        double res = p.x*q.y-p.y*q.x; //hitung cross product
        return  res;//cross product
    }
  
    public double dot(Point p,Point q){ //menghitung dot product
        double res = p.x*q.x+p.y*q.y; //hitung dot product
        return res;//dot product
    }
    
    public boolean intersect(Point p,Point q,Point r,Point s){//mengecek apabila kedua buah garis berpotongan atau tidak
        double posP = this.pos(r,p,q);//mencari posisi titik r pada garis p dan q
        double posQ = this.pos(s,p,q);//mencari posisi titik s pada garis p dan q
    
        if(posP>0&&posQ<0){//cek apakah titik r berada di kanan garis serta titik s di kiri garis
            return true;//jika iya maka berpotongan
        }else if(posP<0&&posQ>0){//cek apakah titik s berada di kanan garis serta titik r di kiri garis
            return true;//jika iya maka berpotongan
        }else{
            return false;//jika tidak maka tidak berpotongan
        }
    } 
    
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
}
