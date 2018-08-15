package net.weizhiyin.base.service.impl;

import net.weizhiyin.base.entity.User;
import net.weizhiyin.base.mapper.UserMapper;
import net.weizhiyin.base.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Huang zhineng
 * @since 2018-08-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
