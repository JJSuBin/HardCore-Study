import java.util.*;

class Point {
	int x,y;
	
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

class info {
	int time;
	char dir;
	
	public info(int time, char dir) {
		this.time=time;
		this.dir=dir;
	}
}

public class BaekJoon_3190 {
	static int n,k,l;
	static int[][] map;
	static int[] dx= {0,1,0,-1}; // 우->하->좌->상 순서
	static int[] dy= {1,0,-1,0}; //
	static int x=1,y=1,dir=0; // 초기 뱀의 정보
	static ArrayList<info> list=new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		n=sc.nextInt();
		k=sc.nextInt(); // 사과의 개수
		
		map=new int[n+1][n+1];
		for(int i=0;i<k;i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			
			map[x][y]=1; // 사과가 있는 곳은 1로 표시 
		}
		
		l=sc.nextInt();
		for(int i=0;i<l;i++) {
			int t=sc.nextInt();
			char d=sc.next().charAt(0);
			
			list.add(new info(t,d));
		}
		
		System.out.println(move_Dummy());
	}
	
	static void turn(char c) { // c 방향으로 회전
		if(c=='D') // 오른쪽으로 회전 -> dx,dy 배열에서 인덱스가 증가하는 상황
			dir=(dir+1)%4;
		else // 왼쪽으로 회전 -> dx, dy 배열에서 인덱스가 감소하는 상황
			dir=(dir-1<0)?3:dir-1;
	}
	
	static int move_Dummy() {
		Queue<Point> q=new LinkedList<>(); // 뱀의 위치가 저장되는 큐
		q.offer(new Point(x,y)); // 시작 지점 큐에 삽입
		map[x][y]=2; // 뱀이 있는 위치는 2로 표시
		
		int time=0; // 뱀의 이동 시간
		int turn=0; // 방향 변환 횟수 l개 중 수행중인 index
		while(!q.isEmpty()) {
			int nx=x+dx[dir]; // 현재 방향으로 머리를 다음칸에 위치시킨다.
			int ny=y+dy[dir];
			
			// 맵을 벗어나지 않고 자신의 몸과 부딪히지 않으면 게임 진행
			if(nx>0&&ny>0&&nx<=n&ny<=n&&map[nx][ny]!=2) {
				if(map[nx][ny]==0) { // 이동한 칸에 사과가 없는 경우
					Point tail=q.poll(); // 꼬리 줄이기
					map[tail.x][tail.y]=0;		
				}
				
				// 새로운 몸 추가
				q.offer(new Point(nx,ny));
				map[nx][ny]=2;
			}
			
			else { // 맵을 벗어나거나 자신의 몸과 부딪힌 경우
				time++;
				break;
			}
			
			x=nx;
			y=ny;
			time++;
			
			// turn이 l보다 작다는 것은 방향 변환 횟수 l개를 아직 다 수행하지 않았다는 의미
			if(turn<l&&time==list.get(turn).time) {
				turn(list.get(turn).dir);
				turn++;
			}
		}
		
		return time;
	}
}
