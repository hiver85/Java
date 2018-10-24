/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desicion.tree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeArray.map;

/**
 *
 * @author lenovo
 */
public class DAO {

    private String URL = "jdbc:mysql://localhost:3306/db_perussel";
    private String userName = "root";
    private String password = "";

    private Connection connection;

    //Constructor
    public DAO() {
        // Connect to MySql 
        try {
            connection = DriverManager.getConnection(URL, userName, password);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /* 
    public boolean testNotNullValue(List<TrainingChar> chars, List<TrainingValue> values)
    {
        
    }
     */

    public TrainingRow extractTrainingRow(int rowId, List<TrainingChar> chars) {
        String sql = "SELECT * FROM tuples t, characteristics c  where t.rowId = " + rowId;
        ResultSet rs;
        Statement st;
        TrainingRow row = new TrainingRow(rowId);
        TrainingValue trainingvalue = null;
        String Text = new String("Textual");
        String num = new String("Numerical");
        //TrainingValue value;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            Map<TrainingChar, TrainingValue> detail = new HashMap<TrainingChar, TrainingValue>();

            while (rs.next()) {
                
                
                for(TrainingChar C: chars){
                        if(rs.getInt("charId")== C.getCharId()){
                            
                            // create trainig value
                            if(C.getTypeChar() .equals(Text)){
                                trainingvalue =  new TextualTrainingValue(rs.getString("value"));
                                //System.out.println("chrId = "+rs.getInt("charId"));
                            }
                            else if(C.getTypeChar().equals(num)){
                                trainingvalue =  new NumericalTraningValue(rs.getInt("value")); 
                                //System.out.println("chrId = "+rs.getInt("charId"));
                            }
                            
                            detail.put(C, trainingvalue);
                            
                        }
                }
            }
            
             row.setDetail(detail);
             String displ = "";
             for (TrainingValue value: detail.values())
             {
                 displ = displ + value.toString() + "  " ;
             }
             //System.out.println(displ);
             rs.close();
        }
         
        catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return row;
    }

    public TrainingDataSet extractTrainingDataset() {
        TrainingDataSet trainingDataSet = new TrainingDataSet("");
        List<TrainingRow> rows = new ArrayList();
        List<TrainingChar> chars = new ArrayList();
        List<Integer> charIDs = new ArrayList();
        TrainingChar character;

        String sql1;
        sql1 = "SELECT * FROM datasources dt, characteristics c"
                + " WHERE dt.dataSourceName like '%Training%' and dt.dataSourceId = c.dataSourceId"
               + " and nameChar in ('Embarked','Pclass','Sex','SibSp','Age','Survived')";
                

        ResultSet rs1;
        Statement st1;
        ResultSet rs2;
        Statement st2;

        try {
            st1 = connection.createStatement();
            rs1 = st1.executeQuery(sql1);

            // Skip the first row which is the header
            rs1.next();
            // Get dataset name
            String dataSourceName = rs1.getString("dataSourceName");
            trainingDataSet = new TrainingDataSet(dataSourceName);

            // Load chars list and verify null value 
            do {

                if ((rs1.getString("typeChar") != null)&& (rs1.getString("nameChar") != null)) {
                    character = new TrainingChar(rs1.getInt("CharId"), rs1.getString("nameChar"), rs1.getString("typeChar"));
                    charIDs.add(rs1.getInt("CharId"));
                    chars.add(character);
                }
            } while (rs1.next());
            trainingDataSet.setChars(chars);
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Build rows
        String sql2 = "SELECT distinct(rowId) FROM  tuples";
        try {
            //System.out.println(sql2);
            st2 = connection.createStatement();
            rs2 = st2.executeQuery(sql2);

            while (rs2.next()) {
                TrainingRow row = extractTrainingRow(rs2.getInt("rowId"), chars);
                if (row.getDetail().size() > 0 && testNotNullValue(row))
                {
                    rows.add(row);
                }
            }

            trainingDataSet.setRows(rows);
            rs2.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return trainingDataSet;
    }
    
      public Boolean testNotNullValue(TrainingRow R){
        Map<TrainingChar,TrainingValue> values = R.getDetail();
        
        for(HashMap.Entry entry:values.entrySet()){
            if((TrainingValue)entry.getValue() == null || (((TrainingValue)entry.getValue()).toString()).trim().equals("")){
                return false;
            }
        }
        
        return true;
        
    }
}
