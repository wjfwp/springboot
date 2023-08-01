package com.coding404.myweb.product.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public int productRegist(ProductVO vo) {
		return productMapper.productRegist(vo);
	}

//	@Override
//	public ArrayList<ProductVO> getList(String writer) {
//		return productMapper.getList(writer);
//	}

	@Override
	public ArrayList<ProductVO> getList(String writer, Criteria cri) {
		return productMapper.getList(writer, cri);
	}
	
	@Override
	public int getTotal(String writer, Criteria cri) {
		return productMapper.getTotal(writer, cri);
	}
	
	@Override
	public ProductVO getDetail(int prod_id) {
		return productMapper.getDetail(prod_id);
	}

	@Override
	public int productUpdate(ProductVO vo) {
		return productMapper.productUpdate(vo);
	}

	@Override
	public void productDelete(int prod_id) {
		productMapper.productDelete(prod_id);
	}


	




}
