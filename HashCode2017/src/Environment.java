import java.util.ArrayList;
import java.util.List;

public class Environment {
	Server datacenter = new Server();
	List<Server> caches = new ArrayList<Server>();
	List<Endpoint> endpoints = new ArrayList<Endpoint>();
	List<Video> videos = new ArrayList<Video>();
}
