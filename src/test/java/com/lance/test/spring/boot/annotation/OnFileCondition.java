package com.lance.test.spring.boot.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.net.URL;
import java.util.Map;

/**
 * 文件条件
 *
 * @author Lance
 * @since 2021/1/6
 */
public class OnFileCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnFile.class.getName());
        String[] value = (String[]) annotationAttributes.get("value");
        for (String filename : value) {
            if (!checkFile(filename)) {
                return ConditionOutcome.noMatch("File is not exist: " + filename);
            }
        }
        return ConditionOutcome.match();
    }

    private boolean checkFile(String filename) {
        URL resource = OnFileCondition.class.getClassLoader().getResource(filename);
        return resource != null;
    }
}
