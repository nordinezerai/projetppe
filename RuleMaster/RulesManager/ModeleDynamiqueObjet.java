package RulesManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

public class ModeleDynamiqueObjet extends AbstractTableModel 
{
	private static final long serialVersionUID = 3574047516478847778L;

	public  ArrayList<Rule> rules = new ArrayList<Rule>();
 
    private final String[] entetes = {"expediteur","subject","body","box","match","Modifier"};
   
    private final static String CSV_EXTENSION = ".csv";
    private String psw;
    private String id;
    private String object;
    private String RULES_FILE ;
    private final CellProcessor[] processors;
    private String[] headers= {"expediteur","subject","body","box","match"};
    private static CsvBeanReader beanReader;
    private static ICsvBeanWriter IbeanWriter;
 
    public ModeleDynamiqueObjet(String psw, String id, String object) throws IOException
    {     
    	 super();
    	
    	 RULES_FILE = id+psw+(String)object+".csv";
    	
    	 this.psw=psw;
    	 this.id=id;
    	 this.object=object;
    	 
    	 this.processors = new CellProcessor[]
    	 {
               new Optional(), // fromQuery
               new Optional(), // fromSubject
               new Optional(), // fromBody
               new Optional(), // destinationFolder
               new Optional(), // match              
    	 };
    	
    	 try 
    	 {  
     		File file_rules = new File(id+psw+(String)object+".csv");
     		
     		if(!file_rules.exists())
     		{
     			file_rules.createNewFile();
                FileOutputStream resultCsvFO = new FileOutputStream(file_rules);
                OutputStreamWriter resultCsvW = new OutputStreamWriter(resultCsvFO, Charset.forName("UTF-8"));
                CsvBeanWriter beanWriter = new CsvBeanWriter(resultCsvW, CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE); 
                beanWriter.writeHeader(headers);
                beanWriter.close();
     		}
    		
            FileInputStream rulesFIS = new FileInputStream(file_rules);
            InputStreamReader rulesISR = new InputStreamReader(rulesFIS, Charset.forName("UTF-8"));
            beanReader = new CsvBeanReader(rulesISR, CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
           
            this.headers = beanReader.getHeader(true);           
            
            ArrayList<Rule> result = new ArrayList<Rule>();

            Rule currRule;
            
            while ((currRule = beanReader.read(Rule.class, headers, processors)) != null) 
            {
                if(currRule.getExpediteur() == null) currRule.setExpediteur("");
                if(currRule.getSubject() == null) currRule.setSubject("");
                if(currRule.getBody() == null) currRule.setBody("");
                if(currRule.getBox() == null) currRule.setBox("");
                if(currRule.getMatch() == null) currRule.setMatch("");;
            	result.add(currRule);
            }
            
            this.rules = result ;            
         } 
    	
    	 catch (FileNotFoundException e) 
    	 {
            throw new RuntimeException("Rules file not found: " + RULES_FILE);
         } 
    	 
    	 catch (IOException e) 
    	 {
            throw new RuntimeException("Error reading rules file: " + RULES_FILE);
         }
    	 
    	 finally 
    	 {
             if (beanReader != null) 
             {
                 beanReader.close();
             }
    	 }
    }
    
    public String[] getHeaders() 
    {
		return headers;
	}

	public void setHeaders(String[] headers) 
	{
		this.headers = headers;
	}

	public void updateCsv(ArrayList<Rule> rules) throws IOException
    {
         try 
         {
        	 String resultFilename = RULES_FILE + '-' + System.currentTimeMillis();
             File resultCsv = new File(resultFilename);
             FileOutputStream resultCsvFO = new FileOutputStream(resultCsv);
             OutputStreamWriter resultCsvW = new OutputStreamWriter(resultCsvFO, Charset.forName("UTF-8"));
             CsvBeanWriter beanWriter = new CsvBeanWriter(resultCsvW, CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

             beanWriter.writeHeader(headers);

             CsvWriteSession session = new CsvWriteSession(resultCsv, beanWriter);           

             IbeanWriter = null;
        	 
        	 IbeanWriter = session.beanWriter;
        	 
             for (Rule currRule : rules) 
             {
            	 IbeanWriter.write(currRule,headers);
             }

             if (IbeanWriter != null) 
             {
            	 IbeanWriter.close();
             }

             Files.copy(session.csvFile.toPath(), new File(RULES_FILE).toPath(), StandardCopyOption.REPLACE_EXISTING);
         } 
         
         catch (FileNotFoundException e) 
         {
             throw new RuntimeException("Rules file not found: " + RULES_FILE);
         } 
         
         catch (IOException e) 
         {
             throw new RuntimeException("Error writing to rules file: " + RULES_FILE);
         }
        
    }
    
    public ArrayList<Rule> getRules() 
    {
		return rules;
	}

	public void setRules(ArrayList<Rule> rules) 
	{
		this.rules = rules;
	}
	   
    public boolean isCellEditable(int rowIndex, int columnIndex) 
    {
        return true;
    }
    
    public int getRowCount() 
    {
        return rules.size();
    }
 
    public int getColumnCount() 
    {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) 
    {
        return entetes[columnIndex];
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
    	 switch(columnIndex)
    	 {
         case 0:
             return rules.get(rowIndex).getExpediteur();
         case 1:
             return rules.get(rowIndex).getSubject();
         case 2:
             return rules.get(rowIndex).getBody();
         case 3:
             return rules.get(rowIndex).getBox();
         case 4:
             return rules.get(rowIndex).getMatch();
         case 5: 
         	return "Modifier";
         default:
            return null; //Ne devrait jamais arriver
    	 }
    }
 
    @Override
    public Class getColumnClass(int columnIndex)
    {
    	return getValueAt(0, columnIndex).getClass();
    }
    
    public void addRule(Rule rule) 
    {
        rules.add(rule);
        fireTableRowsInserted(rules.size() -1, rules.size() -1);
    }
 
    public void removeRule(int rowIndex) 
    {
        rules.remove(rowIndex);    
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    private static class CsvWriteSession 
    {
        public File csvFile;
        public CsvBeanWriter beanWriter;

        public CsvWriteSession(File csvFile, CsvBeanWriter beanWriter) 
        {
            this.csvFile = csvFile;
            this.beanWriter = beanWriter;
        }
    }
}