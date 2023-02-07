import java.io.*;
import java.util.*;

public class PasswordGenerator {
    static final int TC = 10;
    static final int N = 8;
    static List<Integer> pw;
    static int min = Integer.MAX_VALUE;
    static int min_idx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 0; t < TC; t++) {
            int num = Integer.parseInt(br.readLine());
            pw = new ArrayList<>();
            min = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int tmp = Integer.parseInt(st.nextToken());

                if (min > tmp) {
                    min = tmp;
                    min_idx = i;
                }

                pw.add(tmp);
            }

            //반복 제거
            int times = (pw.get(min_idx)-1) / 15;
            if (times > 0)
                for (int i = 0; i < N; i++)
                    pw.set(i, pw.get(i) - (times * 15));

            //1부터 감소
            int d = 0;
            int cur = pw.get(0);
            while (true) {
                pw.remove(0);
                cur -= ++d;

                if(d == 5)
                    d = 0;
                if (cur <= 0)
                    break;

                pw.add(cur);
                cur = pw.get(0);
            }
            pw.add(0);

            System.out.println("#" + num + " ");
            pw.forEach(p -> System.out.print(p + " "));
        }
    }
