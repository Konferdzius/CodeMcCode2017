package logic.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.Environment;

public class Parser {
	
	Environment environment;
	int numberOfVideos;
	int numberOfEndpoints;
	int numberOfRequests;
	
	public Parser() {
		environment = new Environment();
	}
	
	public Environment ParsFile(File inputFile) {
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			int lineCounter = 0;
			
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				lineCounter++;
				if (lineCounter == 1) {
					String[] values = line.split(" ");
					numberOfVideos = Integer.parseInt(values[0]);
					numberOfEndpoints = Integer.parseInt(values[1]);
					numberOfRequests = Integer.parseInt(values[2]);
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return environment;
	}
}
