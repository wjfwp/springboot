package com.coding404.myweb.product.service;

import java.util.ArrayList;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

public interface ProductService {
	
	public int productRegist(ProductVO vo);
//	public ArrayList<ProductVO> getList(String writer); //조회
	public ArrayList<ProductVO> getList(String writer, Criteria cri); //조회
	public int getTotal(String writer, Criteria cri);//전체게시글 수
	
	public ProductVO getDetail(int prod_id); //상세
	public int productUpdate(ProductVO vo); //수정
	public void productDelete(int prod_id); //삭제
}
