package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Factory;
import cn.edu.hdu.clan.entity.sys.ProductLine;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.ProductLineMapper;
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
import java.util.logging.Handler;

@Service
public class     ProductLineServiceImpl implements ProductLineService {

    @Autowired
    private ProductLineMapper ProductLineMapper;

    @Resource
    private AccountingVoucherService accountingVoucherService;
    @Resource
    private InvService invService;

    @Resource
    private ProductLineService productLineService;



    @Transactional
    @Override
    public void add(ProductLine productLine) {
        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        //补充相关字段的取值
        productLine.setTeamCount(userTeam);
        productLine.setGroupId("1000");
        BaseBeanHelper.insert(productLine);
        ProductLineMapper.insert(productLine);
    }

    public void adds(List<ProductLine>  productLines) {
        if(productLines.size() > 0) {
            for (int i = 0; i < productLines.size(); i++) {
                String userTeam = Jurisdiction.getUserTeam();
                int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
                productLines.get(i).setPeriod(period);
                productLines.get(i).setTeamCount(userTeam);
                productLines.get(i).setGroupId("1000");
                BaseBeanHelper.insert(productLines.get(i));
                ProductLineMapper.insert(productLines.get(i));
            }
        }
    }


    @Override
    public void delete(String id) {
    ProductLineMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByTeamCount(String userTeam) {
        Example example = new Example(Factory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        ProductLineMapper.deleteByExample(example);
    }



    //将生产线信息整体复制到下一个会计期间
    @Override

    public void copyDataToNextPeriod(String userTeam ,int period ,int nextPeriod){
        Example example = new Example(ProductLine.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        List<ProductLine> productLines = ProductLineMapper.selectByExample(example);

        if(productLines.size() > 0)
        {
            for(int i= 0 ;i<productLines.size();i++)
            {
                ProductLine myRow =  productLines.get(i);
                myRow.setPeriod(nextPeriod);
                //H 操作初始化
                myRow.setEditFlag(0);

                System.out.println("这是"+myRow.getProductLineNumber()+"号线");
                System.out.println(myRow.getProcessingCycleB());
                System.out.println(myRow.getProcessingCycle());
                System.out.println(myRow.getState());
                //H 对于生产完成的生产线期末结转
                if(myRow.getProcessingCycleB()==myRow.getProcessingCycle()&&myRow.getState()==1){
                    //生产线重置
                    myRow.setProcessingCycleB(0);
                    //生产线停产
                    myRow.setState(2);
                    //产品入库
                    String myProduct = myRow.getProductC();
                    switch (myProduct) {
                        case "P1":
                            invService.stockIntoWarehouse(userTeam, nextPeriod, "P1", 2, myRow.getFactoryNumber() + myRow.getProductLineNumber() + myRow.getProductLineTypeId() + "P1入库");
                            break;
                        case "P2":
                            invService.stockIntoWarehouse(userTeam, nextPeriod, "P2", 3, myRow.getFactoryNumber() + myRow.getProductLineNumber() + myRow.getProductLineTypeId() + "P2入库");
                            break;
                        case "P3":
                            invService.stockIntoWarehouse(userTeam, nextPeriod, "P3", 4, myRow.getFactoryNumber() + myRow.getProductLineNumber() + myRow.getProductLineTypeId() + "P3入库");
                            break;
                        case "P4":
                            invService.stockIntoWarehouse(userTeam, nextPeriod, "P4", 5, myRow.getFactoryNumber() + myRow.getProductLineNumber() + myRow.getProductLineTypeId() + "P4入库");
                            break;

                    }

                }

                BaseBeanHelper.insert(myRow);
                ProductLineMapper.insert(myRow);

            }
        }


    }

    //通过一个生产线的信息，获得后台完整的生产线信息。productLineNumber取值范围为1-10

    public ProductLine productLineRow(ProductLine producptLine) {
        String factoryNumber = producptLine.getFactoryNumber();
        String productLineNumber = producptLine.getProductLineNumber();
        String productLineType = producptLine.getProductLineTypeId();
        int period =  producptLine.getPeriod();
        Example example = new Example(ProductLine.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("factoryNumber", factoryNumber);
        criteria.andEqualTo("productLineNumber", productLineNumber);
        criteria.andEqualTo("period", period);

       return  ProductLineMapper.selectOneByExample(example);
    }

    //投建生产线
    @Override
    public void build(ProductLine productLine) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        String factoryNumber = productLine.getFactoryNumber(); //厂房信息
        String productLineNumber = productLine.getProductLineNumber(); //编号
        String productLineType = productLine.getProductLineTypeId(); //产线类型编码
        String productC = productLine.getProductC(); //H 初始产线对应的产品编号


        ProductLine myRow = productLineRow(productLine);
        if(myRow == null)
        {  //全局变量 写入当前公司或小组ID

            //补充相关字段的取值
            productLine.setTeamCount(userTeam);
            productLine.setGroupId("1000");
            productLine.setPeriod(period);

            //H 初始化相关字段的值
            productLine.setDepreciationC(BigDecimal.valueOf(0));
            productLine.setDeprecationA(BigDecimal.valueOf(0));
            productLine.setInstalledPeriodA(0);
            productLine.setTransferredPeriodA(0);
            productLine.setTransferFeeA(BigDecimal.valueOf(0));
            productLine.setMaintenanceFeeA(BigDecimal.valueOf(0));
            productLine.setProcessingCycleB(0);

            productLine.setPeriodBuy(period);
            productLine.setInvestmentAmountA(BigDecimal.valueOf(5)); //投入建设，每期5M
            productLine.setState(0);
            productLine.setEditFlag(1);
            productLine.setProductC(productC);



            BaseBeanHelper.insert(productLine);
            ProductLineMapper.insert(productLine);


        }else
        {
            myRow.setInvestmentAmountA(myRow.getInvestmentAmountA().add(BigDecimal.valueOf(5))); //投入建设，每期+5M
            myRow.setEditFlag(1);
            BaseBeanHelper.edit(myRow);
            ProductLineMapper.updateByPrimaryKey(myRow);



        }
        ProductLine myRow2 = productLineRow(productLine);
        BigDecimal value1= myRow2.getDeviceValue();
        BigDecimal value2= myRow2.getInvestmentAmountA();
        if(value1.compareTo(value2) ==0) {

            //自动生成在建工程对应的会计凭证
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("5"), "ZJGC", factoryNumber + productLineNumber + productLineType);
            ////自动生成在建工程转出到“机器与设备”对应的会计凭证:在建工程转出
            accountingVoucherService.voucherMaker(userTeam, period, value1, "ZJGCZC", factoryNumber + productLineNumber + productLineType+"转出");//H content不能与上面一致，否则删除

           myRow.setState(2);//H 投产完成变成停产
            BaseBeanHelper.edit(myRow);
            ProductLineMapper.updateByPrimaryKey(myRow);

        }else if(value1.compareTo(value2) ==1)
        {
            //自动生成在建工程对应的会计凭证
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("5"), "ZJGC", factoryNumber + productLineNumber + productLineType);

        }





    }

//投入产品到生产线
    @Override
    public void inputToProduce(ProductLine productLine) {
        String userTeam = Jurisdiction.getUserTeam();

        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());

        String factoryNumber = productLine.getFactoryNumber();
        String productLineNumber = productLine.getProductLineNumber();
       String productLineType = productLine.getProductLineTypeId();
        int processingCycleB = productLine.getProcessingCycleB()+1;//H 投产生产期间+1

        ProductLine myRow = productLineRow(productLine);
        myRow.setProcessingCycleB(processingCycleB); //投入生产期间

        myRow.setEditFlag(1); //操作
        BaseBeanHelper.edit(myRow);
        ProductLineMapper.updateByPrimaryKey(myRow);

        String myProduct = myRow.getProductC();
        switch (myProduct){
            case "P1":
            invService.stockOutToProduce(userTeam,period,"R1",1,factoryNumber + productLineNumber + productLineType+"P1R1");
            break;

            case "P2":
            invService.stockOutToProduce(userTeam,period,"R2",1,factoryNumber + productLineNumber + productLineType+"P2R2");
            invService.stockOutToProduce(userTeam,period,"R3",1,factoryNumber + productLineNumber + productLineType+"P2R3");
            break;

            case "P3":
            invService.stockOutToProduce(userTeam,period,"R2",2,factoryNumber + productLineNumber + productLineType+"P3R2");
            invService.stockOutToProduce(userTeam,period,"R3",1,factoryNumber + productLineNumber + productLineType+"P3R3");
            break;

            case "P4":
            invService.stockOutToProduce(userTeam,period,"R2",1,factoryNumber + productLineNumber + productLineType+"P4R2");
            invService.stockOutToProduce(userTeam,period,"R3",1,factoryNumber + productLineNumber + productLineType+"P4R3");
            invService.stockOutToProduce(userTeam,period,"R4",2,factoryNumber + productLineNumber + productLineType+"P4R4");
            break;
        }

        //自动生成投入生产的会计凭证.借在制品1 贷现金
        accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("1"), "SCRGF", factoryNumber + productLineNumber + productLineType+myProduct);
    }


