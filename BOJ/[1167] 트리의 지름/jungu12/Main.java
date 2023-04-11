import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int V, max, endVertex;
	static List<Node> graph[];
	static boolean[] visited;

	static class Node {
		int vertex;
		int length;

		Node(int vertex, int length) {
			this.vertex = vertex;
			this.length = length;
		}
	}

	public static void main(String[] args) throws IOException {
		init();

		visited[1] = true;
		findEnd(1, 0);
		visited[1] = false;

		max = 0;

		visited[endVertex] = true;
		findEnd(endVertex, 0);

		System.out.println(max);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		V = Integer.parseInt(br.readLine());
		graph = new List[V + 1];
		visited = new boolean[V + 1];
		
		for(int i = 0 ; i <= V ; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 1; i <= V; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			while (st.hasMoreTokens()) {
				int end = Integer.parseInt(st.nextToken());

				if (end == -1) {
					break;
				}
				graph[start].add(new Node(end, Integer.parseInt(st.nextToken())));
			}
		}
	}

	private static void findEnd(int vertex, int length) {
		if (length > max) {
			max = length;
			endVertex = vertex;
		}

		for (int i = 0; i < graph[vertex].size(); i++) {
			Node current = graph[vertex].get(i);
			if (visited[current.vertex]) {
				continue;
			}
			
			visited[current.vertex] = true;
			findEnd(current.vertex, length + current.length);
			visited[current.vertex] = false;
		}
	}
}