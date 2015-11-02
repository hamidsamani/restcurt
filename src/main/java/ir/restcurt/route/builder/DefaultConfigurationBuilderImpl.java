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

package ir.restcurt.route.builder;

import ir.restcurt.util.Assert;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class DefaultConfigurationBuilderImpl implements ConfigurationBuilder {
    private String rootPath;
    private String version;

    @Override
    public ConfigurationBuilder rootPath(String path) {
        Assert.hasText(path, "rootPath should has value");
        this.rootPath = path;
        return this;
    }

    @Override
    public ConfigurationBuilder version(String version) {
        Assert.hasText(version, "Version should has version number");
        this.version = version;
        return this;
    }


    public String getRootPath() {
        return rootPath;
    }

    public String getVersion() {
        return version;
    }
}
