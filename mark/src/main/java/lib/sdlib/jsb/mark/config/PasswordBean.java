package lib.sdlib.jsb.mark.config;

import lib.sdlib.jsb.mark.common.PasswordMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 类名： PasswordBean
 * 描述 TODO：
 * 创建时间： 2022/5/31 11:06
 * 创建人： Administrator
 */
@Configuration
public class PasswordBean {

    @Bean("passwordMap")
    public PasswordMap getPasswordKey()
    {
        PasswordMap passwordMap = new PasswordMap();
        passwordMap.put("public","MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKfxM/TEE7Omj62vAwzeA7IfmLGA4uW1EC3SUSZkmhhVHkug/d071qEiWSXpT/6owRLXpMOmMgcRipEMriWu2XsCAwEAAQ==");
        passwordMap.put("private","MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAp/Ez9MQTs6aPra8DDN4Dsh+YsYDi5bUQLdJRJmSaGFUeS6D93TvWoSJZJelP/qjBEtekw6YyBxGKkQyuJa7ZewIDAQABAkEAk5Uri1Uj2yPr7z10+MHBja6miJYyzpom9Z11IsAVinsU2H3ADOmO6ywt8s2XwclDN8A9n7TmyEJJDvqZPXnsQQIhAOD2j//rDpws82fWEx0G6WrGaXlqBEWfJXsn5Qi+p/g/AiEAvxy6HYMLAIpS46lBcCuN9PcIwzGUhhxsJKuZ5qfW78UCIA+7TlCgOHJVdmQRhV6nSBVucJvL/SZ9PZrCLHMTGh3zAiB+8MrizWcibb6O/566rgN1whys4qLfzcM7PExWfAd1bQIhAN2iDrSSWE3/2yA7lQK6jwAhyY6wsoPFHYt1BJAc4xeo");
        return passwordMap;
    }
}
