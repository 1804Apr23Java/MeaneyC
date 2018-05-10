package com.revature.dao;

import java.util.List;

import com.revature.domain.Cave;

public interface CaveDao {
	
	public List<Cave> getCaves();
	public Cave getCaveById(int id);
	public void AddCave(String caveName, int maxBears);
	public void UpdateCave(int id, String caveName, int maxBears);
	public void DeleteCavebyId(int id);

}
