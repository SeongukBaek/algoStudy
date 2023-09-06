function solution(a) {
  let answer = 0;
  let leftMin = Array.from({ length: a.length }, () => a[0]);
  let rightMin = Array.from({ length: a.length }, () => a[a.length - 1]);

  for (let i = 1; i < a.length; i++) {
    leftMin[i] = Math.min(leftMin[i - 1], a[i]);
    rightMin[a.length - i - 1] = Math.min(
      rightMin[a.length - i],
      a[a.length - i]
    );
  }
  a.forEach((balloon, index) => {
    if (balloon <= leftMin[index] || balloon <= rightMin[index]) answer++;
  });
  return answer;
}
