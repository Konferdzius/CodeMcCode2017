import java.io.File;

import SolutionSaving.SolutionGatherer;
import logic.parser.Parser;
import model.AssignVideos;
import model.Environment;

public class Main {

	public static void main(String[] args) {
		optimize("./src/kittens.in");
		//optimize("./src/me_at_the_zoo.in");
<<<<<<< HEAD
		//optimize("trending_today.in");
		//optimize("video_worth_spreading.in");
=======
		//optimize("./src/trending_today.in");
		//optimize("./src/video_worth_spreading.in");
>>>>>>> origin/master
	}
	
	private static void optimize(String filename) {
		File file = new File(filename);
		
		Parser parser = new Parser();
		
		Environment env = parser.parsFile(file);
		new AssignVideos(env);		
		
		SolutionGatherer gatherer = new SolutionGatherer(env, filename);		
		gatherer.gatherSolution();
	}
}
