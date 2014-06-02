package com.citi.poc.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.test.poc.entity.Product;
import com.test.poc.entity.SalesEntry;

public class TestInsertProduct {
	
	public static void main(String[] args) {
		Session session = null;
		try{
			 SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
			 session = sessionFactory.openSession();
			 Product product = new Product();
			 Set<SalesEntry> salesEntrySet = new HashSet<SalesEntry>();
			 product.setProductName("Product1");
			 for (int i = 0; i < 2; i++) {
				SalesEntry salesEntry = new SalesEntry();
				salesEntry.setDateOfSale(new Date());
				salesEntry.setProduct(product);
				salesEntrySet.add(salesEntry);
			}
			 product.setSalesEntry(salesEntrySet);
			 session.save(product);
			 
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally{
			// Actual contact insertion will happen at this step
			System.out.println("test");
			 session.flush();
			 session.close();
			}
	}

}
