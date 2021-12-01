import java.io.*;
import java.util.*;

class Point_16946 {
	int x,y;
	
	public Point_16946(int x ,int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_16946 {
	static int[][] map,group;
	static int n,m;
	static HashMap<Integer,Integer> hashmap=new HashMap<>();
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());;
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		map=new int[n][m];
		group=new int[n][m];
		
		for(int i=0;i<n;i++) {
			String[] str=br.readLine().split("");
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(str[j]);
			}
		}
		
		int group_num=1; // 그룹 번호
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==0&&group[i][j]==0) {
					// 해쉬맵에 (그룹 번호, 그룹에 속해있는 칸의 갯수)를 저장한다.
					hashmap.put(group_num,make_group(i,j,group_num));
					group_num++;
				}
			}
		}
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				sb.append(break_wall(i,j));
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	// 분리 집합 -> 0으로 이루어진 그룹에 번호를 지정하고, 해당 그룹에 속해있는 칸의 갯수를 count 
	static int make_group(int x, int y, int num) {
		int count=1; // 그룹에 속해있는 칸의 갯수
		Queue<Point_16946> q=new LinkedList<>();
		q.offer(new Point_16946(x,y));
		group[x][y]=num;
		
		while(!q.isEmpty()) {
			Point_16946 p=q.poll();
			
			for(int i=0;i<4;i++) {
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				
				if(nx<0||ny<0||nx>=n||ny>=m) continue;
				
				// 벽이 없는 곳이며 아직 그룹에 속해있지 않은 곳 탐색
				if(map[nx][ny]==0&&group[nx][ny]==0) {
					group[nx][ny]=num; // 그룹 번호 부여
					count++; 
					q.offer(new Point_16946(nx,ny));
				}
			}
		}
		
		return count; // 그룹에 속해있는 칸의 개수 return
	}
	
	static int break_wall(int x, int y) {
		int sum=1; // 시작 지점(벽이 있는 곳)도 이동할 수 있는 곳으로 변경하기 때문에 1로 시작
		HashSet<Integer> set=new HashSet<>(); // (x,y)를 기준으로 이동할 수 있는 칸의 그룹 번호가 저장되어 있음
		
		if(map[x][y]==0) // 벽이 없는 곳은 탐색 X
			return 0;
		
		// 벽이 있는 곳을 기준으로 상,하,좌,우 탐색
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			if(nx<0||ny<0||nx>=n||ny>=m) continue;
			
			// 그룹 번호가 없는 곳은 벽이 있는 곳 -> 이동할 수 없는 칸
			if(group[nx][ny]==0) continue;
			
			// 이동할 수 있는 곳이라면 그룹 번호를 set에 삽입
			if(map[nx][ny]==0)
				set.add(group[nx][ny]);
		}
		
		// set에 삽입된 각 그룹에 속해있는 칸의 개수의 누적합을 구해준다.
		for(int index:set)
			sum+=hashmap.get(index);
		
		return sum%10;
	}
}
