import java.util.*;

public class BaekJoon_5557 {
	static int[] num;
	static long[][] dp;
	static int n;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		num=new int[n];
		dp=new long[n][21];
		
		for(int i=0;i<n;i++)
			num[i]=sc.nextInt();
		
		// dp[i][j] : i번째 수 까지 계산을 했을 때 j를 만들 수 있는 경우의 수
		dp[0][num[0]]=1;
		for(int i=1;i<n-1;i++) {
			for(int j=0;j<=20;j++) {
				if(dp[i-1][j]!=0) {
					// 중간에 나오는 수는 모두 0이상 20이하
					if(j-num[i]>=0)
						dp[i][j-num[i]]+=dp[i-1][j];
					if(j+num[i]<=20)
						dp[i][j+num[i]]+=dp[i-1][j];
				}
			}
		}
		System.out.println(dp[n-2][num[n-1]]);
	}
	
}
