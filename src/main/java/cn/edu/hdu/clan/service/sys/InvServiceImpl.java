package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Inv;
import cn.edu.hdu.clan.entity.sys.MaterialOrder;
import cn.edu.hdu.clan.util.Jurisdiction;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.InvMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


@Service
public class InvServiceImpl implements InvService {

    @Autowired
    private InvMapper InvMapper;

    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Resource
    private MaterialOrderService materialOrderService;

    @Resource
    private InvService invService;

    @Transactional

    @Override
    public void add(Inv Inv) {


        BaseBeanHelper.insert(Inv);
        InvMapper.insert(Inv);

    }
    /**H 转下个季度时原材料入库
     * @param
     */
    public void goToPeriod(String userTeam,int period){

        List<MaterialOrder> materialOrderList12 = materialOrderService.listR1R2(period);
        List<MaterialOrder> materialOrderList34 = materialOrderService.listR3R4(period);
        List<Inv> inv= invService.listInv(userTeam,period-1);//上期数取出期初数
        BigDecimal r1Ab=  BigDecimal.valueOf(0); BigDecimal r2Ab=  BigDecimal.valueOf(0);BigDecimal r3Ab=  BigDecimal.valueOf(0); BigDecimal r4Ab=  BigDecimal.valueOf(0);
        BigDecimal r1Mb=  BigDecimal.valueOf(0); BigDecimal r2Mb=  BigDecimal.valueOf(0);BigDecimal r3Mb=  BigDecimal.valueOf(0); BigDecimal r4Mb=  BigDecimal.valueOf(0);
        if (period!=1) { //如果有期初就不用写
            for (int i = 0; i < inv.size(); i++) {
                switch (inv.get(i).getNumber()) {
                    case "R1":
                        r1Ab=inv.get(i).getAmountB().add(inv.get(i).getAmountI().subtract(inv.get(i).getAmountO()));
                        r1Mb=inv.get(i).getMoneyB().add(inv.get(i).getMoneyI().subtract(inv.get(i).getMoneyO()));
                        break;
                    case "R2":
                        r2Ab=inv.get(i).getAmountB().add(inv.get(i).getAmountI().subtract(inv.get(i).getAmountO()));
                        r2Mb=inv.get(i).getMoneyB().add(inv.get(i).getMoneyI().subtract(inv.get(i).getMoneyO()));
                        break;
                    case "R3":
                        r3Ab=inv.get(i).getAmountB().add(inv.get(i).getAmountI().subtract(inv.get(i).getAmountO()));
                        r3Mb=inv.get(i).getMoneyB().add(inv.get(i).getMoneyI().subtract(inv.get(i).getMoneyO()));
                        break;
                    case "R4":
                        r4Ab=inv.get(i).getAmountB().add(inv.get(i).getAmountI().subtract(inv.get(i).getAmountO()));
                        r4Mb=inv.get(i).getMoneyB().add(inv.get(i).getMoneyI().subtract(inv.get(i).getMoneyO()));
                        break;
                }
            }
        }
        Inv inv1=new Inv();
        Inv inv2=new Inv();
        Inv inv3=new Inv();
        Inv inv4=new Inv();
        for(int i=0;i< materialOrderList12.size();i++) {
            //全局变量 写入当前公司或小组ID

            switch (materialOrderList12.get(i).getMaterialId()) {
                case "R1":
                    inv1.setTeamCount(userTeam);
                    inv1.setPeriod(period);
                    inv1.setGroupId("1000");
                    inv1.setNumber("R1");
                    inv1.setAmountB(r1Ab);
                    inv1.setMoneyB(r1Mb);
                    inv1.setAmountI(BigDecimal.valueOf(materialOrderList12.get(i).getAmount()));//H 材料入库
                    inv1.setMoneyI(materialOrderList12.get(i).getMoneyTotal());//H 材料入库
                    inv1.setAmountO(BigDecimal.valueOf(0));//先预设0
                    inv1.setMoneyO(BigDecimal.valueOf(0));//先预设0
                    BaseBeanHelper.insert(inv1);
                    InvMapper.insert(inv1);
                    break;
                case "R2":
                    inv2.setTeamCount(userTeam);
                    inv2.setPeriod(period);
                    inv2.setGroupId("1000");
                    inv2.setNumber("R2");
                    inv2.setAmountB(r2Ab);
                    inv2.setMoneyB(r2Mb);
                    inv2.setAmountI(BigDecimal.valueOf(materialOrderList12.get(i).getAmount()));//H 材料入库
                    inv2.setMoneyI(materialOrderList12.get(i).getMoneyTotal());//H 材料入库
                    inv2.setAmountO(BigDecimal.valueOf(0));//先预设0
                    inv2.setMoneyO(BigDecimal.valueOf(0));//先预设0
                    BaseBeanHelper.insert(inv2);
                    InvMapper.insert(inv2);
                    break;

            }

        }
        for(int i=0;i< materialOrderList34.size();i++) {
            switch (materialOrderList34.get(i).getMaterialId()) {
                case "R3":
                    inv3.setTeamCount(userTeam);
                    inv3.setPeriod(period);
                    inv3.setGroupId("1000");
                    inv3.setNumber("R3");
                    inv3.setAmountB(r3Ab);
                    inv3.setMoneyB(r3Mb);
                    inv3.setAmountI(BigDecimal.valueOf(materialOrderList34.get(i).getAmount()));//H 材料入库
                    inv3.setMoneyI(materialOrderList34.get(i).getMoneyTotal());//H 材料入库
                    inv3.setAmountO(BigDecimal.valueOf(0));//先预设0
                    inv3.setMoneyO(BigDecimal.valueOf(0));//先预设0
                    System.out.println(materialOrderList34.get(i).getMaterialId());
                    BaseBeanHelper.insert(inv3);
                    InvMapper.insert(inv3);
                    break;
                case "R4":
                    inv4.setTeamCount(userTeam);
                    inv4.setPeriod(period);
                    inv4.setGroupId("1000");
                    inv4.setNumber("R4");
                    inv4.setAmountB(r4Ab);
                    inv4.setMoneyB(r4Mb);
                    inv4.setAmountI(BigDecimal.valueOf(materialOrderList34.get(i).getAmount()));//H 材料入库
                    inv4.setMoneyI(materialOrderList34.get(i).getMoneyTotal());//H 材料入库
                    inv4.setAmountO(BigDecimal.valueOf(0));//先预设0
                    inv4.setMoneyO(BigDecimal.valueOf(0));//先预设0
                    BaseBeanHelper.insert(inv4);
                    InvMapper.insert(inv4);
                    break;

            }


        }
    }


