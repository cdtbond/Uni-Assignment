package dataStorage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Data.HashMaps;
import Menu.MainMenu;
import Menu.Menu;

public class HandleSerialization {
	private static String ctFilename = "collaborationTool.ser";
	HashMaps savedCt = null;
	
	public HandleSerialization() {
		
	}

	
	public void saveSerializedOutput(HashMaps hm) {
        try
        {    		
        	
        savedCt = hm;
		FileOutputStream fileOut = new FileOutputStream(ctFilename); 
        ObjectOutputStream out = new ObjectOutputStream(fileOut); 
          
        // Method for serialization of object 
        out.writeObject(savedCt); 
          
        out.close(); 
        fileOut.close(); 
          
        System.out.println("Collaboration Tool has been serialized"); 		
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        }		 	
	}
	
	public void loadSerializedInput(HandleFile hf, HandleSerialization hs) {
        
		try {
		FileInputStream fileIn = new FileInputStream(ctFilename); 
        ObjectInputStream in = new ObjectInputStream(fileIn); 
          
        // Method for deserialization of object 
        savedCt = (HashMaps)in.readObject(); 
          
        in.close(); 
        fileIn.close(); 
         
		} catch(IOException ex) 
        { 
			System.out.println("File can't be loaded, make sure the file was correctly saved."); 
        } catch (ClassNotFoundException e) {
        	System.out.println("Class Not found"); 
		} 
        
		System.out.println("Collaboration Tool has been deserialized ");
		MainMenu menu = new MainMenu();
		menu.mainMenu(savedCt, hf, hs);
        
	}
	
	public HashMaps loadSerializedInputToGraphics() {
        
		try {
		FileInputStream fileIn = new FileInputStream(ctFilename); 
        ObjectInputStream in = new ObjectInputStream(fileIn); 
          
        // Method for deserialization of object 
        savedCt = (HashMaps)in.readObject(); 
          
        in.close(); 
        fileIn.close(); 
         
		} catch(IOException ex) 
        { 
			System.out.println("File can't be loaded, make sure the file was correctly saved."); 
        } catch (ClassNotFoundException e) {
        	System.out.println("Class Not found"); 
		} 
        
		System.out.println("Collaboration Tool has been deserialized ");
		return savedCt;
        
	}


	
	
	
}
