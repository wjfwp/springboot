package com.simple.basic.memo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.basic.command.MemoVO;

@Service("memoService")
public class MemoServiceImpl implements MemoService {
	
	@Autowired
	MemoMapper memoMapper;

	@Override
	public void memoRegist(MemoVO vo) {
		memoMapper.memoRegist(vo);
	}

	@Override
	public ArrayList<MemoVO> getMemo() {
		return memoMapper.getMemo();
	}

	

}
