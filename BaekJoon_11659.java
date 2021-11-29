import java.util.*;

public class BaekJoon_11659 {
	static int[] dp,arr;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		
		arr=new int[n+1];
		dp=new int[n+1];
		
		for(int i=1;i<=n;i++)
			arr[i]=sc.nextInt();
		
		arr[0]=dp[0]=0;
		
		for(int i=1;i<=n;i++)
			dp[i]=arr[i]+dp[i-1];
		
		for(int i=0;i<m;i++) {
			int start=sc.nextInt();
			int end=sc.nextInt();
			
			System.out.println(dp[end]-dp[start-1]);
		}
		
	}

}
