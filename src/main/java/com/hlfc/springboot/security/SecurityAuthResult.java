package com.hlfc.springboot.security;

/**
 * 描述
 *
 * @author redlwb
 * @date 2018/6/7
 */
public class SecurityAuthResult {
    /**
     * 认证通过
     */
    public static final String OK = "200";
    /**
     * 权限过期
     */
    public static final String UNAUTH = "299";
    /**
     * 密码错误
     */
    public static final String USERNAME_OR_PASSWORD_INVALID = "201";

    /**
     * 未输入用户名或者密码
     */
    public static final String USERNAME_OR_PASSWORD_NULL = "203";
    /**
     * 密码重试次数最大
     */
    public static final String PASSWORD_RETRY_MAX = "202";

    private String code;
    private String msg;
    private String url;

    SecurityAuthResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    SecurityAuthResult(String code, String msg, String url) {
        this.code = code;
        this.msg = msg;
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static SecurityAuthResult OK(String url) {
        return new SecurityAuthResult(OK, "登陆成功。", url);
    }

    public static SecurityAuthResult TRYMAX() {
        return new SecurityAuthResult(PASSWORD_RETRY_MAX, "密码输入错误超过10次，请30分钟后再重试。");
    }

    public static SecurityAuthResult UNAUTH() {
        return new SecurityAuthResult(UNAUTH, "登陆过期或者您还未登陆，请重新登陆。");
    }

    public static SecurityAuthResult INVALID() {
        return new SecurityAuthResult(USERNAME_OR_PASSWORD_INVALID, "用户名或者密码错误。");
    }

    public static SecurityAuthResult NULLINPUT() {
        return new SecurityAuthResult(USERNAME_OR_PASSWORD_NULL, "用户名或者密码未输入。");
    }

    public static SecurityAuthResult INVALID(String msg) {
        return new SecurityAuthResult(USERNAME_OR_PASSWORD_INVALID, msg);
    }
}
