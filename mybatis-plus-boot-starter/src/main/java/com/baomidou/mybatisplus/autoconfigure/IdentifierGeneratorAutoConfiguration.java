/*
 * Copyright (c) 2011-2022, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baomidou.mybatisplus.autoconfigure;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Id生成器自动配置
 *
 * @author nieqiurong 2021/1/29
 * @since 3.4.3
 */
@Lazy
@Configuration(proxyBeanMethods = false)
public class IdentifierGeneratorAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(InetUtils.class)
    public static class InetUtilsAutoConfig {

        @Bean
        @ConditionalOnMissingBean
        public IdentifierGenerator identifierGenerator(InetUtils inetUtils) {
            // 默认的Id生成器
            return new DefaultIdentifierGenerator(inetUtils.findFirstNonLoopbackAddress());
        }
    }
}
