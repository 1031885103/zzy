package com._520it.crm.service.impl;

import com._520it.crm.domain.*;
import com._520it.crm.mapper.*;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IProductService;
import com._520it.crm.service.ISaleAccountService;
import com._520it.crm.service.ISaleBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SaleBillServiceImpl implements ISaleBillService {
    @Autowired
    private SaleBillMapper BillMapper;
    @Autowired
    private SaleBillItemMapper itemMapper;
    @Autowired
    private VipCardMapper vipCardMapper;


    @Override
    public void save(SaleBillItem item) {
        Product product = item.getProduct();
        item.setRealprice(product.getSalePrice());
        BigDecimal discount = product.getVsalePrice().divide(product.getSalePrice(), 2);
        item.setDiscount(discount);
        item.setAmount(item.getNumber().multiply(item.getRealprice()));
        item.setOriginalprice(item.getRealprice());
        Product product1 = productService.selectByName(product.getName());
        Long productStockId = productStockMapper.queryByProductId(product1.getId());
        ProductStock stock = productStockMapper.selectByPrimaryKey(productStockId);
        BigDecimal storeNumber = stock.getStoreNumber().subtract(item.getNumber());
        productStockMapper.updateStatus(storeNumber, product1.getId());
        item.setProduct(product1);
        item.setProductStockId(productStockId);
        SaleBill saleBill = new SaleBill();
        saleBill.setOptime(new Date());
        saleBill.setDiscount(discount);
        saleBill.setPayMethod((byte) 0);
        List<SaleBillItem> items = new ArrayList<>();
        items.add(item);
        saleBill.setItems(items);
        saleBill.setRealAmount(item.getAmount());
        saleBill.setTotalAmount(item.getAmount());
        saleBill.setTotalNumber(item.getNumber());
        saleBill.setRemark("0");
        saleBill.setSalemanId(1L);
        saleBill.setState((byte) 0);
        BillMapper.insert(saleBill);
        item.setBillId(saleBill.getId());
        itemMapper.insert(item);
    }

    @Autowired
    private ProductStockMapper productStockMapper;
    @Autowired
    private ISaleAccountService auditBillService;
    @Autowired
    private IProductService productService;

    public void save(SaleBill bill, ProductStock productStock) {
        //1.设置制单人和制单时间
        bill.setSalemanId(bill.getSalemanId());
        bill.setOptime(new Date());
        //2.重新设置单据的审核状态
        bill.setState((byte) 0);
        Product product = productStock.getProduct();
      /*  BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNumber = BigDecimal.ZERO;
        //3.迭代单据中的每一条明细,计算总金额,总数量
        List<SaleBillItem> items = bill.getItems();
        SaleBillItem item1 = items.get(0);

        Product product1 = productService.selectByName(product.getName());
        item1.setDiscount(new BigDecimal("1"));
        item1.setOriginalprice(product.getSalePrice());
        item1.setProductStockId(productStock.getId());
        item1.setAmount(item1.getNumber().multiply(item1.getOriginalprice()));
        itemMapper.insert(item1);*/
        //5.保存单据
        BillMapper.insert(bill);
        for (SaleBillItem item : bill.getItems()) {
            item.setBillId(bill.getId());
            //5.保存每一条明细(设置小计,设置billId)
            itemMapper.insert(item);

        }
    }


    public void update(SaleBill bill) {
        //更新操作
        //1.查询出数据库中该单据对象是否处理待审核状态,若是
        if (bill.getState() == 0) {
            //2.删除该单据之前所有的明细
            itemMapper.deleteByBillId(bill.getId());
            //3.重新计算出库总额和总数量
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal totalNumber = BigDecimal.ZERO;
            for (SaleBillItem item : bill.getItems()) {
                BigDecimal amount = item.getOriginalprice().multiply(item.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP);
                totalAmount = totalAmount.add(amount);
                totalNumber = totalNumber.add(item.getNumber());
                item.setAmount(amount);
                item.setBillId(bill.getId());
                //4.可以先保存明细
                itemMapper.insert(item);
            }
            //5.设置单据总金额和总数量
            bill.setTotalNumber(totalNumber);
            bill.setTotalAmount(totalAmount);
            //6.更新单据对象
            BillMapper.updateByPrimaryKey(bill);
        }
    }

    public void delete(Long id) {
        //先删除明细,再删除出库单据
        itemMapper.deleteByBillId(id);
        BillMapper.deleteByPrimaryKey(id);
    }

    public SaleBill get(Long id) {
        return BillMapper.selectByPrimaryKey(id);
    }

    public List<SaleBill> listAll() {
        return BillMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return null;
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        Long count = BillMapper.queryPageCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<SaleBill> result = BillMapper.queryPageDataResult(qo);
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }

    @Override
    public PageResult queryStoreBill(QueryObject qo) {
        Long count = BillMapper.queryPageCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<SaleBill> result = BillMapper.queryStoreBill(qo);
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }

    @Override
    public void storeVipBill(SaleBillItem item, Vip vip) {
        Product product = item.getProduct();
        item.setRealprice(product.getSalePrice());
        BigDecimal discount = product.getVsalePrice().divide(product.getSalePrice(), 2);
        item.setDiscount(discount);
        item.setAmount(item.getNumber().multiply(item.getRealprice()));
        item.setOriginalprice(item.getRealprice());
        Product product1 = productService.selectByName(product.getName());
        Long productStockId = productStockMapper.queryByProductId(product1.getId());
        ProductStock stock = productStockMapper.selectByPrimaryKey(productStockId);
        BigDecimal storeNumber = stock.getStoreNumber().subtract(item.getNumber());
        productStockMapper.updateStatus(storeNumber, product1.getId());
        productStockMapper.updateTsNumber(item.getNumber(), product1.getId());
        item.setProduct(product1);
        item.setProductStockId(productStockId);
        SaleBill saleBill = new SaleBill();
        saleBill.setOptime(new Date());
        saleBill.setDiscount(discount);
        saleBill.setPayMethod((byte) 0);
        List<SaleBillItem> items = new ArrayList<>();
        items.add(item);
        saleBill.setItems(items);
        item.setState((byte) 1);
        saleBill.setRealAmount(item.getAmount());
        saleBill.setTotalAmount(item.getAmount());
        saleBill.setTotalNumber(item.getNumber());
        saleBill.setRemark("0");
        saleBill.setSalemanId(1L);
        saleBill.setState((byte) 1);
        saleBill.setVipId(vip.getId());
        saleBill.setVipCard(vip.getTel());

        saleBill.setSn(new Date().toString());
        BillMapper.insert(saleBill);
        item.setBillId(saleBill.getId());
        itemMapper.insert(item);

    }

    @Override
    public void saveVipItem(SaleBillItem item, Vip vip) {
        Product product = item.getProduct();
        item.setRealprice(product.getVsalePrice());
        BigDecimal discount = product.getVsalePrice().divide(product.getSalePrice(), 2);
        item.setDiscount(discount);
        item.setAmount(item.getNumber().multiply(item.getRealprice()));
        item.setOriginalprice(item.getRealprice());
        Product product1 = productService.selectByName(product.getName());
        Long productStockId = productStockMapper.queryByProductId(product1.getId());
        ProductStock stock = productStockMapper.selectByPrimaryKey(productStockId);
        BigDecimal storeNumber = stock.getStoreNumber().subtract(item.getNumber());
        productStockMapper.updateStatus(storeNumber, product1.getId());
        item.setProduct(product1);
        item.setProductStockId(productStockId);
        SaleBill saleBill = new SaleBill();
        saleBill.setOptime(new Date());
        saleBill.setDiscount(discount);
        saleBill.setPayMethod((byte) 0);
        List<SaleBillItem> items = new ArrayList<>();
        items.add(item);
        saleBill.setItems(items);
        saleBill.setRealAmount(item.getAmount());
        saleBill.setTotalAmount(item.getAmount());
        saleBill.setTotalNumber(item.getNumber());
        saleBill.setRemark("0");
        saleBill.setSalemanId(1L);
        saleBill.setState((byte) 0);
        saleBill.setVipId(vip.getId());
        saleBill.setVipCard(vip.getTel());

        saleBill.setSn(new Date().toString());
        BillMapper.insert(saleBill);
        item.setBillId(saleBill.getId());
        itemMapper.insert(item);

        //从会员金额卡从扣除消费金额
        vipCardMapper.subMoney(item.getAmount(),vip.getId());
    }

    public void audit(Long id) {//审核操作
        SaleBill bill = BillMapper.selectByPrimaryKey(id);
        //1.判断单据是否处于审核状态,若是
        if (bill.getState() == 0) {//待审核状态
            //2.设置审核信息
            bill.setSalemanId(bill.getSalemanId());
            bill.setOptime(new Date());
            bill.setState((byte) 1);
            BillMapper.updateState(bill);
            //3.修改单据
            auditBillService.auditOutcome(bill);
        }
    }
}
