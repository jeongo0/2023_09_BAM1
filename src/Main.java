import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		System.out.println(" == 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;
		
		List<Article> articles = new ArrayList<Article>();

		while (true) {
			System.out.printf("명령어)");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("너 명령어 입력 안했어");
				continue;
			}

			if (command.equals("exit")) {
				break;
			}

			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
				}
				else {
					System.out.println("번호    /    제목    ");
					for(int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf(" %d   /   %s   \n", article.id, article.title);
					}
				}

			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body);
				articles.add(article);
				
				System.out.println("입력된 제목 : " + title);
				System.out.println("입력된 내용 : " + body);

				System.out.printf("%d번글이 생성되었습니다.\n", id);
				lastArticleId++;
				
			} else if (command.startsWith("article detail")) {
				String[] commandDiv = command.split(" "); 
						
				System.out.println("commandDiv[0]");
				System.out.println("commandDiv[1]");
				System.out.println("commandDiv[2]");
				
				int id = Integer.parseInt(commandDiv[2]);

				System.out.printf("%d번 게시물은 없어\n", id);
				
			} else {
				System.out.println("존재하지 않는 명령어입니다");
				continue;
			}
		}

		System.out.println(" == 프로그램 종료 ==");
		sc.close();
	}
}

class Article {
	int id;
	String title;
	String body;

	Article(int id, String title, String body) { // <- 생성자(매개변수)
		this.id = id;
		this.title = title;
		this.body = body;
	}
}