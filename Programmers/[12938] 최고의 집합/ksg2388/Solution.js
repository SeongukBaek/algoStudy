function solution(n, s) {
    var answer = [];
    
    if (n > s) {
        return [-1];
    }
    
    let mod = Math.floor(s / n);
    let remider = s % n;
    
    for (let i = 0; i < n; i++) {
        answer.push(mod);
    }
    
    // 나머지들을 1씩 분배
    if (remider !== 0) {
        for (let i = 0; i < remider; i++) {
            answer[answer.length - 1 - i]++;
        }
    }
    
    return answer;
}