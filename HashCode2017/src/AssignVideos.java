
public class AssignVideos {
	private final Environment env;
	private final int[] totalVideoRequests;
	
	public AssignVideos(Environment environment){
		env = environment;
		
		totalVideoRequests = new int[env.datacenter.videos.size()];
	}
	
	private int getTotalRequestCountForVideo(int videoID){
		int count = 0;
		
		for(int i = 0; i<env.endpoints.size(); i++){
			 count += env.endpoints.get(i).videoRequestCount[videoID];
		}
		
		return count;
	}
	
	public void assignVideos(){
		for(int i = 0; i < env.videos.size(); i++){
			
		}
		
	}
}
