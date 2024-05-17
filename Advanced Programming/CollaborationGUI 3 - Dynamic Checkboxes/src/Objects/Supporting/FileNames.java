package Objects.Supporting;
import java.util.ArrayList;

public class FileNames {
	ArrayList<String> files = new ArrayList<String>();
	
	public FileNames() {
	
		//List out each of the files
		String comp = "companies";		
		String own = "owners";
		String proj = "projects";
		String stud = "students";
		String pref = "preferences";
		String shortl = "shortlist";
		
		files.add(comp);
		files.add(own);
		files.add(proj);
		files.add(stud);
		files.add(pref);
		files.add(shortl);
	}
	
	public ArrayList<String> getFiles() {
		return files;
	}
	

}
