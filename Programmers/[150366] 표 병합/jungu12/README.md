# [154540] 무인도 여행

## :pushpin: **Algorithm**

union-find, 구현

## :round_pushpin: **Logic**

```java
public String[] solution(String[] commands) {
    List<String> result = new ArrayList<>();

    for (int i = 1; i <= 2500; i++) {
        union[i] = i;
        value[i] = "";
    }

    for (int i = 0; i < commands.length; i++) {
        String[] command = commands[i].split(" ");
        String type = command[0];

        //명령어가 UPDATE인 경우
        if (type.equals("UPDATE")) {
            //"UPDATE value1 value2"인 경우
            if (command.length == 3) {
                String before = command[1];
                String after = command[2];
                for (int j = 1; j <= 2500; j++) {
                    if (value[j].equals(before)) {
                        value[j] = after;
                    }
                }
                continue;
            }
            //"UPDATE r c value"인 경우
            if (command.length == 4) {
                int x = Integer.parseInt(command[1]);
                int y = Integer.parseInt(command[2]);
                String after = command[3];
                int idx = convert(x, y);
                value[find(idx)] = after;
            }
            continue;
        }
        //명령어가 MERGE인 경우
        if(type.equals("MERGE")) {
            int x1 = Integer.parseInt(command[1]);
            int y1 = Integer.parseInt(command[2]);
            int x2 = Integer.parseInt(command[3]);
            int y2 = Integer.parseInt(command[4]);

            int idx1 = convert(x1, y1);
            int idx2 = convert(x2, y2);

            int root1 = find(idx1);
            int root2 = find(idx2);

            if(root1 == root2) {
                continue;
            }

            if(value[root1].equals("")) {
                value[root1] = value[root2];
            }
            value[root2] = "";
            union(root1, root2);
            continue;
        }
        //명령어가 UNMERGE인 경우
        if (type.equals("UNMERGE")) {
            int x = Integer.parseInt(command[1]);
            int y = Integer.parseInt(command[2]);

            int idx = convert(x, y);
            int root = find(idx);

            String rootString = value[root];
            value[root] = "";
            value[idx] = rootString;


            List<Integer> deletes = new ArrayList<>();
            for (int j = 1; j <= 2500; j++) {
                if(find(j) == root) {
                    deletes.add(j);
                }
            }

            for (int deleteIdx : deletes) {
                union[deleteIdx] = deleteIdx;
            }
            continue;
        }
        //명령어가 PRINT인 경우
        if (type.equals("PRINT")) {
            int x = Integer.parseInt(command[1]);
            int y = Integer.parseInt(command[2]);
            int idx = convert(x, y);
            int root = find(idx);

            if(value[root].equals("")) {
                result.add("EMPTY");
            } else {
                result.add(value[root]);
            }
        }
    }

    return result.toArray(new String[0]);
}
```

- 구현에 편의성을 위해 2차원 배열을 1차원 배열로 변경하여 구현하였다.
- merge의 편의성을 위해 union-find를 사용하였다.
- 나머지 명령어는 단순 구현이라고 보면 된다.

## :black_nib: **Review**

- 문제에 세부적인 조건들이 많아서 헷갈렸다..
- toArray메소드에 대해 좀 더 깊게 알게 되었다!
- toArray안의 인자로 넘겨주는 String 배열의 크기가 0이다. 이와 같이 넘겨 주면 result의 길이 만큼 만들어 진다고 한다 !!
- 참고
  - 1. List를 toArray 메서드에 파라메터로 넘어가는 배열 객체의 size만큼의 배열로 전환한다.
  - 2. 단, 해당 List size가 인자로 넘어가는 배열 객체의 size보다 클때, 해당 List의 size로 배열이 만들어진다.
  - 3. 반대로 해당 List size가 인자로 넘어가는 배열객체의 size보다 작을때는, 인자로 넘어가는 배열객체의 size로 배열이 만들어진다.
