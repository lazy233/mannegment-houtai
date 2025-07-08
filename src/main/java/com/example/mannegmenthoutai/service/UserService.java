package com.example.mannegmenthoutai.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mannegmenthoutai.dto.LoginResponse;
import com.example.mannegmenthoutai.dto.PageRequest;
import com.example.mannegmenthoutai.dto.UserAddResponse;
import com.example.mannegmenthoutai.dto.UserPageResponse;
import com.example.mannegmenthoutai.dto.UserQueryRequest;
import com.example.mannegmenthoutai.dto.UserUpdateRequest;
import com.example.mannegmenthoutai.dto.UserUpdateResponse;
import com.example.mannegmenthoutai.dto.UserDeleteResponse;
import com.example.mannegmenthoutai.mapper.UserMapper;
import com.example.mannegmenthoutai.model.User;
import com.example.mannegmenthoutai.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserAddResponse addUser(User user) {
        // 检查用户名是否已存在
//        if (userMapper.countByUsername(user.getUsername()) > 0) {
//            throw new RuntimeException("用户名已存在");
//        }
//
//        // 检查邮箱是否已存在
//        if (userMapper.countByEmail(user.getEmail()) > 0) {
//            throw new RuntimeException("邮箱已存在");
//        }

        // 加密用户密码
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        // 设置创建时间
        Date now = new Date();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        userMapper.insert(user);
        return new UserAddResponse(user);
    }

    public User registerUser(User user) {
        // 检查用户名是否已存在
        if (userMapper.countByUsername(user.getUsername()) > 0) {
            throw new RuntimeException("用户名已存在");
        }
        
        // 检查邮箱是否已存在
        if (userMapper.countByEmail(user.getEmail()) > 0) {
            throw new RuntimeException("邮箱已存在");
        }
        
        // 加密用户密码
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        
        // 设置创建时间
        Date now = new Date();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        
        userMapper.insert(user);
        return user;
    }

    public User findUserByEmail(String email) {
        return userMapper.findByEmail(email);
    }
    
    public LoginResponse login(String username, String password) {
        LoginResponse response = new LoginResponse();
        
        // 查找用户
        User user = userMapper.findByUsername(username);
        if (user == null) {
            response.setSuccess(false);
            response.setMessage("用户名不存在");
            return response;
        }
        
        // 验证密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            response.setSuccess(false);
            response.setMessage("密码错误");
            return response;
        }
        
        // 生成JWT令牌
        String token = jwtUtil.generateToken(user.getEmail(), user.getUserId());
        
        // 构建用户信息
        LoginResponse.UserInfo userInfo = new LoginResponse.UserInfo();
        userInfo.setUserId(user.getUserId());
        userInfo.setUsername(user.getUsername());
        userInfo.setEmail(user.getEmail());
        userInfo.setClassId(user.getClassId());
        
        // 设置响应
        response.setSuccess(true);
        response.setMessage("登录成功");
        response.setToken(token);
        response.setUserInfo(userInfo);
        
        return response;
    }

    public UserPageResponse getUserList(PageRequest request) {
        // 创建分页对象
        Page<User> page = new Page<>(request.getPageNum(), request.getPageSize());
        
        // 创建查询条件 - 只查询需要的字段，按创建时间倒序
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_id", "username", "email", "created_at", "updated_at", "class_id");
        
        // 添加搜索条件（如果有的话）
        // if (StringUtils.hasText(request.getUsername())) {
        //     queryWrapper.like("username", request.getUsername());
        // }
        if (request.getUsername() != null) {
            queryWrapper.like("username", request.getUsername());
        }
        
        // 按创建时间倒序排列
        queryWrapper.orderByDesc("created_at");
        
        // 执行分页查询
        IPage<User> userPage = userMapper.selectPage(page, queryWrapper);
        
        // 转换为响应对象
        UserPageResponse response = new UserPageResponse();
        response.setTotal(userPage.getTotal());
        response.setSize(userPage.getSize());
        response.setCurrent(userPage.getCurrent());
        response.setPages(userPage.getPages());
        
        // 转换用户信息
        List<UserPageResponse.UserInfo> userInfoList = userPage.getRecords().stream()
                .map(this::convertToUserInfo)
                .collect(Collectors.toList());
        response.setRecords(userInfoList);
        
        return response;
    }





    public UserUpdateResponse updateUser(Integer userId, UserUpdateRequest request) {
        // 检查用户是否存在
        User existingUser = userMapper.selectById(userId);
        if (existingUser == null) {
            throw new RuntimeException("用户不存在，ID: " + userId);
        }
        
        
        // 更新用户信息
        if (request.getUsername() != null) {
            existingUser.setUsername(request.getUsername());
        }
        if (request.getEmail() != null) {
            existingUser.setEmail(request.getEmail());
        }
        if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
            // 加密新密码
            String encryptedPassword = passwordEncoder.encode(request.getPassword());
            existingUser.setPassword(encryptedPassword);
        }
        if (request.getClassId() != null) {
            existingUser.setClassId(request.getClassId());
        }
        
        // 设置更新时间
        existingUser.setUpdatedAt(new Date());
        
        // 更新数据库
        userMapper.updateById(existingUser);
        
        return new UserUpdateResponse(existingUser);
    }




    public UserDeleteResponse deleteUser(Integer userId) {
        // 检查用户是否存在
        User existingUser = userMapper.selectById(userId);
        if (existingUser == null) {
            throw new RuntimeException("用户不存在，ID: " + userId);
        }
        
        userMapper.deleteById(userId);
        return new UserDeleteResponse(userId);
    }

    private UserPageResponse.UserInfo convertToUserInfo(User user) {
        UserPageResponse.UserInfo userInfo = new UserPageResponse.UserInfo();
        userInfo.setUserId(user.getUserId());
        userInfo.setUsername(user.getUsername());
        userInfo.setEmail(user.getEmail());
        userInfo.setClassId(user.getClassId());
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (user.getCreatedAt() != null) {
            userInfo.setCreatedAt(sdf.format(user.getCreatedAt()));
        }
        if (user.getUpdatedAt() != null) {
            userInfo.setUpdatedAt(sdf.format(user.getUpdatedAt()));
        }
        
        return userInfo;
    }
}
