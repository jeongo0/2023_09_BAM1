package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.Controller;
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
		
		Controller controller;
		
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

			String[] commandDiv = command.split(" ");
			
			String controllerName = commandDiv[0];
			if(commandDiv.length == 1) {
				System.out.println("명령어 확인해");
				continue;
			}
			
			String actionMethodName = commandDiv[1];
			
			
			controller = null;
			
			if(controllerName.equals("article")) {
				controller = articleController;
			}
			else if (controllerName.equals("member")) {
				controller = memberController; 
			}
			else {
				System.out.println("존재하지 않는 기능이야");
				continue;
			}
			
			controller.doAction(actionMethodName,command);

		}

		System.out.println("== 프로그램 종료 ==");

		sc.close();
	}
}