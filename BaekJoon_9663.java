import java.util.*;

public class BaekJoon_9663 {
	static int[] chess; // 인덱스는 열, 값은 행 => chess[i]=j : i번째 열에는 j행에 퀸이 놓여있다.
	static int n,count=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		chess=new int[n];
		
		dfs(0);
		System.out.println(count);
	}
	
	static void dfs(int depth) {
		if(depth==n) { // n개의 퀸을 다 배치했다면 count 증가
			count++;
			return;
		}
		
		for(int i=0;i<n;i++) {
			chess[depth]=i; // depth열의 i번째 행에 퀸 배치
			
			// 배치가 가능하다면 다음 열로 넘거간다.
			if(isPossible(depth))
				dfs(depth+1);	
		}
	}
	
	// 해당 열에 퀸을 놓을 수 있는지 검사
	static boolean isPossible(int col) {
		for(int i=0;i<col;i++) {
			if(chess[col]==chess[i]) // 다른 열의 같은 행에 퀸이 놓여있다면 불가능한 경우
				return false;
			
			// 대각선에 놓여있는 경우(열과 행의 차이가 같은 경우)도 불가능 
			else if(Math.abs(col-i)==Math.abs(chess[col]-chess[i]))
				return false;
		}
		return true;
	}
}
