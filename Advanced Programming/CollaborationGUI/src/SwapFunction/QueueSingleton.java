package SwapFunction;

import java.util.LinkedList;
import java.util.Queue;

public class QueueSingleton {
private static Queue<String> q = new LinkedList<String>();
private static QueueSingleton _instance;

	
	private QueueSingleton() {}
	public static synchronized QueueSingleton getSingleton() {
		if(_instance == null) {
			_instance = new QueueSingleton();
		}
		return _instance;
	}
	
	public int queueSize() {
		return q.size();
	}
	public static synchronized void addToQueue(String st) {
		q.add(st);
	}
	public String dequeue() {
		return q.poll();
	}
	public void clear() {
		q.clear();
	}

	
}