    /**
     * Y 批量将存货信息写入当前会计期间。用于初始化操作。
     * @param invs
     */
    public void adds(List<Inv> invs) {
        if (invs.size() > 0) {
            for (int i = 0; i < invs.size(); i++) {
                String userTeam = Jurisdiction.getUserTeam();
                int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
                invs.get(i).setPeriod(period);
                invs.get(i).setTeamCount(userTeam);
                invs.get(i).setGroupId("1000");
                BaseBeanHelper.insert(invs.get(i));
                InvMapper.insert(invs.get(i));
            }
        }
    }


    @Override
    public void delete(String id) {
        InvMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByTeamCount(String userTeam) {
        Example example = new Example(Inv.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        InvMapper.deleteByExample(example);
    }


    @Override
    public void update(Inv Inv) {
        BaseBeanHelper.edit(Inv);
        Example example = new Example(Inv.class);
        example.createCriteria().andEqualTo("id", Inv.getId());
        InvMapper.updateByExampleSelective(Inv, example);
    }

    @Override
    public PageInfo<Inv> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(InvMapper.selectAll());
    }

    public void stockOutToProduce(String userTeam, int period, String product, int amount, String content) {
        Example example = new Example(Inv.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("number", product);
        Inv inv = InvMapper.selectOneByExample(example);
        BigDecimal amountOut = inv.getAmountO();
        BigDecimal moneyOut = inv.getMoneyO();
        inv.setAmountO(amountOut.subtract(new BigDecimal(amount)));
        inv.setMoneyO(moneyOut.subtract(new BigDecimal(amount)));
        BaseBeanHelper.edit(inv);
        InvMapper.updateByPrimaryKey(inv);


        //自动生成出库对应应的会计凭证:借在制品 贷原材料
        accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal(amount), "”SCCK", content);


    }


    public void stockOutToSale(String userTeam, int period, String product, int amount, String content) {
        Example example = new Example(Inv.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("number", product);
        Inv inv = InvMapper.selectOneByExample(example);
        BigDecimal amountOut = inv.getAmountO();
        BigDecimal moneyOut = inv.getMoneyO();
        inv.setAmountO(amountOut.subtract(new BigDecimal(amount)));
        inv.setMoneyO(moneyOut.subtract(new BigDecimal(amount)));
        BaseBeanHelper.edit(inv);
        InvMapper.updateByPrimaryKey(inv);


        //自动生成出库对应应的会计凭证:借直接成本 贷成品
        accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal(amount), "”XSCK", content);


    }

    @Override
    public List<Inv> listInv(String userTeam, int period) {

        Example example = new Example(Inv.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        return InvMapper.selectByExample(example);
    }


    @Override
    public Inv getById(String id) {
        Example example = new Example(Inv.class);
        example.createCriteria().andEqualTo("id", id);
        return InvMapper.selectOneByExample(example);
    }

    @Override
    public void copyDataToNextPeriod(String userTeam, int period, int nextPeriod) {
        Example example = new Example(Inv.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        List<Inv> factorys = InvMapper.selectByExample(example);

        if (factorys.size() > 0) {
            for (int i = 0; i < factorys.size(); i++) {
                Inv myRow = factorys.get(i);
                myRow.setPeriod(nextPeriod);
                BaseBeanHelper.insert(myRow);
                InvMapper.insert(myRow);

            }
        }

    }
}
