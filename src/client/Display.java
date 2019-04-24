package client;

import java.util.List;

public class Display<T> {

	public void displayFull(List<T> e) {
		for (int i = 0; i < e.size(); i++)
			System.out.println(e.get(i).toString());
	}
	
	public void displayElement(T obj) {
		System.out.println(obj.toString());
	}
	
	public void printMsg(String msg) {
		System.out.println(msg);
	}
}
