package com.anodtester.model.data;

import com.anodtester.globals.ConfigsGlobal;
import com.anodtester.model.LoginPOJO;


public class LoginPOJO_Builder {

    public static LoginPOJO getDataLogin(){
        return LoginPOJO.builder()
                .username(ConfigsGlobal.USERNAME)
                .password(ConfigsGlobal.PASSWORD)
                .build();
    }

}
