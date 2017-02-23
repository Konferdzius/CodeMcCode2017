package SolutionSaving;

import java.util.ArrayList;

import model.Environment;
import model.Server;
import model.Video;

public class SolutionGatherer {
	private Environment env;
	
	public SolutionGatherer(Environment env) {
		this.env = env;
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
				output.concat("/n");
				
				cacheToString.add(output);
				amountOfCaches++;
			}
		}
		
		//save in solution Object
		solution.amountOfCaches = amountOfCaches;
		solution.cacheServers = cacheToString;
		
		return solution;
	}
}
