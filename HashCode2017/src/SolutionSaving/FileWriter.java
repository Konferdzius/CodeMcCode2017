package SolutionSaving;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {
	public void writeToFile(Solution solution)
	{
		String solutionFilename = modififyName(solution.filename);
		
		try{
		    PrintWriter writer = new PrintWriter(solutionFilename, "ASCII");
		    
		    if(writer.checkError()) System.out.println("Writer couldn't be created correctly");
		    writer.println(String.valueOf(solution.amountOfCaches));
		    writer.println("\n");
		    
		    for(String CacheServer: solution.cacheServers) {
		    	writer.println(CacheServer);
		    }
		    
		    writer.close();
		} catch (IOException e) {
		   System.out.println("File could not be writen... DuhDUhDUhDUh! Fix it");
		}
	}

	private String modififyName(String filename) {
		
		String modified = filename.substring(0, filename.length()-3);
		
		return modified = modified.concat("_solution.in");
	}
}