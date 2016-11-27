/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programGA;

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
        System.out.println("Jumlah Generasi : "); jGenerasi = scan.nextInt();
        GA2 ga = new GA2(graf,jKromosom);
        
        for (String arg : args) {
            
        }
        System.out.println("===========GENERATE KROMOSOM============");
        Populasi p = ga.generateKromosom();
        for (int i = 0; i < p.listParent.size(); i++) {
            for (int j = 0; j < p.getParent(i).listGen.size(); j++) {
                System.out.print(p.getParent(i).getGen(j)+" ");
            }
            System.out.println("");
        }
        System.out.println("");
        
        System.out.println("==========FIX KROMOSOM+FITNESS==========");
        ga.fixKromosom(p,0);
        ga.hitungfitness(p,0);
        System.out.println("");
        for (int i = 0; i < p.listParent.size(); i++) {
            for (int j = 0; j < p.getParent(i).listGen.size(); j++) {
                System.out.print(p.getParent(i).getGen(j)+" ");
            }
            System.out.println("fitness : "+p.getParent(i).fitness);
        }
        System.out.println("");
        
        System.out.println("================SELEKSI================");
        p = ga.seleksi(p);
        for (int i = 0; i < p.listParent.size(); i++) {
            for (int j = 0; j < p.getParent(i).listGen.size(); j++) {
                System.out.print(p.getParent(i).getGen(j)+" ");
            }
            System.out.println("fitness : "+p.getParent(i).fitness);
        }
        System.out.println("");
        
        System.out.println("================CROSSOVER================");
        ga.crossover(p);
        System.out.println("");
        for (int i = 0; i < p.listAnak.size(); i++) {
            for (int j = 0; j < p.getAnak(i).listGen.size(); j++) {
                System.out.print(p.getAnak(i).getGen(j)+" ");
            }
            System.out.println("fitness : "+p.getAnak(i).fitness);
        }
        System.out.println("");
        
        System.out.println("================MUTASI================");
        ga.mutasi(p);
        for (int i = 0; i < p.listBertahan.size(); i++) {
            for (int j = 0; j < p.getBertahan(i).listGen.size(); j++) {
                System.out.print(p.getBertahan(i).getGen(j)+" ");
            }
            System.out.println("fitness : "+p.getBertahan(i).fitness);
        }
        System.out.println("");
    }
    
}
