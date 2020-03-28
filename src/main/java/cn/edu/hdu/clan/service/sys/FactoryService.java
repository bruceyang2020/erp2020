package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Factory;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface FactoryService {
    void add(Factory Factory);

    void adds(List<Factory>  factories);

    void delete(String id);

    void deleteByTeamCount(String userTeam);

    void update(Factory Factory);

    void sale(Factory Factory);

    List<Factory> list(String userTeam ,int period);

    Factory getById(String id);

    void  copyDataToNextPeriod(String userTeam ,int period ,int nextPeriod);

    Factory FactoryType(int period,String userTeam, String factoryNumber);

   void rent(Factory Factory);//H 租厂房

   void leftCapacity(int period,String userTeam, String factoryNumber); //厂房容量

    void numberOfProductLines(int period,String userTeam, String factoryNumber,int number);
}
