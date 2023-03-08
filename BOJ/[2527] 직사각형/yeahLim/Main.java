import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int x1, y1, p1, q1;
    static int x2, y2, p2, q2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            p1 = Integer.parseInt(st.nextToken());
            q1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            p2 = Integer.parseInt(st.nextToken());
            q2 = Integer.parseInt(st.nextToken());

            detectRectangleRel();
        }
    }

    static void detectRectangleRel() {

        // 공통 부분 : 없음
        if(p2 < x1 || x2 > p1 || q2 < y1 || y2 > q1) {
            System.out.println("d");
        }

        // 공통 부분 : 점
        else if((x1 == p2 && y1 == q2) || (x2 == p1 && y2 == q1) || (x2 == p1 && y1 == q2) || (x1 == p2 && y2 == q1)) {
            System.out.println("c");
        }

        // 공통 부분 : 선분
        else if(x1 == p2 || x2 == p1 || y1 == q2 || y2 == q1) {
            System.out.println("b");
        }

        // 공통 부분 : 직사각형
        else System.out.println("a");
    }
}
