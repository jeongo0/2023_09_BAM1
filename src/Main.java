import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		System.out.println(" == 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		
		while (true) {
			System.out.printf("명령어)");
			String command = sc.nextLine();
			
			if (command.length() == 0) {
				System.out.println("너 명령어 입력 안했어");
				continue;
			}
			
			if (command.equals("exit")) {
				break;
			}
			
			if (command.equals("article list")) {
				System.out.println("게시글이 없습니다");
				
			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				System.out.println("입력된 제목 : " + title );
				System.out.println("입력된 내용 : " + body );
				
				System.out.printf("%d번글이 생성되었습니다.\n", lastArticleId + 1);
				lastArticleId++;
				
			} else {
				System.out.println("존재하지 않는 명령어입니다");
				continue;
			}
		}

		System.out.println(" == 프로그램 종료 ==");

		sc.close();
	}
}