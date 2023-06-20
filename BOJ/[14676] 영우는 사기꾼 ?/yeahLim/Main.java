import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {

        /* 입력 및 변수 초기화 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] parentsNums = new int[n + 1]; // 부모의 개수
        List<Integer>[] relation = new List[n + 1]; // 그래프
        for (int i = 0; i <= n; i++) {
            relation[i] = new ArrayList<>();
        }
        int[] buildingCnt = new int[n + 1];
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            parentsNums[num2] += 1;
            relation[num1].add(num2);
        }

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int building = Integer.parseInt(st.nextToken());
            // 건설
            if (cmd == 1) {
                if (parentsNums[building] != 0) {
                    System.out.println("Lier!");
                    return;
                }
                buildingCnt[building]++;
                if (buildingCnt[building] == 1) {
                    for (int child : relation[building]) {
                        parentsNums[child]--;
                    }
                }
            }
            // 파괴
            if (cmd == 2) {
                if (buildingCnt[building] == 0) {
                    System.out.println("Lier!");
                    return;
                }
                buildingCnt[building]--;
                if (buildingCnt[building] == 0) {
                    for (int child : relation[building]) {
                        parentsNums[child]++;
                    }
                }
            }
        }
        System.out.println("King-God-Emperor");
    }
}
