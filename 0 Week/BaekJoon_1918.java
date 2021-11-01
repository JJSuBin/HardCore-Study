import java.util.*;

public class BaekJoon_1918 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String str=sc.next();
		Stack<Character> stack=new Stack<>();
		
		String result="";
		for(int i=0;i<str.length();i++) {
			char temp=str.charAt(i);
			
			if(temp>='A'&&temp<='Z') // 숫자라면 그대로 결과식에 넣기
				result+=temp;
			
			else {
				if(temp=='(') // 여는 괄호 스택에 삽입
					stack.push(temp);
				else if(temp==')') { // 닫는 괄호가 나온다면
					while(!stack.isEmpty()&&stack.peek()!='(') // 여는 괄호가 나올때까지 
						result+=stack.pop(); // 모는 연산자 꺼내기
					
					if(!stack.isEmpty()) stack.pop(); // 여는 괄호는 삭제해주기, 출력 X
				}
				else { // 연산자인 경우
					/*
					 * 현재 연산자보다 스택에 이쓴 연잔자의 우선순위가 높거나 같다면 스택에 있는 연산자부터 출력해야 하므로
					 * 스택에 자신의 우선순위보다 낮은 연산자만 있도록 모두 꺼내준다.
					 */
					while(!stack.isEmpty()&&check(stack.peek())>=check(temp))
						result+=stack.pop();
					
					stack.push(temp); // 이후 연산자 스택에 삽입
				}
			}
		}
		
		while(!stack.isEmpty()) // 스택에 남아있는 연산자들 모두 꺼내주기
			result+=stack.pop();
		
		System.out.println(result);
	}
	
	static int check(char temp) {
		if(temp=='*'||temp=='/')
			return 2; // 높은 우선순위
		else if(temp=='+'||temp=='-')
			return 1;
		/*
		 * 스택 안에는 '('도 삽입될 수 있다. '('는 꺼내져서는 안되기 때문에 제일 낮은 우선순위를 갖게 해주어야 한다!
		 * 해당 과정을 빼먹으면 괄호가 있는 식에서 (도 스택에서 꺼내져 같이 출력됨
		 */
		return 0;
	}
}
