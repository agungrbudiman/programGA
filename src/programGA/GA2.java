/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programGA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author agungrb
 */
public class GA2 {

    private Graf graf;
    private int jKromosom;
    private double pCross, pMutasi;
    public Populasi p;

    public GA2(Graf graf, int jKromosom, double pCross, double pMutasi) {
        this.graf = graf;
        this.jKromosom = jKromosom;
        this.pCross = pCross;
        this.pMutasi = pMutasi;
    }

    public void proses() {
        fixKromosom(0);
        hitungfitness(0);
        seleksi();
        crossover();
        mutasi();
    }

    public double randomDouble(int min, int max) {
        Random r = new Random();
        double randomValue = min + (max - min) * r.nextDouble();
        return randomValue;
    }

    public int randomInteger(int min, int max) {
        Random r = new Random();
        int randomValue = r.nextInt(max - min + 1) + min;
        return randomValue;
    }

    public void generateKromosom() {
        p = new Populasi();
        for (int i = 0; i < jKromosom; i++) {
            Kromosom k = new Kromosom();
            k.addGen('A');
            k.addGen('B');
            k.addGen('C');
            k.addGen('D');
            k.addGen('E');
            k.addGen('F');
            Collections.shuffle(k.listGen);
            p.addParent(k);
        }
    }

    public void fixKromosom(int x) {
        ArrayList<Kromosom> pointer = null;
        if (x == 0) {
            pointer = p.listParent;
        } else if (x == 1) {
            pointer = p.listAnak;
        } else if (x == 2) {
            pointer = p.listBertahan;
        }
        for (Kromosom k : pointer) {
            int i = 0;
            while (!graf.cekJalur('S', k.getGen(i)) && i < k.listGen.size()) {
                if (k.getGen(i) != 0) {
                    k.addOpen(k.getGen(i));
                }
                k.listGen.set(i, '0');
                i++;
            }
            while (i < k.listGen.size()) {
                if (graf.cekJalur('G', k.getGen(i))) {
                    for (int j = i + 1; j < k.listGen.size(); j++) {
                        if (k.getGen(j) != 0) {
                            k.addOpen(k.getGen(j));
                        }
                        k.listGen.set(j, '0');
                    }
                    break;
                } else if (i < k.listGen.size() - 1) {
                    int j = i + 1;
                    while (j < k.listGen.size() && !graf.cekJalur(k.getGen(i), k.getGen(j))) {
                        if (k.getGen(j) != 0) {
                            k.addOpen(k.getGen(j));
                        }
                        k.listGen.set(j, '0');
                        j++;
                    }
                    i = j;
                } else {
                    i++;
                }

            }
        }
        for (Kromosom k : pointer) {
            k.listGen.removeAll(Arrays.asList('0'));
            while (!graf.cekJalur('G', k.getGen(k.listGen.size() - 1))) {
                for (int i = 0; i < k.open.size(); i++) {
                    if (graf.cekJalur(k.getGen(k.listGen.size() - 1), k.getOpen(i)) && graf.cekJalur('G', k.getOpen(i))) {
                        k.addGen(k.getOpen(i));
                        k.rmOpen(i);
                        break;
                    } else if (graf.cekJalur(k.getGen(k.listGen.size() - 1), k.getOpen(i))) {
                        boolean sign = false;
                        for (int j = 0; j < k.listGen.size(); j++) {
                            if (k.getOpen(i) == k.getGen(j)) {
                                sign = true;
                                break;
                            }

                        }
                        if (!sign) {
                            k.addGen(k.getOpen(i));
                            k.rmOpen(i);
                        }
                    }
                }
            }
            for (int i = k.listGen.size(); i < 6; i++) {
                k.addGen('0');
            }
        }

    }

    public void hitungfitness(int x) {
        ArrayList<Kromosom> pointer = null;
        if (x == 0) {
            pointer = p.listParent;
        } else if (x == 1) {
            pointer = p.listAnak;
        } else if (x == 2) {
            pointer = p.listBertahan;
        }
        double sumFitness = 0;
        for (Kromosom k : pointer) {
            for (int i = 0; i < 5; i++) {
                k.fitness = k.fitness + graf.getTime(k.getGen(i), k.getGen(i + 1));
            }
            k.fitness = k.fitness + graf.getTime('S', k.getGen(0)) + 9;
            k.fitness = 1 / k.fitness;

            sumFitness = sumFitness + k.fitness;
        }
        p.totalFitness = sumFitness;
    }

    public void seleksi() {
        ArrayList<Kromosom> temp = new ArrayList();
        for (Kromosom k : p.listParent) {
            p.probKumulatif.add(k.fitness / p.totalFitness);
        }
        for (int i = 1; i < p.probKumulatif.size(); i++) {
            p.probKumulatif.set(i, p.probKumulatif.get(i) + p.probKumulatif.get(i - 1));
        }
        for (Kromosom k : p.listParent) {
            int i = 0;
            double randomNum = randomDouble(0, 1);
            while (randomNum >= p.probKumulatif.get(i)) {
                i++;
            }
            temp.add(new Kromosom());
            temp.get(temp.size() - 1).fitness = p.getParent(i).fitness;
            for (int j = 0; j < p.getParent(i).listGen.size(); j++) {
                temp.get(temp.size() - 1).addGen(p.getParent(i).getGen(j));
                if (j < p.getParent(i).open.size()) {
                    temp.get(temp.size() - 1).addOpen(p.getParent(i).getOpen(j));
                }
            }
        }
        p.listParent.clear();
        p.listParent = (ArrayList<Kromosom>) temp.clone();
    }

