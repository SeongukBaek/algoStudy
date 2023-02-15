package SWEA;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;
 
public class Solution_1234{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        for(int i = 1; i <= 10; i++) {
            String[] str = br.readLine().split(" ");
            int N = Integer.parseInt(str[0]);
             
            Stack<Integer> stack = new Stack<>();
            int temp = str[1].charAt(0) - '0';
            stack.push(temp); // 가장 첫 번째 숫자 int로 변형해서 저장 
             
            // 하나씩 잘라서 스택에 넣고 같은 게 나오면 빼기 
            for(int j = 1; j < N; j++) {
                stack.push(str[1].charAt(j) - '0');
                 
                if(str[1].charAt(j) - '0' == temp) {
                    stack.pop();
                    stack.pop();
                    if(!stack.isEmpty()) {
                        temp = stack.peek(); // 가장 상단의 값 temp에 저장 
                    }
                    else {
                        j++;
                        temp = str[1].charAt(j) - '0';
                        stack.push(temp);
                    }
                }
                else {
                    temp = str[1].charAt(j) - '0';
                }
            }
             
            //출력 
            System.out.print("#"+i+" ");
            for(int a: stack)
                System.out.print(a);
            System.out.println();
             
            stack.clear();
        }
    }
}