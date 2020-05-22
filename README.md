# Java
**Overview:**
This is a Java project to build a decision tree application. 
The decision tree is built from each single tree and then get the result from the voting (counting). The application loads the data (stored in MySQL), extracts features and process the prediction
- **Dataset**: Titanic
- **Data structure and design**: Classes are organized according to the object-oriented design

**Classes:**
-	TrainingValue: Abstract class 
-	NumericalTrainingValue: Implement TrainingValue which is numerical 
-	TextualTrainingValue: Implement TrainingValue which is textual 
-	TrainingChar: Represent a  characte column in the DB, including (charID, charName, typeChar)
-	TrainigRow: Represent a row in the database including rowID, Map<TrainingChar,TrainingValue>
-	TrainingDataSet: Represent a extracted Dataset from the DB, including name of the database, List<TrainingRow> extracted according to List<TrainingChar> chosen
-	SingCharacteristicTree: A tree of 2 elements (1 character – 1 target) then use it for statistic the probability of survivor of 1 character
- DAO:
  -	To connect with database (MySQL)
  -	Extract the DB:
    -	ExtractTrainingRow(int rowId, List<TrainingChar> chars)
    -	ExtractTrainingDataset() 
- TrainingDataSet:
  -	HashSet<String> distinctValues(TrainingChar c): Create a list of distinct value for each column (character)
  -	List<SingleCharacteristicTree> createSingleCharTree(): Create a list of every SingleTree for each character (column) and calculate the probability of each value to find out which one mostly influence the survivor.

