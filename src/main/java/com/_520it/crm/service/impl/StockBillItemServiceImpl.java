package com._520it.crm.service.impl;

import com._520it.crm.domain.*;
import com._520it.crm.mapper.*;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StockBillItemQueryObject;
import com._520it.crm.service.IStockBillItemService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class StockBillItemServiceImpl implements IStockBillItemService {
    @Autowired
    private StockBillItemMapper stockBillItemMapper;
    @Autowired
    private ProductStockMapper1 productStockMapper;
    @Autowired
    private ProductStockItemMapper productStockItemMapper;
    @Autowired
    private OrderStockItemMapper orderStockItemMapper;
    @Autowired
    private SubbranchMapper subbranchMapper;

    public void deleteByPrimaryKey(Long id) {
        stockBillItemMapper.deleteByPrimaryKey(id);
    }

    public void insert(StockBillItem record) {
        record.setInputTime(new Date());
        if (record.getAmount() != null && record.getCostPrice() != null) {
            BigDecimal amount = record.getAmount();
            BigDecimal costPrice = record.getCostPrice();
            BigDecimal totalPrice = amount.multiply(costPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
            record.setTotalPrice(totalPrice);
        }
        stockBillItemMapper.insert(record);
    }

    public StockBillItem selectByPrimaryKey(Long id) {
        return stockBillItemMapper.selectByPrimaryKey(id);
    }

    public List<StockBillItem> selectAll() {
        return stockBillItemMapper.selectAll();
    }

    public void updateByPrimaryKey(StockBillItem record) {
        stockBillItemMapper.updateByPrimaryKey(record);
    }

    public PageResult queryPage(StockBillItemQueryObject qo) {
        Long total = stockBillItemMapper.queryPageCount(qo);
        if (total <= 0) {
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        List<StockBillItem> rows = stockBillItemMapper.queryPageDataResult(qo);
        return new PageResult(total, rows);
    }

    //==============入库===========================
    public void instock(Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                Date date = new Date();
                StockBillItem stockBillItem = stockBillItemMapper.selectByPrimaryKey(id);
                stockBillItem.setVdate(date);
                stockBillItem.setState(StockBillItem.STATE_QUIT);
                stockBillItemMapper.updateState(stockBillItem);
                //判断入库状态
                if (stockBillItem.getState() == 1) {
                    //添加入库单
                    //===========================================
                    //查询存储有没有此产品
                    ProductStock1 ps = productStockMapper.selectProductId(stockBillItem.getProduct().getId());
                    if (ps == null) {
                        ps = new ProductStock1();
                        //设置
                        ps.setProduct(stockBillItem.getProduct());
                        //散装功能方便以后添加
                        ps.setBulkNumber(new BigDecimal(0));
                        ps.setCategory(stockBillItem.getProduct().getCategoryName());
                        ps.setSalePrice(stockBillItem.getCostPrice());
                        ps.setSpecification(stockBillItem.getMaxunit());
                        ps.setStoreNumber(stockBillItem.getAmount());
                        ps.setVsalePrice(stockBillItem.getProduct().getSalePrice());
                        //设置库存余额
                        BigDecimal storeNumber = ps.getStoreNumber();
                        BigDecimal costPrice = stockBillItem.getCostPrice();
                        if (storeNumber != null && costPrice != null) {
                            BigDecimal totalPrice = ps.getStoreNumber().
                                    multiply(stockBillItem.getCostPrice()).setScale(BigDecimal.ROUND_HALF_UP, 2);
                            ps.setTotalPrice(totalPrice);
                        }
                        ps.setWarningNumber(stockBillItem.getWarningNumber());
                        productStockMapper.insert(ps);
                    } else {
                        //存在入库单据,需该库存数量,库总额和库存价格
                        //设置价格使用移动平均加权
                        BigDecimal storeNumber = ps.getStoreNumber();
                        //数量
                        ps.setStoreNumber(storeNumber.add(stockBillItem.getAmount()));
                        //总额
                        ps.setTotalPrice(ps.getTotalPrice().add(stockBillItem.getCostPrice().multiply(stockBillItem.getAmount())).
                                setScale(2, BigDecimal.ROUND_HALF_UP));
                        //价格
                        ps.setSalePrice(ps.getTotalPrice().divide(ps.getStoreNumber(), 2, BigDecimal.ROUND_HALF_UP));
                        //重新更新预警数量
                        ps.setWarningNumber(stockBillItem.getWarningNumber());
                        //更新入库单据
                        productStockMapper.updateByPrimaryKey(ps);
                    }
                    //====================================
                    //添加入库明细//此明细也是在审核状态下
                    //创建对象
                    ProductStockItem stockItem = new ProductStockItem();
                    //设置入库时间
                    stockItem.setVdate(date);
                    //单位规格
                    stockItem.setSpecification(stockBillItem.getMaxunit());
                    //数量
                    stockItem.setAmount(stockBillItem.getAmount());
                    //进货价
                    stockItem.setCostPrice(stockBillItem.getCostPrice());
                    //进货总价
                    stockItem.setTotalPrice(stockBillItem.getAmount().
                            multiply(stockBillItem.getCostPrice()).setScale(BigDecimal.ROUND_HALF_UP, 2));
                    //产品
                    stockItem.setProduct(stockBillItem.getProduct());
                    //保质期
                    stockItem.setSafeDate(stockBillItem.getSafeDate());
                    //预警日期
                    stockItem.setWarningDate(stockBillItem.getWarningDate());
                    //获取当前用户
                    Employee current = (Employee) SecurityUtils.getSubject().getPrincipal();
                    //设置当前用户名
                    stockItem.setEmployeeName(current.getRealname());
                    //添加明细
                    productStockItemMapper.insert(stockItem);
                }
            }
        }
    }
    //==============出库============================

    public void outstock(Long[] ids, Long subId) {
        if (ids != null && subId != null) {
            for (Long id : ids) {
                Date date = new Date();
                //获取但当前临时明细
                StockBillItem stockBillItem = stockBillItemMapper.selectByPrimaryKey(id);
                stockBillItem.setVdate(date);
                stockBillItem.setState(StockBillItem.STATE_QUIT);
                stockBillItemMapper.updateState(stockBillItem);
                //出库状态
                if (stockBillItem.getState() == 1) {
                    //查询存储有没有此产品
                    ProductStock1 ps = productStockMapper.selectProductId(stockBillItem.getProduct().getId());
                    //如果没有
                    if (ps == null) {
                        //抛出异常
                        throw new RuntimeException
                                (stockBillItem.getProduct().getName() + "库存中不存在!");
                    }
                    if (ps.getStoreNumber().compareTo(stockBillItem.getAmount()) < 0) {
                        //库存数量不足
                        throw new RuntimeException("库存数量:" + ps.getStoreNumber() + "出库数量(" + stockBillItem.getAmount() + "),库数量不足!");
                    }
                    //库存剩余数量
                    BigDecimal storeNumber = ps.getStoreNumber().subtract(stockBillItem.getAmount());
                    ps.setStoreNumber(storeNumber);
                    //设置产品总额
                    ps.setTotalPrice(storeNumber.multiply(ps.getSalePrice()).setScale(BigDecimal.ROUND_HALF_UP, 2));
                    //更新库存
                    productStockMapper.updateByOrderProduct(ps);

                    //填加出库信息
                    //查询当前分店信息
                    Subbranch subbranch = subbranchMapper.selectByPrimaryKey(subId);
                    OrderStockItem orderStockItem = new OrderStockItem();
                    orderStockItem.setProduct(stockBillItem.getProduct());
                    //获取当前用户
                    Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
                    orderStockItem.setEmployeeName(employee.getRealname());
                    orderStockItem.setVdate(date);
                    orderStockItem.setRemark(stockBillItem.getProduct().getRemark());
                    orderStockItem.setSubbranchName(subbranch.getName());
                    orderStockItem.setState(OrderStockItem.STATE_QUIT);
                    orderStockItemMapper.insert(orderStockItem);
                }
            }

        }
    }
}
