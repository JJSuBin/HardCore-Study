import java.util.*;

public class BaekJoon_15661 {
	static boolean[] visited;
	static int n,team_num=0,min=Integer.MAX_VALUE;
	static int[][] power;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		power=new int[n][n];
		visited=new boolean[n];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				power[i][j]=sc.nextInt();
		
		// 팀의 인원수는 최소 한명이기 때문에 1~n-1이 팀일 경우를 모투 탐색해본다.
		for(team_num=1;team_num<n;team_num++)
			dfs(0,0);
		
		System.out.println(min);
	}
	
	// 한 팀이 될 수 있는 인원(team_num)만큼 팀 나누기
	static void dfs(int depth, int start) {
		if(depth==team_num) {
			diff();
			return;
		}
		
		// 백트래킹
		for(int i=start;i<n;i++) {
			if(!visited[i]) {
				visited[i]=true;
				dfs(depth+1,i+1);
				visited[i]=false;
			}
		}
	}
	
	// 같은 팀이된 사람들의 능력치 계산
	static void diff() {
		int start=0;
		int link=0;
		
		for(int i=0;i<n;i++) { 
			for(int j=i+1;j<n;j++) { // 다음 사람은 i와 중복되지 않게 다음 사람부터 탐색
				if(visited[i]&&visited[j]) { // start팀
					start+=power[i][j];
					start+=power[j][i];
				}
				else if(!visited[i]&&!visited[j]){ // link팀
					link+=power[i][j];
					link+=power[j][i];
				}
			}
		}
		
		min=Math.min(min, Math.abs(start-link)); // 최솟값 갱신
		
		if(min==0) { // 차이가 0인 경우가 있다면 해당 경우가 최솟값을 갖는 경우이기 때문에 종료
			System.out.println(0);
			System.exit(0);
		}
	}
}
