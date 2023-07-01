import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
    private static char[][] map;
    private static int N;
    private static int M;
    private static final int[][] moves = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    private static final Map<String, Integer> wordCount = new HashMap<>();
    private static String[] keys;

    static class Word {
        int x;
        int y;
        String result;

        Word(int x, int y, String result) {
            this.x = x;
            this.y = y;
            this.result = result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");

        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        map = new char[N][M];
        int K = Integer.parseInt(info[2]);

        for (int x = 0; x < N; x++) {
            String line = br.readLine();
            for (int y = 0; y < M; y++) {
                map[x][y] = line.charAt(y);
            }
        }

        keys = new String[K];
        for (int index = 0; index < K; index++) {
            String line = br.readLine();
            keys[index] = line;
            wordCount.put(line, 0);
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                makeString(x, y);
            }
        }

        System.out.println(print());
    }

    private static void makeString(int x, int y) {
        Queue<Word> words = new LinkedList<>();
        words.add(new Word(x, y, String.valueOf(map[x][y])));

        while(!words.isEmpty()) {
            Word cur = words.poll();

            wordCount.put(cur.result, wordCount.getOrDefault(cur.result, 0) + 1);

            for (int[] move : moves) {
                int nx = (cur.x + move[0]) % N;
                int ny = (cur.y + move[1]) % M;

                if (nx < 0) {
                    nx += N;
                }
                if (ny < 0) {
                    ny += M;
                }

                // 이미 최대 길이라면 더 이상 진행 X
                if (cur.result.length() == 5) {
                    continue;
                }

                words.add(new Word(nx, ny, cur.result + map[nx][ny]));
            }
        }
    }

    private static String print() {
        StringBuilder answer = new StringBuilder();
        for (String s : keys) {
            answer.append(wordCount.get(s)).append("\n");
        }
        return answer.toString();
    }
}