/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
/**
 *
 * @author Felix
 */
public class DataPair<C, P>{
    private final C left;
    private final P right;
    
    public DataPair(C left, P right){
        this.left = left;
        this.right = right;
    }
    
        
    public C getLeft(){
        return left;
    }
    public P getRight(){
        return right;
    }
}
