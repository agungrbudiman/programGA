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
public class Kromosom {
    public ArrayList<Character> listGen = new ArrayList();
    public ArrayList<Character> open = new ArrayList();
    public double fitness=0;
    
    public void addGen(char c) {
        listGen.add(c);
    }
    
    public void addOpen(char c) {
        open.add(c);
    }
    
    public Character getOpen(int idx) {
        return open.get(idx);
    }
    
    public Character getGen(int idx) {
        return listGen.get(idx);
    }
    
    public void rmGen(int idx) {
        listGen.remove(idx);
    }
    
    public void rmOpen(int idx) {
        open.remove(idx);
    }
    
}
