import java.util.Scanner;

public class BaekJoon_12865 {
	public static int dp[][],w[],v[];
	public static int n,k;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		k=sc.nextInt();
		
		/* 
		 * dp는 dp[i번째 아이템][무게]를 의미한다.
		 * 아이템의 개수는 n개 제한된 무게는 k이며,
		 * 1부터 인덱스를 시작하기 위해 아래와 같이 초기화를 한다.
		 */
		dp=new int[n+1][k+1];
		w=new int[n+1];
		v=new int[n+1];
		
		for(int i=1;i<=n;i++) {
			w[i]=sc.nextInt();
			v[i]=sc.nextInt();
		}
		
		/* 
		 * dp의 각 행은 n개의 아이템을 의미하고, 열은 k까지의 무게를 의미한다.
		 * 첫 번째 아이템부터 1~k 무게 이내로 담을 수 있는지 판단하고 담을수 있다면 가치를 저장한다.
		 * 이때 dp[i][j]는 이전 행의 누적 값을 갖는데 해당 아이텀을 담고도 무게가 남는다면
		 * 남는 무게만큼의 아이템을 더하여 이전 행의 누적 값과 비교하여 큰 값을 선택한다.
		 */
		
		for(int i=1;i<=n;i++) { // n개의 아이템을
			for(int j=1;j<=k;j++) { // 1~k 무게까지 담을 수 있는지 비교
				dp[i][j]=dp[i-1][j]; // 이전 행의 무게 값을 갖는다.
				if(j-w[i]>=0) // 아이템을 담고도 무게가 남는다면
					// 이전 아이템에서 구한 가치 or 남는 무게 가치 + 자신의 가치 중 큰 값 선택
					dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-w[i]]+v[i]);
			}
		}
		System.out.println(dp[n][k]);
	}
}
