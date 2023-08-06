package com.coding404.myweb.product.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

@Mapper //이거 매퍼야
public interface ProductMapper {
	
	public int productRegist(ProductVO vo); //상품등록
	public void productFileRegist(ProductUploadVO vo); //파일등록
	
//	public ArrayList<ProductVO> getList(String writer); //조회
	public ArrayList<ProductVO> getList(@Param("writer") String writer, 
										@Param("cri") Criteria cri); //조회, 매개변수가 2개 이상이면 이름을 붙여줘야 함
	public int getTotal(@Param("writer") String writer,
						@Param("cri") Criteria cri); //전체게시글 수
	
	public ProductVO getDetail(int prod_id); //상세
	public int productUpdate(ProductVO vo); //수정
	public void productDelete(int prod_id); //삭제
	
	//카테고리 처리
	public ArrayList<CategoryVO> getCategory(); //처음 가져올 때
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo); //2단 3단 가져올 때
	
	//이미지데이터 가져오기
	public ArrayList<ProductUploadVO> getAjaxImg(int prod_id);

}
