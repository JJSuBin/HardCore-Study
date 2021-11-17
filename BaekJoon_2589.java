import java.util.*;

class Point_2589 {
	int x,y,distance;
	
	public Point_2589(int x, int y, int distance) {
		this.x=x;
		this.y=y;
		this.distance=distance;
	}
}

public class BaekJoon_2589 {
	static char[][] map;
	static boolean[][] visited;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static int n,m,max=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		map=new char[n][m];
		
		// 맵 정보 입력 받기
		for(int i=0;i<n;i++) {
			String str=sc.next();
			for(int j=0;j<m;j++) {
				map[i][j]=str.charAt(j);
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				// 어떤 육지에서부터 가장 긴 시간이 걸리는 육지까지의 거리 계산하기
				if(map[i][j]=='L') {
					visited=new boolean[n][m];
					max=Math.max(max,findtreasure(i,j)); // 최댓값 갱신
				}
			}
		}
		
		System.out.println(max);
	}
	
	// (x,y) 육지에서부터 시작해 가장 멀리있는 육지까지의 거리 계산하는 함수
	static int findtreasure(int x, int y) {
		Queue<Point_2589> q=new LinkedList<>();
		q.offer(new Point_2589(x,y,0)); // 처음 거리는 0
		visited[x][y]=true;
		
		int distance=0; // 해당 케이스의 거리를 저장하는 변수
		while(!q.isEmpty()) {
			Point_2589 p=q.poll();
			
			for(int i=0;i<4;i++) {
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				
				if(nx>=0&&ny>=0&&nx<n&&ny<m) {
					// 방문하지 않은 육지여야 최단거리가 될 수 있음
					if(!visited[nx][ny]&&map[nx][ny]=='L') {
						q.offer(new Point_2589(nx,ny,p.distance+1));
						visited[nx][ny]=true;
						distance=Math.max(distance,p.distance+1); // 거리 갱신
					}
				}
			}
		}
		
		// (x,y) 육지에서부터 시작해 가장 멀리있는 육지까지의 거리 반환
		return distance;
	}
}
