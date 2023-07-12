import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] parent;
    static Stack<Integer> p1, p2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            parent = new int[10001];

            N = Integer.parseInt(br.readLine());

            for(int i = 0; i < N-1; i++){
                String[] temp = br.readLine().split(" ");
                parent[Integer.parseInt(temp[1])] = Integer.parseInt(temp[0]);
            }

            String[] temp2 = br.readLine().split(" ");
            int num1 = Integer.parseInt(temp2[0]);
            p1 = new Stack<>();
            int num2 = Integer.parseInt(temp2[1]);
            p2 = new Stack<>();

            // 부모를 거슬러가면서 스택에 넣기
            int index = num1;
            p1.push(index);
            while(parent[index] != 0){
                p1.push(parent[index]);
                index = parent[index];
            }

            index = num2;
            p2.push(index);
            while(parent[index] != 0){
                p2.push(parent[index]);
                index = parent[index];
            }

            System.out.println(getAncestor(num1, num2));
        }
    }

    static int getAncestor(int n1, int n2){
        int answer = 0;

        // 두 스택에서 하나씩 빼면서 같은 수가 나오면 공통 조상
        // --> 마지막에 나오는 공통 조상이 제일 가까운 공통 조상

        while(!p1.isEmpty() && !p2.isEmpty()){ // 둘 중 하나가 빌 때까지
            int num1 = p1.pop();
            int num2 = p2.pop();

            if(num1 != num2){ // 부모가 다르면 멈춤
                break;
            }

            answer = num1;
        }

        return answer;
    }
}

// 가장 가까운 공통 조상 찾기

