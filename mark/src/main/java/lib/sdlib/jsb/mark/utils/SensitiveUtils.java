package lib.sdlib.jsb.mark.utils;

import com.alibaba.druid.filter.config.ConfigTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类名： SensitiveUtils
 * 描述 TODO：封闭一下druid自带的加解密码程序
 * 创建时间： 2022/5/31 11:30
 * 创建人： Administrator
 */

public class SensitiveUtils {

     static public String encrypt(String str) throws Exception {
        String privateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAp/Ez9MQTs6aPra8DDN4Dsh+YsYDi5bUQLdJRJmSaGFUeS6D93TvWoSJZJelP/qjBEtekw6YyBxGKkQyuJa7ZewIDAQABAkEAk5Uri1Uj2yPr7z10+MHBja6miJYyzpom9Z11IsAVinsU2H3ADOmO6ywt8s2XwclDN8A9n7TmyEJJDvqZPXnsQQIhAOD2j//rDpws82fWEx0G6WrGaXlqBEWfJXsn5Qi+p/g/AiEAvxy6HYMLAIpS46lBcCuN9PcIwzGUhhxsJKuZ5qfW78UCIA+7TlCgOHJVdmQRhV6nSBVucJvL/SZ9PZrCLHMTGh3zAiB+8MrizWcibb6O/566rgN1whys4qLfzcM7PExWfAd1bQIhAN2iDrSSWE3/2yA7lQK6jwAhyY6wsoPFHYt1BJAc4xeo";
        return ConfigTools.encrypt(privateKey,str);
    }
    static public String decrypt(String str) throws Exception {
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKfxM/TEE7Omj62vAwzeA7IfmLGA4uW1EC3SUSZkmhhVHkug/d071qEiWSXpT/6owRLXpMOmMgcRipEMriWu2XsCAwEAAQ==";
        return ConfigTools.decrypt(publicKey,str);
    }
}
