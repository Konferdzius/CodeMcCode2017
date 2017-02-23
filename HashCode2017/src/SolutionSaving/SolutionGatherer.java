package SolutionSaving;

import java.util.ArrayList;

import model.Environment;
import model.Server;
import model.Video;

public class SolutionGatherer {
	private Environment env;
	private String srcFile;
	
	public SolutionGatherer() {
		env = new Environment();
		ArrayList<Server> servers = new ArrayList<Server>();
		Server one =  new Server();
		ArrayList<Video> videos = new ArrayList<Video>();
		Video v0 = new Video(); v0.id = 0;
		Video v1 = new Video();	v1.id = 1;
		Video v2 = new Video();	v2.id = 2;
		Video v3 = new Video();	v3.id = 3;
		videos.add(v0);
		videos.add(v1);
		one.videos = videos;
		
		videos = new ArrayList<Video>();
		videos.add(v2);
		videos.add(v3);
		
		Server two =  new Server();
		
		two.videos = videos;
		
		servers.add(one);
		servers.add(two);
		
		env.caches = servers;
	}
	
	public SolutionGatherer(Environment env, String filename) {
		this.env = env;
		this.srcFile = filename;
	}
	
	public Solution gatherSolution() {
		Solution solution = new Solution();
		ArrayList<String> cacheToString = new  ArrayList<String>();
		int amountOfCaches = 0;
		
		for(int i = 0; i < env.caches.size(); i++){
			Server cache = env.caches.get(i);
			
			if(!cache.videos.isEmpty()) {
				String output = "";
				
				//save cache ID!
				output = output.concat(String.valueOf(i));
				output = output.concat(" ");
				
				//save every video-id 
				for(Video video: cache.videos){
					output = output.concat(String.valueOf(video.id)).concat(" ");
				}
				//delete last unused space and make new line
				output.substring(0, output.length()-1);
				output.concat("\n");
				
				cacheToString.add(output);
				amountOfCaches++;
			}
		}
		
		//save in solution Object
		solution.filename = srcFile;
		solution.amountOfCaches = amountOfCaches;
		solution.cacheServers = cacheToString;
		
		return solution;
	}
}
