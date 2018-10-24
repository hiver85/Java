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
public class TextualTrainingValue extends TrainingValue {
    private String value;

    //Constructor
    public TextualTrainingValue(String value) {
        this.value = value;
    }

    // Getter and setter
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
  
    
}
