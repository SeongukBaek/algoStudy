import java.io.*;
import java.util.*;

public class Main {

    static char[] arr;
    static boolean[] visited;
    static int l, c;
    static HashSet<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new char[c];
        visited = new boolean[c];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++)
            arr[i] = st.nextToken().charAt(0);

        Arrays.sort(arr);

        dfs(0, 0);

    }

    public static void dfs(int depth, int length) {
        if(length == l) {
            StringBuilder sb = new StringBuilder(); // + 연산자보다 메모리 효율이 좋음.
            int vowel = 0;
            int consonant = 0;

            for (int i = 0 ; i < c; i++) {
                if (visited[i]) {
                    if (isVowel(arr[i])) {
                        vowel += 1;
                    } else {
                        consonant +=1;
                    }

                    sb.append(arr[i]);
                }
            }

            if (vowel >= 1 && consonant >= 2) {
                System.out.println(sb);
            }

        } else {
            for (int i = depth; i < c; i++) {
                visited[i] = true;
                dfs(i+1, length+1);
                visited[i] = false;
            }
        }
    }

    public static boolean isVowel(char c) {
        if (set.contains(c)) {
            return true;
        } else {
            return false;
        }
    }

}