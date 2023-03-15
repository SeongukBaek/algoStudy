import java.util.*;

class Solution {
    boolean[] visited;
    boolean[] isGate, isSummit;
    int[] distance;
    List<Path>[] mountain;
    int[] answer = new int[2]; //산봉우리 번호, intensity의 최소값
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        // 출입구, 산봉우리인지 표시
        setPlaceInfo(n, gates, summits);
        // 인접리스트 생성
        mountain = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++){
            mountain[i] = new ArrayList<>();
        }
        
        // 출입구와 산봉우리는 단방향 노드로 생성
        for(int[] path : paths){
            if(isSummit[path[1]] || isGate[path[0]]){
                mountain[path[0]].add(new Path(path[1],path[2]));
            }else if(isSummit[path[0]] || isGate[path[1]]){
                mountain[path[1]].add(new Path(path[0],path[2]));
            }else{
                mountain[path[0]].add(new Path(path[1],path[2]));
                mountain[path[1]].add(new Path(path[0],path[2]));
            }
        }
        
        // 다익스트라 배열 생성
        distance = new int[n+1];
        answer[1] = Integer.MAX_VALUE;
        goSummit(gates, n);
        
        return answer;
    }
    
    /*출입구, 산봉우리 정보를 입력한다.*/
    void setPlaceInfo(int n, int[] gates, int[] summits){
         
        isGate = new boolean[n+1];
        isSummit = new boolean[n+1];
        for(int gate : gates){
            isGate[gate] = true;
        }
        for(int summit : summits){
            isSummit[summit] = true;
        }
    }
    
    /* 다익스트라를 사용해 모든 산봉우리까지의 최소 intensity를 구한다. */
    void goSummit(int[] gates, int n){
        Queue<Path> hiking = new ArrayDeque<>();
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        for(int gate : gates){
            hiking.add(new Path(gate, 0));
            distance[gate] = 0;
        }
        
        while(!hiking.isEmpty()){
            Path cur = hiking.poll();
            
            // 현재까지 구한 최단 intensity보다 현재 동선의 cost가 더 클 경우 패스
            if(distance[cur.v] < cur.cost){
                continue;
            }
            
            for(Path next : mountain[cur.v]){
                // 현재 노드까지의 최소 intensity와 다음 노드의 intensity 중 큰 값을 다음 노드 최소 intentsity로 결정함
                int maxIntensity = Math.max(distance[cur.v], next.cost);
                if(distance[next.v] > maxIntensity){
                    distance[next.v] = maxIntensity;
                    hiking.add(new Path(next.v, maxIntensity));
                }
            }
        }
        
        // 최소 intensity와 산봉우리 구하기
        for(int summit = 1; summit <= n; summit++){
            if(!isSummit[summit]){
                continue;
            }
            if(answer[1] > distance[summit]){
                answer[1] = distance[summit];
                answer[0] = summit;
            }
        }
        
        
    }
    
    class Path{
        int v; //좌표
        int cost; //비용

        public Path(int v, int cost){
            this.v = v;
            this.cost = cost;
        }
    }
    
}