/**
 * 文件名：Admin
 * 作者：liuzeming
 * 时间：2020/6/21 12:10
 * 描述：
 */

package com.order.system.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Package: com.order.system.pojo
 * @ClassName: Admin
 * @Author: liuzeming
 * @Description: 账户
 * @Date: 2020/6/21 12:10
 */
public class Admin implements Serializable {
    private Long id;

    private String name;

    private String password;

    private String salt;

    private Integer status;

    private String address;

    private String phone;

    private Date lasttime;

    private List<Role> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
