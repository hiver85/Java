/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desicion.tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class DesicionTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DAO dao; 
        List<SingleCharacteristicTree> treeList = new ArrayList();
        
        dao = new DAO();
        
        TrainingDataSet trainingDataSet;
        trainingDataSet = dao.extractTrainingDataset();
        
        treeList = trainingDataSet.createSingleCharTree();
        
        for (SingleCharacteristicTree tree: treeList)
        {
            tree.toString();
        }         
    }
    
}
