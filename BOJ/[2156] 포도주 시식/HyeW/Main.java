import java.io.*;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] wine;
        int[] maxWine;

        n = Integer.parseInt(br.readLine());
        wine = new int[n];
        for (int i = 0; i < n; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        maxWine = new int[n];
        maxWine[0] = wine[0];
        for (int cur = 1; cur < n; cur++) {
            // 현재 와인을 마시지 않을때
            maxWine[cur] = maxWine[cur-1];

            // 연속으로 먹지 않을때
            int pre = (cur - 2 < 0)?0 : maxWine[cur-2];
            maxWine[cur] = Math.max(maxWine[cur], wine[cur] + pre);

            // 두잔을 연속으로 먹을때
            pre = (cur - 3 < 0)?0 : maxWine[cur-3];
            maxWine[cur] = Math.max(maxWine[cur], wine[cur]+wine[cur-1]+pre);
        }

        System.out.print(maxWine[n-1]);
    }
}
