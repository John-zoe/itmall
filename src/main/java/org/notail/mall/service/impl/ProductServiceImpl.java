package org.notail.mall.service.impl;

import org.notail.mall.common.PageBean;
import org.notail.mall.dao.ProductDao;
import org.notail.mall.dao.impl.ProductDaoImpl;
import org.notail.mall.pojo.Product;
import org.notail.mall.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao dao = new ProductDaoImpl();

    @Override
    public PageBean<Product> loadPage(int cateId, int currentPage, int pageSize) {
        //获取当前分类的总记录数
        long totalCount = dao.selectTotalCount(cateId);

        //获取总页数
        long totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;

        //获取一个存有分页商品的集合
        //将当前页转换成一个起始位置
        int start = (currentPage-1)*pageSize;
        List<Product> productList = dao.selectByPage(cateId, start, pageSize);

        //构建一个PageBean对象
        PageBean<Product> pageBean =new PageBean<>();
        pageBean.setList(productList);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);

        return pageBean;

    }
}
