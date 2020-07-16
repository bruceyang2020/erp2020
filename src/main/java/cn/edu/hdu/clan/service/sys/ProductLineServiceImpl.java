package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.BaseBean;
import cn.edu.hdu.clan.entity.sys.Factory;
import cn.edu.hdu.clan.entity.sys.ProductLine;
import cn.edu.hdu.clan.entity.sys.ResearchFee;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.FactoryMapper;
import cn.edu.hdu.clan.mapper.sys.ProductLineMapper;
import cn.edu.hdu.clan.util.Jurisdiction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.genid.GenId;

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
    private ResearchFeeService researchFeeService;

    @Resource
    private ProductLineService productLineService;

    @Resource
    private FactoryService factoryService;



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
                int process=myRow.getProcessingCycleB()+1;
                myRow.setPeriod(nextPeriod);
                //H 操作初始化
                myRow.setEditFlag(0);

                switch (myRow.getState()) {
                    case 0:         //在建
                        BaseBeanHelper.insert(myRow);
                        ProductLineMapper.insert(myRow);
                        break;

                    case 1:         //在产
                        //H 对于生产完成的生产线期末结转
                        if (myRow.getProcessingCycleB().equals(myRow.getProcessingCycle())) {
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
                        else{
                            myRow.setProcessingCycleB(process);
                        }
                        BaseBeanHelper.insert(myRow);
                        ProductLineMapper.insert(myRow);
                        break;

                    case 2:  //停产

                        BaseBeanHelper.insert(myRow);
                        ProductLineMapper.insert(myRow);
                        break;


                    case 3:   //转产


                        if(myRow.getProductLineTypeId().equals("全自动")&&myRow.getTransferredPeriodA()!=2){

                            //H  自动线转产两期
                            myRow.setState(3);
                            BaseBeanHelper.insert(myRow);
                            ProductLineMapper.insert(myRow);
                        }
                        //H 转产一期，转产周期重置
                        else{
                            myRow.setTransferredPeriodA(0);
                            myRow.setTransferFeeA(BigDecimal.valueOf(0));
                            //H 状态转为停产
                            myRow.setState(2);
                            BaseBeanHelper.insert(myRow);
                            ProductLineMapper.insert(myRow);
                        }
                            break;

                    case 4: //出售
                        break;

                }
            }
        }
    }



    //通过一个生产线的信息，获得后台完整的生产线信息。productLineNumber取值范围为1-10

    public ProductLine productLineRow(ProductLine producptLine) {
        String userTeam = Jurisdiction.getUserTeam();
        String factoryNumber = producptLine.getFactoryNumber();
        String productLineNumber = producptLine.getProductLineNumber();
        int period =  producptLine.getPeriod();
        Example example = new Example(ProductLine.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("factoryNumber", factoryNumber);
        criteria.andEqualTo("productLineNumber", productLineNumber);
        criteria.andEqualTo("teamCount", userTeam);
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

            factoryService.leftCapacity(period,userTeam,factoryNumber);// 生产线数加一

        }else
        {
            myRow.setInvestmentAmountA(myRow.getInvestmentAmountA().add(BigDecimal.valueOf(5))); //投入建设，每期+5M
            myRow.setEditFlag(1);
            BaseBeanHelper.edit(myRow);
            ProductLineMapper.updateByPrimaryKey(myRow);



        }
        ProductLine myRow2 = productLineRow(productLine);
        BigDecimal value1= myRow2.getDeviceValue(); //设备原值
        BigDecimal value2= myRow2.getInvestmentAmountA();//总投资额 每期投5
        if(value1.compareTo(value2) ==0) {

            //自动生成在建工程对应的会计凭证
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("5"), "ZJGC", factoryNumber + productLineNumber + productLineType);
            ////自动生成在建工程转出到“机器与设备”对应的会计凭证:在建工程转出
            accountingVoucherService.voucherMaker(userTeam, period, value1, "ZJGCZC", factoryNumber + productLineNumber + productLineType+"转出");//H content不能与上面一致，否则删除

           myRow2.setState(2);//H 投产完成变成停产
            BaseBeanHelper.edit(myRow2);
            ProductLineMapper.updateByPrimaryKey(myRow2);

        }else if(value1.compareTo(value2) ==1)
        {
            //自动生成在建工程对应的会计凭证
            accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("5"), "ZJGC", factoryNumber + productLineNumber + productLineType);

        }



    }

