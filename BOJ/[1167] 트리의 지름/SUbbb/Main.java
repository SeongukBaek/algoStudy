import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static List<List<int[]>> adjInfo;
	private static int V;
	private static int farNode;
	private static int howFar;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        adjInfo = new ArrayList<>();
        for (int v = 0; v < V; v++) {
        	adjInfo.add(new ArrayList<>());
        }
        
        int startLeaf = 0;
        for (int v = 0; v < V; v++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int node = Integer.parseInt(st.nextToken()) - 1;
        	
        	int value = 0;
        	while ((value = Integer.parseInt(st.nextToken())) != -1) {
        		int distance = Integer.parseInt(st.nextToken());
        		adjInfo.get(node).add(new int[] {value - 1, distance});
        	}
        	
        	if (adjInfo.get(v).size() == 1) {
        		startLeaf = v;
        	}
        }
        
        findFar(new boolean[V], startLeaf, 0);
        howFar = 0;
        findFar(new boolean[V], farNode, 0);
        System.out.println(howFar);
    }
    
    /**
     * 지름 계산을 위해 가장 멀리 떨어진 노드 찾기
     * */
    private static void findFar(boolean[] isVisited, int prevNode, int distance) {
    	if (adjInfo.get(prevNode).size() == 1 && isVisited[adjInfo.get(prevNode).get(0)[0]]) {
    		if (distance > howFar) {
        		farNode = prevNode;
        		howFar = distance;
        	}
    		return;
    	}
    	
    	isVisited[prevNode] = true;
    	for (int[] next : adjInfo.get(prevNode)) {
    		if (isVisited[next[0]]) {
    			continue;
    		}
    		findFar(isVisited, next[0], distance + next[1]);
    	}
    }
}