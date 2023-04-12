import java.io.*;
import java.util.*;

public class Main {
  static int n;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    n = Integer.parseInt(br.readLine());
    int max = 0;
    int[] prevLine = new int[n + 1]; // 이전 줄의 누적값을 저장하기 위해 사용
    int[] curLine; // 현재 줄의 값들을 저장

    prevLine[1] = Integer.parseInt(br.readLine());

    for (int i = 2; i <= n; i++) {
      int[] temp = new int[n + 1];
      curLine = new int[n + 1];
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= i; j++) {
        curLine[j] = Integer.parseInt(st.nextToken());
      }

      // 아래층으로 합 누적
      for (int j = 1; j <= i; j++) {
        temp[j] = curLine[j] + Math.max(prevLine[j - 1], prevLine[j]);
      }
      prevLine = temp;
    }

    for (int t : prevLine) {
      if (t > max) {
        max = t;
      }
    }
    System.out.println(max);
  }
}