import java.io.*;
import java.util.*;

public class BaekJoon_15684 {
	static int[][] map;
	static int n,m,h,result;
	static boolean finish=false; // 끝나야 하는 조건을 만족했는가 확인하는 변수
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st=new StringTokenizer(br.readLine());

		n=Integer.parseInt(st.nextToken()); // 세로선의 개수
		m=Integer.parseInt(st.nextToken()); // 이미 설치된 가로선의 개수
		h=Integer.parseInt(st.nextToken()); // 가로선의 개수
		
		/*
		 * map 배열은 각 세로선과 가로선의 교차하는 지점, 즉 가로선이 시작될 수 있는 칸을 의미한다.
		 * 0 : 가로선 없음
		 * 1 : 오른쪽으로 이동
		 * 2 : 왼쪽으로 이동
		 */
		map=new int[h+1][n+1];
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			/*
			 * b번 세로선과 b+1세로선을 a 점선 위치에서 연결
			 * map[a][b]칸에서는 오른쪽으로 이동해야하고, map[a][b+1]칸에서는 왼쪽으로 이동해야함
			 */
			int a=Integer.parseInt(st.nextToken()); 
			int b=Integer.parseInt(st.nextToken()); 
			
			map[a][b]=1;
			map[a][b+1]=2;
		}
		
		// 설치할 사다리의 개수는 0~3개
		for(int i=0;i<=3;i++) {
			// 설치할 사다리의 개수를 전역변수로 저장하고 dfs의 재귀 깊이가 해당 수만큼 진행되도록 한다.
			result=i;
			dfs(0,1);
			if(finish) // 중간에 만족하는 경우가 있다면 종료
				break;
		}
		
		System.out.println((finish)?result:-1);
	}
	
	static void dfs(int line, int start) {
		if(result==line) { // result 개수만큼 가로줄을 만들었다면
			if(check()) // 조건을 만족하는지 확인하여 finish 값을 바꿔준다.
				finish=true;
			
			return;
		}
		
		// 맵을 전체 탐색하면서 가로선 세우기
		for(int i=start;i<=h;i++) {
			for(int j=1;j<n;j++) {
				if(map[i][j]==0&&map[i][j+1]==0) { // 가로선이 없는 곳이라면 
					map[i][j]=1; // 가로선을 설치했을때 이동해야 하는 방향 저장
					map[i][j+1]=2;
					dfs(line+1,i); // 가로선의 갯수 +1, 다음 탐색 위치를 매개변수로 재귀호출
					map[i][j]=map[i][j+1]=0; // 값 복귀
				}
			}
		}
	}
	
	// i번 세로선의 결과가 i번이 나오는지 확인하는 함수
	static boolean check() {
		for(int i=1;i<=n;i++) { // 각 세로줄 탐색
			int nx=1; // 시작하는 가로줄
			int ny=i; // 시작하는 세로줄
				
			while(nx<=h) { // 가로줄의 끝까지 탐색
				if(map[nx][ny]==1) ny++; // 오른쪽으로 이동해야 하는 경우
				else if(map[nx][ny]==2) ny--; // 왼쪽으로 이동해야 하는 경우
				nx++; // 다음줄로 내려가기
			}
			// 가로줄의 끝에 내려간후 i번 세로선의 결과가 i번이 나오는지 확인
			if(ny!=i) return false; 
		}
			
		return true;
	}
}
