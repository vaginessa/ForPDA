package forpdateam.ru.forpda.api.auth.models;

import forpdateam.ru.forpda.client.Client;

/**
 * Created by radiationx on 29.07.16.
 */
public class AuthForm {
    private final static String returnField = Client.minimalPage;
    private final static String rememberField = "1";
    private String captchaImageUrl;
    private String captcha;
    private String captchaTime;
    private String captchaSig;
    private String nick;
    private String password;
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCaptchaImageUrl() {
        return captchaImageUrl;
    }

    public void setCaptchaImageUrl(String captchaImageUrl) {
        this.captchaImageUrl = captchaImageUrl;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptchaTime() {
        return captchaTime;
    }

    public void setCaptchaTime(String captchaTime) {
        this.captchaTime = captchaTime;
    }

    public String getCaptchaSig() {
        return captchaSig;
    }

    public void setCaptchaSig(String captchaSig) {
        this.captchaSig = captchaSig;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReturnField() {
        return returnField;
    }

    public String getRememberField() {
        return rememberField;
    }
}
