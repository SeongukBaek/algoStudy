# [118669] 등산코스 정하기

## :pushpin: **Algorithm**

다익스트라

## :round_pushpin: **Logic**

```java
for(int i = 0; i < paths.length; i++) {
        	int n1 = paths[i][0];
        	int n2 = paths[i][1];
        	int w = paths[i][2];


        	// 만약 출입구라면 출입구 --> 다른 정점으로 가는 단방향 입력
        	// 만약 산봉우리라면 다른 정점 --> 산봉우리로 가는 단방향 입력
        	if(isGate(n1, gates) || isSummit(n2, summits)) {
        		// 만약 n1이 출입문이거나 또는 n2가 산봉우리라면 : n1 --> n2 방향으로 연결
        		graph.get(n1).add(new Node(n2, w));
        	}else if(isGate(n2, gates) || isSummit(n1, summits)) {
        		// 만약 n2 가 출입문이거나 또는 n1이 산봉우리라면 : n2 --> n1 방향으로 연결
        		graph.get(n2).add(new Node(n1, w));
        	}else {
        		// 출입문 또는 산봉우리가 없으면 양방향 연결
        		graph.get(n1).add(new Node(n2, w));
        		graph.get(n2).add(new Node(n1, w));
        	}
        }
```

- intensity 최소값을 찾으면 되기때문에 출입구에서 산봉우리까지 올라가는 경로로 최소값을 찾으면 내려오는 길은 그대로 내려오면 된다. 따라서 만약 출입구라면 출입구 --> 다른 정점으로 가는 단방향, 만약 산봉우리라면 다른 정점 --> 산봉우리로 가는 단방향으로 그래프 관계를 입력해주었다.

```java
static int[] findPath(int n, int[] gates, int[] summits) {
		int num = Integer.MAX_VALUE;
		int min = Integer.MAX_VALUE;

		int[] intensity = new int[n+1]; // 1 ~ n번까지 번호가 있으니까
		for(int i = 0; i <= n; i++) {
			intensity[i] = Integer.MAX_VALUE; // 최소값 찾을거니까
		}

		// 출입구에서 산봉우리까지 intensity 구하기
		// 한 번에 모든 출입구 다 탐색하기 위해
		Queue<Node> searchNode = new LinkedList<>();
		for(int i : gates) {
			searchNode.add(new Node(i, 0)); // 출입구는 거리가 0
			intensity[i] = 0;
		}

		while(!searchNode.isEmpty()) {
			Node current = searchNode.poll();

			// 저장된 최소값보다 더 큰 값이면 확인할 필요 x
			if(current.w > intensity[current.e]) {
				continue;
			}

			// 해당 노드에서 갈 수 있는 모든 노드 탐색
			for(int i = 0; i < graph.get(current.e).size(); i++) {
				Node node = graph.get(current.e).get(i);

				// 이전 정점까지의 최소값과 이전 정점부터 이번 노드까지의 시간을 비교해서 더 큰 수 찾기
				int max = intensity[current.e];
				if(intensity[current.e] < node.w) {
					max = node.w;
				}

				// 원래 저장된 최소값과 비교하여 최소값 갱신
				if(intensity[node.e] > max) {
					intensity[node.e] = max;
					// 다음 탐색을 위해 최소값을 가진 노드를 큐에 넣어주기
					searchNode.add(new Node(node.e, max));
				}
			}
		}

		// 산봉우리의 intensity 최소값 찾아주기
		for(int i = 0; i < summits.length; i++) {
			if(intensity[summits[i]] < min) {
				min = intensity[summits[i]];
				num = summits[i];
			}
		}

		return new int[] {num, min};
	}
```

- 모든 출입구를 다 탐색하기 위해서 출입구를 큐에 넣고, 다음 탐색할 곳을 큐에 추가해주는 방식으로 탐색했다.
- 해당 노드에서 갈 수 있는 모든 노드를 탐색하는데 이전 정점까지의 최소값(intensity[current])과 이전 정점부터 이번 노드까지의 시간(node.w)을 비교해서 더 큰 수를 찾고, 원래 저장된 최소값(intensity[node.e])와 비교하여 최소값을 갱신하였다.
- 마지막으로 출입구인 경우 intensity 배열에서 최소값을 찾았다.

## :black_nib: **Review**

- 어려워어려워어려워어려워어려웠다
