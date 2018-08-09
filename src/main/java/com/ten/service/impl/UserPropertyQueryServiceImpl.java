//package com.wang.service.impl;
//
//import com.wang.bean.doo.UserPropertyDO;
//import com.wang.bean.dto.UserVO;
//import com.wang.bean.dto.UserPropertyVO;
//import com.wang.constant.Page;
//import com.wang.dao.DAOFactory;
//import com.wang.dao.UserIconDAO;
//import com.wang.dao.UserPropertyDAO;
//import com.wang.dao.jdbcimpl.JdbcDAOFactory;
//import com.wang.service.UserPropertyQueryService;
//import com.wang.service.util.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.sql.SQLException;
//
///**
// * 查询用户资料
// * <p>
// * 失败 Page.PAGE_USERHOME
// * 成功 Page.PAGE_USERHOME
// *
// * @date 2018/5/24
// * @auther ten
// */
//class UserPropertyQueryServiceImpl implements UserPropertyQueryService {
//
//    private Logger logger = LoggerFactory.getLogger(UserPropertyQueryServiceImpl.class);
//
//    private UserPropertyQueryServiceImpl() {
//    }
//
//    @Override
//    public ServiceResult execute(HttpServletRequest req, HttpServletResponse resp) {
//
//        UserVO userDTO = (UserVO) req.getSession().getAttribute("user");
//        Integer userId = userDTO.getUserId();
//
//        DAOFactory factory = new JdbcDAOFactory();
//        UserPropertyDAO propertyDAO = (UserPropertyDAO) factory.getDaoByTableName("user_property");
//        UserIconDAO iconDAO = (UserIconDAO) factory.getDaoByTableName("user_icon");
//
//        UserPropertyDO userPropertyDO = null;
//        String userIconPath = null;
//        boolean success = false;
//        try {
//            userPropertyDO = propertyDAO.queryUserProperty(userId);
//            userIconPath = iconDAO.queryUserIcon(userId);
//            success = true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        if (!success) {
//            return new ServiceResult.Builder(false)
//                    .errormsg("数据库查询异常").page(Page.PAGE_USERHOME).build();
//        }
//
//        if (userPropertyDO == null && userIconPath == null) {
//            return new ServiceResult.Builder(false)
//                    .errormsg("用户信息为空").page(Page.PAGE_USERHOME).build();
//        }
//
//        assert userPropertyDO != null;
//        UserPropertyVO userPropertyDTO = new UserPropertyVO.Builder(userId)
//                .nickname(userPropertyDO.getPropertyNickname())
//                .signature(userPropertyDO.getPropertySignature())
//                .sex(userPropertyDO.getPropertySex())
//                .email(userPropertyDO.getPropertyEmail())
//                .birthday(userPropertyDO.getPropertyBirthday())
//                .location(userPropertyDO.getPropertyLocation())
//                .iconPath(userIconPath)
//                .build();
//
//        req.getSession().setAttribute("userProperty", userPropertyDTO);
//        return new ServiceResult.Builder(true).page(Page.PAGE_USERHOME).build();
//    }
//
//    @Override
//    public UserPropertyDO queryUserProperty(int userId) {
//        return null;
//    }
//}