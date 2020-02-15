package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.AccountingVoucher;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.AccountingVoucherMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.Date;

@Service
public class AccountingVoucherServiceImpl implements AccountingVoucherService {

    @Autowired
    private AccountingVoucherMapper AccountingVoucherMapper;


    @Transactional
    @Override
    public void add(AccountingVoucher AccountingVoucher) {
        BaseBeanHelper.insert(AccountingVoucher);
        AccountingVoucherMapper.insert(AccountingVoucher);
    }

    @Override
    public void delete(String id) {
    AccountingVoucherMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(AccountingVoucher AccountingVoucher) {
        BaseBeanHelper.edit(AccountingVoucher);
        Example example = new Example(AccountingVoucher.class);
        example.createCriteria().andEqualTo("id", AccountingVoucher.getId());
        AccountingVoucherMapper.updateByExampleSelective(AccountingVoucher, example);
    }

    @Override
    public BigDecimal sumMoney(String userTeam ,int period,String acode,String aType) {
        BigDecimal myMoney = BigDecimal.valueOf(0);
        BigDecimal  addMoney = BigDecimal.valueOf(0);

        Example example = new Example(AccountingVoucher.class);
        example.createCriteria().andEqualTo("teamCount", userTeam);
        example.createCriteria().andEqualTo("acode", acode);
        example.createCriteria().andEqualTo("period", period);
        List<AccountingVoucher> oldRow = AccountingVoucherMapper.selectByExample( example);

        for(int i=1;i<oldRow.size();i++)
        {
            if("借".equals(aType) && null !=oldRow.get(i).getMoneyD())
            {
                addMoney = oldRow.get(i).getMoneyD();
            }
            if("贷".equals(aType) && null !=oldRow.get(i).getMoneyC())
            {
                addMoney = oldRow.get(i).getMoneyC();
            }
            myMoney = myMoney.add(addMoney);

        }

        return myMoney;
    }

    @Override
    public PageInfo<AccountingVoucher> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(AccountingVoucherMapper.selectAll());
    }

    @Override
    public AccountingVoucher getById(String id) {
        Example example = new Example(AccountingVoucher.class);
        example.createCriteria().andEqualTo("id", id);
        return AccountingVoucherMapper.selectOneByExample(example);
    }

    private  void  voucherMakerBase(String teamCount, int period ,String debtAcount,String CreditAcount, BigDecimal amount,String content)
    {
        Example example = new Example(AccountingVoucher.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount",teamCount);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("substract", content);
        List<AccountingVoucher> oldRow1 = AccountingVoucherMapper.selectByExample(example);
        if(oldRow1.size() > 0)
        {
            AccountingVoucherMapper.deleteByExample(example);
        }
        //插入会计分录-借方
        AccountingVoucher vd1 = new AccountingVoucher();
        vd1.setTeamCount(teamCount);
        vd1.setPeriod(period);
        vd1.setSubstract(content);
        vd1.setAcode(debtAcount);
        vd1.setAname(debtAcount);
        vd1.setMoneyD(amount);
        BaseBeanHelper.insert(vd1);
        AccountingVoucherMapper.insert(vd1);
         //插入会计分录-贷方
        AccountingVoucher vc1 = new AccountingVoucher();
        vc1.setTeamCount(teamCount);
        vc1.setPeriod(period);
        vc1.setSubstract(content);
        vc1.setAcode(CreditAcount);
        vc1.setAname(CreditAcount);
        vc1.setMoneyC(amount);
        BaseBeanHelper.insert(vc1);
        AccountingVoucherMapper.insert(vc1);

    }

   //yang 一个巨长的逻辑代码：自动记账
    public void voucherMaker(String teamCount, int period , BigDecimal amount, String voucherType,String content) {
        Example example = new Example(AccountingVoucher.class);
        Example.Criteria criteria = example.createCriteria();
        switch (voucherType)
        {
           case "CHANGDAI":  //长贷的自动会计分录
               voucherMakerBase(teamCount,period,"现金","长期负债",amount,content);
               break;
           case "DUANDAI":  //短贷的会计分录
               voucherMakerBase(teamCount,period,"现金","短期负债",amount,content);
               break;
            case "CPYF": //产品研发的
                voucherMakerBase(teamCount,period,"综合费用","现金",amount,content);
                 break;

        case "SCKF":  //市场研发的会计分录
                voucherMakerBase(teamCount,period,"综合费用","现金",amount,content);
                break;
            case "ISOZZ": //ISO认证的会计分录
                voucherMakerBase(teamCount,period,"综合费用","现金",amount,content);
               break;

            //产品交货，生成主营业务收入，应收账款的会计分录
            case "JH": //交货的会计分录
                voucherMakerBase(teamCount,period,"应收款","销售收入",amount,content);
                break;
            //原材料入库，生成借原料，贷现金的会计分录
            case "CLRK": //材料入库的会计分录
                voucherMakerBase(teamCount,period,"原料","现金",amount,content);
               break;


            //销售收款，生成借现金，贷应收款的会计分录
            case "XSSK": //材料入库的会计分录
                voucherMakerBase(teamCount,period,"现金","应收款",amount,content);
                break;
            //销售出库。
            case "XSCK":
                voucherMakerBase(teamCount,period,"直接成本","成品",amount,content);
                break;


            //出售厂房，生成借应收款，贷应收款的会计分录
            case "CSCF": //出售厂房的会计分录
                voucherMakerBase(teamCount,period,"应收款","土地与建筑",amount,content);
                break;


            //购买厂房，生成借土地与建筑，贷现金的会计分录
            case "GMCF": //购买厂房的会计分录
                voucherMakerBase(teamCount,period,"土地与建筑","现金",amount,content);
                break;


            //生产线建造，生成借在建工程，贷现金的会计分录
            case "ZJGC":
                voucherMakerBase(teamCount,period,"在建工程","现金",amount,content);
               break;
            //生产线建造完成。在建工程转出到“机器与设备”
            case "ZJGCZC":
                voucherMakerBase(teamCount,period,"机器与设备","在建工程",amount,content);
                break;


            //生产领料。
            case "SCCK":
                voucherMakerBase(teamCount,period,"在制品","原料",amount,content);

                break;
            //生产人工费。
            case "SCRGF":
                voucherMakerBase(teamCount,period,"在制品","现金",amount,content);
                break;


            //卖生产线收回残值。
            case "SHCZ":
                voucherMakerBase(teamCount,period,"现金","机器与设备",amount,content);
                break;

            //卖出生产线的损失的会计凭证.借其它支出贷机器与设备
            case "SS":
                voucherMakerBase(teamCount,period,"其它支出","机器与设备",amount,content);
                break;

             //自动生成转产费用的凭证。借综合费用 贷现金
            case "SCXZC":
                voucherMakerBase(teamCount,period,"综合费用","现金",amount,content);
                break;



     }

    }
}
