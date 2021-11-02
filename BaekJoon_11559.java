import java.util.*;

class Point_11559 {
	int x,y;
	
	public Point_11559(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_11559 {
	static char[][] map=new char[12][6];
	static boolean[][] visited;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static int time=0,count=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		for(int i=0;i<12;i++) {
			String str=sc.next();
			for(int j=0;j<6;j++) {
				map[i][j]=str.charAt(j);
			}
		}
		
		while(true) {
			visited=new boolean[12][6];
			
			boolean flag=false; // 수행될 연산이 있는지 체크하는 변수
			
			for(int i=0;i<12;i++) {
				for(int j=0;j<6;j++) {
					if(!visited[i][j]&&map[i][j]!='.') {
						count=1; // 자신을 포함해 주위에 같은 색의 뿌요가 몇개 있는지 count하는 변수
						
						if(find_puyo(i,j,map[i][j])) { // 제거할 뿌요가 있다면(주위에 같은 뿌요 4개 이상) 
							flag=true; // 변수 값 변경
							remove_puyo(i,j,map[i][j]); // 뿌요 제거
						}
					}
				}
			}
			
			if(flag) time++;
			else break;
			
			while(true) {
				boolean check=down_puyo(); // 내려야할 뿌요가 있는지 확인
				
				if(!check) break; // 내려야할 뿌요가 없다는 것은 맵이 비었다는 것 -> 종료
			}
		}
		
		System.out.println(time);
	}
	
	// 시작점 (x,y)의 색 color와 같은 색인 뿌요를 없애주는 함수
	static void remove_puyo(int x, int y, char color) {
		Queue<Point_11559> q=new LinkedList<>();
		q.offer(new Point_11559(x,y));
		
		while(!q.isEmpty()) {
			Point_11559 p=q.poll();
			
			for(int i=0;i<4;i++) {
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				
				if(nx>=0&&ny>=0&&nx<12&&ny<6) {
					// find_puyo 함수에서 이미 방문처리 됐으므로 방문된 칸에서 같은 색의 뿌요를 없애야한다.
					if(visited[nx][ny]&&map[nx][ny]==color) {
						map[nx][ny]='.';
						q.offer(new Point_11559(nx,ny));
					}
				}
			}
		}
	}
	
	// 시작점 (x,y)의 색 color와 같은 색인 뿌요가 주위에 4개 이상인지 확인하는 변수 
	static boolean find_puyo(int x, int y, char color) {
		visited[x][y]=true;
		
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			if(nx>=0&&ny>=0&&nx<12&&ny<6) {
				if(!visited[nx][ny]&&map[nx][ny]==color) {
					count++;
					find_puyo(nx,ny,color);
				}
			}
		}
		
		if(count>=4) return true; // 4개 이상이라면 true return 
		else return false;	
	}
	
	// 뿌요 내리는 함수
	static boolean down_puyo() {
		boolean check=false; // 연산이 수행됐는지 확인하는 변수
		
		// 아래서 위의 뿌요를 잡아당기는 방식으로 생각
		for(int i=11;i>=1;i--) {
			for(int j=5;j>=0;j--) {
				// 뿌요와 빈 공간을 swap하면서 위치 바꾸기
				if(map[i][j]=='.'&&map[i-1][j]!='.') {
					map[i][j]=map[i-1][j];
					map[i-1][j]='.';
					check=true;
				}
			}
		}
		return check;
	}
}
