package cn.yht.simpleOA.model;

import cn.yht.simpleOA.service.PrivilegeService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by YHT on 2015/5/16.
 */
public class Privilege implements Serializable {
    private Long id;
    private String name;
    private String url;

    private Set<Role> roles = new HashSet<>();
    private Privilege parent;
    private List<Privilege> children = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Privilege() {
    }

    public Privilege(String name, String url, Privilege parent) {
        this.name = name;
        this.url = url;
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Privilege getParent() {
        return parent;
    }

    public void setParent(Privilege parent) {
        this.parent = parent;
    }

    public List<Privilege> getChildren() {
        return children;
    }

    public void setChildren(List<Privilege> children) {
        this.children = children;
    }
}
