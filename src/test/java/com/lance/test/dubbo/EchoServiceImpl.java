package com.lance.test.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service
@Component
public class EchoServiceImpl implements IEchoService {

    @Override
    public String echo(String msg) {
        return msg;
    }
}
