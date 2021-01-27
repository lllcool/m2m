package com.post.common.xss;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * 过滤XSS工具
 *
 * @author ljm
 * @date 2021/01/25
 */
public class XSSUtil {

    public static String clean(String value) {
        if (value == null) {
            return null;
        }
        // 允许base64格式的图片,字符串不进行美化
        return Jsoup.clean(value, "",
                Whitelist.basicWithImages().addProtocols("img", "src", "data"),
                new Document.OutputSettings().prettyPrint(false));
    }

}