//投入产品到生产线
    @Override
    public String inputToProduce(ProductLine productLine) {

        String myMsg="OK";

        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        String factoryNumber = productLine.getFactoryNumber();
        String productLineNumber = productLine.getProductLineNumber();
        String productC=productLine.getProductC();

        ProductLine myRow = productLineRow(productLine);

        int processingCycleBe = myRow.getProcessingCycleB();
        String productLineType = myRow.getProductLineTypeId();

        List<ResearchFee> productRow= researchFeeService.listByperiod(userTeam,period-1,productC);
        if(productRow.get(0).getState()==1) {

            //手工线柔性线投产直接转了，其他进不来
            if (productLineType.equals("手工线") || productLineType.equals("柔性线")) {
                myRow.setProductC(productC);
            }


            myRow.setProcessingCycleB(1); //投入生产期间

            myRow.setEditFlag(1); //操作

            if (myRow.getState() == 2 && processingCycleBe == 0)//H 停产转投产
            {

                myRow.setState(1);// 在产

                String myProduct = myRow.getProductC();
                switch (myProduct) {
                    case "P1":
                        invService.stockOutToProduce(userTeam, period, "R1", 1, factoryNumber + productLineNumber + productLineType + "P1R1");
                        break;

                    case "P2":
                        invService.stockOutToProduce(userTeam, period, "R1", 1, factoryNumber + productLineNumber + productLineType + "P2R1");
                        invService.stockOutToProduce(userTeam, period, "R3", 1, factoryNumber + productLineNumber + productLineType + "P2R3");
                        break;

                    case "P3":
                        invService.stockOutToProduce(userTeam, period, "R2", 2, factoryNumber + productLineNumber + productLineType + "P3R2");
                        invService.stockOutToProduce(userTeam, period, "R3", 1, factoryNumber + productLineNumber + productLineType + "P3R3");
                        break;

                    case "P4":
                        invService.stockOutToProduce(userTeam, period, "R2", 1, factoryNumber + productLineNumber + productLineType + "P4R2");
                        invService.stockOutToProduce(userTeam, period, "R3", 1, factoryNumber + productLineNumber + productLineType + "P4R3");
                        invService.stockOutToProduce(userTeam, period, "R4", 2, factoryNumber + productLineNumber + productLineType + "P4R4");
                        break;
                }

                //自动生成投入生产的会计凭证.借在制品1 贷现金
                accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("1"), "SCRGF", factoryNumber + productLineNumber + productLineType + myProduct);
            }

            BaseBeanHelper.edit(myRow);
            ProductLineMapper.updateByPrimaryKey(myRow);
        }
        else{
            myMsg="False";
        }
      return myMsg;
    }


