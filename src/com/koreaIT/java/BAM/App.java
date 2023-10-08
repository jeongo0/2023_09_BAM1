package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.MemberController;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;

public class App {

	List<Article> articles;
	List<Member> members;

	App() {
		articles = new ArrayList<Article>();
		members = new ArrayList<Member>();
	}

	public void start() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		articleController.makeTestData();

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

			if (command.equals("member join")) {
				memberController.doJoin();
			} else if (command.startsWith("article list")) {
				articleController.showList(command);
			} else if (command.equals("article write")) {
				articleController.doWrite();
			} else if (command.startsWith("article detail")) {
				articleController.showDetail(command);
			} else if (command.startsWith("article modify")) {
				articleController.doModify(command);
			} else if (command.startsWith("article delete")) {
				articleController.doDelete(command);
			} else {
				System.out.println("존재하지 않는 명령어입니다");
				continue;
			}
		}

		System.out.println("== 프로그램 종료 ==");

		sc.close();
	}
}