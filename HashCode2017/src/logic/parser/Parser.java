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

	int readedRequests = 0;
	
	public Parser() {
		environment = new Environment();
	}
	
	public Environment parsFile(File inputFile) {
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			int lineCounter = 0;
			
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				lineCounter++;
				System.out.println("readLine " + lineCounter);
				if (lineCounter == 1) {
					//Read first line environment data
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
					//Read video sizes
					String[] values = line.split(" ");
					for (int i = 0; i<numberOfVideos; i++) {
						Video tmpVideo = new Video();
						tmpVideo.id = i;
						tmpVideo.size = Integer.parseInt(values[0]);
						environment.videos.add(tmpVideo);
					}
					inputType = "endpoint";
				} else if (lineCounter > 2) {
					if (inputType.equals("endpoint")) {
						//Read Endpoint
						readedEndpoints++;
						String[] values = line.split(" ");
						//Read Values
						int distToCenter = Integer.parseInt(values[0]);
						int cashesOfEndpoint = Integer.parseInt(values[1]);
						
						System.out.println("endpoint " + (readedEndpoints-1));
						System.out.println("Distance to center " + distToCenter);
						System.out.println("Number of cashes " + cashesOfEndpoint);
						//Create endpoint and add to list
						Endpoint tmpEnd = new Endpoint();
						tmpEnd.distance = distToCenter;
						tmpEnd.distanceToCache = new int[1000];
						environment.endpoints.add(tmpEnd);
						
						tmpCashes = cashesOfEndpoint;	
						tmpReadCashes = 0;
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
						int distToCash = Integer.parseInt(values[1]);
						System.out.println("readCash " + tmpReadCashes);
						System.out.println("of Cashes " + tmpCashes);
						
						environment.endpoints.get(readedEndpoints-1).distanceToCache[cashID] = distToCash;
						
						if (tmpCashes != tmpReadCashes) {
							inputType = "cash";
						} else if (readedEndpoints == numberOfEndpoints) {
							tmpCashes = 0;
							inputType = "request";
						} else {
							tmpCashes = 0;
							inputType = "endpoint";
						}
					} else if (inputType.equals("request")) {
						readedRequests++;
						String[] values = line.split(" ");
						int reqVideo = Integer.parseInt(values[0]);
						int reqEnd = Integer.parseInt(values[1]);
						int reqNumber = Integer.parseInt(values[2]);
						environment.endpoints.get(reqEnd)
							.videoRequestCount[reqVideo] = reqNumber;
						System.out.println("request imported: " + readedRequests);
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
		testOutput();
		return environment;
	}
	
	private void testOutput() {
		int requests = 0;
		for (Endpoint e: environment.endpoints) {
			for (int i: e.videoRequestCount) {
				if (i != 0) {
					requests++;
				}
			}
		}
		System.out.println("videos: " + environment.videos.size());
		System.out.println("endpoints: " + environment.endpoints.size());
		System.out.println("requests: " + requests);
		System.out.println("casches: " + environment.caches.size());
	}
}
