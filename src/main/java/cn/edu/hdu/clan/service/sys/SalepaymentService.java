package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Factory;
import cn.edu.hdu.clan.entity.sys.OrderManagement;
import cn.edu.hdu.clan.entity.sys.Salepayment;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.List;

public interface SalepaymentService {
    void add(Salepayment Salepayment);

    void adds(List<Salepayment>  salepayments);

    void addByOrderManagement(OrderManagement OrderManagement);

    void addBySaleFactory(Factory factory);

    void delete(String id);

    void deleteByTeamCount(String userTeam);

    void deleteByTeamCountAndPeriod(String userTeam ,int period);

    void update(Salepayment Salepayment);

    PageInfo<Salepayment> list(int pageNum, int pageSize);

    Salepayment getById(String id);

    void receivePayment(String userTeam ,int period);

    List <Salepayment> listRec(String userTeam,int period);

    String discountedMoney(int period, String teamCount, BigDecimal amount);

    void copyDataToNextPeriod(String userTeam, int period, int nextPeriod);
}
