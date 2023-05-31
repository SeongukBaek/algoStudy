import java.io.*;
import java.util.*;

class Main {

    static int n, m;
    static char[][] map;
    static int[][] visited;
    static class Current {
        int x, y;
        char dir;
        public Current(int x, int y) {
            this.x = x;
            this.y = y;
            this.dir = map[x][y];
        }
    }
    static int[] root;

    public static void main(String[] args) throws IOException {

        /* 입력 및 변수 초기화 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        n = Integer.parseInt(info[0]);
        m = Integer.parseInt(info[1]);
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String infor = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = infor.charAt(j);
            }
        }
        visited = new int[n][m];
        root = new int[n*m];

        /* make-set */
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 현재좌표, 다음좌표 구하기
                char current = map[i][j];
                int ni = i;
                int nj = j;
                if (current == 'U') {
                    ni -= 1;
                }
                else if (current == 'D') {
                    ni += 1;
                }
                else if (current == 'L') {
                    nj -= 1;
                }
                else {
                    nj += 1;
                }
                // 좌표 1차원화
                int curIdx = i*m + j;
                int nxtIdx = ni*m + nj;
                // 유니온 파인드
                union(curIdx, nxtIdx);
            }
        }


        /* 출력 */
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < root.length; i++) {
            set.add(getParent(root[i]));
        }
        System.out.println(set.size());
    }

    /* find-paraent */
    public static int getParent(int child) {
        if (root[child] == child) return child;
        return root[child] = getParent(root[child]);
    }

    /* union */
    public static void union(int node1, int node2) {
        int parent1 = getParent(node1);
        int parent2 = getParent(node2);

        if (parent1 != parent2) {
            root[parent2] = parent1;
        }

    }
}