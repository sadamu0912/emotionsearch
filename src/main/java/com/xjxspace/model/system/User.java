package com.xjxspace.model.system;

import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.List;

public class User  {

	private static final long serialVersionUID =6931939270939159104L;

	private long id;

    private String userId;

    /**
     * 姓名
     */
    private String userName;

	/**
	 * 用户账号
	 */
	private String userAccount;

	/**
	 * 密码
	 */
    private String password;

	/**
	 * 是否删除 默认是0 ，1代表删除
	 */
	private Integer enable;

	/**
	 * 创建时间
	 */
    private Date createTime ;

	/**
	 * 上次登录时间
	 */
	private Date lastLoginTime;

	/**
	 * 头像
	 */
	private String avatar;

	private String email;

	private String phoneNumber;

	/**
	 * 第三方账号
	 */
	private String thirdPartyAccount;

	private List<GrantedAuthority> authorityList;

	public List<GrantedAuthority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<GrantedAuthority> authorityList) {
		this.authorityList = authorityList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getThirdPartyAccount() {
		return thirdPartyAccount;
	}

	public void setThirdPartyAccount(String thirdPartyAccount) {
		this.thirdPartyAccount = thirdPartyAccount;
	}
}
