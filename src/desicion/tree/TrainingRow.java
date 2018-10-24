/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desicion.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class TrainingRow {
    
    private int rowID;
    private Map<TrainingChar,TrainingValue> detail;
    // 1row = Char 1+ Val1 ; Char2+Val2...Charn +Valn 

    public TrainingRow(int rowID) {
        this.rowID = rowID;
        this.detail = new HashMap<>();
    }

    public int getRowID() {
        return rowID;
    }

    public void setRowID(int rowID) {
        this.rowID = rowID;
    }

    public Map<TrainingChar, TrainingValue> getDetail() {
        return detail;
    }

    public void setDetail(Map<TrainingChar, TrainingValue> detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        System.out.println("\n" + "TrainingRow" + rowID + ":" + detail.values() );
        return "TrainingRow{" + "rowID=" + rowID + ", charLists=" + detail.values() + '}';
    }

   
    
}
