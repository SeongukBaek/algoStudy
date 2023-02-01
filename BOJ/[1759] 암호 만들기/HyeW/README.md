# [1759] 암호 만들기

## :pushpin: **Algorithm**

브루트포스, 백트래킹

## :round_pushpin: **Logic**

사전순으로 문자열을 만들어야 하기에 순서가 있는 **조합**을 사용했다.

```java
public static void comb(int d, int r, int start, char[] output) {

        if(d == r) {
            if(validate(output))
            System.out.println(new String(output));
            return;
        }

        for(int i = start; i < c; i++) {
            output[d] = alpa[i].charAt(0);
            comb(d+1, r, i+1, output);
        }
}
```

`start`파라미터를 전달하여 다음 문자를 선택할 때 이전의 문자보다 사전순으로 뒤에 있는 문자를 선택하도록 한다.
<br/> 
```java
public static boolean validate(char[] arr) {
		boolean result = false;
		int vow = 0;
		int con = 0;
		
		for(int i = 0; i< arr.length; i++) 
			for(int j = 0; j < vowel.length; j++) {
				if(arr[i] == vowel[j])
					vow++;
			}
		con = arr.length-vow;
				
		if(con > 1 && vow > 0)
			result = true;
		
		return result;	
	}
```
원하는 길이의 문자열을 만든 후 자음 2개 이상, 모음 1개 이상 포함됐는지 `validate(char[])`함수로 검사를 한다.
<br/>
`vowel`은 char배열로 모음 5개를 담아 받아온 문자열의 모음과 자음 개수를 센다.

## :black_nib: **Review**
- 모음을 구분하기 위해 모음을 담은 배열을 만들어서 해시처럼 사용을 했다.