//出售生产线
    @Override
    public void sale(ProductLine productLine) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        String factoryNumber = productLine.getFactoryNumber();
        String productLineNumber = productLine.getProductLineNumber();
        String productLineType = productLine.getProductLineTypeId();

        ProductLine myRow = productLineRow(productLine);

        BigDecimal value1= myRow.getDeviceValue(); //设备原值
        BigDecimal value2= myRow.getDeprecationA(); //已提折旧
        BigDecimal value3= value1.subtract(value2); //账面净值
        BigDecimal value4= new BigDecimal(0);

        myRow.setState(4); //状态码设置为4.表示这条生产线已卖掉。
        myRow.setEditFlag(1);
        BaseBeanHelper.edit(myRow);
        Example example = new Example(ProductLine.class);
        example.createCriteria().andEqualTo("id", myRow.getId());
        ProductLineMapper.updateByExampleSelective(myRow, example);


        if(productLineType == "手工线")
        {
            //自动生成收回生产线残值的会计凭证.借现金1 贷机器与设备
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("1"), "SHCZ", factoryNumber + productLineNumber + productLineType+"收回残值");
            value4 = value3.subtract(new BigDecimal(1));

        }
        if(productLineType == "半自动")
        {
            //自动生成收回生产线残值的会计凭证.借现金1 贷机器与设备
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("2"), "SHCZ", factoryNumber + productLineNumber + productLineType+"收回残值");
            value4 = value3.subtract(new BigDecimal(2));
        }
        if(productLineType == "全自动")
        {
            //自动生成收回生产线残值的会计凭证.借现金1 贷机器与设备
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("3"), "SHCZ", factoryNumber + productLineNumber + productLineType+"收回残值");
            value4 = value3.subtract(new BigDecimal(3));
        }
        if(productLineType == "柔性线")
        {
            //自动生成收回生产线残值的会计凭证.借现金1 贷机器与设备
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("4"), "SHCZ", factoryNumber + productLineNumber + productLineType+"收回残值");
            value4 = value3.subtract(new BigDecimal(4));
        }

        if(value4.compareTo(new BigDecimal(0)) == 1)  //当未提折旧减去残值后的值大于零。
        {
            //卖出生产线的损失的会计凭证.借其它支出贷机器与设备
            accountingVoucherService.voucherMaker(userTeam, period, value4,"SS", factoryNumber + productLineNumber + productLineType+"卖出生产线损失");
        }


    }

    //转产准备
    @Override
    public void switching(ProductLine productLine) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        String factoryNumber = productLine.getFactoryNumber();
        String productLineNumber = productLine.getProductLineNumber();
        String productLineType = productLine.getProductLineTypeId();

        ProductLine myRow = productLineRow(productLine);

        if(productLineType == "半自动")
        {
            myRow.setState(3);
            myRow.setEditFlag(1);
            myRow.setTransferredPeriodA(1);
            myRow.setTransferFeeA(new BigDecimal(1));
            BaseBeanHelper.edit(myRow);
            Example example = new Example(ProductLine.class);
            example.createCriteria().andEqualTo("id", myRow.getId());
            ProductLineMapper.updateByExampleSelective(myRow, example);

            //自动生成转产费用的凭证。借综合费用 贷现金  注意：这里跟会计的基本方法不一样。为了生产成本的标准成本不被破坏，把本应列入制造费用的金额放到综合费用里处理。
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("1"), "SCXZC", factoryNumber + productLineNumber + productLineType+"转产");


        }

        if(productLineType == "全自动")
        {
            myRow.setState(3);
            myRow.setEditFlag(1);
            myRow.setTransferredPeriodA(1);
            myRow.setTransferFeeA(new BigDecimal(2)); //全自动线的转产费用为每期2M
            BaseBeanHelper.edit(myRow);
            Example example = new Example(ProductLine.class);
            example.createCriteria().andEqualTo("id", myRow.getId());
            ProductLineMapper.updateByExampleSelective(myRow, example);

            //自动生成转产费用的凭证。借综合费用 贷现金  注意：这里跟会计的基本方法不一样。为了生产成本的标准成本不被破坏，把本应列入制造费用的金额放到综合费用里处理。
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("2"), "SCXZC", factoryNumber + productLineNumber + productLineType+"转产");


        }


    }

    @Override
    public void update(ProductLine ProductLine) {
        BaseBeanHelper.edit(ProductLine);
        Example example = new Example(ProductLine.class);
        example.createCriteria().andEqualTo("id", ProductLine.getId());
        ProductLineMapper.updateByExampleSelective(ProductLine, example);
    }


    //显示当前所有的生产线信息
    @Override
    public List<ProductLine> list() {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        Example example = new Example(ProductLine.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andNotEqualTo("state", 4);  //反回的生产线信息里， state=4 表示该线在本期已卖掉了。
        return  ProductLineMapper.selectByExample(example);
    }

    //显示指定生产线信息的明细信息
    @Override
    public List<ProductLine> listDetail(ProductLine productLine) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        String myProductLineNumber = productLine.getProductLineNumber();
        Example example= new Example(ProductLine.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("productLineNumber", myProductLineNumber);  //
        List<ProductLine> myListDetail = ProductLineMapper.selectByExample(example);
        return  myListDetail;
    }

    @Override
    public ProductLine getById(String id) {
        Example example = new Example(ProductLine.class);
        example.createCriteria().andEqualTo("id", id);
        return ProductLineMapper.selectOneByExample(example);
    }

    //H 专门计算折旧 在建0 投产1 转产3 出售4 空闲2
    public void getDepreciation(String userTeam,int period)
    {
        //选出
        Example example= new Example(ProductLine.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount",userTeam);
        criteria.andEqualTo("period", period);
        List<ProductLine> myList = ProductLineMapper.selectByExample(example);
        //判断状态 根据生产线种类赋值 设置DepC 并累加DepA
        if(period%4==0) {
            for (int i = 0; i < myList.size(); i++) {
                int mystate = myList.get(i).getState();
                if (mystate != 0 && mystate != 4) {
                    String productionLineType = myList.get(i).getProductLineTypeId();
                    switch (productionLineType) {
                        case "手工线":
                            myList.get(i).setDepreciationC(new BigDecimal(1));
                            myList.get(i).setDeprecationA(myList.get(i).getDeprecationA().add(myList.get(i).getDeprecationA()));//不知道是不是这样写 A=A+C？

                            break;
                        case "半自动":
                            myList.get(i).setDepreciationC(new BigDecimal(1));
                            myList.get(i).setDeprecationA(myList.get(i).getDeprecationA().add(myList.get(i).getDeprecationA()));
                            break;
                        case "全自动":
                            myList.get(i).setDepreciationC(new BigDecimal(1));
                            myList.get(i).setDeprecationA(myList.get(i).getDeprecationA().add(myList.get(i).getDeprecationA()));
                            break;
                        case "柔性线":
                            myList.get(i).setDepreciationC(new BigDecimal(1));
                            myList.get(i).setDeprecationA(myList.get(i).getDeprecationA().add(myList.get(i).getDeprecationA()));
                            break;

                    }

                }
            }
        }

    }

    //H 折旧记账
    public  void voucherMakerOfDep(String userTeam,int period){
        Example example= new Example(ProductLine.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount",userTeam);
        criteria.andEqualTo("period", period);
        List<ProductLine> myList = ProductLineMapper.selectByExample(example);

        BigDecimal sumDepreciation =new BigDecimal("0");
        productLineService.getDepreciation(userTeam,period);

        if(period%4==0){
            for (int i = 0; i < myList.size(); i++) {
                sumDepreciation.add(myList.get(i).getDeprecationA());
            }

        }
        accountingVoucherService.voucherMaker(userTeam,period,sumDepreciation,"ZJFY","计提折旧费用");
    }
}
