package com.koreaIT.java.BAM;

import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.Controller;
import com.koreaIT.java.BAM.controller.MemberController;

public class App {

	App() {

	}

	public void start() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController( sc);

		articleController.makeTestData();
		memberController.makeTestData();
		
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
			
			switch (controllerName + " " + actionMethodName) {
			case "article write":
			case "article modigy":
			case "article delete":
			case "member logout":
			case "member whoami":
				if (Controller.isLogined() == false) {  // 로그아웃 상태면 막아야 한다.
					System.out.println("현재 로그아웃 상태야. 로그인 후 이용해");
					return;
				}
				break;
			}
			
			switch (controllerName + " " + actionMethodName) {
			case "member login":
			case "member join":
				if (Controller.isLogined()) {  // 로그아웃 상태면 막아야 한다.
					System.out.println("이미 누가 로그인 했다. 로그아웃 후 이용해");
					return;
				}
				break;
			}
			
			controller.doAction(actionMethodName, command);

		}

		System.out.println("== 프로그램 종료 ==");

		sc.close();
	}
}