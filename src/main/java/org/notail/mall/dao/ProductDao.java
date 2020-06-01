package org.notail.mall.dao;

import org.notail.mall.pojo.Product;

import java.util.List;

public interface ProductDao {
    long selectTotalCount(int cateId);

    List<Product> selectByPage(int cateId, int start, int pageSize);
}
