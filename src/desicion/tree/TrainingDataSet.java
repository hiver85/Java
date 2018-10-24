/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desicion.tree;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author lenovo
 */
public class TrainingDataSet {
    
    private String name;
    private List<TrainingRow> rows;
    private List<TrainingChar> chars;

    public TrainingDataSet(String name)
    {
        this.name = name;
        this.rows = new ArrayList();
        this.chars = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TrainingRow> getRows() {
        return rows;
    }

    public void setRows(List<TrainingRow> rows) {
        this.rows = rows;
    }

    public List<TrainingChar> getChars() {
        return chars;
    }

    public void setChars(List<TrainingChar> chars) {
        this.chars = chars;
    }

    // name of dataset + all rows 
    @Override
    public String toString() {
        String result = "TrainingDataSet{" + "name=" + name + ")";
        for (int i = 0 ; i < rows.size(); i++)
        {
            result = result + "\n" + rows.get(i).toString();
        }
        return result; 
    }
    
    //HashSet don't allow duplicate value
    //Input: A TrainingChar - output: Loop all row and get all values of this TrainingChar
    //==> distinctValues : Corresponding to a column in normal table 
    public HashSet<String> distinctValues(TrainingChar c){
        HashSet<String> distinctVal = new HashSet();
        TrainingValue profile;
        Map<TrainingChar,TrainingValue> values;
        for(TrainingRow R: rows){
            values = R.getDetail();
            profile = values.get(c);
            distinctVal.add(profile.toString());

        }
       
        return distinctVal;
    }
 
    // Extract a couple of (Characters column + target) and count the number of survival + death corresponding     
    public List<SingleCharacteristicTree> createSingleCharTree()
    {
        SingleCharacteristicTree tree;
        Map <String, Integer> map = new HashMap(); 
        Map<String,Map <String, Integer>> mainMap = new HashMap();
        List<SingleCharacteristicTree> treeList = new ArrayList();    
         Map<TrainingChar,TrainingValue> rowValues = new HashMap();

        HashSet<String> distinctVal;
        
        TrainingChar target = new TrainingChar();
        // count the frequencies of survived/death for each character
        int count = 0; 

        // get target character
        for (TrainingChar character: chars)
        {
            if ("Survived".equals(character.getNameChar()))
            {
                target = character;    
                
            }
        }
        
        //build  trees
        //Extract a couple of (Characters column + target) and count the number of survival + death corresponding 
        //With each value in the tree, build a map of 0,1 and count the frequency of survived, death  and store it in mainMap              
        for (TrainingChar character: chars)
        {
            if (!"Survived".equals(character.getNameChar())) // for all characteristics except survived which is the target
            {
                mainMap = new HashMap();
                tree = new SingleCharacteristicTree();
                tree.setTarget(target);
                tree.setProfile(character);
                //pick up the list of value of this character in the dataset 
                distinctVal = distinctValues(character);
                
                //System.out.println(character.getNameChar());
                // For each value, build an empty tree with (character, target and 0,1) for counting later 
                for(String s :distinctVal){
                    map = new HashMap();
                    map.put("0", 0);
                    map.put("1",0);
                    mainMap.put(s,map);

                }
                
                //count number of 0,1 for each value 
                for (TrainingRow row:rows)
                {
                    // Get value profile and target
                    TrainingValue t1;
                    TrainingValue t2;
                    t1 = row.getDetail().get(character);
                    t2 = row.getDetail().get(target);
   
                    Map<String,Integer> map2 = mainMap.get(t1.toString());
                     
                    count = map2.get(t2.toString()) +1;
                    //System.out.println(t1.toString()+" " +t2.toString() + " " + count);

                    map2.put(t2.toString(), count);
                }
                
                for(String s:distinctVal){
                     Map<String,Integer> temp = mainMap.get(s);
                     TrainingValue T = null;
                     if(character.getTypeChar().equals("Textual")){
                         T = new TextualTrainingValue(s);
                     }
                     else{
                         T = new NumericalTraningValue(Integer.parseInt(s));
                     }
                     
                     
                     if(temp.get("0")>temp.get("1")){
                        tree.getTree().put(T, new NumericalTraningValue(0));

                     }
                     else{
                        tree.getTree().put(T, new NumericalTraningValue(1));
                     }
                     
                     
                 }
       
                System.out.println(tree.toString());
                treeList.add(tree);
            }
        }
        return treeList;
    }
        
}
