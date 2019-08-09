package me.mason.springbatch.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

/**
 * user for mongodb
 *
 * @author mason
 * @since 2019/8/9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "user")
public class MongoUser {
    @Id
    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    @Indexed
    private String name;

    /**
     * 手机号
     */
    @Indexed
    private String phone;

    /**
     * 职称职别
     */
    private String title;

    /**
     * 邮箱
     */
    @Indexed
    private String email;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生时间
     */
    private Date dateOfBirth;

    /**
     * sys_create_time
     */
    private Date sysCreateTime;

    /**
     * sys_create_user
     */
    private String sysCreateUser;

    /**
     * sys_update_time
     */
    private Date sysUpdateTime;

    /**
     * sys_update_user
     */
    private String sysUpdateUser;
}
