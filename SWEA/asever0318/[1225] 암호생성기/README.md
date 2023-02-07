# [1225] 암호 생성기

## **Algorithm**

 - Queue


## **Logic**

``` java
        for(int i = 1; i<= 10; i++) {
			int N = Integer.parseInt(br.readLine());
			String[] str = br.readLine().split(" ");
			 
			// 큐에 집어넣기 
			for(int j = 0; j<8; j++) {
				queue.offer(Integer.parseInt(str[j])); // 실패시 false 반환 
			}
			
			int count = 1;
			int num =  queue.poll() - count;
			
			while(num > 0) {
				queue.offer(num);
				count++;
				
				if(count > 5)
					count = 1;
				
				num = queue.poll() - count;
			}
			
			queue.offer(0); // 마지막 요소 0 삽입 
			
			printQueue(N);
				
			queue.clear(); //queue 비우기 
		}

```
- 큐 자료구조를 통해서 앞에 있는 요소를 꺼내서 count만큼 뺀 후 리스트 끝에 다시 삽입하는 방식으로 문제를 해결했다.
- 1~5까지 증가하면서 값을 빼주기 위해 count 변수를 두고 증가시킨 후 가져온 요소에서 빼주었다.
- 0이거나 0보다 작은 값이 나오면 반복문 종료 후 0을 삽입해주었다.


# **Review**
- 이 문제를 풀기 바로 직전에 우선순위 큐에 관한 공부를 하고와서 비교적 쉽게 큐라는 자료구조를 생각해낼 수 있었다. 처음에 문제 이해를 잘못해서 한 사이클이 전체 8개의 숫자를 모두 순회하는 것으로 코드를 짰다가 문제를 찾는데 시간을 많이 보냈던 것 같다. 문제 조건을 더 주의깊게 확인하는 연습을 해야겠다. 