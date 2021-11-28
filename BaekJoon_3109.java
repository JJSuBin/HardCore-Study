import java.util.*;

public class BaekJoon_3109 {
	static char[][] map;
	static int r,c,width,result=0;
	// 오른쪽 열로 이동할 수 있는 경우의 수(오른쪽 위, 옆, 대각선 아래) -> 행의 변화
	static int[] dx= {-1,0,1}; 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		r=sc.nextInt();
		c=sc.nextInt();
		
		map=new char[r][c];
		for(int i=0;i<r;i++) {
			String str=sc.next();
			for(int j=0;j<c;j++) {
				map[i][j]=str.charAt(j);
			}
		}
		
		// 각각의 행을 시작으로 마지막 열로 도달하는 경우 탐색
		for(int i=0;i<r;i++) 
			still_pipe(i,0);
		
		System.out.println(result);
	}
	
	static boolean still_pipe(int x, int y) {
		if(y==c-1) { // 마지막 열에 도달했다면
			result++; // 결과값 count
			
			return true;
		}
		
		for(int i=0;i<3;i++) {
			int nx=x+dx[i]; // 행은 오른쪽 대각선 위, 옆, 대각선 아래 중 한곳으로 이동
			int ny=y+1; // 열은 다음 칸으로 이동 
			
			// 맵을 벗어나거나 벽이 있는 경우는 이동할 수 없음
			if(nx<0||ny<0||nx>=r||ny>=c||map[nx][ny]=='x') continue;
			
			map[nx][ny]='x'; // 방문한 곳은 X로 변경하기
			
			/*
			 * 다음 라인 검사
			 * 라인의 가지치기를 방지하기 위해 true를 return 하여 다음 분기문으로 넘어가지 않도록 한다.
			 */
			if(still_pipe(nx,ny))
				return true;
		}
		
		return false;
	}
}
