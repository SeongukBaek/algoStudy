import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static int N;
	static String catStr;
	static int maxLen;

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		catStr = br.readLine();
	}

	static void findMaxLen() {
		for (int start = 0; start < catStr.length() - maxLen; start++) {
			//현재 몇 종류의 알파벳을 사용하는지 저장하는 HashSet
            Set<Character> typeNum = new HashSet<>();
			int end = start;
			while (true) {
				typeNum.add(catStr.charAt(end));
                //알파벳의 최대 갯수를 넘긴다면 탈출
				if(typeNum.size() > N) {
					end --;
					break;
				}
				end++;
                //문자열 끝까지 확인했다면 탈출
				if (end == catStr.length()) {
					end--;
					break;
				}
			}
			int len = end - start + 1;
			if (len > maxLen) {
				maxLen = len;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		findMaxLen();
		System.out.println(maxLen);
	}
}
