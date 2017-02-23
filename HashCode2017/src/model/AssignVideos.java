package model;

public class AssignVideos {
	private final Environment env;
	private final int[] totalVideoRequests;
	private final int[] endpointTotalRequests;
	
	private final Video[] bestRatedVideos;
	
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
		
		for(int i=0; i<env.videos.size(); i++){
			Video v = env.videos.get(i);
			v.globalRating = totalVideoRequests[v.id] / v.size;
		}
		
		bestRatedVideos = sortVideosByGlobalRating();
	}
	
	private Video[] sortVideosByGlobalRating(){
		Video[] videos = new Video[env.videos.size()];
		
		for(int i = 0; i< env.videos.size(); i++){
			videos[i] = env.videos.get(i);
		}
		
		boolean swapped = true;
	    int j = 0;
	    Video tmp;
	    while (swapped) {
	        swapped = false;
	        j++;
	        for (int i = 0; i < videos.length - j; i++) {
	            if (videos[i].globalRating < videos[i + 1].globalRating) {
	                tmp = videos[i];
	                videos[i] = videos[i + 1];
	                videos[i + 1] = tmp;
	                swapped = true;
	            }
	        }
	    }
		
		return videos;
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
		for (int i = 0; i<bestRatedVideos.length; i++){
			for(int x=0; x<env.caches.size();x++){
				Server cache = env.caches.get(x);
				if(cache.getCurrentCapacity() + bestRatedVideos[i].size <= cache.capacity){
					cache.videos.add(bestRatedVideos[i]);
				}
			}
		}
	}
}
