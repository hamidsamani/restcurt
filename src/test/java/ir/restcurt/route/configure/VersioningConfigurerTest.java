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

import ir.restcurt.http.HttpMethod;
import ir.restcurt.route.mapping.CompositeMapping;
import ir.restcurt.route.mapping.RouteMapping;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class VersioningConfigurerTest {
    @Test
    public void testApplyingVersiontoHandler() {
        RouteMapping rm = RouteMapping.RouteMappingBuilder.route().path("/persons/:id/").method(HttpMethod.GET).build();
        CompositeMapping cm = new CompositeMapping();
        cm.setRouteMapping(rm);
        VersioningConfigurer configurer = new VersioningConfigurer("v4.3");
        configurer.apply(cm);

        assertThat(cm.getPath(), is("/v4.3/persons/:id/"));

        RouteMapping rm2 = RouteMapping.RouteMappingBuilder.route().path("/persons/:id/").method(HttpMethod.GET).build();
        VersioningConfigurer configurer2 = new VersioningConfigurer("/v4.3");
        cm.setRouteMapping(rm2);
        configurer2.apply(cm);

        assertThat(cm.getPath(), is("/v4.3/persons/:id/"));
    }

}