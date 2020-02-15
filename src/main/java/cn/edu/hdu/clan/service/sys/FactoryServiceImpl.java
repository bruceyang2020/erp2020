package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Factory;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.FactoryMapper;
import cn.edu.hdu.clan.util.Jurisdiction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private FactoryMapper FactoryMapper;

    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Resource
    private SalepaymentService  salepaymentService;


    //购买厂房
    @Transactional
    @Override
    public void add(Factory Factory) {
        String userTeam = Jurisdiction.getUserTeam();
        int period  = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        BigDecimal  myMoney = Factory.getMoneyTotal();
        String  number = Factory.getNumber();
        Factory.setPeriod(period);
        Factory.setTeamCount(userTeam);
        Factory.setGroupId("1000");
        BaseBeanHelper.insert(Factory);
        FactoryMapper.insert(Factory);
        //自动生成收款的会计凭证
        accountingVoucherService.voucherMaker(userTeam,period, myMoney,"GMCF",number+"购买");
    }

    @Override
    public void delete(String id) {
    FactoryMapper.deleteByPrimaryKey(id);
    }

    //出售厂房。
    @Override
    public void sale(String userTeam ,int period,String number) {
        Example example = new Example(Factory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("number", number);
        criteria.andEqualTo("state", 1);//只有状态为1,自有的厂房才能卖掉。
        List<Factory> factorys = FactoryMapper.selectByExample(example);

        if(factorys.size() == 1)
        {
            BigDecimal myMoney = factorys.get(0).getMoneyTotal();

            //出售厂房，得到对应原值的应收账款。
            salepaymentService.addBySaleFactory(factorys.get(0));

            //自动生成收款的会计凭证
            accountingVoucherService.voucherMaker(userTeam,period, myMoney,"CSCF",number+"出售");


            //记账：出售厂房，借应收款  贷土地与建筑

            FactoryMapper.selectByExample(example);
        }



    }

    @Override
    public void copyDataToNextPeriod(String userTeam ,int period ,int nextPeriod) {
        Example example = new Example(Factory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        List<Factory> factorys = FactoryMapper.selectByExample(example);

        if(factorys.size() > 0)
        {
            for(int i= 0 ;i<factorys.size();i++)
            {
                Factory myRow =  factorys.get(i);
                myRow.setPeriod(nextPeriod);
                BaseBeanHelper.insert(myRow);
                FactoryMapper.insert(myRow);

            }
        }



    }

    @Override
    public void update(Factory Factory) {
        BaseBeanHelper.edit(Factory);
        Example example = new Example(Factory.class);
        example.createCriteria().andEqualTo("id", Factory.getId());
        FactoryMapper.updateByExampleSelective(Factory, example);
    }

    @Override
    public List<Factory> list(String userTeam ,int period) {
        Example example = new Example(Factory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        return FactoryMapper.selectByExample(example);
    }

    @Override
    public Factory getById(String id) {
        Example example = new Example(Factory.class);
        example.createCriteria().andEqualTo("id", id);
        return FactoryMapper.selectOneByExample(example);
    }
}
