import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Article> articles = new ArrayList<Article>();
	static List<Member> members = new ArrayList<Member>();

	public static void main(String[] args) {

		System.out.println("== 프로그램 시작 ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 5;
		int lastMemberId = 0;

		while (true) {

			System.out.printf("명령어) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("너 명령어 입력 안했어");
				continue;
			}

			if (command.equals("exit")) {
				break;
			}

			else if (command.equals("member join")) {
				int id = lastMemberId + 1;
				String regDate = Util.getNow();
				String loginId = null;
				String loginPw = null;
				String loginPwConfirm = null;
				String name = null;
				
				while (true) {
					System.out.printf("로그인 아이디 : ");
					loginId = sc.nextLine();

					if (loginId.length() == 0) {
						System.out.println("아이디 입력해라");
						continue;

					} else if (isjoinableLoginId(loginId) == false) {
						System.out.println("이미 쓰는 아이디야");
						continue;
					}

					break;
				}
				
				while (true) {
					System.out.printf("로그인 비밀번호 : ");
					loginPw = sc.nextLine();
					
					if (loginPw.length() == 0) {
						System.out.println("비밀번호 입력해라");
						continue;
					}
					
					while (true) {
						System.out.printf("로그인 비밀번호 확인: ");
						loginPwConfirm = sc.nextLine();
						
						if (loginPwConfirm.length() == 0) {
							System.out.println("비밀번호 입력해라");
							continue;
						}
						break;						
					}
					
					if (loginPw.equals(loginPwConfirm) == false) {
						System.out.println("비밀번호 확인해");
						continue;
					}
					break;
					
				}
				
				while(true) {
					System.out.printf("이름 : ");
					name = sc.nextLine();
					
					if (name.length() == 0) {
						System.out.println("이름 입력해라");
						continue;
					}
					break;
				}

				Member member = new Member(id, regDate, regDate, loginId, loginPw, name);
				members.add(member);

				System.out.printf("%d번글이 생성되었습니다.\n", id);
				lastMemberId++;
			}

			else if (command.startsWith("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}
				
				String searchKeyword = command.substring("article list".length()).trim();

				System.out.println("searchKeyword : " + searchKeyword);

				List<Article> forPrintArticles = articles;

				if (searchKeyword.length() > 0) {
					forPrintArticles = new ArrayList<Article>();

					for (Article article : articles) {
						if (article.title.contains("searchKeyword")) {
							forPrintArticles.add(article);
						}
					}
					if (forPrintArticles.size() == 0) {
						System.out.println("검색 결과 없어");
						continue;
					}
				} else {
					System.out.println("검색어가 없어");

				}

				System.out.println("번호    /    제목    /    조회    ");
				for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf(" %4d   /   %5s   /   %4d   \n", article.id, article.title, article.hit);
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

				Article foundArticle = gatArticleById(id); // : 함수 실행 (return type)

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 없어\n", id);
					continue;
				}

				foundArticle.hit++;

				System.out.println("번호 : " + foundArticle.id);
				System.out.println("작성날짜 : " + foundArticle.regDate);
				System.out.println("수정날짜 : " + foundArticle.updateDate);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);
				System.out.println("조회수 : " + foundArticle.hit);

			} else if (command.startsWith("article modify")) {

				String[] commandDiv = command.split(" ");

				int id = Integer.parseInt(commandDiv[2]);

				Article foundArticle = gatArticleById(id);

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

				int foundIndex = getArticleIndexById(id); // (4) 0이상의 정수나 -1이 남는다.

				if (foundIndex == -1) { // (5) -1이면
					System.out.printf("%d번 게시물은 없어\n", id); // (6) 여기에 들어간다.
					continue; // (7) -1이 아니면 아래로 계속 내려갈 것이다.
				}

				articles.remove(foundIndex); // (8) 0이상의 정수는 foundindex에 들어가서 덮어 씌운다.
				System.out.println(id + "번 글을 삭제했어");

			} else {
				System.out.println("존재하지 않는 명령어입니다");
				continue;
			}
		}

		System.out.println(" == 프로그램 종료 ==");

		sc.close();

	}

	private static boolean isjoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private static int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
//			if (member.loginId == loginId) {         <- 이건 안됨, 자바 문자열 비교 equals 검색
			if (member.loginId.equals(loginId)) {
				return i;
			}
		}

		return -1;
	}

	private static int getArticleIndexById(int id) {

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.id == id) {
				return i; // (2) 실제의 i값을 남기던가, -1을 남기던가 둘 중 하나여야 한다.
			}
		}

		return -1; // (1) 없다의 경우를 0이아닌 -1로 하기로함.
	}

	private static Article gatArticleById(int id) {

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.id == id) {
				return article;
			}
		}

		return null;
	}

	private static void makeTestData() {
		System.out.println("테스트를 위한 데이터 3개 생성 완료 ");
		articles.add(new Article(1, Util.getNow(), Util.getNow(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNow(), Util.getNow(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNow(), Util.getNow(), "제목3", "내용3", 33));
		articles.add(new Article(4, Util.getNow(), Util.getNow(), "제목4", "내용4", 44));
		articles.add(new Article(5, Util.getNow(), Util.getNow(), "제목5", "내용5", 55));
	}
}

class Article {
	int id;
	String regDate;
	String updateDate;
	String title;
	String body;
	int hit;

	Article(int id, String regDate, String updateDate, String title, String body) {
		this(id, regDate, updateDate, title, body, 0);
	}

	Article(int id, String regDate, String updateDate, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}
}

class Member {
	int id;
	String regDate;
	String updateDate;
	String loginId;
	String loginPw;
	String name;

	Member(int id, String regDate, String updateDate, String loginId, String loginPw, String name) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
	}
}