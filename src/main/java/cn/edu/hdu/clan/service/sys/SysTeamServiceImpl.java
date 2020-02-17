package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.*;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.SysTeamMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import net.sf.json.JSONArray;
import cn.edu.hdu.clan.util.PropertiesUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysTeamServiceImpl implements SysTeamService {

    @Autowired
    private SysTeamMapper SysTeamMapper;


    @Resource
    private  FactoryService factoryService;
    @Resource
    private  ProductLineService productLineService;
    @Resource
    private  SalepaymentService salepaymentService;
    @Resource
    private LongTermLoansService longTermLoansService;
    @Resource
    private InvService invService;
    @Resource
    private ResearchFeeService researchFeeService;
    @Resource
    private MaterialOrderService materialOrderService;
    @Resource
    private AccountBalanceService accountBalanceService;
    @Resource
    private BalancesheetService balancesheetService;
    @Resource
    private IncomesheetService incomesheetService;


    @Transactional
    @Override
    public void add(SysTeam SysTeam) {
        BaseBeanHelper.insert(SysTeam);
        SysTeamMapper.insert(SysTeam);
    }

    @Override
    public void delete(String id) {
    SysTeamMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SysTeam SysTeam) {
        BaseBeanHelper.edit(SysTeam);
        Example example = new Example(SysTeam.class);
        example.createCriteria().andEqualTo("id", SysTeam.getId());
        SysTeamMapper.updateByExampleSelective(SysTeam, example);
    }

    @Override
    public PageInfo<SysTeam> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(SysTeamMapper.selectAll());
    }

    @Override
    public SysTeam getById(String id) {
        Example example = new Example(SysTeam.class);
        example.createCriteria().andEqualTo("id", id);
        return SysTeamMapper.selectOneByExample(example);
    }

    @Override
    public void reloadData(String userTeam,int period) {







         //初始化厂房数据
          String jsonStr = PropertiesUtils.readJsonFile("jsondata/initFatory.json");
          JSONArray json = JSONArray.fromObject(jsonStr);
          List<Factory> factories= (List<Factory>)JSONArray.toCollection(json, Factory.class);
          factoryService.deleteByTeamCount(userTeam);
          factoryService.adds(factories);
          System.out.println(json);

        //初始化生产线
        String jsonStr1 = PropertiesUtils.readJsonFile("jsondata/initProductLine.json");
        JSONArray json1 = JSONArray.fromObject(jsonStr1);
        List<ProductLine> productLines= (List<ProductLine>)JSONArray.toCollection(json1, ProductLine.class);
        productLineService.deleteByTeamCount(userTeam);
        productLineService.adds(productLines);
        System.out.println(json);


        //初始化应收账款。这里的应收账款也包括了出售厂房产生的应收账款
        String jsonStr2 = PropertiesUtils.readJsonFile("jsondata/initSalepayment.json");
        JSONArray json2 = JSONArray.fromObject(jsonStr2);
        List<Salepayment> salepayments= (List<Salepayment>)JSONArray.toCollection(json1, Salepayment.class);
        salepaymentService.deleteByTeamCount(userTeam);
        salepaymentService.adds(salepayments);
        System.out.println(json2);


        //初始化应收账款。这里的应收账款也包括了出售厂房产生的应收账款
        String jsonStr3 = PropertiesUtils.readJsonFile("jsondata/initLongTermLoan.json");
        JSONArray json3 = JSONArray.fromObject(jsonStr3);
        List<LongTermLoans> longTermLoanList= (List<LongTermLoans>)JSONArray.toCollection(json3, LongTermLoans.class);
        longTermLoansService.deleteByTeamCount(userTeam);
        longTermLoansService.adds(longTermLoanList);
        System.out.println(json3);

        //存货。产品P1-3 .原料 3
        String jsonStr4 = PropertiesUtils.readJsonFile("jsondata/initInv.json");
        JSONArray json4 = JSONArray.fromObject(jsonStr4);
        List<Inv> invs= (List<Inv>)JSONArray.toCollection(json4, LongTermLoans.class);
        invService.deleteByTeamCount(userTeam);
        invService.adds(invs);
        System.out.println(json4);



        //PI生产资格
        //存货。产品P1-3 .原料 3
        String jsonStr5 = PropertiesUtils.readJsonFile("jsondata/initResearchFee.json");
        JSONArray json5 = JSONArray.fromObject(jsonStr5);
        List<ResearchFee> researchFees= (List<ResearchFee>)JSONArray.toCollection(json5, ResearchFee.class);
        researchFeeService.deleteByTeamCount(userTeam);
        researchFeeService.adds(researchFees);
        System.out.println(json5);


        //原材料定单
        String jsonStr6 = PropertiesUtils.readJsonFile("jsondata/initMaterialOrder.json");
        JSONArray json6 = JSONArray.fromObject(jsonStr6);
        List<MaterialOrder> materialOrders= (List<MaterialOrder>)JSONArray.toCollection(json6, MaterialOrder.class);
        materialOrderService.deleteByTeamCount(userTeam);
        materialOrderService.adds(materialOrders);
        System.out.println(json5);

        //科目余额表
        String jsonStr7 = PropertiesUtils.readJsonFile("jsondata/initAccountBalance.json");
        JSONArray json7 = JSONArray.fromObject(jsonStr7);
        List<AccountBalance> accountBalanceList= (List<AccountBalance>)JSONArray.toCollection(json7, AccountBalance.class);
        accountBalanceService.deleteByTeamCount(userTeam);
        accountBalanceService.adds(accountBalanceList);
        System.out.println(json7);

        //资产负债表

        String jsonStr8 = PropertiesUtils.readJsonFile("jsondata/initBalancesheetjson");
        JSONArray json8 = JSONArray.fromObject(jsonStr8);
        List<Balancesheet> balancesheets= (List<Balancesheet>)JSONArray.toCollection(json7, Balancesheet.class);
        balancesheetService.deleteByTeamCount(userTeam);
        balancesheetService.adds(balancesheets);
        System.out.println(json8);

        //利润表。

        String jsonStr9 = PropertiesUtils.readJsonFile("jsondata/initIncomesheet.json");
        JSONArray json9 = JSONArray.fromObject(jsonStr9);
        List<Incomesheet> incomesheets= (List<Incomesheet>)JSONArray.toCollection(json7, Incomesheet.class);
        incomesheetService.deleteByTeamCount(userTeam);
        incomesheetService.adds(incomesheets);
        System.out.println(json9);



    }
}
