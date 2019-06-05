package me.mason.springbatch.service;

import org.springframework.stereotype.Service;

/**
 * console服务类
 * @author mason
 * @date 2019/6/1
 */
@Service
public class ConsoleService {

    /**
     * 把字符串转为大写
     * @param str 需要转换的字符串
     * @return 转为大写后的字符串
     */
    public String convert2UpperCase(String str){
        return str.toUpperCase();
    }
}
