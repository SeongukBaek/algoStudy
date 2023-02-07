# [11000] 강의실 배정

## :pushpin: **Algorithm**

우선순위 큐

## :round_pushpin: **Logic**

```java
	static void pro() {
		Queue<Integer> lecture = new PriorityQueue<>();
		lecture.add(endTimes.poll());
		startTimes.poll();
		int size = startTimes.size();
		for(int i = 0 ; i < size; i ++) {
			while(!startTimes.isEmpty() && !lecture.isEmpty()) {
				if(lecture.peek() <= startTimes.poll())
					lecture.poll();
			}
			lecture.add(endTimes.poll());
			if(lecture.size() > result)
				result = lecture.size();
		}
		System.out.println(result);
	}
  ```
   - 우선순위 큐 두개에 강의 시작 시간, 강의 종료 시간을 입력 받았다.
   - 총 강의 수 만큼 for문을 돌며, 강의 종료 시간을 lecture priorityqueue에 넣고, lecture 안에 다음 강의 시작 시간 보다 더 작은 값은 버려준다.
  
  
## :black_nib: **Review**
 - 우선순위 큐 메소드 활용이 다소 미숙하여 구현 시간이 오래걸렸다.
 - peek() poll() 등 여러 메소드의 queue가 비었을 때 예외처리에 대해 알게 되었다.


  
  	

  
