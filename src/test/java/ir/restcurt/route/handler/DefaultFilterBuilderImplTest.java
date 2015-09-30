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

package ir.restcurt.route.handler;

import ir.restcurt.http.HttpMethod;
import ir.restcurt.route.builder.FilterBuilder;
import ir.restcurt.route.builder.DefaultFilterBuilderImpl;
import ir.restcurt.route.mapping.FilterMapping;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author  Hamid Samani
 * @since 0.0.1
 */
public class DefaultFilterBuilderImplTest {


    @Test
    public void filtersCreatedAsExpected() {
        FilterBuilder builder = new DefaultFilterBuilderImpl("/persons");
        builder.afterGet("/:id/", (req, res) -> req.variable("id"));

        final FilterMapping mapping = builder.getFilterMappings().iterator().next();

        assertThat(mapping.getType(), is(FilterMapping.FilterType.AFTER));
        assertThat(mapping.getMethod(), is(HttpMethod.GET));
        assertThat(mapping.getPath(), is("/persons/:id/"));
    }

}