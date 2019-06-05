package me.mason.springbatch.example.console.step;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.LogConstants;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * 写入，控制台输出转换后的字符
 * @author  mason
 * @since  2019/6/1
 **/
@Slf4j
public class ConsoleWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) {
        for (String msg :list) {
            log.debug(LogConstants.LOG_TAG + "write data: "+msg);
        }
    }
}
