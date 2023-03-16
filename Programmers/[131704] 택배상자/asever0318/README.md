# [131704] 택배상자

## :pushpin: **Algorithm**

스택

## :round_pushpin: **Logic**

```java
static int countBox(int[] order) {
		int count = 0;
		int box = 1;
		int i = 0;

		while(i < order.length) { // 순서

			if(order[i] != box) { // 상자 - 순서 다르면
				if(!container.isEmpty()) { // 스택이 비어있지 않으면
					if(container.peek() == order[i]) { // top에 있는 것과 같으면
						container.pop(); // 스택에서 빼줌
						count++;
						i++;
						continue;
					}
				}

				if(box == order.length) {
					break;
				}

				for(int b = box; b <= order.length; b++) {
					if(order[i] == b) {
						count++;
						box = b + 1;
						i++;
						break;
					}
					else {
						container.push(b);
						box = b;
					}
				}
			}
			else { // 상자 - 순서 같으면
				count++; // 상자 올리기
				box++; // 다음 상자
				i++;
			}
		}

		return count;
	}
```

- 순서와 상자 번호를 비교하여 만약 순서와 상자 번호가 일치하면 상자를 올리고, 일치하지 않으면 스택에 있는 상자와 비교하여 있으면 상자를 올리고 없으면 순서와 같은 상자번호가 나올 때까지 스택에 넣어준다.
- 정해진 순서 배열(order) 끝까지 반복하되 만약 이전에 박스를 다 소진하면 끝난다.

## :black_nib: **Review**

- 처음에 상자를 스택에 넣어야 하는데 순서를 스택에 넣는 실수를 했다. 더 꼼꼼하게 확인해야겠다.
