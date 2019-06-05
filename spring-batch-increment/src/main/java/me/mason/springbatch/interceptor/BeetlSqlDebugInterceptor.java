package me.mason.springbatch.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.beetl.sql.ext.DebugInterceptor;

/**
 * beetlsql日志输出到logback文件中
 * @author mason
 * @date 2019/6/1
 **/
@Slf4j
public class BeetlSqlDebugInterceptor extends DebugInterceptor {
    @Override
    protected void println(String str) {
        log.debug(System.lineSeparator() + str);
    }
}
