/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programGA;

import java.util.ArrayList;

/**
 *
 * @author agungrb
 */
public class Populasi {
    public ArrayList<Kromosom> listParent = new ArrayList();
    public ArrayList<Kromosom> listAnak = new ArrayList();
    public ArrayList<Kromosom> listBertahan = new ArrayList();
    public ArrayList<Double> probKumulatif = new ArrayList();
    public double totalFitness;
    
    public void addParent(Kromosom k) {
        listParent.add(k);
    }
    
    public Kromosom getParent(int idx) {
        return listParent.get(idx);
    }
    
    public void addAnak(Kromosom k) {
        listAnak.add(k);
    } 
    
    public Kromosom getAnak(int idx) {
        return listAnak.get(idx);
    }
    
    public void addBertahan(Kromosom k) {
        listBertahan.add(k);
    }
    
    public Kromosom getBertahan(int idx) {
        return listBertahan.get(idx);
    }

}
