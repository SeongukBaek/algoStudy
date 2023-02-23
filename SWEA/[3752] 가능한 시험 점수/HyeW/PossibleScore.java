import java.io.*;
import java.util.*;

public class PossibleScore {
    static int[] problem;
    static int N;
    static Set<Integer> scoreSet;
    static List<Integer> scoreList;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int TC = Integer.parseInt(br.readLine());
        
        for(int t = 1; t <= TC; t++) {
            N = Integer.parseInt(br.readLine());
            problem = new int[N];
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                problem[i] = Integer.parseInt(st.nextToken());
            }
            
            scoreSet = new HashSet<>();
            scoreList = new ArrayList<>();
            scoreList.add(0);
            
            for(int i = 0; i < N; i++) {
                addNumber(problem[i]);
            }
      
            System.out.println("#"+t+" "+scoreList.size());
        }
    }
    
    static void addNumber(int num) {
    	int size = scoreList.size();
    	
    	for(int i = 0; i < size; i++) {
    		int nxt = scoreList.get(i) + num;
    		if(!scoreSet.contains(nxt)) {
    			scoreList.add(nxt);
    			scoreSet.add(nxt);
    		}
    	}
        
    }

}