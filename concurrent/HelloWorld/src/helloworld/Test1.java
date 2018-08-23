package helloworld;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Test1 {
	public static void main(String[] argv) {
		List<String> strs = new ArrayList<String>();
		strs.forEach(System.out::println);
		strs.add("tom");
		strs.add("java");
		strs.add("amy");
		strs.add("bit");
		System.out.println(strs);
		Arrays.sort(strs, String::compareToIgnoreCase);
		System.out.println(strs);
	}
}