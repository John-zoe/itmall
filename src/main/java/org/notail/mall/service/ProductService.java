package org.notail.mall.service;

import org.notail.mall.common.PageBean;
import org.notail.mall.pojo.Product;

import java.util.List;

public interface ProductService {
    PageBean<Product> loadPage(int cateId, int currentPage, int pageSize);
}
