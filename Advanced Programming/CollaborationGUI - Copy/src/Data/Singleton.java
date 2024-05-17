package Data;

public class Singleton {
	private static Singleton _instance;
	
	private Singleton() {}
	
	public static synchronized Singleton getSingleton() {
		if(_instance == null) {
			_instance = new Singleton();
		}
		return _instance;
	}
}
