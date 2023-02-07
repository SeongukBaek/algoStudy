
import java.util.*;
import java.io.*;

public class Main {
	static int[] pw;
	static int T = 1;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static void input() throws NumberFormatException, IOException {
		String tc = br.readLine();

		StringTokenizer st = null;
        pw = new int[8];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 8; i++) {
			pw[i] = Integer.parseInt(st.nextToken());
		}
	}

	static void pro() {
		int now;
		for(int i = 0 ; ; i++) {
			if(pw[(i+7)%8] == 0) {
				now = i;
				break;
			}
			pw[i % 8] -= (i%5+1);
			if(pw[i % 8] < 0)
				pw[i % 8] = 0;
		}
		System.out.print("#" + (T++)+ " ");
		for(int i = 0 ; i < 8 ; i++) {
			System.out.print(pw[(i+now)%8] + " ");
		}System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		for(int i = 0 ; i < 10 ; i++) {
			input();
			pro();
		}
	}
}