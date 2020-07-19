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
        Factory.setTeamCount(userTeam);



        BigDecimal  myMoney = Factory.getMoneyTotal();
        String  number = Factory.getNumber();
        Factory myFactory = FactoryType(period,userTeam,number);

        myFactory.setState(1);
        BaseBeanHelper.edit(myFactory);
        FactoryMapper.updateByPrimaryKey(myFactory);
        //自动生成收款的会计凭证
        accountingVoucherService.voucherMaker(userTeam,period, myMoney,"GMCF",number+"购买");
    }


    //初始化
    @Transactional
    @Override
    public void adds(List<Factory>  factories) {
        if(factories.size() > 0) {
            for (int i = 0; i < factories.size(); i++) {
                String userTeam = Jurisdiction.getUserTeam();
                int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
                Factory factory =  factories.get(i);

                factory.setPeriod(period);
                factory.setTeamCount(userTeam);
                factory.setGroupId("1000");
                BaseBeanHelper.insert(factory);
                FactoryMapper.insert(factory);
            }
        }
    }

    @Override
    public void delete(String id) {
    FactoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByTeamCount(String userTeam) {
        Example example = new Example(Factory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        FactoryMapper.deleteByExample(example);
    }


    @Override
    public void deleteByTeamCountAndPeriod(String userTeam ,int period) {
        Example example = new Example(Factory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        FactoryMapper.deleteByExample(example);

    }


    //出售厂房。
    @Override
    public void sale(Factory Factory) {


       int period= Factory.getPeriod();
        String userTeam = Jurisdiction.getUserTeam();
        String number = Factory.getNumber();
        Factory myFactory= FactoryType(period,userTeam,number);
        if(myFactory.getState() == 1)
        {
            BigDecimal myMoney = myFactory.getMoneyTotal();

            //出售厂房，得到对应原值的应收账款。
            salepaymentService.addBySaleFactory(myFactory);

            //自动生成收款的会计凭证
            accountingVoucherService.voucherMaker(userTeam,period, myMoney,"CSCF",number+"出售");

            //记账：出售厂房，借应收款  贷土地与建筑

            myFactory.setState(0);
            BaseBeanHelper.edit(myFactory);
            FactoryMapper.updateByPrimaryKey(myFactory);

        }
        else if(myFactory.getState()==2){  //H 租赁出售
            myFactory.setState(0);
            BaseBeanHelper.edit(myFactory);
            FactoryMapper.updateByPrimaryKey(myFactory);

        }

    }

    //租厂房。
    @Override
    public void rent(Factory Factory) {


        int period= Factory.getPeriod();
        String userTeam = Jurisdiction.getUserTeam();
        String number = Factory.getNumber();
        Factory myFactory= FactoryType(period, userTeam,number);
        //H 买转租，加一个卖出的步骤
        if(myFactory.getState()==1)
        {
            BigDecimal myMoney = myFactory.getMoneyTotal();

            //出售厂房，得到对应原值的应收账款。
            salepaymentService.addBySaleFactory(myFactory);

            //自动生成收款的会计凭证
            accountingVoucherService.voucherMaker(userTeam,period, myMoney,"CSCF",number+"买转租");

            //记账：出售厂房，借应收款  贷土地与建筑

        }

         //自动生成租金的会计凭证
        accountingVoucherService.voucherMaker(userTeam,period,new BigDecimal( myFactory.getRent()),"CFZL",number+"租赁");

        myFactory.setState(2);
        BaseBeanHelper.edit(myFactory);
        FactoryMapper.updateByPrimaryKey(myFactory);


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


    public Factory FactoryType(int period,String userTeam, String factoryNumber) {   //H 根据厂房信息查找厂房


        Example example = new Example(Factory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("number", factoryNumber);
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);

        return  FactoryMapper.selectOneByExample(example);
    }


    //H 生产线建造时，厂房剩余容量-1
    public void leftCapacity(int period,String userTeam, String factoryNumber){
        Factory myfactory= FactoryType(period,userTeam,factoryNumber);
        int leftCapacity = myfactory.getLeftCapacity();

        myfactory.setLeftCapacity(leftCapacity+1);
        BaseBeanHelper.edit(myfactory);
        FactoryMapper.updateByPrimaryKey(myfactory);

    }
   //H 初始化时数一下有几条线
    public void numberOfProductLines(int period,String userTeam, String factoryNumber,int number){

        Factory myfactory= FactoryType(period,userTeam,factoryNumber);

        myfactory.setLeftCapacity(number);
        BaseBeanHelper.edit(myfactory);
        FactoryMapper.updateByPrimaryKey(myfactory);

    }
}
