package SolutionSaving;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {
	public void writeToFile(Solution solution)
	{
		try{
		    PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
		    
		    if(writer.checkError()) System.out.println("Writer couldn't be created correctly");
		    writer.println(solution.amountOfCaches);
		    writer.println("/n");
		    
		    for(String CacheServer: solution.cacheServers) {
		    	writer.println(CacheServer);
		    }

		    
		    writer.close();
		} catch (IOException e) {
		   System.out.println("File could not be writen... DuhDUhDUhDUh! Fix it");
		}
	}
}