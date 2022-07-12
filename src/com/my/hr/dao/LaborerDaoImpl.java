package com.my.hr.dao;

import java.time.LocalDate;
import java.util.List;

import com.my.hr.domain.Laborer;

public class LaborerDaoImpl implements LaborerDao {
	private List<Laborer> laborers;
	private int laborerIdSeq;
	
	public LaborerDaoImpl(List<Laborer> laborers) {
		this.laborers = laborers;
		this.laborerIdSeq = 1;
	}
	
	@Override
	public List<Laborer> selectLaborers() {
		return laborers;
	}
	
	@Override
	public Laborer selectLaborer(int laborerId) {
		Laborer laborer = null;
		
		for(Laborer laborerTmp: laborers) {
			if(laborerTmp.getLaborerId() == laborerId) {
				laborer = laborerTmp;
				break;
			}
		}
		
		return laborer;
	}
	
	@Override
	public void insertLaborer(String laborerName, LocalDate hireDate) {
		laborers.add(new Laborer(laborerIdSeq++, laborerName, hireDate));
	}
	
	@Override
	public void updateLaborer(Laborer laborerTmp) {
		laborers.forEach(laborer -> {
			if(laborer.getLaborerId() == laborerTmp.getLaborerId()) {
				laborer.setName(laborerTmp.getName());
				laborer.setHireDate(laborerTmp.getHireDate());
			}
		});
	}
	
	@Override
	public void deleteLaborer(int laborerId) {
		Laborer laborer = null;
		laborer = selectLaborer(laborerId);
		if(laborer != null) laborers.remove(laborer);
	}
}
