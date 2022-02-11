import java.io.*;
import java.util.*;

public class BaekJoon_17090 {
	static char[][] map;
	static boolean[][] visited;
	static int n,m;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		map=new char[n][m];
		visited=new boolean[n][m];
		
		for(int i=0;i<n;i++) 
			map[i]=br.readLine().toCharArray();
		
		int count=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(dfs(i,j)) // 각 칸에서 탈출할 수 있는지 탐색
					count++;
			}
		}
			
		System.out.println(count);			
	}
	
	static boolean dfs(int x, int y) {
		boolean result=false; // 사이클이 생기는지 탐색하는 변수
		
		// 경계 밖으로 나간 경우 탈출 성공 -> return true
		if(x<0||y<0||x>=n||y>=m)
			return true;
		
		if(map[x][y]=='F') // 순환하는 경우
			return false;
		else if(map[x][y]=='T')
			return true;
		
		// 이미 방문한적 있는 칸이라면 return false
		if(visited[x][y])
			return false;
		
		visited[x][y]=true; // 방문처리
		if(map[x][y]=='U')
			result=dfs(x-1,y);
		else if(map[x][y]=='R')
			result=dfs(x,y+1);
		else if(map[x][y]=='D')
			result=dfs(x+1,y);
		else if(map[x][y]=='L')
			result=dfs(x,y-1);
		
		// result 값에 따라 T or F 저장
		map[x][y]=result?'T':'F';
		
		return result;
	}
}
