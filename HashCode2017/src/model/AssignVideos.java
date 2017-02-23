package model;

public class AssignVideos {
	private final Environment env;
	private final int[] totalVideoRequests;
	
	public AssignVideos(Environment environment){
		env = environment;
		
		totalVideoRequests = new int[env.videos.size()];
		
		for(int i=0; i<env.videos.size(); i++){
			totalVideoRequests[env.videos.get(i).id] = getTotalRequestCountForVideoWithID(env.videos.get(i).id);
		}
	}
	
	private int getTotalRequestCountForVideoWithID(int id){
		int count = 0;
		
		for(int i = 0; i < env.endpoints.size(); i++){
			count += env.endpoints.get(i).videoRequestCount[id];
		}
		
		return count;
	}
	
	public void assignVideos(){
		
	}
}
