import java.util.*;

public class BaekJoon_11727 {
	static int[] dp;
	static int n;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		
		dp=new int[n+1];
		
		// 정화식 -> dp[i]=dp[i-1]+(dp[i-2]x2)
		dp[0]=0;
		dp[1]=1;
		dp[2]=3;
		dp[3]=5;
		
		for(int i=3;i<=n;i++)
			dp[i]=(dp[i-1]+dp[i-2]*2)%10007;
		
		System.out.println(dp[n]);
	}

}
