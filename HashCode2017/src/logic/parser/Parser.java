package logic.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.Endpoint;
import model.Environment;
import model.Server;
import model.Video;

public class Parser {
	
	Environment environment;
	int numberOfVideos;
	int numberOfEndpoints;
	int numberOfRequests;
	String inputType;
	int readedEndpoints = 0;
	int tmpCashes = 0;
	int tmpReadCashes = 0;
	
	public Parser() {
		environment = new Environment();
	}
	
	public Environment parsFile(File inputFile) {
		
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
					int cashCapacity = Integer.parseInt(values[4]);
					for (int i = 0; i< Integer.parseInt(values[3]); i++) {
						Server tmpServer = new Server();
						tmpServer.capacity = cashCapacity;
						environment.caches.add(tmpServer);
					}
				} else if (lineCounter == 2) {
					String[] values = line.split(" ");
					for (int i = 0; i<numberOfVideos; i++) {
						Video tmpVideo = new Video();
						tmpVideo.id = i;
						tmpVideo.size = Integer.parseInt(values[0]);
					}
					inputType = "endpoint";
				} else if (lineCounter > 2) {
					if (inputType.equals("endpoint")) {
						readedEndpoints++;
						String[] values = line.split(" ");
						Endpoint tmpEnd = new Endpoint();
						tmpEnd.distance = Integer.parseInt(values[0]);
						tmpCashes = Integer.parseInt(values[1]);
						tmpEnd.distanceToCache = new int[1000];
						environment.endpoints.add(tmpEnd);
						if (tmpCashes>0) {
							inputType = "cash";
						} else if (readedEndpoints == numberOfEndpoints) {
							inputType = "request";
						} else {
							inputType = "endpoint";
						}
					} else if (inputType.equals("cash")) {
						tmpReadCashes++;
						String[] values = line.split(" ");
						int cashID = Integer.parseInt(values[0]);
						environment.endpoints.get(readedEndpoints-1).distanceToCache[cashID] = Integer.parseInt(values[1]);
						if (tmpCashes > tmpReadCashes) {
							inputType = "cash";
						} else if (readedEndpoints == numberOfEndpoints) {
							inputType = "request";
						} else {
							inputType = "endpoint";
						}
					} else if (inputType.equals("request")) {
						String[] values = line.split(" ");
						int reqVideo = Integer.parseInt(values[0]);
						int reqEnd = Integer.parseInt(values[1]);
						int reqNumber = Integer.parseInt(values[2]);
						environment.endpoints.get(reqEnd)
							.videoRequestCount[reqVideo] = reqNumber;
					}
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
