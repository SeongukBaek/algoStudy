# [131704] 택배상자

## :pushpin: **Algorithm**

스택, 큐

## :round_pushpin: **Logic**

```java
for(int od : order) {
    while(true) {
        if (od < n) {
            if (container.peek() == od) {
                n = container.pop();
                answer++;
                break;
            } else return answer;
        } else if (!belt.isEmpty()) {
            if (belt.peek() == od) {
                n = belt.poll();
                answer++;
                break;
            } else container.add(belt.poll());
        }
    }
}
```

-

## :black_nib: **Review**

-
