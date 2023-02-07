import java.io.*;
import java.util.*;

public class HamburgerDiet {
    static int n;
    static int cal;
    static int max = -1;
    static List<Ingredient> list;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            cal = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            max  =  -1;

            for(int i = 0; i < n; i++ ) {
                st = new StringTokenizer(br.readLine());

                list.add(new Ingredient(Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())));
            }

            comb(0,0,0,0);

            System.out.println("#"+t+" "+max);
        }

    }

    static void comb(int start, int sum, int score, int pre) {

        if(sum > cal) {
            max = Math.max(max, score-pre);
            return;
        }
        if(start == n) {
            max = Math.max(max, score);
        }

        for(int i = start; i < n; i++) {
            comb(i+1, sum+list.get(i).cal, score + list.get(i).score, list.get(i).score);
        }

    }

    static class Ingredient{
        int score;
        int cal;

        public Ingredient(int score, int cal) {
            this.score = score;
            this.cal = cal;
        }
    }
}