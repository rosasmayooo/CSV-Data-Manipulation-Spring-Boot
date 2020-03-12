package com.codechallenge.service;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.codechallenge.model.Record;
import com.codechallenge.repository.RecordDTO;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


@Service
public class RecordService {
	
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;
	
	@Autowired
	private RecordDTO objRecordDTO;
	
	public void saveRecord() throws IOException {
		
		String csvInputFile = "src/main/resources/ms3Interview.csv";
		String csvOutputFile = "src/main/resources/ms3Interview_bad.csv";
		String logOutputFile = "src/main/resources/ms3Interview.log";
		
		String[] inputRecord;
		
		List<String[]> outputRecord = new ArrayList<String[]>();
		List<Record> records = new ArrayList<Record>();
		
		int recordsCount = 0;
        int failCount = 0;
        int successCount = 0;
        
        // input
    	CSVReader reader = new CSVReader(new FileReader(csvInputFile));
    	
    	// output csv file
		FileWriter outputFile = new FileWriter(csvOutputFile);
        CSVWriter writer = new CSVWriter(outputFile); 
        
        // output log file
        FileWriter outputLogFile = new FileWriter(logOutputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(outputLogFile);
        
        // skip header to exclude in db insert
        reader.readNext();
        
        // add header to bad csv output file
        outputRecord.add(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"});
        
        try {
                  
            while ((inputRecord = reader.readNext()) != null) {
                
            	recordsCount++;
            	
            	// check records for null or empty columns
            	boolean failedRecord = checkRecordHasNullValue(inputRecord);
            	
            	if(failedRecord == true) {
            		
					outputRecord.add(new String[] { inputRecord[0], inputRecord[1], inputRecord[2], inputRecord[3],
							inputRecord[4], inputRecord[5], inputRecord[6], inputRecord[7], inputRecord[8],
							inputRecord[9] });
					failCount++;
            		
            	} else {
            		
            		Record record = new Record();
            		
            		record.setA(inputRecord[0]);
    				record.setB(inputRecord[1]);
    				record.setC(inputRecord[2]);
    				record.setD(inputRecord[3]);
    				record.setE(inputRecord[4]);
    				record.setF(inputRecord[5]);
    				record.setG(inputRecord[6]);
    				record.setH(inputRecord[7]);
    				record.setI(inputRecord[8]);
    				record.setJ(inputRecord[9]);
    				records.add(record);
            		successCount++;
            		
            		 if (successCount % batchSize == 0 && successCount > 0) {
            			 objRecordDTO.saveAll(records);
            			 records.clear();
                     }
            		
            	}  
            }
            
//            if (records.size() > 0) {
//            	objRecordDTO.saveAll(records);
//                records.clear();
//            }
//            
            writer.writeAll(outputRecord);
            
            bufferedWriter.write(recordsCount + " of records received \n");
            bufferedWriter.write(successCount + " of records successful \n");
            bufferedWriter.write(failCount + " of records failed");
            
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        reader.close();
		outputFile.flush();
        outputLogFile.flush();
        writer.close();
        bufferedWriter.close();
         
	}

	private static boolean checkRecordHasNullValue(String[] lines) {
		
		boolean isNull = false;
		
		//check each column for null or empty value
		for(String line: lines) {
			if(line.isBlank() || line.isEmpty()) {
				isNull = true;
			}
		}
		
		return isNull;
	}
	
}
