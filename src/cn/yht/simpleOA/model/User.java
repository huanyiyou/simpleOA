package cn.yht.simpleOA.model;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2015/5/13.
 */
public class User implements Serializable {
    private Long id;
    private String loginName; //登录名
    private String name;  //显示名
    private String password;

    private Long[] roleIds;


    private Set<Role> roles = new HashSet<>();
    private Set<Overtime> overtimes = new HashSet<>();
    private Set<PreOvertime> preOvertimes = new HashSet<>();
    private Set<Breaktime> breaktimes = new HashSet<>();
    private Set<OvertimeCount> overtimeCounts = new HashSet<>();



    public boolean hasPrivilegeByName(String name){
        //判断是否是超级管理员
        if(isAdmin()){
            return  true;
        }
        for(Role role : roles){
            for(Privilege privilege : role.getPrivileges()){
                if(name.equals(privilege.getName())){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasPrivilegeByUrl(String url){
        //判断是否是超级管理员
        if(isAdmin()){
            return  true;
        }
        if(isCommonPrivileges(url)){
            return true;
        }
        if(url.endsWith("UI")){
            url = url.substring(0, url.length()-2);
        }
        for(Role role : roles){
            for(Privilege privilege : role.getPrivileges()){
                if(url.equals(privilege.getUrl())){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAdmin(){
        return "admin".equals(loginName);
    }

    private boolean isCommonPrivileges(String url){
        if(url.startsWith("/user/login") || url.startsWith("/home") || url.startsWith("/user/setting")){
            return true;
        }else {
            return false;
        }
    }

    public User(){

    }

    public User(String name) {
        this.name = name;
    }

    public User(String loginName, String name, String password) {
        this.loginName = loginName;
        this.name = name;
        this.password = password;
    }

    public User(String loginName, String name, String password, Set<Role> roles) {
        this.loginName = loginName;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Set<Overtime> getOvertimes() {
        return overtimes;
    }

    public void setOvertimes(Set<Overtime> overtimes) {
        this.overtimes = overtimes;
    }

    public Set<PreOvertime> getPreOvertimes() {
        return preOvertimes;
    }

    public void setPreOvertimes(Set<PreOvertime> preOvertimes) {
        this.preOvertimes = preOvertimes;
    }

    public Set<Breaktime> getBreaktimes() {
        return breaktimes;
    }

    public void setBreaktimes(Set<Breaktime> breaktimes) {
        this.breaktimes = breaktimes;
    }

    public Set<OvertimeCount> getOvertimeCounts() {
        return overtimeCounts;
    }

    public void setOvertimeCounts(Set<OvertimeCount> overtimeCounts) {
        this.overtimeCounts = overtimeCounts;
    }
}
