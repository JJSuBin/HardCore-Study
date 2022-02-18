import java.util.*;

public class BaekJoon_14500 {
	static int[][] map;
	static boolean[][] visited;
	static int n,m,max=Integer.MIN_VALUE;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	
	/*
	 * ㅗ 모양은 DFS로 나올 수 없는 모양이기 때문에
	 * ㅗ,ㅜ,ㅏ,ㅓ 모양은 따로 이동 배열을 선언하여 완전탐색으로 모두 검사한다.
	 * 
	 * ㅗ,ㅜ,ㅏ,ㅓ 순서
	 */
	static int[][] ex= {{1,1,1,0},{0,0,0,1},{0,1,2,1},{0,1,2,1}};
	static int[][] ey= {{0,1,2,1},{0,1,2,1},{0,0,0,1},{1,1,1,0}};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		map=new int[n][m];
		visited=new boolean[n][m];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				map[i][j]=sc.nextInt();
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				visited[i][j]=true; // 방문처리
				dfs(i,j,0,0); // 탐색
				visited[i][j]=false; // 다음 경우의 수를 위해 값 회복
				
				exception(i,j); // 예외 모양도 탐색
			}
		}
		
		System.out.println(max);
	}
	
	// ㅗ 를 제외한 나머지 4가지 경우는 DFS를 수행하면서 모두 나오는 모양이다(대칭,회전 포함)
	static void dfs(int x, int y, int depth, int sum) {
		// 4조각 모두 탐색했다면 최댓값 갱신
		if(depth==4) {
			max=Math.max(max, sum);
			return;
		}
		
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			// 범위를 벗어났거나 이미 방문했던 배열이면 넘어간다.
			if(nx<0||ny<0||nx>=n||ny>=m)
				continue;
			if(visited[nx][ny])
				continue;
			
			visited[nx][ny]=true; // 방문처리
			dfs(nx,ny,depth+1,sum+map[nx][ny]); // 조각의 개수 증가, 누적합 증가한 채로 이동한 좌표부터 재탐색
			visited[nx][ny]=false; // 다음 경우의 수를 위해 값 회복
		}
	}
	
	// DFS로 모양을 낼 수 없는 ㅗ,ㅜ,ㅏ,ㅓ 모양 탐색
	static void exception(int x, int y) {
		for(int i=0;i<4;i++) {
			int result=0; // 조각의 합

			for(int j=0;j<4;j++) {
				int nx=x+ex[i][j];
				int ny=y+ey[i][j];
				
				// 범위를 벗어났다면 조각의 합을 구하지 않고 넘어간다.
				if(nx<0||ny<0||nx>=n||ny>=m) 
					continue;
				
				// 배열 범위 내에 조각이 위치할 수 있다면 칸에 적인 숫자 더함
				result+=map[nx][ny];
			}

			max=Math.max(result, max); // 최댓값 갱신
		}
	}
}
