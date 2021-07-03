package com.github.qing.autoconfigure;

import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.Properties;
import java.util.Set;

/**
 * @author qing
 */
public class AcmProperties {
    public static final String PREFIX = "acm";
    private Map<String, EnvProperties> env;
    private String envName;
    private String accessKey;
    private String secretKey;
    private String regionId;
    private String groupId="DEFAULT_GROUP";
    private Set<String> dataIds;
    private static Properties nowAcm;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Set<String> getDataIds() {
        return dataIds;
    }

    public void setDataIds(Set<String> dataIds) {
        this.dataIds = dataIds;
    }

    public Map<String, EnvProperties> getEnv() {
        return env;
    }

    public void setEnv(Map<String, EnvProperties> env) {
        this.env = env;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Properties getAcmProperties() {
        synchronized (AcmProperties.class) {
            if (nowAcm == null) {
                Properties properties = new Properties();
                EnvProperties envProperties = this.getEnv().get(envName);
                properties.put("endpoint", envProperties.getEndpoint());
                properties.put("namespace", envProperties.getNamespace());
                properties.put("accessKey", this.accessKey);
                properties.put("secretKey", this.secretKey);
                if (this.regionId != null) {
                    properties.put("openKMSFilter", true);
                    properties.put("regionId", this.regionId);
                }
                nowAcm = properties;
            }
        }
        return nowAcm;
    }
}
