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
    public ArrayList<Kromosom> listKromosom = new ArrayList();
    public ArrayList<Kromosom> listAnak = new ArrayList();
    
    public void addKromosom(Kromosom k) {
        listKromosom.add(k);
    }
    
    public Kromosom getKromosom(int idx) {
        return listKromosom.get(idx);
    }
    
    public void addAnak(Kromosom k) {
        listAnak.add(k);
    } 
    
    public Kromosom getAnak(int idx) {
        return listAnak.get(idx);
    }
}
