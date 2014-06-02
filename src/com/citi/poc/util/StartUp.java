package com.citi.poc.util;

import java.io.FileNotFoundException;

import com.citi.poc.service.DataGenerationService;
import com.citi.poc.servieImpl.DataGenerationServiceImpl;

public class StartUp {

	public static void main(String[] args) {

		if (args.length > 1) {
			String filePath = args[0];// 
			String delimiter = args[1];
			String batchSize = args[2];
			DataGenerationService dataGenerationService = new DataGenerationServiceImpl();
			try {

				dataGenerationService.parseData(filePath, delimiter, Integer
						.parseInt(batchSize));
			} catch (FileNotFoundException e) {
				System.out.println("Please Enter Valid File Path:");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("UnExpected Error :");
			}

		} else {
			System.out
					.println("Please Enter File Path and Delimeter of the File as an Argument");
		}
	}
}
