package me.mason.springbatch.example.console.step;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.LogConstants;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * 读取，字符串读取
 * @author  mason
 * @since  2019/6/1
 **/
@Slf4j
public class StringReader implements ItemReader<String> {
    private String[] messages = {"aaa1","aaa2","aaa3","aaa4"};
    private int count = 0;
    @Override
    public String read() throws UnexpectedInputException, ParseException, NonTransientResourceException {
        if(count < messages.length){
            String message = messages[count++];
            log.debug(LogConstants.LOG_TAG + "read data:"+message);
            return message;
        }else{
            log.debug(LogConstants.LOG_TAG + "read data end.");
            count = 0;
        }
        return null;
    }
}
