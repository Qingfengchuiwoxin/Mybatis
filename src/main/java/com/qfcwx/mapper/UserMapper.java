package com.qfcwx.mapper;

import com.qfcwx.pojo.User;

import java.util.List;

/**
 * @ClassName: Mapper
 * @Author: 清风一阵吹我心
 * @Description: TODO
 * @Date: 2019/4/19 13:17
 * @Version 1.0
 **/
public interface UserMapper {

    User selectUserById(Long id);

    List<User> selectList();
}
