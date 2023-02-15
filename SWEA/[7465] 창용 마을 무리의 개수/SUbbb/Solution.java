import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
    private static int N;
    private static List<List<Integer>> friends;
    private static boolean[] isVisited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();

        for (int test = 1; test <= T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            friends = new ArrayList<>();
            isVisited = new boolean[N];

            for (int index = 0; index < N; index++) {
                friends.add(new ArrayList<>());
            }

            for (int index = 0; index < M; index++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;

                friends.get(from).add(to);
                friends.get(to).add(from);
            }

            int count = 0;
            for (int person = 0; person < N; person++) {
                if (!isVisited[person]) {
                    connectFriends(person);
                    count++;
                }
            }

            answer.append("#").append(test).append(" ").append(count).append("\n");
        }
        System.out.println(answer);
    }

    /**
     * 해당 사람부터 친구 관계인 사람들은 모두 탐색하여 방문 처리를 수행
     * @param person 친구관계를 시작하는 사람
     * */
    private static void connectFriends(int person) {
        isVisited[person] = true;
        for (int friend : friends.get(person)) {
            if (isVisited[friend]) {
                continue;
            }
            connectFriends(friend);
        }
    }
}