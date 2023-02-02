import java.io.*;
import java.util.*;

public class MakePassword {
    static String[] alpa;
    static char[] vowel = {'a','e','i','o','u'};
    static int l;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        alpa = new String[c];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < c; i++) {
            alpa[i] = st.nextToken();
        }

        Arrays.sort(alpa);

        comb(0, l, 0, new char[l]);

    }

    public static void comb(int d, int r, int start, char[] output) {

        if(d == r) {
            if(validate(output))
                System.out.println(new String(output));
            return;
        }

        for(int i = start; i < c; i++) {
            output[d] = alpa[i].charAt(0);
            comb(d+1, r, i+1, output);
        }
    }

    public static boolean validate(char[] arr) {
        boolean result = false;
        int vow = 0;
        int con = 0;

        for(int i = 0; i< arr.length; i++)
            for(int j = 0; j < vowel.length; j++) {
                if(arr[i] == vowel[j])
                    vow++;
            }
        con = arr.length-vow;

        if(con > 1 && vow > 0)
            result = true;

        return result;
    }
}
