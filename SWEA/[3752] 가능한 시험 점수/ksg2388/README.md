## 사용한 알고리즘

구현

## 중요 구현 로직 및 설명
1. Set에 0을 넣어 초기화해둔다.
2. 새로운 값이 들어올때마다 Set에 있는 값들에 그 값을 더한 값을 다시 Set에 추가해준다. 

   ```java
    int n = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    Set<Integer> scores = new HashSet<>(Arrays.asList(0));
             
    for (int i = 0; i < n; i++) {
        int num = Integer.parseInt(st.nextToken());
        List<Integer> temp = new ArrayList<>(scores);
        for (int j = 0; j < temp.size(); j++) {
            scores.add(temp.get(j) + num);
        }
    }
   ```
Set에 있는 값을 리스트로 만든 후 그 값들에 새로 들어온 값들을 더해 Set에 추가하면서 길이만큼 반복해준다.

## 풀이 후기

어떤 식으로 푸는 것이 효율적인지 많이 생각해 볼 수 있는 문제였다.
