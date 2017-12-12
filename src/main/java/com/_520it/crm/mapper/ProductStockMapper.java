package com._520it.crm.mapper;

import com._520it.crm.domain.ProductStock;
import com._520it.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductStockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductStock record);

    ProductStock selectByPrimaryKey(Long id);

    List<ProductStock> selectAll();

    int updateByPrimaryKey(ProductStock record);

    Long queryPageCount(QueryObject qo);

    List<ProductStock> queryPageDataResult(QueryObject qo);

    Long queryByProductId(Long id);

    void updateStatus(@Param("storeNumber") BigDecimal storeNumber, @Param("productId") Long productId);

    ProductStock selectByproductStockId(Long productStockId);

    void updateTsNumber(@Param("tsNumber") BigDecimal number,@Param("productId") Long id);
}