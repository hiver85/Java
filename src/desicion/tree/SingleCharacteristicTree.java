/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desicion.tree;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lenovo
 */
// A SingleCharacteristicTree is a couple of (1 profile and target profile)
// which facilitate to do simple statistic later 
public class SingleCharacteristicTree  {
    private TrainingChar profile;
    private TrainingChar target;
    //set of values 
    private Map<TrainingValue,TrainingValue> tree;

    public SingleCharacteristicTree() {
        this.profile = new TrainingChar() ;
        this.target = new TrainingChar();
        this.tree = new HashMap<>();
    }
    
    public SingleCharacteristicTree(TrainingChar profile, TrainingChar target) {
        this.profile = profile;
        this.target = target;
        this.tree = new HashMap<>();
    }

    public TrainingChar getProfile() {
        return profile;
    }

    public void setProfile(TrainingChar profile) {
        this.profile = profile;
    }

    public TrainingChar getTarget() {
        return target;
    }

    public void setTarget(TrainingChar target) {
        this.target = target;
    }

    public Map<TrainingValue, TrainingValue> getTree() {
        return tree;
    }

    public void setTree(Map<TrainingValue, TrainingValue> tree) {
        this.tree = tree;
    }

    //Display the tree (all couple values of couple profile)
    // It will support subsequent complex toString()
    public String toStringTree()
    {
        String s = "";
        for (TrainingValue key:tree.keySet())
        {
            s = s+ key.toString()+ " / " + tree.get(key) + "\n";
        }
        return s;
    }
    
    @Override
    public String toString() {
        String s = "SingleCharacteristicTree{profile=" 
                + profile.getNameChar() + ", target=" 
                + target.getNameChar() + "}\n"
                + toStringTree()
                + ' ';
        return s;
    }
 
    // pick up the corresponding target value of 1 profile value 
    public TrainingValue getTargetValue(TrainingValue profileValue)
    {
        return tree.get(profileValue);
    }
    
}
