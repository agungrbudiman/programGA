/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programGA;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author agungrb
 */
public class GA2 {
    private Graf graf;
    private int jKromosom;
    
    public GA2(Graf graf,int jKromosom) {
        this.graf = graf;
        this.jKromosom = jKromosom;
    }
    
    public double randomDouble(int min, int max) {
        Random r = new Random();
        double randomValue = min + (max - min) * r.nextDouble();
        return randomValue;
    }
    
    public Populasi generateKromosom() {
        Populasi p = new Populasi();
        for (int i = 0; i < jKromosom; i++) {
            Kromosom k = new Kromosom();
            k.addGen('A');k.addGen('B');k.addGen('C');k.addGen('D');k.addGen('E');k.addGen('F');
            Collections.shuffle(k.listGen);
            p.addKromosom(k);
        }
        return p;
    }
    
    public void fixKromosom(Populasi p) {
        for (Kromosom k : p.listKromosom) {
            int i=0;
            while(!graf.cekJalur('S', k.getGen(i)) && i<6) {
                k.addOpen(k.getGen(i));
                k.listGen.set(i, '0');
                i++;
            }
            while(i<6) {
                if (graf.cekJalur('G', k.getGen(i))) {
                    for (int j = i + 1; j < 6; j++) {
                        k.addOpen(k.getGen(i));
                        k.listGen.set(j, '0');
                    }
                    break;
                } else if (i<5){
                    int j=i+1;
                    while (j<6 && !graf.cekJalur(k.getGen(i), k.getGen(j))) {
                        k.addOpen(k.getGen(j));
                        k.listGen.set(j, '0');
                        j++;
                    }
                    i=j;
                }
                else {
                    i++;
                }
            }
        }
        
        for (Kromosom k : p.listKromosom) {
            k.listGen.removeAll(Arrays.asList('0'));
            
            if(!graf.cekJalur('G', k.getGen(k.listGen.size()-1))) {
                for (int i = 0; i < k.open.size(); i++) {
                    if(graf.cekJalur(k.getGen(k.listGen.size()-1),k.getOpen(i)) && graf.cekJalur('G', k.getOpen(i))) {
                        k.addGen(k.getOpen(i));
                        break;
                    }
                }
            }
            
            for (int i = k.listGen.size(); i < 6; i++) {
                k.addGen('0');
            }
        }
    }
    
    public void hitungfitness(Populasi p) {
        double sumFitness=0;
        for (Kromosom k : p.listKromosom) {
            for (int i = 0; i < 5; i++) {
                k.fitness = k.fitness + graf.getTime(k.getGen(i), k.getGen(i+1));
            }
            k.fitness = 1/k.fitness;
            sumFitness = sumFitness + k.fitness;
        }
        p.totalFitness = sumFitness;
    }
    
    public void seleksi(Populasi p) {
        for (Kromosom k : p.listKromosom) {
            p.probKumulatif.add(k.fitness/p.totalFitness);
        }
        for (int i = 1; i < p.probKumulatif.size(); i++) {
            p.probKumulatif.set(i, p.probKumulatif.get(i)+p.probKumulatif.get(i-1));
        }
        for (Kromosom k : p.listKromosom) {
            
        }
    }
}
