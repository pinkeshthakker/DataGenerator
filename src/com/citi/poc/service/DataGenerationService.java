package com.citi.poc.service;

public interface DataGenerationService {
	public void parseData(String filePath, String delimiter, int batchSize) throws Exception;
}
