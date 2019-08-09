package me.mason.springbatch.example.mysql2mongo.step;

import me.mason.springbatch.entity.mongo.MongoUser;
import org.springframework.batch.item.data.MongoItemWriter;

import java.util.List;

/**
 * MongoUser写入类
 *
 * @author mason
 * @since 2019/8/9
 */
public class UserItemWriter extends MongoItemWriter<MongoUser> {
    @Override
    public void write(List<? extends MongoUser> items) throws Exception {
        super.write(items);
    }
}
