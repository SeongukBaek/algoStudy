# [11000] 강의실 배정

## **Algorithm**

 - 우선순위 큐


## **Logic**

``` java
Collections.sort(llist);
```
``` java
@Override
		public int compareTo(Lecture o) {
			// TODO Auto-generated method stub
			
			if(start == o.start)
				return end - o.end;
			
			return start - o.start;
		}
```
- 문제 조건인 강의실을 최소로 쓰도록 하기 위해서는 강의시간이 겹치지 않는 강의를 최대한 많이 뽑아야한다. 그래서 먼저 강의 시작시간이 빠른 순 -> 종료 시간이 빠른 순으로 정렬했다. 

``` java
public static int classroom(int N) {
		 
		queue.offer(llist.get(0).getEnd()); // 수업 종료시간을 큐에 넣어서 강의실 배정 
		
		for(int i = 1; i < N; i++) {
			
			if(llist.get(i).getStart() >= queue.peek())
				queue.poll(); // 강의실에 넣을 수 있으면 넣고 이전 종료시간은 큐에서 삭제 
							  // poll()은 우선순위가 가장 우선인 값을 꺼냄 
			
			queue.offer(llist.get(i).getEnd()); // 새 종료시간 큐에 넣기 or 새 강의실 배정 
		}
		return queue.size(); // 큐에 들어있는 요소 수가 배정된 강의실 수 
	}
```
- 우선순위 큐를 통해 강의실을 나타내며, 가장 처음 시작하는 강의는 무조건 강의실을 할당하고 우선순위큐에 종료시간을 저장한다. 가장 빨리 종료되는 강의실과 비교하여 새 종료시간을 넣거나 새로운 강의실을 배정한다.


# **Review**
- 처음에는 이중반복문을 돌면서 리스트로 푸는 방법으로 접근했는데, $O(N^2)$의 시간복잡도를 가지는 방법으로는 문제를 풀 수 없어서 다른 방법을 찾아보게 되었다. 이 문제를 풀면서 우선순위 큐라는 개념에 대해 다시 공부할 수 있었다.