package cn.edu.hdu.clan.service.sys;


import cn.edu.hdu.clan.entity.sys.Usury;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.UsuryMapper;
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
public class UsuryServiceImpl implements UsuryService {

    @Autowired
    private UsuryMapper UsuryMapper;

    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Transactional
    @Override
    public void add(Usury Usury) {
        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        //补充相关字段的取值
        Usury.setTeamCount(userTeam);
        Usury.setGroupId("1000");
        //随借随还SurplusPeriod多余
       // Usury.setSurplusPeriod(Usury.getPeriod()+20);

        //删除当前高利贷记录
        Example example = new Example(Usury.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount",Usury.getTeamCount());
        criteria.andEqualTo("period", Usury.getPeriod());
        List<Usury> oldRow = UsuryMapper.selectByExample(example);
        if(oldRow.size() > 0)
        {
            UsuryMapper.deleteByExample(example);
        }

        //提交新增记录，自动生成GUID主键及新增的createuser ,createtime

        BaseBeanHelper.insert(Usury);
        UsuryMapper.insert(Usury);
        //自动生成高利贷会计凭证,同时用于高利贷还款（-）就可
        accountingVoucherService.voucherMaker(userTeam,Usury.getPeriod(),Usury.getMoneyTotal(),"GAOLIDAI","新增高利贷");

    }

    @Override
    public void delete(String id) {
    UsuryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Usury Usury) {
//        BaseBeanHelper.edit(Usury);
        Example example = new Example(Usury.class);
        example.createCriteria().andEqualTo("id", Usury.getId());
        UsuryMapper.updateByExampleSelective(Usury, example);
    }

    @Override
    public PageInfo<Usury> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(UsuryMapper.selectAll());
    }

    @Override
    public Usury getById(String id) {
        Example example = new Example(Usury.class);
        example.createCriteria().andEqualTo("id", id);
        return UsuryMapper.selectOneByExample(example);
    }
    @Override
    public List<Usury> list() {
        return UsuryMapper.selectAll();
    }


    @Override
    public List<Usury> getByUserIdAndPeriod(String userTeam) {
        Example example = new Example(Usury.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        return UsuryMapper.selectByExample(example);
    }

    @Override
    public void voucherMakerOfInterest(String userTeam,int period) {
                //H 按teamCount取出所有高利贷
                Example example = new Example(Usury.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("teamCount", userTeam);
                List<Usury> myList = UsuryMapper.selectByExample(example);
                //H 计算每期结算利息
                BigDecimal usuryInterest = BigDecimal.valueOf(0);
                for (int i = 0; i < myList.size(); i++) {
                    usuryInterest = usuryInterest.add(myList.get(i).getMoneyTotal().multiply(BigDecimal.valueOf(0.05)).setScale(0, BigDecimal.ROUND_DOWN));
                }
                //H BigDecimal大于0等于int 1，防止bug
                if(usuryInterest.compareTo(BigDecimal.valueOf(0))==1) {
                    //H 利息记账
                    accountingVoucherService.voucherMaker(userTeam, period, usuryInterest, "LXFY", "高利贷利息");

                }
    }
    @Override
    public void deleteByTeamCount(String userTeam) {

        //用于初始化，清空高利贷
        Example example = new Example(Usury.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount",userTeam);
        List<Usury> oldRow1 = UsuryMapper.selectByExample(example);
        if(oldRow1.size() > 0)
        {
            UsuryMapper.deleteByExample(example);
        }
    }
}
