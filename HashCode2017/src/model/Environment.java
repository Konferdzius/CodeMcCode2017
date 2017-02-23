package model;
import java.util.ArrayList;
import java.util.List;

public class Environment {
	public Server datacenter = new Server();
	public List<Server> caches = new ArrayList<Server>();
	public List<Endpoint> endpoints = new ArrayList<Endpoint>();
	public List<Video> videos = new ArrayList<Video>();
}
