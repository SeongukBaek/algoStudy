import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String burst = br.readLine();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			sb.append(str.charAt(i));
			if (sb.length() >= burst.length()) {
				boolean isTarget = true;
				for (int j = 0; j < burst.length(); j++) {
					char c1 = burst.charAt(j);
					char c2 = sb.charAt(sb.length() - burst.length() + j);
					if(c1 != c2) {
						isTarget = false;
						break;
					}
				}
				if(isTarget) {
					sb.delete(sb.length()-burst.length(), sb.length());
				}
			}

		}

		if (sb.length() == 0) {
			sb.append("FRULA");
		}
		
		System.out.println(sb);
	}
}
