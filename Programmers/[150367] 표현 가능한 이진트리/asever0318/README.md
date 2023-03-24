# [150367] 표현 가능한 이진트리

## :pushpin: **Algorithm**

트리

## :round_pushpin: **Logic**

```java
public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i = 0; i < answer.length; i++) {
        	String num = Long.toBinaryString(numbers[i]); // 숫자를 이진수로 바꿔주기

        	int length = num.length();
        	int h = 1; // 트리의 높이
        	int totalNode = (int)Math.pow(2, h) - 1; // 포화이진트리의 노드의 수
        	// 더미노드 추가하기
        	while(totalNode < length) {
        		// 해당 숫자를 이진수로 바꾼 길이보다 포화이진트리의 노드수가 더 많아지는 것은
        		// 해당 이진수 길이로 만든 이진 트리에 더미노드를 추가해준 것과 같음!
        		h++;
        		totalNode = (int)Math.pow(2, h) - 1;
        	}

        	// 찾은 포화이진트리 사이즈의 배열을 만든다
        	binary = new boolean[totalNode];
        	int index = totalNode - length; // 더미노드의 수(왼쪽에서부터 이만큼은 더미노드임)
        	for(int j = 0; j < length; j++) {
        		boolean check = num.charAt(j) == '1'; // 더미노드인지 아닌지 확인
        		binary[index++] = check; // 결과를 배열에 저장
        	}

        	result = 1; // 표현할 수 있음으로 초기화
        	checkTree(0, totalNode-1, false); // 배열 시작, 끝, 시작점은 더미가 아니므로 false
        	answer[i] = result;
        }
        return answer;
	}
```

- 먼저 받은 숫자를 이진수로 바꿔준다. 해당 이진수를 포화이진트리로 표현하는데 가능한 포화이진트리의 노드수를 구한다. 더미노드의 수를 구해주고 그 뒤부터 더미노드(0)인지 아닌지(1) 확인하고 결과를 binary배열에 저장하여 포화이진트리를 완성한다.
- checkTree 함수를 통해 만들어진 이진트리로 해당 수를 표현할 수 있는지 확인한다.

```java
static void checkTree(int start, int end, boolean check) {
		int mid = (start + end) / 2;

		if(check && binary[mid]) { // 루트가 더미이고 자식이 더미가 아니면
			result = 0; // 표현 할 수 없음
			return;
		}

		// 현재 노드가 마지막 노드가 아니면 재귀 호출
		if(start != end) {
			checkTree(start, mid-1, !binary[mid]); // 왼쪽
			checkTree(mid+1, end, !binary[mid]); // 오른쪽
		}
	}
```

- 포화이진트리이기 때문에 루트는 더미가 될 수 없으므로 해당 조건을 기저조건으로 한다.
- 루트노드를 기준으로 왼쪽, 오른쪽 서브트리로 나누어서 검사한다.

## :black_nib: **Review**

- 문제 자체를 이해하는데도 시간이 오래걸렸다. 이전에 트리문제를 많이 풀어보지 않아서 어떻게 접근해야 될지 몰랐다. 답을 보고 풀었는데 트리 관련된 문제를 더 많이 풀어보고 강해져서 다시 풀어보고싶다..
