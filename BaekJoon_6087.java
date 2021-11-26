import java.util.*;

class Point_6087 {
	int x,y,dir,mirror;
	
	public Point_6087(int x, int y, int dir, int mirror) {
		this.x=x;
		this.y=y;
		this.dir=dir;
		this.mirror=mirror;
	}
}

public class BaekJoon_6087 {
	static Point_6087 start,end; 
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static int w,h,min=Integer.MAX_VALUE;
	static char[][] map;
	static int[][] visited; // 각 칸에 도달하기까지 설치한 거울의 개수 저장 배열
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		w=sc.nextInt();
		h=sc.nextInt();
		
		map=new char[h][w];
		for(int i=0;i<h;i++) {
			String str=sc.next();
			for(int j=0;j<w;j++) {
				map[i][j]=str.charAt(j);
				
				// 레이저가 있는 두 곳을 각각 시작 지점과 끝 지점으로 저장
				if(map[i][j]=='C') {
					if(start==null)
						start=new Point_6087(i,j,-1,0);
					
					else 
						end=new Point_6087(i,j,-1,0);
				}
			}
		}
		
		Laser();
		System.out.println(min);
	}
	
	static void Laser() {
		Queue<Point_6087> q=new LinkedList<>();
		q.offer(start); // 시작지점 삽입
		
		visited=new int[h][w];
		visited[start.x][start.y]=1;
		
		while(!q.isEmpty()) {
			Point_6087 p=q.poll();
			
			// 큐에서 꺼낸 좌표가 도착 지점이라면 설치된 거울의 개수 갱신
			if(p.x==end.x&&p.y==end.y) {
				min=Math.min(min, p.mirror);
				continue;
			}
			
			for(int i=0;i<4;i++) {
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				int ndir=i; // 탐색할 방향
				
				// 범위를 벗어나거나 벽인 경우 넘어간다.
				if(nx<0||ny<0||nx>=h||ny>=w||map[nx][ny]=='*') continue;
				
				int nmirror=p.mirror; // 설치될 거울의 개수
				
				if(p.dir!=-1&&ndir!=p.dir) 
					nmirror++;
				
				if(visited[nx][ny]==0) { // 방문하지 않았던 칸인 경우
					visited[nx][ny]=nmirror;
					q.offer(new Point_6087(nx,ny,ndir,nmirror));
				}
				
				else if(visited[nx][ny]>=nmirror) { // 이미 방문했지만 설치한 거울의 개수가 더 적은 경우
					visited[nx][ny]=nmirror;
					q.offer(new Point_6087(nx,ny,ndir,nmirror));
				}
			}
		}
	}
}
