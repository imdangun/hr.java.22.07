package com.my.hr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.my.hr.dao.LaborerDao;
import com.my.hr.dao.LaborerDaoImpl;
import com.my.hr.domain.Laborer;
import com.my.hr.presentation.Console;
import com.my.hr.presentation.LaborerIo;
import com.my.hr.service.LaborerService;
import com.my.hr.service.LaborerServiceImpl;

public class Main {
	public static void main(String[] args) {
		List<String> menuItems = Arrays.asList("목록", "추가", "수정", "삭제");
		List<Laborer> laborers = new ArrayList<>();		
		
		LaborerDao laborerDao = new LaborerDaoImpl(laborers);
		LaborerService laborerService = new LaborerServiceImpl(laborerDao);
		LaborerIo laborerIo = new LaborerIo(laborerService, menuItems);
		
		laborerIo.play();
		Console.info("끝..");
	}
}
