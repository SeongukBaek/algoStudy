import java.io.*;
import java.util.*;

public class Cat {
	static int N;
	static String meow;
	static int max = 0;
	static Map<Character,Integer> chars;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		meow = br.readLine();
		chars = new HashMap<>();
		
		int start = 0;
		for(int i = 0; i < meow.length(); i++) {
			
			chars.put(meow.charAt(i), chars.getOrDefault(meow.charAt(i), 0)+1);
			
			while(chars.size() > N) {
				char start_char = meow.charAt(start);
				
				chars.put(start_char, chars.get(start_char)-1);
				if(chars.get(start_char) == 0)
					chars.remove(start_char);
				
				start++;
			}
			
			max = Math.max(max, i - start + 1);
		}
		
		System.out.println(max);
	}

}