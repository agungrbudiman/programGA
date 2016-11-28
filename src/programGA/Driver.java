/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programGA;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author agungrb
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Graf graf = new Graf();
        int jKromosom, jGenerasi;
        double pCross,pMutasi;
        
        System.out.print("Jumlah Kromosom : "); jKromosom = scan.nextInt();
        System.out.print("Jumlah Generasi : "); jGenerasi = scan.nextInt();
        System.out.print("Probabilitas Crossover : "); pCross = scan.nextDouble();
        System.out.print("Probabilitas Mutasi : "); pMutasi = scan.nextDouble();
        
        GA2 ga = new GA2(graf,jKromosom,pCross,pMutasi);
        ga.generateKromosom();
        
        for (int i = 0; i < jGenerasi; i++) {
            int h;
            ga.fixKromosom(0);
            ga.hitungfitness(0);
            System.out.println("==================GENERASI KE-"+(i+1)+"==================");
            System.out.println("===================CALON PARENT===================");
            h=0;
            for (Kromosom k : ga.p.listParent) {
                System.out.print("["+h+"]");
                for (int j = 0; j < k.listGen.size(); j++) {
                    System.out.print(k.getGen(j)+" ");  
                }
                System.out.println("fitness : "+k.fitness);
                h++;
            }
            ga.seleksi();
            System.out.println("====================SELEKSI====================");
            h=0;
            for (Kromosom k : ga.p.listParent) {
                System.out.print("["+h+"]");
                for (int j = 0; j < k.listGen.size(); j++) {
                    System.out.print(k.getGen(j)+" ");  
                }
                System.out.println("fitness : "+k.fitness);
                h++;
            }
            ga.crossover();
            System.out.println("======================ANAK======================");
            h=0;
            for (Kromosom k : ga.p.listAnak) {
                System.out.print("["+h+"]");
                for (int j= 0; j < k.listGen.size(); j++) {
                    System.out.print(k.getGen(j)+" ");  
                }
                System.out.println("fitness : "+k.fitness);
                h++;
            }
//            System.out.println("ok");
            ga.mutasi();
            System.out.println("====================BERTAHAN====================");
            h=0;
            for (Kromosom k : ga.p.listBertahan) {
                System.out.print("["+h+"]");
                for (int j = 0; j < k.listGen.size(); j++) {
                    System.out.print(k.getGen(j)+" "); 
                }
                System.out.println("fitness : "+k.fitness);
                h++;
            }
            System.out.print("jalur terbaik : ");
            for (int j = 0; j < ga.p.listBertahan.get(0).listGen.size(); j++) {
                System.out.print(ga.p.listBertahan.get(0).getGen(j)+" ");
            }
             System.out.println(", Cost = "+(int)(1/ga.p.listBertahan.get(0).fitness)+" menit");
            System.out.println("");
           
            ArrayList<Kromosom> temp = (ArrayList<Kromosom>) ga.p.listBertahan.clone();
            ga.p = new Populasi();
            ga.p.listParent = (ArrayList<Kromosom>) temp.clone();

            System.out.println("");
        }
  
    }
    
}
