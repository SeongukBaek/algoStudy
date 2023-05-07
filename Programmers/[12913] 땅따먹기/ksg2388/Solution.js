function solution(land) {
    let n = land.length;
    let prev = land[0];
    let cur = [];
    
    for (let i = 1; i < n; i++) {
        cur = land[i];
        for (let j = 0; j < 4; j++) {
            let max = 0;
            for (let k = 0; k < 4; k++) {
                // 같은 줄을 선택하는 경우는 무시
                if (j === k) {
                    continue;
                }
                if (max < prev[k]) {
                    max = prev[k];
                }
            }
            cur[j] += max;
        }
        // 현재 배열을 이전 배열로 이동
        prev = cur;
    }
    return Math.max(...cur);
}