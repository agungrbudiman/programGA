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
public class Graf {
    private ArrayList<Node> S = new ArrayList();
    private ArrayList<Node> A = new ArrayList();
    private ArrayList<Node> B = new ArrayList();
    private ArrayList<Node> C = new ArrayList();
    private ArrayList<Node> D = new ArrayList();
    private ArrayList<Node> E = new ArrayList();
    private ArrayList<Node> F = new ArrayList();
    private ArrayList<Node> G = new ArrayList();
    
    public Graf() {
        S.add(new Node('A',6));S.add(new Node('B',14));S.add(new Node('C',10));
        A.add(new Node('S',6));A.add(new Node('B',6));A.add(new Node('D',24));
        B.add(new Node('S',14));B.add(new Node('A',6));B.add(new Node('C',4));B.add(new Node('E',15));
        C.add(new Node('S',10));C.add(new Node('B',4));C.add(new Node('F',18));
        D.add(new Node('A',24));D.add(new Node('E',4));D.add(new Node('G',9));
        E.add(new Node('B',15));E.add(new Node('D',4));E.add(new Node('F',4));E.add(new Node('G',9));
        F.add(new Node('C',18));F.add(new Node('E',4));F.add(new Node('G',9));
        G.add(new Node('D',9));G.add(new Node('E',9));G.add(new Node('F',9)); 
    }
    
    public boolean cekJalur(char c1, char c2) {
        ArrayList<Node> p;
        switch(c1) {
            case 'S' : p=S;break;
            case 'A' : p=A;break;
            case 'B' : p=B;break;
            case 'C' : p=C;break;
            case 'D' : p=D;break;
            case 'E' : p=E;break;
            case 'F' : p=F;break;
            case 'G' : p=G;break;
            default : p=null;
        }
        if(p!=null) {
           for (Node g : p) {
                if(g.node == c2) {
                    return true;
                }
            } 
        }
        
        return false;
    }
    
    public int getTime(char c1, char c2) {
        ArrayList<Node> p;
        switch(c1) {
            case 'S' : p=S;break;
            case 'A' : p=A;break;
            case 'B' : p=B;break;
            case 'C' : p=C;break;
            case 'D' : p=D;break;
            case 'E' : p=E;break;
            case 'F' : p=F;break;
            case 'G' : p=G;break;
            default : p=null;
        }
        if(p!=null) {
            for (Node g : p) {
                if(g.node == c2) {
                    return g.time;
                }
            }
        }
        return 0;
    }
    
}
