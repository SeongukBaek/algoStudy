import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {	
	private static Map<Character, Integer> alphabets = new HashMap<>();
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	String input = br.readLine();
    	int max = 0;
    	
    	int left = 0;
    	int right = 0;
    	boolean removeLeft = false;
    	
    	while (left <= right && right < input.length()) {
    		char current = input.charAt(right);
    		
    		if (!removeLeft) {
    			// 이미 후보 문자열에 해당 알파벳이 있는 경우
        		if (alphabets.containsKey(current)) {
        			alphabets.put(current, alphabets.get(current) + 1);
        			right++;
        			continue;
        		}
        		
        		// 후보 문자열에 알파벳이 없고, 아직 후보 문자열의 알파벳 종류 추가가 가능한 경우
        		if (alphabets.size() < N) {
        			alphabets.put(current, 1);
        			right++;
        			continue;
        		}
    		}
    		
    		// 후보 문자열에 알파벳을 추가할 수 없고, 왼쪽부터 문자열을 지워내야 하는 경우
    		removeLeft = true;
    		max = Math.max(max, right - left);
    		
    		char first = input.charAt(left);
    		int count = alphabets.get(first) - 1;
    		if (count == 0) {
    			alphabets.remove(first);
    			removeLeft = false;
    		} else {
    			alphabets.put(first, count);
    		}
    		left++;
    	}

    	// 마지막에 생성되는 문자열에 대해 최대 거리 갱신
		max = Math.max(max, right - left);
		
    	System.out.println(max);
    }
}