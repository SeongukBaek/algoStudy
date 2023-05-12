# [172927] 광물 캐기

## :pushpin: **Algorithm**

DFS

## :round_pushpin: **Logic**

```java
static void pick(int size, int cnt, int[] picks, String[] minerals){
	if(size == cnt){ // n개 다 뽑았으면
		// 다 뽑았으면 피로도 최소값 갱신
		min = Math.min(min, mining(minerals));
		return;
	}

	for(int i = 0; i < picks.length; i++){
		if(picks[i] > 0){
			picks[i]--;
			result[cnt] = i;
			pick(size, cnt+1, picks, minerals);
			picks[i]++;
		}
	}
}
```

- DFS를 통해서 개수가 0이 아닌 곡괭이들의 순서의 모든 경우의 수를 뽑았고 size개만큼 다 뽑았으면(한 경우를 다 뽑으면) mining 함수를 통해서 피로도를 구해서 min값을 갱신했다.

```java
static int mining(String[] minerals){
	int cost = 0;
	int index = 0;

	for(int i = 0; i < size; i++){ // 곡괭이 순서
		for(int j = 0; j < 5; j++){ // 광물은 최대 5개까지 캘 수 있음
			if(index == minerals.length){ // 광물 모두 캐면 멈춤
				return cost;
			}
			cost += getCost(minerals[index], result[i]);
			index++;
		}
	}
	return cost;
}
```

- 광물 캐는 함수, 누적 피로도를 계산해서 반환한다.
- 위에서 뽑은 순서 끝까지 for문 반복하면서 최대 5개의 광물을 캐고 (두 번째 for문) 모든 곡괭이를 다사용하면 cost를 반환한다, 만약 다 돌기전에 모든 광물을 캐면 cost를 반환한다(index가 캔 광물 수).

```java
static int getCost(String mineral, int pickaxe){

	if(pickaxe == 0){ // 다곡이면 1반환
		return 1;
	}
	else if(pickaxe == 1){ // 철곡이면 광물이 다이아일 경우만 5, 나머지 1반환
		if(mineral.equals("diamond")){
			return 5;
		}else{
			return 1;
		}
	}else{ // 돌곡이면 다이아 25, 철 5, 돌 1반환
		if(mineral.equals("diamond")){
			return 25;
		}
		else if(mineral.equals("iron")){
			return 5;
		}
		else{
			return 1;
		}
	}
}
```

- 피로도 계산하는 함수

## :black_nib: **Review**

- DFS 오랜만에 사용했더니 경우의 수를 뽑는 데 시간이 걸렸다 유유
- getCost에서 문자열 비교를 통해서 광물을 구분했는데, "diamond"를 "dia"와 비교하는 바람에 헛짓했다.. 문제를 자세히 읽자..
