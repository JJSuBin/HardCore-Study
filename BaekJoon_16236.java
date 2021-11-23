import java.util.*;

class Point_16236 {
	int x,y;
	
	public Point_16236(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_16236 {
	static int[][] map;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static int n,x,y,size=2,time=0,ate=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		map=new int[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j]=sc.nextInt();
				
				// 초기 상어 위치
				if(map[i][j]==9) {
					x=i;
					y=j;
					// 상어가 있던 위치는 이후 이동 가능한 칸이기 때문에 0으로 초기화(물고기는 X)
					map[i][j]=0;
				}
			}
		}
		
		while(true) {
			int[] info=find_fish(distance());
			
			x=info[0];
			y=info[1];
			time+=info[2];
			
			map[x][y]=0; // 상어가 이동해서 물고기를 먹은 칸 비우기
			ate+=1; // 먹은 물고기의 수 증가
			
			if(ate==size) { // 상어가 자신의 크기만큼 물고기를 먹었다면 크기 증가
				size++;
				ate=0;
			}
		}
	}
	
	// 상어의 위치를 기준으로 각 칸까지의 최단거리를 구하는 함수
	static int[][] distance() {
		 int[][] distance=new int[n][n];
		 
		 // 최단거리 저장 배열 -1로 초기화 = 상어가 이동할 수 없는 칸은 -1
		 for(int i=0;i<n;i++)
			 Arrays.fill(distance[i],-1);
		 
		 Queue<Point_16236> q=new LinkedList<>();
		 q.offer(new Point_16236(x,y)); // 상어의 위치 삽입
		 distance[x][y]=0;
		 
		 while(!q.isEmpty()) {
			 Point_16236 p=q.poll();
			 
			 for(int i=0;i<4;i++) {
				 int nx=p.x+dx[i];
				 int ny=p.y+dy[i];
				 
				 if(nx>=0&&ny>=0&&nx<n&&ny<n) {
					 /*
					  * 방문여부 체크는 distance 배열 값이 -1인지로 확인 가능
					  * 상어는 이동할 칸에 있는 물고기가 자신의 크기보다 크다면 이동 불가 
					  */
					if(distance[nx][ny]==-1&&map[nx][ny]<=size) {
						distance[nx][ny]=distance[p.x][p.y]+1;
						q.offer(new Point_16236(nx,ny));
					}
				 }
			 }
		 }
		 return distance;
	}
	
	// 최단거리 테이블을 사용하여 먹을 수 있는 물고기가 있는 칸으로 이동
	static int[] find_fish(int[][] distance) {
		int[] info=new int[3]; // 이동할 상어의 x,y와 소요시간을 저장하는 배열
		
		int next_x=0;
		int next_y=0;
		int dis=Integer.MAX_VALUE;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				// 상어가 이동할 수 있는 칸이면서, 칸에 물고기가 있고, 상어보다 크기가 작다면 이동
				if(distance[i][j]!=-1&&map[i][j]<size&&map[i][j]>=1) {
					if(dis>distance[i][j]) {
						next_x=i;
						next_y=j;
						dis=distance[i][j];
					}
				}
			}
		}
		
		// 소요시간이 초기값 그대로라면 이동할 수 있는 칸이 없다는 의미이므로 종료
		if(dis==Integer.MAX_VALUE) {
			System.out.println(time);
			System.exit(0);
		}
		
		info[0]=next_x;
		info[1]=next_y;
		info[2]=dis;
		
		return info;
	}
}
