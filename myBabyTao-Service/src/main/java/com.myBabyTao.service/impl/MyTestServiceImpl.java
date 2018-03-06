package com.myBabyTao.service.impl;

import com.myBabyTao.api.service.MyTestService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/12/26.
 */
@Service("myTestService")
public class MyTestServiceImpl implements MyTestService {

    @Override
      public String getHellow() {
        return "Hellow";
    }
}
