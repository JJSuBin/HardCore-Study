import java.util.*;

class Ball {
	int rx,ry,bx,by,count;
	
	public Ball(int rx,int ry,int bx,int by,int count) {
		this.rx=rx;
		this.ry=ry;
		this.bx=bx;
		this.by=by;
		this.count=count;
	}
}

public class BaekJoon_13460 {
	static Ball Red, Blue;
	static int[] dx={-1,0,1,0}; 
	static int[] dy={0,1,0,-1};
	static int n,m,hole_x,hole_y;
	static char[][] map;
	static boolean[][][][] visited;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		map=new char[n][m];
		visited=new boolean[n][m][n][m];
		
		for(int i=0;i<n;i++) {
			String str=sc.next();
			for(int j=0;j<m;j++) {
				map[i][j]=str.charAt(j);
				
				if(map[i][j]=='O') {
					hole_x=i;
					hole_y=j;
				}
				
				if(map[i][j]=='R')
					Red=new Ball(i,j,0,0,0);
				
				if(map[i][j]=='B')
					Blue=new Ball(0,0,i,j,0);
			}
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<Ball> q=new LinkedList<>();
		q.add(new Ball(Red.rx,Red.ry,Blue.bx,Blue.by,1));
		visited[Red.rx][Red.ry][Blue.bx][Blue.ry]=true;
		
		while(!q.isEmpty()) {
			Ball p=q.poll();
			
			if(p.count>10) // 10번 이상 움직인 경우
				return -1;
			
			int rx=p.rx;
			int ry=p.ry;
			int bx=p.bx;
			int by=p.by;
			
			for(int i=0;i<4;i++) { // 상,하,좌,우 이동
				int nrx=rx;
				int nry=ry;
				int nbx=bx;
				int nby=by;
				
				boolean red_Hole=false; // 각 구슬이 구멍에 빠졌는지 확인하는 변수
				boolean blue_Hole=false;
				
				// 빨간 공을 벽에 닿을 때까지 굴리기
				while(map[nrx+dx[i]][nry+dy[i]]!='#') {
					nrx+=dx[i];
					nry+=dy[i];
					
					// 구멍에 빠진 경우
					if(nrx==hole_x&&nry==hole_y) {
						red_Hole=true;
						break;
					}
						
				}
				
				// 파란 공을 벽에 닿을 때까지 굴리기
				while(map[nbx+dx[i]][nby+dy[i]]!='#') {
					nbx+=dx[i];
					nby+=dy[i];
					
					// 구멍에 빠진 경우
					if(nbx==hole_x&&nby==hole_y) {
						blue_Hole=true;
						break;
					}
				}
				
				if(blue_Hole) // 파란색 공이 빠진 경우 -> 해당 경우의 수 고려 X
					continue;
				
				if(red_Hole&&!blue_Hole) // 빨간 공만 빠진 경우
					return p.count;
				
				if(nrx==nbx&&nry==nby) { // 두 공이 같은 위치로 굴러간 경우
					if(i==0) { // 위로 굴린 경우
						if(rx>bx)
							nrx-=dx[i];
						else
							nbx-=dx[i];
							
					}
					else if(i==1) { // 오른쪽으로 굴린 경우
						if(ry>by)
							nby-=dy[i];
						else
							nry-=dy[i];
					}
					else if(i==2) { // 아랫쪽으로 굴린 경우
						if(rx>bx)
							nbx-=dx[i];
						else
							nrx-=dx[i];
					}
					else { // 왼쪽으로 굴린 경우
						if(ry>by)
							nry-=dy[i];
						else
							nby-=dy[i];
					}
				}
				
				if(!visited[nrx][nry][nbx][nby]) { // 방문하지 않은 경우
					q.offer(new Ball(nrx,nry,nbx,nby,p.count+1)); // 이동 횟수 늘리고 큐에 삽입 
					visited[nrx][nry][nbx][nby]=true; // 방문 처리
				}
			}
		}
		return -1;
	}
}
