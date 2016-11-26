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
public class Node {
    public int time;
    public char node;

    public Node(char node, int time) {
        this.time = time;
        this.node = node;
    }
    
    public Node(char node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return Character.toString(node);
    }
    
    
}

