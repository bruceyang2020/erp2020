package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.SandtableManual;
import cn.edu.hdu.clan.entity.sys.SandtableOrder;
import cn.edu.hdu.clan.entity.sys.ShortTermLoan;

import java.util.List;

public interface SandtableOrderService {

    String adds(String groupId);

    String add(SandtableOrder sandtableOrder);

    String del(SandtableOrder sandtableOrder);

    List<SandtableOrder> listByGroupId (String groupId, int period);


    List<SandtableOrder> listByGroupIdAndUserId(String groupId,String userId,int period);

}
