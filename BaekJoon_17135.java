import java.util.*;

class Point_17135{
	int x,y;
	
	public Point_17135(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_17135 {
	static int[][] map,copy;
	static int[] archer;
	static int n,m,d;
	static int max=Integer.MIN_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		d=sc.nextInt();
		
		archer=new int[m];
		for(int i=0;i<m;i++)
			archer[i]=i;
		
		map=new int[n][m];
		copy=new int[n][m];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				map[i][j]=sc.nextInt();
		
		int[] temp=new int[3];
		dfs(0,0,temp);
		
		System.out.println(max);
	}
	
	static void copymap() {
		for(int i=0;i<n;i++)
			copy[i]=Arrays.copyOf(map[i],m);
	}
	
	static void dfs(int depth, int start, int[] temp) {
		// 3명의 궁수를 배치했다면
		if(depth==3) {
			copymap();
			
			int count=0; // 제거 가능한 적의 수
			int turn=0; 
			Queue<Point_17135> q=new LinkedList<>(); // 제거할 적의 위치 저장
			
			/*
			 * 적들을 한 행씩 옮기는 것보다 궁수들을 한 행 위로 올리는 것이 더 간단함!
			 * turn이 증가하면서 궁수들의 행 한 칸씩 전진
			 */
			while(turn<n) {
				// 3명의 궁수 탐색
				for(int k=0;k<3;k++) {
					int x=n-turn; // 궁수의 열
					int y=temp[k]; // 궁수의 행
					
					int min=Integer.MAX_VALUE; // 적까지의 최소거리
					int minX=-1; // 적의 위치 저장
					int minY=-1;
					
					// 모든 적 탐색
					for(int i=n-1-turn;i>=0;i--) {
						for(int j=0;j<m;j++) {
							// 적이 있다면 거리 계산
							if(copy[i][j]==1) {
								int distance=Math.abs(i-x)+Math.abs(j-y);
								
								// 적과의 거리가 d 이하라면 공격 가능
								if(distance<=d) {
									// 적과의 거리가 가장 최소라면
									if(distance<min) {
										// 적의 정보 갱신
										min=distance;
										minX=i;
										minY=j;
									}
									// 최소거리를 가진 적이 둘 이상이라면
									else if(distance==min) {
										// 가장 왼쪽에 있는 적 정보 저장(minY가 작은 것이 더 왼쪽에 있는 적)
										if(minY>j) {
											minX=i;
											minY=j;
										}
									}
								}
							}
						}
					}
					// 모든 적을 탐색했다면 제거할 적의 위치 큐에 삽입
					if(minX!=-1&&minY!=-1) 
						q.offer(new Point_17135(minX,minY));
				}
				
				// 제거할 적의 맵 값을 0으로 변경, 제거한 적의 수 count
				while(!q.isEmpty()) {
					Point_17135 p=q.poll();
					
					/*
					 * 이때 여러 궁수가 하나의 적을 제거할 수 있기 때문에 
					 * 맵의 값이 궁수가 있는 곳인지(값이 1인지) 확인하고 죽인 궁수들의 수를 count해주어야 한다.
					 * 이미 앞선 궁수에 의해 제거된 적일수 있기 때문! 
					 */
					if(copy[p.x][p.y]==1) {
						copy[p.x][p.y]=0;
						count++;
					}
				}
				turn++; // 한 턴 증가 -> 궁수들 한 칸 위로 전진
			}
			
			max=Math.max(max,count); // 최댓값 갱신
			return;
		}
		
		// 궁수가 위치할 수 있는 m칸 중 3개의 칸에 궁수 배치 -> 백트래킹, 조합
		for(int i=start;i<archer.length;i++) {
			temp[depth]=archer[i];
			dfs(depth+1,i+1,temp);
		}
	}
}
