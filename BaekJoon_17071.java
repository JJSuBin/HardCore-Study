import java.util.*;

public class BaekJoon_17071 {
	static int n,k;
	static boolean[][] visited=new boolean[2][500001]; // 짝수초와 홀수초를 나눠 방문처리를 해주어야 한다.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		k=sc.nextInt();
		
		if(n==k)
			System.out.println(0);
		else 
			System.out.println(move(n));
	}
	static int move(int start) {
		Queue<Integer> q=new LinkedList<>();
		q.offer(start);
		visited[0][start]=true; // 첫 위치 방문처리
		
		int time=0;
		int size;
		while(!q.isEmpty()) {
			size=q.size();
			
			if(k>500000) // 범위를 벗어나는 경우
				return -1;
			
			if(visited[time%2][k]) // 만난 경우(짝수, 홀수)
				return time;
			
			// 한 턴씩 검사하기 위해 현재 q의 크기만큼만 반복
			for(int i=0;i<size;i++) {
				int now=q.poll();
				int nexttime=(time+1)%2; // 다음 이동 시간의 짝수 홀수 계산
				int next;
				
				// 수빈이가 이동할 수 있는 3가지 경우 계산
				for(int j=0;j<3;j++) {
					// j가 0~2인 경우 모두를 검사해야 하기 때문에 switch문에 break 작성하면 안됌!
					switch(j) {
					
					/*
					 * 수빈이가 갈 수 있는 칸의 값을 계산 후 이동할 칸이 이동 가능 범위 내에
					 * 있고, 아직 방문하지 않은 곳이라면 해당 위치를 방문처리, 큐에 삽입한다.
					 */
					case 1:
						next=now-1; 
						if(next>=0&&!visited[nexttime][next]) {
							visited[nexttime][next]=true;
							q.offer(next);
						}
					
					case 2:
						next=now+1;
						if(next<=500000&&!visited[nexttime][next]) {
							visited[nexttime][next]=true;
							q.offer(next);
						}
						
					case 3:
						next=now*2;
						if(next<=500000&&!visited[nexttime][next]) {
							visited[nexttime][next]=true;
							q.offer(next);
						}
					}
				}
			}
			
			time++; // 시간 증가
			k+=time;
		}
		return -1;
	}
	
}
