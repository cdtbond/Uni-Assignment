package Menu;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Menu.Options.EntryMenuOptions;
import Objects.Project.Company;
import dataStorage.DatabaseInterface;
import dataStorage.HandleFile;
import dataStorage.HandleSerialization;


public class Menu {
	
	private Scanner scan = new Scanner(System.in);
	ArrayList<Company> comps = new ArrayList<Company>();
	protected Integer entryInt;
	protected Double entryDouble;
	protected String entryString;

	
	static char entryChar = 'n';

	
	public static void main(String[] args) {
		Menu menu = new Menu();
		HandleFile hf = new HandleFile();
		HandleSerialization hs = new HandleSerialization();
		DatabaseInterface di = new DatabaseInterface();
		do {
			println("Make a Selection:");
			println("0. Load from txt file");
			println("1. Load from Serialization");		
			println("2. Build from SQL");
			println("3. Build from Scratch");
			println("4. Quit");			
			
			menu.entryInt = menu.intScanner();
			HardCodedEntries hc = new HardCodedEntries();
			EntryMenuOptions selection = EntryMenuOptions.values()[menu.entryInt];
			switch (selection) {
			//Choice 0: Load from txt files.
			case FromTxt:
				hf.loadTxtInput(hf,hs);
				break;
			//Choice 1: Load from Serialization	
			case FromSerial:
				hs.loadSerializedInput(hf,hs);
				break;
			//Choice 2: Build from SQL				
				case FromSQL:
				di.loadSQL(hf,hs);
				break;	
			//Choice 3: Build from Scratch				
			case FromScratch:
				hc.buildFromScratch(hf,hs);
				break;
			case Exit:
				
				break;
			default:
				println("That's not a valid selection, try again");				
				
			}
			
		} 		while (menu.entryInt != 4);
		println("Quitting");			
	}
	
	public Menu() {
		
	}
	
	// method for getting Int from keyboard, error check if error is made	
	protected int intScanner() {
		while (true) {
			try {
				return scan.nextInt();
			} catch (InputMismatchException e) {
				scan.next();
				print("That’s not an integer. Try again: ");
			}
		}
	}

	// method for getting String from keyboard, error check if error is made
	protected String stringScanner() {
		while (true) {
			try {
				return scan.nextLine();
			} catch (InputMismatchException e) {
				scan.next();
				print("That’s not a string. Try again: ");
			}
		}
	}

	// method for getting char from keyboard, error check if error is made
	protected char charScanner() {
		char c;
		while (true) {
			try {
				return c = scan.next().charAt(0);
			} catch (InputMismatchException e) {
				print("That’s not a character. Try again: ");
			}
		}
	}
	
	
	// method for getting Double from keyboard, error check if error is made
	protected Double doubleScanner() {
		while (true) {
			try {
				return scan.nextDouble();
			} catch (InputMismatchException e) {
				scan.next();
				print("That’s not a double. Try again: ");
			}
		}
	}

	protected static void println(String str) {
		System.out.println(str);
	}
	
	protected static void print(String str) {
		System.out.print(str);
	}
	
}
