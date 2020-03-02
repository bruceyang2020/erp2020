package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Inv;
import cn.edu.hdu.clan.util.Jurisdiction;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.InvMapper;
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
public class InvServiceImpl implements InvService {

    @Autowired
    private InvMapper InvMapper;

    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Transactional
    @Override
    public void add(Inv Inv) {
        BaseBeanHelper.insert(Inv);
        InvMapper.insert(Inv);
    }

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
