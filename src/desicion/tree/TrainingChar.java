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
public class TrainingChar {
    private int CharId; 
    private String nameChar;
    private String typeChar;

    public TrainingChar()
    {
        this.CharId = 0;
        this.nameChar = new String();
        this.typeChar = new String();
    }
        
    public TrainingChar(int idChar, String nameChar, String typeChar) 
    {
        this.CharId = idChar;
        this.nameChar = nameChar;
        this.typeChar = typeChar;
    }
    
    public int getCharId() {
        return CharId;
    }

    public void setCharId(int CharId) {
        this.CharId = CharId;
    }

    public String getNameChar() {
        return nameChar;
    }

    public void setNameChar(String nameChar) {
        this.nameChar = nameChar;
    }

    public String getTypeChar() {
        return typeChar;
    }

    public void setTypeChar(String typeChar) {
        this.typeChar = typeChar;
    }

    @Override
    public String toString() {
        return "TrainingChar{" + "CharId=" + CharId + ", nameChar=" + nameChar + ", typeChar=" + typeChar + '}';
    }
    
    

}
