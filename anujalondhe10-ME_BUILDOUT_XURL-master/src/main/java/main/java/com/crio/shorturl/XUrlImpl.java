package main.java.com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;
import com.crio.shorturl.XUrl;

public class XUrlImpl implements XUrl {
    Map<String, String> map1 = new HashMap<>();
    Map<String, String> map2 = new HashMap<>();
    Map<String, Integer> map3 = new HashMap<>();

    @Override
    public String registerNewUrl(String LongUrl) {
        if (map1.containsKey(LongUrl)) {
            map3.put(LongUrl, map3.getOrDefault(LongUrl, 0) + 1);
            return map1.get(LongUrl);
        }
        String shortUrl = "http://short.url/" + RandGeneratedStr(9);
        map1.put(LongUrl, shortUrl);
        map2.put(shortUrl, LongUrl);
        map3.put(LongUrl, map3.getOrDefault(LongUrl, 0) + 1);
        return shortUrl;
    }

    @Override
    public String registerNewUrl(String LongUrl, String ShortUrl) {
        if (map2.containsKey(ShortUrl)) {
            map3.put(LongUrl, map3.getOrDefault(LongUrl, 0) + 1);
            return null;
        }
        map1.put(LongUrl, ShortUrl);
        map2.put(ShortUrl, LongUrl);
        map3.put(LongUrl, map3.getOrDefault(LongUrl, 0) + 1);
        return ShortUrl;
    }

    @Override
    public String getUrl(String shortUrl) {
        if (!map2.containsKey(shortUrl)) {
            return null;
        }
        String longUrl = map2.get(shortUrl);
        return longUrl;
    }

    @Override
    public Integer getHitCount(String LongUrl) {
        if (!map3.containsKey(LongUrl)) {
            return 0;
        }
        return map3.get(LongUrl);
    }

    @Override
    public String delete(String longUrl) {
        String shortUrl = map1.get(longUrl);
        map1.remove(longUrl);
        map2.remove(shortUrl);
        return null;
    }

    static String RandGeneratedStr(int l) {
        String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
        StringBuilder s = new StringBuilder(l);
        for (int i = 0; i < l; i++) {
            int ch = (int) (AlphaNumericStr.length() * Math.random());
            s.append(AlphaNumericStr.charAt(ch));
        }
        return s.toString();
    }
    
}
