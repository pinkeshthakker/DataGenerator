package com.citi.poc.servieImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;

import com.citi.poc.util.HibernateUtil;
import com.test.poc.entity.User;

public class InsertDataServiceImpl implements Runnable{

	private List<User> dataObjects = new ArrayList<User>();
	private int batchNumber; 
	private long recordNumber; 
	
	public int getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	public long getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(long recordNumber) {
		this.recordNumber = recordNumber;
	}

	public List<User> getDataObjects() {
		return dataObjects;
	}

	public void setDataObjects(List<User> dataObjects) {
		this.dataObjects = dataObjects;
	}

	@Override
	public void run() {
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			for (User dataObject : this.dataObjects) {
				session.save(dataObject);
			}
			session.getTransaction().commit();
			session.clear();
			session.flush();
			session.close();
			System.out.println("Time inside Thread:"+ (System.currentTimeMillis()));
		}
		catch(DataAccessException de){
			System.out.println("Error While Executing the Record :"+ this.recordNumber);
			System.out.println("Error While Executing the Batch :"+ this.batchNumber);
			
		}
	}

}
