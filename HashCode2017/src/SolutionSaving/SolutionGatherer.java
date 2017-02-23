package SolutionSaving;

import java.util.ArrayList;

import model.Environment;
import model.Server;

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
				
				//save cache Number!
				output = output.concat(String.valueOf(i));
				output = output.concat(" ");
				
				//save every video in cache
				for(Video video: cache.videos){
					
				}
				
				cacheToString.add(output);
				amountOfCaches++;
			}
		}
		
		return solution;
	}
}
