package com.lance.test.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service
@Component
public class SumCalculatorService implements ICalculatorService {

    @Override
    public int execute(int num1, int num2) {
        return num1 + num2;
    }
}
