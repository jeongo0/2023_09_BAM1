import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		System.out.println("== 프로그램 시작 ==");

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
				} else {
					System.out.println("번호    /    제목    ");
					for (int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf(" %d   /   %s   \n", article.id, article.title);
					}
				}

			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				String regDate = Util.getNow();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, regDate, title, body);
				articles.add(article);

				System.out.printf("%d번글이 생성되었습니다.\n", id);
				lastArticleId++;

			} else if (command.startsWith("article detail")) {

				String[] commandDiv = command.split(" ");

				int id = Integer.parseInt(commandDiv[2]);

				boolean found = false; // 못찾았을 때의 가정을 나타낸다.

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						found = true;
						System.out.println("번호 : " + article.id);
						System.out.println("작성날짜 : " + article.regDate);
						System.out.println("수정날짜 : " + article.updateDate);
						System.out.println("제목 : " + article.id);
						System.out.println("내용 : " + article.id);
						break;
					}
				}

				if (found == false) {
					System.out.printf("%d번 게시물은 없어\n", id);
				}

			}  else if (command.startsWith("article modify")) {

				String[] commandDiv = command.split(" ");

				int id = Integer.parseInt(commandDiv[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 없어\n", id);
					continue;
				}
				
				System.out.printf("제목 : ");
				String newTitle = sc.nextLine();
				System.out.printf("내용 : ");
				String newBody = sc.nextLine();	
				
				
				String updateDate = Util.getNow();
				foundArticle.title = newTitle;
				foundArticle.body = newBody;
				foundArticle.updateDate = updateDate;
						
			} else if (command.startsWith("article delete")) {

				String[] commandDiv = command.split(" ");

				int id = Integer.parseInt(commandDiv[2]);

				int foundIndex = -1; // 없다고 가정

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundIndex = i;
						break;
					}
				}
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 없어\n", id);
					continue;
				}

				articles.remove(foundIndex);
				System.out.println(id + "번 글을 삭제했어");

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
	String regDate;
	String updateDate;
	String title;
	String body;

	Article(int id, String title, String body) { // <- 생성자(매개변수)
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public Article(int id, String regDate, String updateDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}
}