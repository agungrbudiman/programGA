/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programGA;

import java.util.Collections;

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
        
        for (Object object : col) {
            
        }
    }
}
