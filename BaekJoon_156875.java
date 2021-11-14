import java.util.*;

public class BaekJoon_15657 {
	static StringBuilder sb=new StringBuilder();
	static int n,m;
	static int[] num,arr;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		num=new int[n];
		arr=new int[m];
		
		for(int i=0;i<n;i++)
			num[i]=sc.nextInt();
				
		Arrays.sort(num);
				
		dfs(0,0);
		System.out.println(sb);
	}
			
	static void dfs(int depth, int start) {
		if(depth==m) {
		for(int i=0;i<arr.length;i++)
			sb.append(arr[i]+" ");
					
			sb.append('\n');
			return;
		}
				
		for(int i=start;i<n;i++) {
			arr[depth]=num[i];
			dfs(depth+1,i);
		}
	}
}
