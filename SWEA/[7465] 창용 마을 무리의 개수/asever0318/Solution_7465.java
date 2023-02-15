package SWEA;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
 
// [7465] 창용 마을 무리의 개수 
public class Solution_7465{
    static int N, M, p1, p2;
    static List<ArrayList<Integer>> linkmap; // 연결된 관계 표시 
    static boolean[] visited; // 방문표시 
    static int count; // 무리 수 카운트할 변수 
     
    // dfs 탐색할 함수 
    private static boolean dfs(int n) {
         
        if(!visited[n]) { // 방문하지 않았으면 넣음 
            visited[n] = true;
                 
            // 해당 번호와 연결된 번호들 돌면서 
            for(int j = 0; j < linkmap.get(n).size(); j++) {
                if(!visited[linkmap.get(n).get(j)]) {
                    dfs(linkmap.get(n).get(j)); 
                }
            }
            return true;
        }
        else {
            return false; // 이미 방문한 노드일 경우 바로 return
        }
    }
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트케이스 수 
         
        for(int i = 0; i < T; i++) {
             
            linkmap = new ArrayList<ArrayList<Integer>>();
             
            String[] str = br.readLine().split(" ");
            N = Integer.parseInt(str[0]); // 사람 수 
            M = Integer.parseInt(str[1]); // 서로 알고 있는 사람의 관계 수 
             
            visited = new boolean[N+1]; // 1부터 시작할거라 N+1
            for(int k = 0; k <= N; k++) {
                visited[k] = false; // 방문하지 않음으로 초기화 
            }
             
            // 사람 수만큼 리스트 만들어주기 (N까지!)
            for(int a = 0; a <= N; a++) {
                List link = new ArrayList<Integer>();
                linkmap.add((ArrayList<Integer>) link);
            }
             
            // 입력받은 관계마다 각자 서로의 리스트에 넣어주기 
            for(int j = 0; j < M; j++) {
                str = br.readLine().split(" ");
                p1 = Integer.parseInt(str[0]);
                p2 = Integer.parseInt(str[1]);
                 
                // 두 사람 모두에게 서로 등록  
                linkmap.get(p1).add(p2);
                linkmap.get(p2).add(p1);
            }
             
            count = 0;
             
            // 한 사람씩 돌면서 
            for(int p = 1; p <= N; p++) {
                if(dfs(p)) {
                    count++;
                }
            }
             
            System.out.println("#"+(i+1)+" "+count);
        }
    }
}