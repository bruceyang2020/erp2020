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

    @Transactional
    @Override
    public void add(IsoFee IsoFee) {

        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        //补充相关字段的取值
        IsoFee.setTeamCount(userTeam);
        IsoFee.setGroupId("1000");
        IsoFee.setPeriodLeft(1);

        //删除当前市场开发的记录
        Example example = new Example(IsoFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", IsoFee.getTeamCount());
        criteria.andEqualTo("period", IsoFee.getPeriod());
        criteria.andEqualTo("number", IsoFee.getNumber());
        List<IsoFee> oldRow = IsoFeeMapper.selectByExample(example);
        if(oldRow.size() > 0)
        {
            IsoFeeMapper.deleteByExample(example);
        }

        //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
        BaseBeanHelper.insert(IsoFee);
        IsoFeeMapper.insert(IsoFee);

        String isoNumber = IsoFee.getNumber();

        switch (isoNumber)
        {
            case "ISO9K":
                //自动生成ISO9K会计凭证
                accountingVoucherService.voucherMaker(userTeam,IsoFee.getPeriod(),new BigDecimal("200"),"ISOZZ","ISO9K");
                break;

            case "ISO14K":
                //自动生成ISO14K会计凭证
                accountingVoucherService.voucherMaker(userTeam,IsoFee.getPeriod(),new BigDecimal("400"),"ISOZZ","ISO14K");
                break;

        }
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
    public List<IsoFee> list(String userTeam ,int period) {
        //Y 列表查询ISO的信息。
        Example example = new Example(IsoFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        return IsoFeeMapper.selectByExample(example);
    }

    @Override
    public void deleteByPeriod(String userTeam,Integer period,String number) {
        //H 删除ISO认证的行，并删除本期会计凭证
        Example example = new Example(IsoFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("number", number);
        List<IsoFee> oldRow = IsoFeeMapper.selectByExample(example);
        if (oldRow.size() > 0) {
            IsoFeeMapper.deleteByExample(example);
        }
        accountingVoucherService.deleteByPeriodAndContent(userTeam,period,number);
    }


    @Override
    public void copyDataToNextPeriod(String userTeam, int period, int nextPeriod) {
        Example example = new Example(IsoFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        List<IsoFee> factorys = IsoFeeMapper.selectByExample(example);

        if (factorys.size() > 0) {
            for (int i = 0; i < factorys.size(); i++) {
                IsoFee myRow = factorys.get(i);
                myRow.setPeriod(nextPeriod);
                BaseBeanHelper.insert(myRow);
                IsoFeeMapper.insert(myRow);

            }
        }

    }
}
