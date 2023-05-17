# [152995] 인사고과

## :pushpin: **Algorithm**

구현

## :round_pushpin: **Logic**

```java
for (int i = 1; i < scores.length; i++) {
    if(scores[0][0] + scores[0][1] < scores[i][0] + scores[i][1]) {
        employee.add(scores[i]);
    }
}

for (int i = 1; i < scores.length; i++) {
    //완호보다 두 점수가 모두 높은 사원이 있다면 -1 반환
    if(scores[i][0] > scores[0][0] && scores[i][1] > scores[0][1]) {
        return -1;
    }

    //완호보다 점수의 합이 높은 사원이라면
    if(scores[i][0] + scores[i][1] > scores[0][0] + scores[0][1]) {
        canInsentive = true;

        //완호보다 점수합이 높은 사원들을 순회하며
        for(int j = 1; j < employee.size(); j++) {
            //현재 사원이 인센티브를 받을 수 있는지 검사
            if(employee.get(j)[0] > scores[i][0] && employee.get(j)[1] > scores[i][1]) {
                canInsentive = false;
                break;
            }
        }

        //인센티브를 받을 수 있는 사원이라면 인센티브 받는 사원을 저장하는 insentiveEmployee에 저장
        if(canInsentive) {
            insentiveEmployee.add(scores[i]);
        }
    }
}
```

- employee(ArrayList)에 완호보다 점수의 합이 높은 사원들의 점수만 add한다.
- scores 배열을 차례대로 순회한다.
- 완호보다 두 점수가 모두 높은 사원이 존재한다면 -1을 반환한다.
- 인센티브를 받을 수 있는 사원은 insentiveEmployee(ArrayList)에 추가한다.
- 완호보다 두 점수가 모두 높지 않으며, 점수의 합이 높은 사원이 있다면 insentiveEmployee를 순회하며 그 사원이 인센티브를 받을 수 있는지 검사하고 insentiveEmployee에 add한다.

## :black_nib: **Review**

- 정렬을 한다면 좀 더 효율적으로 풀 수 있을 것 같았으나, 브루트 포스하게 풀어도 시간초과가 나지 않을 것 같아서 위 방법으로 구현하였다.
