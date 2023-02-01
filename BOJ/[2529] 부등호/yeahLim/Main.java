import java.util.*;

public class Main {
    private static int k;
    private static boolean[] visited = new boolean[10];
    private static char[] signs;
    private static ArrayList<String> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        signs = new char[k];
        for (int i = 0; i < k; i++) {
            signs[i] = sc.next().charAt(0);
        }
        dfs("", 0);
        Collections.sort(result);
        System.out.println(result.get(result.size() - 1));
        System.out.println(result.get(0));
    }

    private static void dfs(String n, int depth) {
        if (depth == k + 1) {
            result.add(n);
            return;
        }
        for (int i = 0; i <= 9; i++) {
            if (depth == 0 || !visited[i] && check(n.charAt(n.length() - 1) - '0', i, signs[depth - 1])) {
                visited[i] = true;
                dfs(n + i, depth + 1);
                visited[i] = false;
            }
        }
    }
    private static boolean check(int a, int b, char c) {
        if (c == '<')
            return a < b;
        else
            return a > b;
    }
}