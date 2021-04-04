package mapper;

import entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author okkristen
 * @date 2021年03月27日 23:13
 */
@Mapper
public interface TestMapper {

    @Select("  SELECT user_id userId, user_name userName from sys_user where user_id = #{userId} ")
//　　 User selectUserById(@Param("id") String id);
    List<User>  findAll(User user);
}
