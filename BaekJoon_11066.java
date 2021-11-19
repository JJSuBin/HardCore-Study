import java.util.*;

public class BaekJoon_11066 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		
		while(t-->0) {
			int k=sc.nextInt();
			int[] cost=new int[k+1];
			/*
			 * dp[i][j] : i번째 페이지부터 j번 페이지까지 비용을 합한 최솟값
			 * dp[i][i] : i번째 페이지의 비용
			 * dp[i][i+1] : i번째 페이지 비용 + i+1번째 페이지 비용 
			 */
			int[][] dp=new int[k+1][k+1];
			int[] sum=new int[k+1];
			
			for(int i=1;i<=k;i++) {
				cost[i]=sc.nextInt();
				sum[i]=sum[i-1]+cost[i];
			}
			
			for(int i=1;i<=k;i++) { // 1장부터 k장까지 묶기
				for(int from=1;from+i<=k;from++) { // 어디서부터 묶기 시작할것인가
					int to=from+i;
					dp[from][to]=Integer.MAX_VALUE;
					
					for(int divide=from;divide<to;divide++) // 특정 지점으로 나눠 최댓값 구하기
						dp[from][to]=Math.min(dp[from][to],dp[from][divide]+
								dp[divide+1][to]+sum[to]-sum[from-1]);
				}
			}
			
			System.out.println(dp[1][k]);
		}
	}

}
