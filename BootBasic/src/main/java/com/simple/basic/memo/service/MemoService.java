package com.simple.basic.memo.service;

import java.util.ArrayList;

import com.simple.basic.command.MemoVO;

public interface MemoService {
	public void memoRegist(MemoVO vo);
	public ArrayList<MemoVO> getMemo();
}
