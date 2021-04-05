package hello;


import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import java.util.List;

/**
 * @author okkristen
 * @date 2021年03月27日 17:23
 */
@RestController
public class TestController {

    @Autowired
    private HelloWorld helloWorld;

    @Autowired
    private UserService userService;

    @RequestMapping("okkristen")
    public String aaa() {
        System.out.println("测试策划"+helloWorld.getAge());
        List<User> list = userService.findAll(new User());
        System.out.println(list);
        return  "测试测试" + list.toString();
    }
}
