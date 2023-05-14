function solution(sequence) {
  let seq1 = sequence.map((item, idx) => {
    if (idx % 2 === 0) {
      return item * -1;
    }
    return item;
  });
  let seq2 = sequence.map((item, idx) => {
    if (idx % 2 === 1) {
      return item * -1;
    }
    return item;
  });
  let max = Math.max(seq1[0], seq2[0]);
  const dp1 = [seq1[0]];
  const dp2 = [seq2[0]];

  for (let i = 1; i < sequence.length; i++) {
    dp1[i] = Math.max(dp1[i - 1] + seq1[i], seq1[i]);
    dp2[i] = Math.max(dp2[i - 1] + seq2[i], seq2[i]);
    max = Math.max(dp1[i], dp2[i], max);
  }

  return max;
}
