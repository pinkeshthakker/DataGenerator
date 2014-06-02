package com.citi.poc.servieImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hibernate.Session;

import com.citi.poc.service.DataGenerationService;
import com.citi.poc.util.HibernateUtil;
import com.test.poc.entity.DataObject;
import com.test.poc.entity.User;

public class DataGenerationServiceImpl implements DataGenerationService {

	long globalRecordCount = 0;
	int batchCount = 0;
	

	public void parseData(String filePath, String delimiter, int batchDbSize) {
		try {
			long gstartTime = System.currentTimeMillis();
			System.out.println("Start Time for Overall Execution:"+gstartTime );
			ExecutorService executor = Executors.newFixedThreadPool(5);
			String currentRecord;
			List<User> dataObjects = new ArrayList<User>();
			int recordCount = 0;
			FileReader fr = new FileReader(filePath);
			// Given buffer size to Improve the Performance
			BufferedReader br = new BufferedReader(fr, 1024);
			while ((currentRecord = br.readLine()) != null) {
				User user = this	
						.parseLine(currentRecord, delimiter);
				if (null == dataObjects)
					dataObjects = new ArrayList<User>();
				dataObjects.add(user);
				if (recordCount == batchDbSize) {
					long insertBatchStartTime = System.currentTimeMillis();
					
					/* Without Executor
					 * InsertDataServiceImpl dataServiceImpl = new InsertDataServiceImpl();
					dataServiceImpl.setDataObjects(dataObjects);
					dataServiceImpl.run();*/
					
					//With Executor Framwwork
					 	InsertDataServiceImpl dataServiceImpl = new InsertDataServiceImpl();
					 	dataServiceImpl.setDataObjects(dataObjects);
					 	dataServiceImpl.setBatchNumber(batchCount);
					 	dataServiceImpl.setRecordNumber(recordCount);
					      executor.execute(dataServiceImpl);
					    // This will make the executor accept no new threads
					    // and finish all existing threads in the queue
					    
					//Without Thread
					//this.insertData(dataObjects);
					long insertBatchEndTime = System.currentTimeMillis();
					System.out.println("Total insertion time for the Batch::"
							+ (insertBatchEndTime - insertBatchStartTime));
					batchCount++;
					recordCount = 0;
					dataObjects = null;
				}
				globalRecordCount++;
				recordCount++;
			}
			executor.shutdown();
			br.close();
			long gendTime = System.currentTimeMillis();
			System.out.println("Overall Time::" + (gendTime - gstartTime));
		} catch (FileNotFoundException e) {
			System.out.println("Please Enter Valid File Path:");
			System.out.println("Error After the RecordCount:"
					+ globalRecordCount);
			System.out.println("Error After the batchCount:" + batchCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error After the RecordCount:"
					+ globalRecordCount);
			System.out.println("Error After the batchCount:" + batchCount);
			System.out.println("UnExpected Error :");
		}
	}

	private void insertData(final List<DataObject> dataObjects) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for (DataObject dataObject : dataObjects) {
			session.save(dataObject);
		}
		session.getTransaction().commit();
		session.clear();
		session.flush();
		session.close();
	}

	private User parseLine(String currentRecord, String delimiter) {
		User user = new User();
		String[] values = currentRecord.split(delimiter);
		user.setFirstName(values[0]);
		user.setLastName(values[1]);
		user.setUserName(values[2]);
		user.setPassword(values[3]);
		user.setMiddleName(values[4]);
		return user;
	}
}