import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] post, in, pre;
    static int N, idx;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        in = new int[N];
        post = new int[N];
        pre = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int i = 0;
        while(st.hasMoreTokens()) {
            in[i] = Integer.parseInt(st.nextToken());
            i++;
        }
        
        st = new StringTokenizer(br.readLine());
        i = 0;
        while(st.hasMoreTokens()) {
            post[i] = Integer.parseInt(st.nextToken());
            i++;
        }
        
        preOrder(0, N-1, 0, N-1);
        printPre(pre);
        
    }
    
    static void preOrder(int inStart, int inEnd, int postStart, int postEnd) {
    	//System.out.println(inStart+" "+inEnd+" "+postStart+" "+postEnd);
        
        if(postEnd < postStart || inEnd < inStart) {
            return;
        }
        
        pre[idx] = post[postEnd]; // post에서 루트(마지막 요소)를 pre에 저장 
        idx++;
        
        int iRoot = inStart;
        for(int i = inStart; i <= inEnd; i++) {
            if(in[i] == post[postEnd]) { // inOrder에서 루트 위치 찾으면
                iRoot = i; // 해당 위치 갱신 
                break;
            }
        }

        // 왼쪽 자식 노드 --> iRoot-inStart --> 왼쪽 트리 개수
        preOrder(inStart, iRoot-1, postStart, postStart+(iRoot - inStart)-1);
        // 오른쪽 자식 노드
        preOrder(iRoot+1, inEnd, postStart+(iRoot - inStart), postEnd-1);
    }
    
    static void printPre(int[] arr) {
        for(int i = 0; i < N; i++) { 
            System.out.print(arr[i]+" ");
        }
    }
}

// inorder : left -> root -> right ==> root를 기준으로 왼쪽 자식 트리 / 오른쪽 자식 트리
// postorder : left -> right -> root ==> 마지막 노드가 root
// preorder : root -> left -> right
