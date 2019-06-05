package me.mason.springbatch.example.increment.step;

import me.mason.springbatch.dao.target.TargetUserRepository;
import me.mason.springbatch.entity.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User写入类
 *
 * @author mason
 * @since 2019/5/31
 */
public class IncrementUserItemWriter implements ItemWriter<User> {
    @Autowired
    private TargetUserRepository targetUserRepository;

    @Override
    public void write(List<? extends User> items) throws Exception {
        targetUserRepository.getSQLManager().updateBatch("user.insertIncreUser",items);
    }
}
