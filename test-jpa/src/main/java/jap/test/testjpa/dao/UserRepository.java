package jap.test.testjpa.dao;

import jap.test.testjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 类名： UserRepository
 * 描述 TODO：
 * 创建时间： 2022/5/20 14:40
 * 创建人： Administrator
 */

//继承JpaRepository来完成对数据库的操作
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select truename from User where username = :username")
    String findByEmailAddress(String username);
}