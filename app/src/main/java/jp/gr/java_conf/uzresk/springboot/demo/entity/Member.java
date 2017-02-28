package jp.gr.java_conf.uzresk.springboot.demo.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;

import jp.gr.java_conf.uzresk.springboot.framework.dao.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Member extends BaseEntity {

    @Id
    private String userId;

    private String password;

    private String name;

    private String authority;

    private String gender;

//    @Version
//    private Long version;
}
