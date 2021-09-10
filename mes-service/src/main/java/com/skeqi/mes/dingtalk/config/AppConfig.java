package com.skeqi.mes.dingtalk.config;

import org.springframework.context.annotation.Configuration;

/**
 * 应用凭证配置
 */
@Configuration
public class AppConfig {
    private String appKey = "dingwhh1gixximnalswg";

    private String appSecret = "JkiQpSFXx6vkc-FRbD7nesEcydbEw48IFrjFSxwo3r77RKKqAbiGOecgN1BoCyQY";

    private String agentId = "1186278641";

    private String corpId = "ding4acdfb4fff883932";

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }
}
