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
        GA ga = new GA(graf,10,0.25,0.25);
        
        System.out.println("===========GENERATE KROMOSOM============");
        Populasi p = ga.generateKromosom();
        for (int i = 0; i < p.listKromosom.size(); i++) {
            for (int j = 0; j < p.getKromosom(i).listGen.size(); j++) {
                System.out.print(p.getKromosom(i).getGen(j)+" ");
            }
            System.out.println("");
        }
        System.out.println("");
        
        System.out.println("==========FIX KROMOSOM+FITNESS==========");
        ga.fixKromosom(p);
        ga.hitungFitness(p);
        for (int i = 0; i < p.listKromosom.size(); i++) {
            for (int j = 0; j < p.getKromosom(i).listGen.size(); j++) {
                System.out.print(p.getKromosom(i).getGen(j)+" ");
            }
            System.out.println("fitness : "+p.getKromosom(i).fitness);
        }
        System.out.println("");
        
        System.out.println("================SELEKSI================");
        p = ga.seleksi(p);
        for (int i = 0; i < p.listKromosom.size(); i++) {
            for (int j = 0; j < p.getKromosom(i).listGen.size(); j++) {
                System.out.print(p.getKromosom(i).getGen(j)+" ");
            }
            System.out.println("fitness : "+p.getKromosom(i).fitness);
        }
        System.out.println("");
    }
    
}
