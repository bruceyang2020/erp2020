package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.SandtableManual;

public interface SandtableManualService {
    void add(SandtableManual sandtableManual);

    void delByUserIdAndPeriod(SandtableManual sandtableManual);

    void reloadByUserIdAndPeriod(SandtableManual sandtableManual);

    SandtableManual  findByUserIdAndPeriod(String userId ,int period);
}
