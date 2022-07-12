package com.my.hr.presentation;

import java.time.LocalDate;
import java.util.List;

import com.my.hr.domain.Laborer;
import com.my.hr.service.LaborerService;

public class LaborerIo {
	private LaborerService laborerService;
	private String menu;
	private int menuLength;
	
	public LaborerIo(LaborerService laborerService, List<String> menuItems) {
		this.laborerService = laborerService;
		this.menu = Console.menu(menuItems);
		this.menuLength = menuItems.size();
	}
	
	public void play() {
		int job = 0;
		while((job = choose(menu)) != Job.EXIT) {
			switch(job) {
			case Job.LIST: listLaborers(); break;
			case Job.ADD: addLaborer(); break;
			case Job.FIX: fixLaborer(); break;
			case Job.DEL: delLaborer();
			}
		}
	}
	
	private int choose(String menu) {
		boolean isGood = false;
		int choice = 0;
		
		do {
			choice = Console.inNum(menu);
			if(choice < 0 || choice > menuLength)
				Console.err("메뉴 번호를 입력하세요.");
			else isGood = true;
		} while(!isGood);
		
		return choice;
	}
	
	private void listLaborers() {
		List<Laborer> laborers = laborerService.getLaborers();
		
		System.out.println("ID 이름     입사일");
		System.out.println("------------------");
		
		if(laborers.size() > 0)
			laborers.forEach(laborer -> Console.info(laborer));
		else Console.info("노동자가 없습니다.");
	}
	
	private void addLaborer() {
		String laborerName = Console.inStr("노동자명을 입력하세요.", 5);
		
		if(!laborerName.equals("0")) {
			LocalDate hireDate = Console.inDate("입사일을 입력하세요.");
			laborerService.addLaborer(laborerName, hireDate);
			Console.info("노동자를 추가했습니다.");
		} else Console.info("추가 취소합니다.");
	}
	
	private void fixLaborer() {
		if(laborerService.getLaborers().size() > 0) {
			int laborerId = getLaborer("수정");
			if(laborerId > 0) {
				String laborerName = Console.inStr("노동자명을 입력하세요.", 5);
				LocalDate hireDate = Console.inDate("입사일을 입력하세요.");
				
				laborerService.fixLaborer(new Laborer(laborerId, laborerName, hireDate));
				Console.info("노동자를 수정했습니다.");
			}
		} else Console.info("노동자가 없습니다.");
	}
	
	private void delLaborer() {
		if(laborerService.getLaborers().size() > 0) {
			int laborerId = getLaborer("삭제");
			if(laborerId > 0) {
				laborerService.delLaborer(laborerId);
				Console.info("노동자를 삭제했습니다.");
			}
		} else Console.info("노동자가 없습니다.");
	}
	
	private int getLaborer(String job) {
		Laborer laborer = null;
		int laborerId = 0;
		
		do {
			laborerId = Console.inNum("노동자ID를 입력하세요.");
			if(laborerId == 0) {
				Console.info(job + " 취소합니다.");
				return 0;
			}
			
			laborer = laborerService.getLaborer(laborerId);
			if(laborer == null) Console.err("해당 ID의 노동자가 없습니다.");			
		} while(laborer == null);
		
		return laborerId;
	}
}
