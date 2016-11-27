/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programGA;

/**
 *
 * @author agungrb
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graf graf = new Graf();
        GA2 ga = new GA2(graf,10);
        
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
        for (int i = 0; i < p.listParent.size(); i++) {
            for (int j = 0; j < p.getParent(i).listGen.size(); j++) {
                System.out.print(p.getParent(i).getGen(j)+" ");
            }
            System.out.println("fitness : "+p.getParent(i).fitness);
        }
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
        
    }
    
}
