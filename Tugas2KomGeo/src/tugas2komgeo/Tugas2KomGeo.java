/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas2komgeo;
import java.util.Scanner;
/**
 * Kelas tester untuk memanggil seluruh kode yang dibutuhkan
 * @author Kelvin Adrian Darmawan / 2017730043
 * @author Ivan Hardja / 2017730002
 */
public class Tugas2KomGeo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int kasus = sc.nextInt();//banyak kasus
        
        for(int i=0;i<kasus;i++){//utk setiap kasus
            int n = sc.nextInt();//banyak titik
            Point[]p=new Point[n];//array yang menyimpan titik
            for(int j=0;j<n;j++){//untuk sebanyak n dilakukan
                p[j]= new Point(sc.nextInt(),sc.nextInt());//buat titik baru untuk array
            }
            Calculation calc = new Calculation();
            System.out.println(calc.idxClosestPair(p));//mengeluarkan index kedua titik dengan jarak terdekat
            System.out.println(calc.idxLongestPair(p));
        }
    }
    
}
