package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.IsoFee;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.IsoFeeMapper;
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
public class IsoFeeServiceImpl implements IsoFeeService {

    @Autowired
    private IsoFeeMapper IsoFeeMapper;
    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Resource
    private IsoFeeService isoFeeService;



    @Transactional
    @Override
   //H ISO初始化
    public void adds(List<IsoFee>  isoFees) {
        if(isoFees.size() > 0) {
            for (int i = 0; i < isoFees.size(); i++) {
                String userTeam = Jurisdiction.getUserTeam();
                int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
                isoFees.get(i).setPeriod(period);
                isoFees.get(i).setTeamCount(userTeam);
                isoFees.get(i).setGroupId("1000");
                BaseBeanHelper.insert(isoFees.get(i));
                IsoFeeMapper.insert(isoFees.get(i));
            }
        }
    }

    //H ISO确定按钮更新
    public void add(IsoFee IsoFee) {

        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        //每一期都有复制，取出原始的数据
        Example example = new Example(IsoFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", IsoFee.getPeriod());
        criteria.andEqualTo("number", IsoFee.getNumber());
        List<IsoFee> updateRow = IsoFeeMapper.selectByExample(example);

        //补充相关字段的取值
        if(updateRow.get(0).getPeriodLeft()>0) {
            updateRow.get(0).setPeriodLeft(updateRow.get(0).getPeriodLeft() - 1);
            System.out.println(updateRow.get(0).getPeriodLeft());
            updateRow.get(0).setState(updateRow.get(0).getPeriodLeft() == 0 ? 1 : 0);//这期开发过了


            //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
            BaseBeanHelper.edit(updateRow.get(0));
            IsoFeeMapper.updateByPrimaryKey(updateRow.get(0));

            String isoNumber = IsoFee.getNumber();

            switch (isoNumber) {
                case "ISO9K":
                    //自动生成ISO9K会计凭证
                    accountingVoucherService.voucherMaker(userTeam, IsoFee.getPeriod(), new BigDecimal("1"), "ISOZZ", "ISO9K");
                    break;

                case "ISO14K":
                    //自动生成ISO14K会计凭证
                    accountingVoucherService.voucherMaker(userTeam, IsoFee.getPeriod(), new BigDecimal("1"), "ISOZZ", "ISO14K");
                    break;

            }
        }
    }

    //H ISO取消投资的按钮更新
    @Override
    public void deleteByPeriod(String userTeam,Integer period,String number) {

        List<IsoFee> oldRow = isoFeeService.listByperiod(userTeam, period - 1, number);
        if (oldRow.get(0).getState() == 0) {
            //补充相关字段的取值
            List<IsoFee> updateRow = isoFeeService.listByperiod(userTeam, period, number);
            updateRow.get(0).setPeriodLeft(updateRow.get(0).getPeriodLeft() + 1);//剩余时间回撤
            updateRow.get(0).setState(updateRow.get(0).getPeriodLeft() == 0 ? 1 : 0);//这期开发过了
            //补充相关字段的取值
            //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
            BaseBeanHelper.edit(updateRow.get(0));
            IsoFeeMapper.updateByPrimaryKey(updateRow.get(0));
            //删除会计凭证
            accountingVoucherService.deleteByPeriodAndContent(userTeam, period, number);
        }
    }

    //H
    @Override
    public void deleteByTeamCount(String userTeam) {
        Example example = new Example(IsoFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        IsoFeeMapper.deleteByExample(example);
    }

    @Override
    public void delete(String id) {
    IsoFeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(IsoFee IsoFee) {
        BaseBeanHelper.edit(IsoFee);
        Example example = new Example(IsoFee.class);
        example.createCriteria().andEqualTo("id", IsoFee.getId());
        IsoFeeMapper.updateByExampleSelective(IsoFee, example);
    }

    @Override
    public PageInfo<IsoFee> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(IsoFeeMapper.selectAll());
    }

    @Override
    public IsoFee getById(String id) {
        Example example = new Example(IsoFee.class);
        example.createCriteria().andEqualTo("id", id);
        return IsoFeeMapper.selectOneByExample(example);
    }

    @Override
    public List<IsoFee> listByperiod(String userTeam ,int period,String number) {
        //H 根据number查询ISO的信息
        Example example = new Example(IsoFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("number", number);
        return IsoFeeMapper.selectByExample(example);
    }

    @Override
    public List<IsoFee> list(String userTeam ,int period) {
        //Y 列表查询ISO的信息。
        Example example = new Example(IsoFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        return IsoFeeMapper.selectByExample(example);
    }


    @Override
    public void copyDataToNextPeriod(String userTeam, int period, int nextPeriod) {
        Example example = new Example(IsoFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        List<IsoFee> isos = IsoFeeMapper.selectByExample(example);

        if (isos.size() > 0) {
            for (int i = 0; i < isos.size(); i++) {
                IsoFee myRow = isos.get(i);
                myRow.setPeriod(nextPeriod);
                myRow.setTakeRight(0);
                BaseBeanHelper.insert(myRow);
                IsoFeeMapper.insert(myRow);

            }
        }

    }
}
