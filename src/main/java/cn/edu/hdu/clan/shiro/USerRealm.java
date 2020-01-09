package cn.edu.hdu.clan.shiro;


import cn.edu.hdu.clan.entity.sys.SysUser;
import cn.edu.hdu.clan.helper.SessionHelper;
import cn.edu.hdu.clan.service.sys.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class USerRealm extends AuthorizingRealm {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        principalCollection.getPrimaryPrincipal();

        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser user = userService.findByUsernameAndPassword(token.getUsername(), token.getPassword());
        if (user == null) {
            return null;
        }
        SessionHelper.setUser(user);
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), user.getUsername());

    }

}
