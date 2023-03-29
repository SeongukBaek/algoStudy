import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n; // 노드의 개수
    static int[] tree;
    static boolean[] isTree;

    public static void main(String[] args) throws IOException {

        /* 입력 및 변수 선언 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int e; // 간선의 개수
        String[] treeInfor;
        int nodeA, nodeB;
        int tc = 0; // 테스트 케이스의 개수

        while(true) {
            tc++;
            treeInfor = br.readLine().split(" ");
            n = Integer.parseInt(treeInfor[0]);
            e = Integer.parseInt(treeInfor[1]);
            makeSet();

            if(n == 0 && e == 0) return;

            for(int i = 0; i < e; i++) {
                treeInfor = br.readLine().split(" ");
                nodeA = Integer.parseInt(treeInfor[0]);
                nodeB = Integer.parseInt(treeInfor[1]);
                union(nodeA, nodeB);
            }

            /* 출력 */
            printTreeCount(tc);
        }
    }

    /* make tree */
    static void makeSet() {
        tree = new int[n+1];
        isTree = new boolean[n+1];
        for(int i=1; i<=n; i++) {
            tree[i] = i;
            isTree[i] = true;
        }
    }

    /* union */
    static void union(int nodeA, int nodeB) {
        // nodeA < nodeB로 만들기
        if(nodeA > nodeB) {
            int tmp = nodeA;
            nodeA = nodeB;
            nodeB = tmp;
        }
        int rootA = findRoot(nodeA);
        int rootB = findRoot(nodeB);
        // 같은 집합이거나, 두 노드 중 하나가 트리가 아닐 경우
        if(rootA == rootB || !isTree[rootA] || !isTree[rootB]) {
            isTree[rootA] = isTree[rootB] = false;
        }
        tree[rootB] = rootA;
    }

    /* find root */
    static int findRoot(int node) {
        if(node == tree[node]) return node;
        return tree[node] = findRoot(tree[node]);
    }

    /* 출력 */
    static void printTreeCount(int tc) {
        int answer = 0;
        for(int i=1; i<=n; i++) {
            int root = findRoot(i);
            // 루트노드의 개수 세기 (루트 노드의 개수 == 트리의 개수)
            if(isTree[root]) {
                isTree[root] = false;
                answer++;
            }
        }

        System.out.printf("Case %d: ", tc);
        if(answer == 0) System.out.println("No trees.");
        else if(answer == 1) System.out.println("There is one tree.");
        else System.out.printf("A forest of %d trees.\n", answer);
    }
}
