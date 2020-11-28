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
            int n = sc.nextInt();
            Point[]p=new Point[n];
            for(int j=0;j<n;j++){
                p[j]= new Point(sc.nextInt(),sc.nextInt());
            }
            Calculation calc = new Calculation();
            Line shortest = calc.closestPair(p);
            int i1=-1;
            int i2=-1;
            int count=0;
            while(count<p.length){
                if(p[count].x==shortest.p.x){
                    i1=count;
                }
                if(p[count].y==shortest.q.y){
                    i2=count;
                }
                if(i1!=-1&&i2!=-1){
                    break;
                }
                count++;
            }
            System.out.println(shortest.p.x+" "+shortest.p.y+" "+shortest.q.x+" "+shortest.q.y);
            System.out.println(i1+" "+i2);
        }
    }
    
}
