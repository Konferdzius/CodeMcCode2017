package model;

public class AssignVideos {
	private final Environment env;
	private final int[] totalVideoRequests;
	private final int[] endpointTotalRequests;
	
	public AssignVideos(Environment environment){
		env = environment;
		
		totalVideoRequests = new int[env.videos.size()];
		endpointTotalRequests = new int[env.caches.size()];
		
		for(int i=0; i<env.videos.size(); i++){
			totalVideoRequests[env.videos.get(i).id] = getTotalRequestCountForVideoWithID(env.videos.get(i).id);
		}
		
		for(int i=0; i<env.endpoints.size(); i++){
			endpointTotalRequests[i] = getTotalRequestCountForCacheWithID(i);
		}
	}
	
	private int getTotalRequestCountForCacheWithID(int id){
		int count = 0;
		
		Endpoint ep = env.endpoints.get(id);
		
		for(int i = 0; i < ep.videoRequestCount.length; i++){
			count += ep.videoRequestCount[i];
		}
		
		return count;
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