//出售生产线
    @Override
    public void sale(ProductLine productLine) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        String factoryNumber = productLine.getFactoryNumber();
        String productLineNumber = productLine.getProductLineNumber();


        ProductLine myRow = productLineRow(productLine);

        BigDecimal value1 = myRow.getDeviceValue(); //设备原值
        BigDecimal value2 = myRow.getDeprecationA(); //已提折旧
        BigDecimal value3 = value1.subtract(value2); //账面净值
        BigDecimal value4 = new BigDecimal(0);
        String productLineType = myRow.getProductLineTypeId();

        myRow.setState(4); //状态码设置为4.表示这条生产线已卖掉。
        myRow.setEditFlag(1);
        BaseBeanHelper.edit(myRow);
        ProductLineMapper.updateByPrimaryKey(myRow);

        switch (productLineType) {
            case "手工线":
                //自动生成收回生产线残值的会计凭证.借现金1 贷机器与设备
                accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("1"), "SHCZ", factoryNumber + productLineNumber + productLineType + "收回残值");
                value4 = value3.subtract(new BigDecimal(1));
                break;


            case "半自动":
                //自动生成收回生产线残值的会计凭证.借现金1 贷机器与设备
                accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("2"), "SHCZ", factoryNumber + productLineNumber + productLineType + "收回残值");
                value4 = value3.subtract(new BigDecimal(2));
                break;

            case "全自动"://自动生成收回生产线残值的会计凭证.借现金1 贷机器与设备
                accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("3"), "SHCZ", factoryNumber + productLineNumber + productLineType + "收回残值");
                value4 = value3.subtract(new BigDecimal(3));
                break;

            case "柔性线"://自动生成收回生产线残值的会计凭证.借现金1 贷机器与设备
                accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("4"), "SHCZ", factoryNumber + productLineNumber + productLineType + "收回残值");
                value4 = value3.subtract(new BigDecimal(4));
                break;
        }

        if(value4.compareTo(new BigDecimal(0)) == 1)  //当未提折旧减去残值后的值大于零。
        {
            //卖出生产线的损失的会计凭证.借其它支出贷机器与设备
            accountingVoucherService.voucherMaker(userTeam, period, value4,"SS", factoryNumber + productLineNumber + productLineType+"卖出生产线损失");
        }

    }

    //转产准备
    @Override
    public String switching(ProductLine productLine) {
        String myMsg="OK";


        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        String factoryNumber = productLine.getFactoryNumber();
        String productLineNumber = productLine.getProductLineNumber();
        String productC=productLine.getProductC();


        ProductLine myRow = productLineRow(productLine);
        String productLineType = myRow.getProductLineTypeId();

        switch (productLineType) {

            case "手工线":
                myRow.setProductC(productC); //H 更新新的产品
                myRow.setState(2); //Y 将手工线改成“停产”状态。
                myRow.setProcessingCycleB(0);
                BaseBeanHelper.edit(myRow);
                ProductLineMapper.updateByPrimaryKey(myRow);
                myMsg="tostop"; //手工线不需要转产，直接变成停产状态
                break;

            case "柔性线":
                myRow.setProductC(productC); //H 更新新的产品
                myRow.setState(2);  //Y 将柔性线改成“停产”状态。
                myRow.setProcessingCycleB(0);
                BaseBeanHelper.edit(myRow);
                ProductLineMapper.updateByPrimaryKey(myRow);
                myMsg="tostop";//柔性线不需要转产，直接变成停产状态
                break;

            case "半自动":
                myRow.setState(3);
                myRow.setEditFlag(1);
                myRow.setProductC(productC); //H 更新新的产品
                myRow.setTransferredPeriodA(1);
                myRow.setTransferFeeA(new BigDecimal(1));
                BaseBeanHelper.edit(myRow);
           /* Example example = new Example(ProductLine.class);
            example.createCriteria().andEqualTo("id", myRow.getId());*/
                ProductLineMapper.updateByPrimaryKey(myRow);

                //自动生成转产费用的凭证。借综合费用 贷现金  注意：这里跟会计的基本方法不一样。为了生产成本的标准成本不被破坏，把本应列入制造费用的金额放到综合费用里处理。
                accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("1"), "SCXZC", factoryNumber + productLineNumber + productLineType + "转产");
                break;


            case "全自动":

                if(listDetail(productLine).get(0).getTransferredPeriodA()==1) {
                    //H 第二次转产
                    myRow.setState(3);
                    myRow.setEditFlag(1);
                    /*myRow.setProductC(productC); //H 更新新的产品*/
                    myRow.setTransferredPeriodA(2);
                    myRow.setTransferFeeA(new BigDecimal(4)); //全自动线的转产费用为每期2M
                }
                else{
                    //H 第一次转产
                    myRow.setState(3);
                    myRow.setEditFlag(1);
                    myRow.setProductC(productC); //H 更新新的产品
                    myRow.setTransferredPeriodA(1);
                    myRow.setTransferFeeA(new BigDecimal(2)); //全自动线的转产费用为每期2M
                }
                BaseBeanHelper.edit(myRow);
                ProductLineMapper.updateByPrimaryKey(myRow);
                //自动生成转产费用的凭证。借综合费用 贷现金  注意：这里跟会计的基本方法不一样。为了生产成本的标准成本不被破坏，把本应列入制造费用的金额放到综合费用里处理。
                accountingVoucherService.voucherMaker(userTeam, period, new BigDecimal("2"), "SCXZC", factoryNumber + productLineNumber + productLineType + "转产");
                break;
        }

        return myMsg;

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

    //H 专门计算折旧+维修 在建0 投产1 转产3 出售4 空闲2
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
                            //H 只有当累计折旧大于残值时继续折旧
                            if (myList.get(i).getDeprecationA().compareTo(new BigDecimal(1))==1){
                                myList.get(i).setDepreciationC(new BigDecimal(1));//折旧
                                myList.get(i).setDeprecationA(myList.get(i).getDeprecationA().add(myList.get(i).getDepreciationC()));//累计折旧
                            }
                            myList.get(i).setMaintenanceFeeC(new BigDecimal(1));//维修费
                            myList.get(i).setMaintenanceFeeA(myList.get(i).getMaintenanceFeeA().add(myList.get(i).getMaintenanceFeeC()));//累计维修费

                            break;
                        case "半自动":
                            //H 只有当累计折旧大于残值时继续折旧
                            if (myList.get(i).getDeprecationA().compareTo(new BigDecimal(2))==1){
                                myList.get(i).setDepreciationC(new BigDecimal(2));//折旧
                                myList.get(i).setDeprecationA(myList.get(i).getDeprecationA().add(myList.get(i).getDepreciationC()));//累计折旧
                            }

                            myList.get(i).setMaintenanceFeeC(new BigDecimal(1));//维修费
                            myList.get(i).setMaintenanceFeeA(myList.get(i).getMaintenanceFeeA().add(myList.get(i).getMaintenanceFeeC()));//累计维修费
                            break;
                        case "全自动":
                            //H 只有当累计折旧大于残值时继续折旧
                            if (myList.get(i).getDeprecationA().compareTo(new BigDecimal(3))==1){
                                myList.get(i).setDepreciationC(new BigDecimal(3));//折旧
                                myList.get(i).setDeprecationA(myList.get(i).getDeprecationA().add(myList.get(i).getDepreciationC()));//累计折旧
                            }
                            myList.get(i).setMaintenanceFeeC(new BigDecimal(1));//维修费
                            myList.get(i).setMaintenanceFeeA(myList.get(i).getMaintenanceFeeA().add(myList.get(i).getMaintenanceFeeC()));//累计维修费
                            break;
                        case "柔性线":
                            //H 只有当累计折旧大于残值时继续折旧
                            if (myList.get(i).getDeprecationA().compareTo(new BigDecimal(4))==1){
                                myList.get(i).setDepreciationC(new BigDecimal(4));//折旧
                                myList.get(i).setDeprecationA(myList.get(i).getDeprecationA().add(myList.get(i).getDepreciationC()));//累计折旧
                            }
                            myList.get(i).setMaintenanceFeeC(new BigDecimal(1));//维修费
                            myList.get(i).setMaintenanceFeeA(myList.get(i).getMaintenanceFeeA().add(myList.get(i).getMaintenanceFeeC()));//累计维修费
                            break;

                    }
                    BaseBeanHelper.edit(myList.get(i));
                    ProductLineMapper.updateByPrimaryKey(myList.get(i));

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

        if(period%4==0)
          {
            for (int i = 0; i < myList.size(); i++) {
                sumDepreciation=sumDepreciation.add(myList.get(i).getDepreciationC());
            }

        }
        accountingVoucherService.voucherMaker(userTeam,period,sumDepreciation,"ZJFY","计提折旧费用");
    }

    //H 维修费记账
    public  void voucherMakerOfMT(String userTeam,int period){
        Example example= new Example(ProductLine.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount",userTeam);
        criteria.andEqualTo("period", period);
        List<ProductLine> myList = ProductLineMapper.selectByExample(example);

        BigDecimal sumMaintenance =new BigDecimal("0");
        productLineService.getDepreciation(userTeam,period);

        if(period%4==0)
        {
            for (int i = 0; i < myList.size(); i++) {
                if( myList.get(i).getMaintenanceFeeC() != null) {
                    sumMaintenance = sumMaintenance.add(myList.get(i).getMaintenanceFeeC());
                }
            }

        }
        accountingVoucherService.voucherMaker(userTeam,period,sumMaintenance,"WXFY","维修费用合计");
    }


    public void countNumberOfProductLines(String userTeam, int period){

        Example example= new Example(ProductLine.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount",userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("factoryNumber", "大厂房");
        int count1= ProductLineMapper.selectCountByExample(example);

        Example example2= new Example(ProductLine.class);
        Example.Criteria criteria1 = example2.createCriteria();
        criteria1.andEqualTo("teamCount",userTeam);
        criteria1.andEqualTo("period", period);
        criteria1.andEqualTo("factoryNumber", "小厂房");
        int count2= ProductLineMapper.selectCountByExample(example2);

        factoryService.numberOfProductLines(period,userTeam,"大厂房",count1);
        factoryService.numberOfProductLines(period,userTeam,"小厂房",count2);

    }
}
