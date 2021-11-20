import java.util.*;

class Point_2146 {
	int x,y,distance;
	
	public Point_2146(int x, int y, int distance) {
		this.x=x;
		this.y=y;
		this.distance=distance;
	}
}

public class BaekJoon_2146 {
	static int[][] map;
	static int n,min=Integer.MAX_VALUE,num=2;
	static boolean[][] visited;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		
		map=new int[n][n];
		for(int i=0;i<n;i++) 
			for(int j=0;j<n;j++)
				map[i][j]=sc.nextInt();
		
		// 각각 섬에 번호 붙이기
		visited=new boolean[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!visited[i][j]&&map[i][j]==1) {
					Distinguish_Island(i,j);
					num++; // 섬 번호 증가
				}
			}
		}
		
		// 섬 사이 중 가장 짧은 거리 찾기(= 다리 놓기)
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]!=0) {
					visited=new boolean[n][n]; // 매 경우마다 방문체크 배열 초기화
					build_bridge(i,j,map[i][j]);
				}
			}
		}
		
		System.out.println(min);
	}
	
	// 각 섬에 번호를 붙여 구별해주는 함수
	static void Distinguish_Island(int x, int y) {
		visited[x][y]=true;
		map[x][y]=num; // 번호 붙이기
		
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			if(nx>=0&&ny>=0&&nx<n&&ny<n) {
				if(!visited[nx][ny]&&map[nx][ny]==1) {
					Distinguish_Island(nx,ny);
				}
			}
		}
	}
	
	// 가장 짧은 다리 찾기
	static void build_bridge(int x, int y, int cur_num) {
		Queue<Point_2146> q=new LinkedList<>();
		q.offer(new Point_2146(x,y,0));
		visited[x][y]=true;
		
		while(!q.isEmpty()) {
			Point_2146 p=q.poll();
			
			for(int i=0;i<4;i++) {
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				
				if(nx>=0&&ny>=0&&nx<n&&ny<n&&!visited[nx][ny]) {
					if(map[nx][ny]!=cur_num) { // 현재 섬과 번호와 다른 번호를 만났다면(바다 or 다른 섬)
						visited[nx][ny]=true;
						
						if(map[nx][ny]==0) // 바다일 경우 거리 증가시키고 큐에 삽입
							q.offer(new Point_2146(nx,ny,p.distance+1));
						
						else // 다른 섬일 경우 최단거리 갱신
							min=Math.min(min,p.distance);
					}
				}
			}
		}
	}
}
