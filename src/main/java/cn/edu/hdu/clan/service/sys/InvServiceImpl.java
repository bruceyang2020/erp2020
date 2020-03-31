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
        //H 上期期末数，如果period=1 默认上期期末数为0
        BigDecimal r1Ab=  BigDecimal.valueOf(0); BigDecimal r2Ab=  BigDecimal.valueOf(0);BigDecimal r3Ab=  BigDecimal.valueOf(0); BigDecimal r4Ab=  BigDecimal.valueOf(0);
        BigDecimal r1Mb=  BigDecimal.valueOf(0); BigDecimal r2Mb=  BigDecimal.valueOf(0);BigDecimal r3Mb=  BigDecimal.valueOf(0); BigDecimal r4Mb=  BigDecimal.valueOf(0);
        BigDecimal p1Ab=  BigDecimal.valueOf(0); BigDecimal p2Ab=  BigDecimal.valueOf(0);BigDecimal p3Ab=  BigDecimal.valueOf(0); BigDecimal p4Ab=  BigDecimal.valueOf(0);
        BigDecimal p1Mb=  BigDecimal.valueOf(0); BigDecimal p2Mb=  BigDecimal.valueOf(0);BigDecimal p3Mb=  BigDecimal.valueOf(0); BigDecimal p4Mb=  BigDecimal.valueOf(0);
        //H 通过上期期初数和发生额计算本期期初数
        if (period!=1) {
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

                    case "P1":
                        p1Ab=inv.get(i).getAmountB().add(inv.get(i).getAmountI().subtract(inv.get(i).getAmountO()));
                        p1Mb=inv.get(i).getMoneyB().add(inv.get(i).getMoneyI().subtract(inv.get(i).getMoneyO()));
                        break;
                    case "P2":
                        p2Ab=inv.get(i).getAmountB().add(inv.get(i).getAmountI().subtract(inv.get(i).getAmountO()));
                        p2Mb=inv.get(i).getMoneyB().add(inv.get(i).getMoneyI().subtract(inv.get(i).getMoneyO()));
                        break;
                    case "P3":
                        p3Ab=inv.get(i).getAmountB().add(inv.get(i).getAmountI().subtract(inv.get(i).getAmountO()));
                        p3Mb=inv.get(i).getMoneyB().add(inv.get(i).getMoneyI().subtract(inv.get(i).getMoneyO()));
                        break;
                    case "P4":
                        p4Ab=inv.get(i).getAmountB().add(inv.get(i).getAmountI().subtract(inv.get(i).getAmountO()));
                        p4Mb=inv.get(i).getMoneyB().add(inv.get(i).getMoneyI().subtract(inv.get(i).getMoneyO()));
                        break;
                }
            }
        }
        // 根据material 表计算发生值
        BigDecimal r1Ai=  BigDecimal.valueOf(0); BigDecimal r2Ai=  BigDecimal.valueOf(0);BigDecimal r3Ai=  BigDecimal.valueOf(0); BigDecimal r4Ai=  BigDecimal.valueOf(0);
        BigDecimal r1Mi=  BigDecimal.valueOf(0); BigDecimal r2Mi=  BigDecimal.valueOf(0);BigDecimal r3Mi=  BigDecimal.valueOf(0); BigDecimal r4Mi=  BigDecimal.valueOf(0);
        Inv inv1=new Inv();Inv inv2=new Inv();Inv inv3=new Inv();Inv inv4=new Inv();
        for(int i=0;i< materialOrderList12.size();i++) {
            //全局变量 写入当前公司或小组ID
            switch (materialOrderList12.get(i).getMaterialId()) {
                case "R1":
                    r1Ai= BigDecimal.valueOf(materialOrderList12.get(i).getAmount());//H 材料入库
                    r1Mi= materialOrderList12.get(i).getMoneyTotal();//H 材料入库
                    break;
                case "R2":
                    r2Ai= BigDecimal.valueOf(materialOrderList12.get(i).getAmount());//H 材料入库
                    r2Mi= materialOrderList12.get(i).getMoneyTotal();//H 材料入库
                    break; } }
        for(int i=0;i< materialOrderList34.size();i++) {
            switch (materialOrderList34.get(i).getMaterialId()) {
                case "R3":
                    r3Ai= BigDecimal.valueOf(materialOrderList34.get(i).getAmount());//H 材料入库
                    r3Mi= materialOrderList34.get(i).getMoneyTotal();//H 材料入库
                    break;
                case "R4":
                    r4Ai= BigDecimal.valueOf(materialOrderList34.get(i).getAmount());//H 材料入库
                    r4Mi= materialOrderList34.get(i).getMoneyTotal();//H 材料入库
                    break; } }
        //H 在结转时写入
        inv1.setTeamCount(userTeam);
        inv1.setGroupId("1000");
        inv1.setPeriod(period);
        inv1.setNumber("R1");
        inv1.setMoneyB(r1Mb);
        inv1.setAmountB(r1Ab);
        inv1.setMoneyI(r1Mi);
        inv1.setAmountI(r1Ai);
        inv1.setAmountO(BigDecimal.valueOf(0));//先预设0，原料投入生产时更新写入
        inv1.setMoneyO(BigDecimal.valueOf(0));//先预设0，原料投入生产时更新写入
        BaseBeanHelper.insert(inv1);
        InvMapper.insert(inv1);

        inv2.setTeamCount(userTeam);
        inv2.setGroupId("1000");
        inv2.setPeriod(period);
        inv2.setNumber("R2");
        inv2.setMoneyB(r2Mb);
        inv2.setAmountB(r2Ab);
        inv2.setMoneyI(r2Mi);
        inv2.setAmountI(r2Ai);
        inv2.setAmountO(BigDecimal.valueOf(0));//先预设0，原料投入生产时更新写入
        inv2.setMoneyO(BigDecimal.valueOf(0));//先预设0，原料投入生产时更新写入
        BaseBeanHelper.insert(inv2);
        InvMapper.insert(inv2);

        inv3.setTeamCount(userTeam);
        inv3.setGroupId("1000");
        inv3.setPeriod(period);
        inv3.setNumber("R3");
        inv3.setMoneyB(r3Mb);
        inv3.setAmountB(r3Ab);
        inv3.setMoneyI(r3Mi);
        inv3.setAmountI(r3Ai);
        inv3.setAmountO(BigDecimal.valueOf(0));//先预设0，原料投入生产时更新写入
        inv3.setMoneyO(BigDecimal.valueOf(0));//先预设0，原料投入生产时更新写入
        BaseBeanHelper.insert(inv3);
        InvMapper.insert(inv3);

        inv4.setTeamCount(userTeam);
        inv4.setGroupId("1000");
        inv4.setPeriod(period);
        inv4.setNumber("R4");
        inv4.setMoneyB(r4Mb);
        inv4.setAmountB(r4Ab);
        inv4.setMoneyI(r4Mi);
        inv4.setAmountI(r4Ai);
        inv4.setAmountO(BigDecimal.valueOf(0));//先预设0，原料投入生产时更新写入
        inv4.setMoneyO(BigDecimal.valueOf(0));//先预设0，原料投入生产时更新写入
        BaseBeanHelper.insert(inv4);
        InvMapper.insert(inv4);


        Inv invP1=new Inv();Inv invP2=new Inv();Inv invP3=new Inv();Inv invP4=new Inv();

        //H 在结转时写入
        invP1.setTeamCount(userTeam);
        invP1.setGroupId("1000");
        invP1.setPeriod(period);
        invP1.setNumber("P1");
        invP1.setMoneyB(p1Mb);
        invP1.setAmountB(p1Ab);
        invP1.setMoneyI(BigDecimal.valueOf(0));
        invP1.setAmountI(BigDecimal.valueOf(0));
        invP1.setAmountO(BigDecimal.valueOf(0));
        invP1.setMoneyO(BigDecimal.valueOf(0));
        BaseBeanHelper.insert(invP1);
        InvMapper.insert(invP1);

        invP2.setTeamCount(userTeam);
        invP2.setGroupId("1000");
        invP2.setPeriod(period);
        invP2.setNumber("P2");
        invP2.setMoneyB(p2Mb);
        invP2.setAmountB(p2Ab);
        invP2.setMoneyI(BigDecimal.valueOf(0));
        invP2.setAmountI(BigDecimal.valueOf(0));
        invP2.setAmountO(BigDecimal.valueOf(0));
        invP2.setMoneyO(BigDecimal.valueOf(0));
        BaseBeanHelper.insert(invP2);
        InvMapper.insert(invP2);

        invP3.setTeamCount(userTeam);
        invP3.setGroupId("1000");
        invP3.setPeriod(period);
        invP3.setNumber("P3");
        invP3.setMoneyB(p3Mb);
        invP3.setAmountB(p3Ab);
        invP3.setMoneyI(BigDecimal.valueOf(0));
        invP3.setAmountI(BigDecimal.valueOf(0));
        invP3.setAmountO(BigDecimal.valueOf(0));
        invP3.setMoneyO(BigDecimal.valueOf(0));
        BaseBeanHelper.insert(invP3);
        InvMapper.insert(invP3);

        invP4.setTeamCount(userTeam);
        invP4.setGroupId("1000");
        invP4.setPeriod(period);
        invP4.setNumber("P4");
        invP4.setMoneyB(p4Mb);
        invP4.setAmountB(p4Ab);
        invP4.setMoneyI(BigDecimal.valueOf(0));
        invP4.setAmountI(BigDecimal.valueOf(0));
        invP4.setAmountO(BigDecimal.valueOf(0));
        invP4.setMoneyO(BigDecimal.valueOf(0));
        BaseBeanHelper.insert(invP4);
        InvMapper.insert(invP4);



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
        inv.setAmountO(amountOut.add(new BigDecimal(amount)));//H 本期出库额的合计
        inv.setMoneyO(moneyOut.add(new BigDecimal(amount)));//H 本期出库额的合计
        BaseBeanHelper.edit(inv);
        InvMapper.updateByPrimaryKey(inv);


        //自动生成出库对应应的会计凭证:借在制品 贷原材料
        accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal(amount), "SCCK", content);


    }

    //H 产品入库
    public void stockIntoWarehouse(String userTeam, int period, String product, int amount, String content) {
        Example example = new Example(Inv.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("number", product);
        Inv inv = InvMapper.selectOneByExample(example);
        BigDecimal amountIn = inv.getAmountI();
        BigDecimal moneyIn = inv.getMoneyI();
        inv.setAmountI(amountIn.add(new BigDecimal(1)));//H 本期入库额的合计
        inv.setMoneyI(moneyIn.add(new BigDecimal(amount)));//H 本期入库额的合计
        BaseBeanHelper.edit(inv);
        InvMapper.updateByPrimaryKey(inv);


        //自动生成出库对应应的会计凭证:借成品 贷在制品
        accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal(amount), "CPRK", content);


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
        accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal(amount), "XSCK", content);


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

}
