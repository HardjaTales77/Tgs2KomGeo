/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas2komgeo;
import java.util.Scanner;
/**
 *
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
                p[j].x=sc.nextInt();
                p[j].y=sc.nextInt();
            }
        }
    }
    
}
