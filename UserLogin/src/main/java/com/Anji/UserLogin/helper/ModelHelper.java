package com.Anji.UserLogin.helper;

import com.Anji.UserLogin.dto.UserBean;
import com.Anji.UserLogin.model.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ModelHelper {

    private final ModelMapper modelMapper;

    @Autowired
    public ModelHelper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserEntity convertToEntity(UserBean userBean) {
        return modelMapper.map(userBean, UserEntity.class);
    }

    public UserBean convertToBean(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserBean.class);
    }
}

