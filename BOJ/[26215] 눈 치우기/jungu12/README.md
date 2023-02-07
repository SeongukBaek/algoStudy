# [26215] 눈 치우기

## :pushpin: **Algorithm**

우선순위 큐

## :round_pushpin: **Logic**

```java
static void clean() {
	if(snows.peek() > 1440) {
		time = -1;
		return;
	}
	while(!snows.isEmpty()) {
		time++;
		cleanSnow();
	}
}
	
static void cleanSnow() {
	List<Integer> tmp = new ArrayList<>();
	for(int i = 0; i < 2; i++) {
		if(snows.isEmpty())
			break;
		int snow = snows.poll() -1;
		if(snow == 0)
			continue;
		tmp.add(snow);
	}
	snows.addAll(tmp);
}
  ```
   - 우선순위 큐에 집 앞 쌓인 눈의 양을 넣고 큐가 빌 때 까지 두 집의 눈의 양을 하나씩 줄여 나간다.
   - 시작 전, peek()한 값이 1440를 넘는지, 끝내기 전 결과값이 1440를 넘는지 확인해준다.
  
  
## :black_nib: **Review**
 - 큐가 빈 후 결과값이 1440를 넘는지 확인하는 로직의 필요성을 뒤늦게 알아차렸다.
 - 한 집 앞의 눈의 양이 전체 눈의 양이 반이 넘는 경우 결과 값은 해당 집의 눈의 양, 그렇지 않다면 sum /2 + 나머지 라는 방법도 생각하였으나 이 방법으론 풀지 못하였다.

  
  	

  
