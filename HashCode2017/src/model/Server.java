package model;
import java.util.ArrayList;
import java.util.List;

public class Server {
	public int capacity = 0;
	public List<Video> videos = new ArrayList<Video>();
	
	public int getCurrentCapacity(){
		int count = 0;
		for(int i = 0; i<videos.size(); i++){
			count += videos.get(i).size;
		}
		return count;
	}
	
	public float getCapacityRatio(){
		return getCurrentCapacity()/capacity;
	}
}
