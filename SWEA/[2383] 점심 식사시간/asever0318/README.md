# [2383] 점심 식사시간 

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
static int simulation() {
		queueStair[0] = new LinkedList<>(); // 1번 계단
		queueStair[1] = new LinkedList<>(); // 2번 계단
		
		int count = 0; // 계단을 내려간 사람의 수 
		int time = 1; // 걸린 시간
		
		while(true) {
			
			for(Queue<Person> q : queueStair) { // 2개의 계단에 대해서 
				int size = q.size();
				
				if(size == 0) {
					continue;
				}
				
				for(int j = 0; j < size; j++) {
					Person person = q.poll(); 
					Stair ps = stair.get(person.stair); // 해당 사람의 계단 
					
					// 계단까지 도착시간 + 계단 내려간 시간이 현재 시간 안에 수행가능하면 
					if(person.stairTime + ps.k <= time) {
						count++; // 내려보냄
						continue;
					}
					
					// 그렇지 않으면 다시 큐로 넣음 
					q.add(person);
				}
			}
			
			// 만약 모든 사람이 다 내려오고, 각 계단에 아무도 없으면 끝 
			if(count == persons.size() && queueStair[0].isEmpty() && queueStair[1].isEmpty()) {
				return time;
			}
			
			for(int i = 0; i < persons.size(); i++) {
				
				if(visited[i]) {
					continue;
				}
				
				Person person = persons.get(i);
				// 도착할 때까지 걸리는 시간이 지금 걸린 시간보다 작거나 같고, 계단에 자리가 있으면  
				if(person.arriveTime + 1 <= time && queueStair[person.stair].size() <= 2) {
					person.stairTime = time;
					visited[i] = true;
					queueStair[person.stair].add(person); // 해당 계단에 등록 
				}
			}
			time++;
		}
	}
```
- Person과 Stair 클래스를 만들어서 각각 큐에 넣어서 관리했다.
- 도착할 때까지 걸리는 시간이 현재 걸린 시간보다 작거나 같고, 계단에 자리가 있으면 해당 계단에 그 사람을 등록한다. 
- 각 계단 큐를 돌면서 해당 사람의 도착시간 + 선택한 계단을 내려가는 시간이 현재 진행된 시간 안에 수행가능하면 내려보내고 그렇지 않으면 다시 큐에 넣었다.
- 만약 모든 사람이 다 내려오고, 각각 계단 큐에 아무도 없으면 현재까지의 시간을 return 한다.


## :black_nib: **Review**
- 큐를 사용해서 구현하는 것은 파악했는데 어떤식으로 구현해야될 지 생각나지 않았다.. 여러 풀이와 스터디원들의 설명을 듣고 조금 이해할 수 있었다. 여전히 혼자서 구현하려면 못할 거 같지만 어려운 문제에 대해서 생각해볼 수 있는 시간이 되었다. 나중에 실력이 늘면 다시 풀어보고싶다. 
