import java.util.*;

public class BaekJoon_16926 {
	static int[][] map;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int r=sc.nextInt();
		
		map=new int[n][m];
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				map[i][j]=sc.nextInt();
		
		int level=Math.min(n, m)/2; // n과 m중 작은 수를 2로 나눈 값이 돌려야 하는 그룹 수
		
		// 회전 횟수만큼 반복
		for(int i=0;i<r;i++) {
			// 그룹 개수만큼 반복
			for(int j=0;j<level;j++) {  
				int temp=map[j][j]; // 각 라인의 첫 번째 값을 덮어씌어지기 전에 따로 저장해둔다.
				
				for(int k=j+1;k<m-j;k++) // 윗쪽 줄 회전 
					map[j][k-1]=map[j][k];
				
				for(int k=j+1;k<n-j;k++) // 오른쪽 줄 회전
					map[k-1][m-1-j]=map[k][m-1-j];
				
				for(int k=m-j-2;k>=j;k--) // 아랫 줄 회전
					map[n-1-j][k+1]=map[n-1-j][k];
				
				for(int k=n-2-j;k>=j;k--) // 왼쪽 줄 회전
					map[k+1][j]=map[k][j];
				
				map[j+1][j]=temp;
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
			
	}
}
