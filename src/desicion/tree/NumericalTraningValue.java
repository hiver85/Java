/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desicion.tree;

/**
 *
 * @author lenovo
 */
public class NumericalTraningValue extends TrainingValue {
    private int value;

    // Constructor
    public NumericalTraningValue(int value) {
        this.value = value;
    }

    //Getter and setter
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
    
    
    
}
