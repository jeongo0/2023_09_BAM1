package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private List<Member> members;
	private Scanner sc;
	private String actionMethodName;
	private String command;
	
	boolean isLogined = false;

	int lastMemberId = 3;

	public MemberController(Scanner sc) {
		this.members = new ArrayList<Member>();
		this.sc = sc;
	}

	public void doAction(String actionMethodName, String command) {
		this.actionMethodName = actionMethodName;
		this.command = command;

		switch (actionMethodName) {
		case "join":
			if (isLogined()) {
				System.out.println("이미 누가 로그인 했다.");
				return;
			}
			doJoin();
			break;
		case "login":
			if (isLogined()) {
				System.out.println("이미 누가 로그인 했다.");
				return;
			}
			doLogin();
			break;
		case "logout":
			if(isLogined == false) {
				System.out.println("현재 로그아웃 상태");
				return;
			}
			doLogout();
			break;
		case "whoami":
			if(isLogined == false) {
				System.out.println("현재 로그아웃 상태");
				return;
			}
			showWhoami();
			break;
		default:
			System.out.println("그런 세부기능은 없어");
			break;
		}
	}

	private void showWhoami() {
		
		System.out.println("== 현재 로그인 한 회원 정보 ==");
		System.out.println("가입일 : " + loginedMember.regDate);
		System.out.println("로그인 아이디 : " + loginedMember.loginId);
		System.out.println("이름 : " + loginedMember.name);
	}

	private void doLogout() {
	
		isLogined = false;
		loginedMember = null; 
		
		System.out.println(" 로그아웃 ");
		
	}

	private void doLogin() {
		
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();

		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("너같은 회원은 없어");
			return;
		}
		
		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비번 틀림");
			return;
		}
		
		isLogined = true;
		loginedMember = member; 
		
		System.out.println("로그인 성공");
	}

	public void doJoin() {
		
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

		while (true) {
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

		System.out.printf("%d번 회원이 가입되었습니다.\n", id);
		lastMemberId++;
	}
	
	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);
	}

	private boolean isjoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 회원 데이터 데이터 3개 생성 완료 ");
		members.add(new Member(1, Util.getNow(), Util.getNow(), "test1", "test1", "회원1"));
		members.add(new Member(2, Util.getNow(), Util.getNow(), "test2", "test2", "회원2"));
		members.add(new Member(3, Util.getNow(), Util.getNow(), "test3", "test3", "회원3"));

	}

}