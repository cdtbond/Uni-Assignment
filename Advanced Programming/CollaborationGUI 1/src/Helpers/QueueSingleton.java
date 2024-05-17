package Helpers;

import java.util.Queue;

public class QueueSingleton {
private Queue<String> q;	
	
private static QueueSingleton _instance;
	

	
	private QueueSingleton() {}
	public static synchronized QueueSingleton getSingleton() {
		if(_instance == null) {
			_instance = new QueueSingleton();
		}
		return _instance;
	}
	
	public void addToQueue(String st) {
		q.add(st);
	}
	public String dequeue() {
		return q.poll();
	}
	public void clear() {
		q.clear();
	}
	
}