    public void crossover() {
        ArrayList<Integer> list = new ArrayList();
        for (int i = 0; i < p.listParent.size(); i++) {
            double randNum = randomDouble(0, 1);
            if (randNum < pCross) {
                list.add(i);
            }
        }
        if (list.size() > 1) {

            for (int p1 = 0; p1 < p.listParent.size(); p1++) {
                boolean sign = false;
                int p2 = 0;
                for (int k = 0; k < list.size(); k++) {
                    if (p1 == list.get(k)) {
                        if (k < list.size() - 1) {
                            p2 = list.get(k + 1);
                            sign = true;
                            break;
                        }
                    }
                }
                if (list.size() > 2 && p1 == p.listParent.size()-1) {
//                    System.out.println("ok");
                    sign = true;
                    p2 = list.get(0);
                }
                if (sign && p2 != p1) {

                    int minSize = 0;
                    if (p.getParent(p1).listGen.indexOf('0') <= p.getParent(p2).listGen.indexOf('0')) {
                        minSize = p.getParent(p1).listGen.indexOf('0') - 1;
                    } else {
                        minSize = p.getParent(p2).listGen.indexOf('0') - 1;
                    }
                    int rand1 = randomInteger(0, minSize);
                    int rand2 = randomInteger(0, minSize);
                    if (rand1 > rand2) {
                        rand1 = rand1 + rand2;
                        rand2 = rand1 - rand2;
                        rand1 = rand1 - rand2;
                    }
                    HashSet<Character> temp = new HashSet();
                    p.listAnak.add(new Kromosom());
                    for (Character c : p.getParent(p2).listGen) {
                        p.listAnak.get(p.listAnak.size() - 1).addGen(c);
                    }
                    for (Character c : p.getParent(p2).open) {
                        temp.add(c);
//                        p.listAnak.get(p.listAnak.size() - 1).addOpen(c);
                    }
                    for (Character c : p.getParent(p1).open) {
                        temp.add(c);
//                        p.listAnak.get(p.listAnak.size() - 1).addOpen(c);
                    }
//                    p.listAnak.get(p.listAnak.size() - 1).open.clear();
                    for (Character c : temp) {
                        p.listAnak.get(p.listAnak.size() - 1).open.add(c);
                    }

                    for (int j = rand1; j <= rand2; j++) {
                        boolean signn = false;
                        int k;
                        for (k = 0; k < p.getParent(p2).listGen.size(); k++) {
                            if (p.getParent(p2).getGen(k) == p.getParent(p1).getGen(j)) {
                                signn = true;
                                break;
                            }
                        }
                        if (!signn) {
                            p.getAnak(p.listAnak.size() - 1).listGen.set(j, p.getParent(p1).getGen(j));
                        }
                    } 
                }
            }
                fixKromosom(1);
                hitungfitness(1);
        }
    }

    public void mutasi() {
        for (Kromosom k : p.listParent) {
            p.listBertahan.add(new Kromosom());
            for (int i = 0; i < k.listGen.size(); i++) {
                p.getBertahan(p.listBertahan.size() - 1).addGen(k.getGen(i));
                if(i < k.open.size()) {
                    p.getBertahan(p.listBertahan.size() - 1).addOpen(k.getOpen(i));
                }
            }
        }
        if (p.listAnak.size() > 0) {
            for (Kromosom k : p.listAnak) {
                p.listBertahan.add(new Kromosom());
                for (int i = 0; i < k.listGen.size(); i++) {
                    p.getBertahan(p.listBertahan.size() - 1).addGen(k.getGen(i));
                    if(i < k.open.size()) {
                        p.getBertahan(p.listBertahan.size() - 1).addOpen(k.getOpen(i));
                    }   
                }
            }
        }
        
        for (int i = 0; i < p.listBertahan.size(); i++) {
            double randNum = randomDouble(0, 1);
            if (randNum < pMutasi) {
                int rand1 = randomInteger(0, p.listBertahan.get(i).listGen.indexOf('0') - 1);
                int rand2 = randomInteger(0, p.listBertahan.get(i).listGen.indexOf('0') - 1);
                if (rand1 != rand2) {
                    Collections.swap(p.listBertahan.get(i).listGen, rand1, rand2);
                }
            }

        }

        fixKromosom(2);
        hitungfitness(2);
        Collections.sort(p.listBertahan);
        if (p.listBertahan.size() > jKromosom) {
            for (int i = jKromosom; i < p.listBertahan.size(); i++) {
                p.listBertahan.set(i, null);
            }
            p.listBertahan.removeAll(Arrays.asList(null, 0));

        }
    }
}
