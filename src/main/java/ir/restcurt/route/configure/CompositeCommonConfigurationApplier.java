/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ir.restcurt.route.configure;

import ir.restcurt.route.builder.ConfigurationBuilder;
import ir.restcurt.route.builder.DefaultConfigurationBuilderImpl;
import ir.restcurt.route.mapping.CompositeMapping;
import ir.restcurt.util.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class CompositeCommonConfigurationApplier implements CommonConfigurer {
    private Set<CommonConfigurer> commonConfigurers = new HashSet<>();
    private DefaultConfigurationBuilderImpl configurationBuilder;

    public CompositeCommonConfigurationApplier(ConfigurationBuilder configurationBuilder) {
        Assert.notNull(configurationBuilder, "configurationBuilder should not be null");
        this.configurationBuilder = (DefaultConfigurationBuilderImpl) configurationBuilder;
        buildNewCommonConfigurers();
    }

    private void buildNewCommonConfigurers() {
        if (configurationBuilder.getVersion() != null && !configurationBuilder.getVersion().isEmpty())
            this.commonConfigurers.add(new VersioningConfigurer(configurationBuilder.getVersion()));
        if (configurationBuilder.getRootPath() != null && !configurationBuilder.getRootPath().isEmpty())
            this.commonConfigurers.add(new RootPathConfigurer(configurationBuilder.getRootPath()));
    }

    @Override
    public void apply(CompositeMapping compositeMapping) {
        for (CommonConfigurer configurer : commonConfigurers) {
            configurer.apply(compositeMapping);
        }
    }
}
