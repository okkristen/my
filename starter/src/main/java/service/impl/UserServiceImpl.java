package service.impl;

import entity.User;
import mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author okkristen
 * @date 2021年03月27日 23:21
 */
@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    TestMapper testMapper;


    @Override
    public List<User> findAll(User user) {
        return testMapper.findAll(user);
//        List<User> list = new ArrayList<>();
//        User user1  = new User();
//        user1.setUserId("111");
//        list.add(user1);
//        return  list;
    }
}
