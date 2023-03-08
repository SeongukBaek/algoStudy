import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Node[][] squre = new Node[2][4];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                squre[j][0] = new Node(x1, y1); // 왼쪽 위
                squre[j][1] = new Node(x2, y1); // 왼쪽 아래
                squre[j][2] = new Node(x2, y2); // 오른쪽 아래
                squre[j][3] = new Node(x1, y2); // 오른쪽 위
            }

            // 공통 부분이 없는 경우 (첫번째 사각형의 y2보다 두번째 사각형의 y1이 크거나 첫번째 사각형의 x)
            if (squre[0][2].y < squre[1][0].y || squre[0][0].y > squre[1][2].y || squre[0][0].x > squre[1][1].x
                    || squre[0][1].x < squre[1][0].x) {
                System.out.println("d");
                continue;
            }
            // 점이 겹치는 경우
            if ((squre[0][0].x == squre[1][2].x && squre[0][0].y == squre[1][2].y)
                    || (squre[0][1].x == squre[1][3].x && squre[0][1].y == squre[1][3].y)
                    || (squre[0][2].x == squre[1][0].x && squre[0][2].y == squre[1][0].y)
                    || (squre[0][3].x == squre[1][1].x && squre[0][3].y == squre[1][1].y)) {
                System.out.println("c");
                continue;
            }

            // 모서리가 만나는 경우 (위아래가 만나는 경우 or 좌우가 만나는 경우)
            if (squre[0][0].x == squre[1][1].x || squre[0][1].x == squre[1][0].x || squre[0][0].y == squre[1][2].y
                    || squre[0][2].y == squre[1][0].y) {
                System.out.println("b");
                continue;
            }
            System.out.println("a");
        }
    }
}
