package week4;

import java.io.*;
import java.util.*;
 
public class Solution_3752{
    static int N;
    static int[] score;
    static Set<Integer> set; // 점수 중복을 피하기 위해 set 사용 
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int T = Integer.parseInt(br.readLine());
         
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            score = new int[N];
            set = new HashSet<>();
             
            // 점수 입력 받기 
            String[] str = br.readLine().split(" ");
            for(int i = 0; i < N; i++) {
                score[i] = Integer.parseInt(str[i]);
            }
             
            // 원본 set에서 반복문을 돌리면서 추가까지 하면 오류남 
            // tempSet을 만들어서 복사해준 후 tempSet 내용을 참조하면서 추가는 원본 set에 해준다 
            Set<Integer> tempSet = new HashSet<>();
             
            for(int i = 0; i < N; i++) {
                tempSet.addAll(set);
                 
                // Set은 순서가 없기 떄문에 순회하려면 iterator 사용해야함 
                Iterator<Integer> iter = tempSet.iterator();
                while(iter.hasNext()) {
                    set.add(iter.next() + score[i]);
                }
                set.add(score[i]);
            }
             
            int result = set.size() + 1;
            System.out.println("#" + t + " " + result);
        }
    }   
}