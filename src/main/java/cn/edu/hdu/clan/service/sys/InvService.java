package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Inv;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public interface InvService {
    void add(Inv Inv);

    void adds(List<Inv> invs);

    void delete(String id);

    void deleteByTeamCount(String userTeam);

    void deleteByTeamCountAndPeriod(String userTeam ,int period);

    void update(Inv Inv);

    PageInfo<Inv> list(int pageNum, int pageSize);

    List<Inv> listInv(String userTeam,int period);

    void stockOutToProduce(String userTeam,int period,String product ,int amount,String content);//生产领用原材料出库

    void stockOutToSale(String userTeam,int period,String product ,int amount,String content);  //产品出库

    void stockIntoWarehouse(String userTeam, int period, String product, int amount, String content); //产品入库


    Inv getById(String id);

    void goToPeriod(String userTeam,int nextPeriod);// H 原材料结转和入库

    BigDecimal amountByProductId(String userTeam, int period, String productId);

   void copyDataToNextPeriod(String userTeam, int period, int nextPeriod);
}
