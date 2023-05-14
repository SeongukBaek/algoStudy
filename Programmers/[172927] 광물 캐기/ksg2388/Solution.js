function solution(picks, minerals) {
  let answer = 0;
  let size = Math.ceil(minerals.length / 5);
  let mineralSet = [];
  let pickCnt = 0;
  picks.forEach((pick) => (pickCnt += pick));

  for (let i = 0; i < size; i++) {
    let diaPoint = 0;
    let ironPoint = 0;
    let stonePoint = 0;

    for (let j = 0; j < 5; j++) {
      if (i * 5 + j === minerals.length) {
        break;
      }
      if (minerals[i * 5 + j] === 'diamond') {
        diaPoint += 1;
        ironPoint += 5;
        stonePoint += 25;
        continue;
      }
      if (minerals[i * 5 + j] === 'iron') {
        diaPoint += 1;
        ironPoint += 1;
        stonePoint += 5;
        continue;
      }
      if (minerals[i * 5 + j] === 'stone') {
        diaPoint += 1;
        ironPoint += 1;
        stonePoint += 1;
      }
    }
    mineralSet.push([diaPoint, ironPoint, stonePoint]);
  }
  mineralSet = mineralSet.slice(0, pickCnt);
  mineralSet.sort((a, b) => b[2] - a[2]);
  console.log(mineralSet);

  // 광물 캐기
  for (let i = 0; i < mineralSet.length; i++) {
    // 다이아 곡괭이가 있는 경우
    if (picks[0] > 0) {
      picks[0]--;
      answer += mineralSet[i][0];
      continue;
    }
    // 철 곡괭이가 있는 경우
    if (picks[1] > 0) {
      picks[1]--;
      answer += mineralSet[i][1];
      continue;
    }
    picks[2]--;
    answer += mineralSet[i][2];
  }
  return answer;
}
