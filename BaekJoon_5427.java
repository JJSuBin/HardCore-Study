import java.util.*;

class Point_5427 {
	int x,y,count;
	
	public Point_5427(int x, int y, int count) {
		this.x=x;
		this.y=y;
		this.count=count;
	}
	public Point_5427(int x, int y) {
		this.x=x;
		this.y=y;
	}
}
public class BaekJoon_5427 {
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static char[][] map;
	static boolean[][] visited;
	static Queue<Point_5427> human;
	static Queue<Point_5427> fire;
	static int w,h,result;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		
		while(t-->0) {
			w=sc.nextInt();
			h=sc.nextInt();
			
			map=new char[h][w];
			visited=new boolean[h][w];
			
			human=new LinkedList<>();
			fire=new LinkedList<>();
			
			// 맵 정보 입력 받기
			for(int i=0;i<h;i++) {
				String str=sc.next();
				for(int j=0;j<w;j++) {
					map[i][j]=str.charAt(j);
					
					if(map[i][j]=='@')
						human.add(new Point_5427(i,j,0));
					
					if(map[i][j]=='*')
						fire.add(new Point_5427(i,j));
				}
			}
			
			result=0;
			move();
			
			if(result==0)
				System.out.println("IMPOSSIBLE");
			else
				System.out.println(result);
		}
	}
	
	static void move() {
		while(!human.isEmpty()) {
			// 상근이는 불이 붙으려는 칸으로 이동할 수 없기 때문에 불을 먼저 이동시킨다.
			int size=fire.size();
			
			while(size-->0) {
				Point_5427 p=fire.poll();
				
				for(int i=0;i<4;i++) {
					int nx=p.x+dx[i];
					int ny=p.y+dy[i];
					
					if(nx<0||ny<0||nx>=h||ny>=w) 
						continue;
					
					// 불은 벽 제외 모든 칸 이동 가능(이미 불이 있는 곳은 상관 X)
					if(map[nx][ny]=='.'||map[nx][ny]=='@') {
						fire.add(new Point_5427(nx,ny));
						map[nx][ny]='*';
					}
				}
			}
			
			// 상근이 이동
			size=human.size();
			
			while(size-->0) {
				Point_5427 p=human.poll();
				
				for(int i=0;i<4;i++) {
					int nx=p.x+dx[i];
					int ny=p.y+dy[i];
					
					// 탈출할 수 있는 경우를 찾았다면 이동횟수 저장 후 return
					if(nx<0||ny<0||nx>=h||ny>=w) {
						result=p.count+1;
						return;
					}
					
					// 상근이는 빈 공간으로만 이동 가능
					if(map[nx][ny]=='.') {
						human.add(new Point_5427(nx,ny,p.count+1));
						map[nx][ny]='@';
					}
				}
			}
		}
	}
}
