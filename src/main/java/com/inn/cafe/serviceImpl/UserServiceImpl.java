package com.inn.cafe.serviceImpl;


import com.inn.cafe.DAO.UserDao;
import com.inn.cafe.POJO.User;
import com.inn.cafe.constents.CafeConstan;
import com.inn.cafe.service.UserService;
import com.inn.cafe.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        if(validateSignUpMap(requestMap)){
            User user = userDao.findByIdentityDocument(requestMap.get("identityDocument"));
            if(Objects.isNull(user)){
                userDao.save(getUserFromMap(requestMap));
                return CafeUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
            }else{
                return CafeUtils.getResponseEntity("Documento ya registrado",HttpStatus.BAD_REQUEST);
            }
        }else{
            return CafeUtils.getResponseEntity(CafeConstan.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }
    }
    private boolean validateSignUpMap(Map<String, String> requestMap){
        return requestMap.containsKey("identityDocument") && requestMap.containsKey("name")
                && requestMap.containsKey("contactNumber") && requestMap.containsKey("username") && requestMap.containsKey("password");
    }
    private User getUserFromMap (Map<String, String> requestMap){
        User user = new User();
        user.setIdentityDocument(requestMap.get("identityDocument"));
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setUsername(requestMap.get("username"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
}
