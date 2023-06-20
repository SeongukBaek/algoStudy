# [14676] 영우는 사기꾼?

## :pushpin: **Algorithm**

위상정렬, 구현

## :round_pushpin: **Logic**

- 위상정렬에서 사용되는 개념인 진입차수를 사용하여 푸는 문제이다.
- 입력을 받으면서 각 건물에 대한 진입차수를 함께 구한다.
- 건물을 짓고 파괴하면서 진입차수를 더하고 빼주면서 치트키를 썼는지 판별해주면 된다 ~

```java
private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		buildOrder = new List[N + 1];
		indegrees = new int[N + 1];
		buildings = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			buildOrder[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int before = Integer.parseInt(st.nextToken());
			int after = Integer.parseInt(st.nextToken());
			buildOrder[before].add(after);
			indegrees[after]++;
		}
	}
	
	private static String checkIsCheatKey() throws IOException {
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int buildingIdx = Integer.parseInt(st.nextToken());
			
			if(command == 1) {
				if(indegrees[buildingIdx] > 0) {
					return "Lier!";
				} else {
					buildings[buildingIdx]++;
					if(buildings[buildingIdx] == 1) {
						for(int nextBuilding : buildOrder[buildingIdx]) {
							indegrees[nextBuilding]--;
						}
					}
				}
			}
			
			if(command == 2) {
				if(buildings[buildingIdx] == 0) {
					return "Lier!";
				} else {
					buildings[buildingIdx]--;
					if(buildings[buildingIdx] == 0) {
						for(int nextBuilding : buildOrder[buildingIdx]) {
							indegrees[nextBuilding]++;
						}
					}
				}
			}
		}
		return "King-God-Emperor";
	}
}
```
- 건물을 지을 때는 해당 건물의 진입 차수가 0인지 우선 확인한다.
- 0이라면 건물의 수를 관리하는 배열에서 값을 1 더해준다.
- 건물이 처음 지어진 것이라면 해당 건물의 상위 건물들의 진입 차수를 1씩 빼준다.
- 건물을 파괴할 때는 해당 건물이 있는지 없는지를 우선 확인한다.
- 있다면 건물의 수를 줄여준다.
- 건물이 이제 하나도 남지 않았으면 해당 건물의 상위 건물들의 진입 차수를 1씩 더해준다. 

## :black_nib: **Review**

- 블로그에서 풀이를 참고하였는데 그 분 정말 존경스럽다..
