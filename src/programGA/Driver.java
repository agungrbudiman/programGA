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
        
        System.out.print("Jumlah Kromosom : "); jKromosom = scan.nextInt();
        System.out.print("Jumlah Generasi : "); jGenerasi = scan.nextInt();
        
        GA2 ga = new GA2(graf,jKromosom);
        ga.generateKromosom();
        
        for (int i = 0; i < jGenerasi; i++) {;
            ga.fixKromosom(0);
            ga.hitungfitness(0);
            System.out.println("==================GENERASI KE-"+(i+1)+"==================");
            System.out.println("===================CALON PARENT===================");
            for (Kromosom k : ga.p.listParent) {
                for (int j = 0; j < k.listGen.size(); j++) {
                    System.out.print(k.getGen(j)+" "); 
                }
                System.out.println("fitness : "+k.fitness);
            }
            ga.seleksi();
            System.out.println("====================PASANGAN====================");
            for (Kromosom k : ga.p.listParent) {
                for (int j = 0; j < k.listGen.size(); j++) {
                    System.out.print(k.getGen(j)+" "); 
                }
                System.out.println("fitness : "+k.fitness);
            }
            ga.crossover();
            System.out.println("======================ANAK======================");
            for (Kromosom k : ga.p.listAnak) {
                for (int j = 0; j < k.listGen.size(); j++) {
                    System.out.print(k.getGen(j)+" "); 
                }
                System.out.println("fitness : "+k.fitness);
            }
            ga.mutasi();
            System.out.println("====================BERTAHAN====================");
            for (Kromosom k : ga.p.listBertahan) {
                for (int j = 0; j < k.listGen.size(); j++) {
                    System.out.print(k.getGen(j)+" "); 
                }
                System.out.println("fitness : "+k.fitness);
            }
            System.out.print("jalur terbaik : ");
            for (int j = 0; j < ga.p.listBertahan.get(0).listGen.size(); j++) {
                System.out.print(ga.p.listBertahan.get(0).getGen(j)+" ");
            }
            System.out.println("");
            System.out.println("Cost = "+(int)(1/ga.p.listBertahan.get(0).fitness)+" menit");
            
            ArrayList<Kromosom> temp = (ArrayList<Kromosom>) ga.p.listBertahan.clone();
            ga.p = new Populasi();
            for (int j = 0; j < temp.size(); j++) {
                ga.p.listParent.add(new Kromosom());
                for (int jj = 0; jj < temp.get(j).listGen.size(); jj++) {
                   ga.p.listParent.get(j).addGen(temp.get(j).getGen(jj)); 
                   if(jj < temp.get(j).open.size()) {
                       ga.p.listParent.get(j).addOpen(temp.get(j).getOpen(jj)); 
                   }
                }
            }
            System.out.println("");
        }
  
    }
    
}
