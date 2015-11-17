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

package ir.restcurt.route.mapping;

import ir.restcurt.http.HttpMethod;
import ir.restcurt.route.handler.Handler;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class CompositeMappingTest {
    private Handler handler = mock(Handler.class);

    @Test
    public void filtersSelectedAsExpected() {
        final String path = "/persons/:id";
        FilterMapping filterMapping = new FilterMapping(path, HttpMethod.GET, handler, FilterMapping.FilterType.AFTER);
        FilterMapping filterMapping2 = new FilterMapping(path, HttpMethod.GET, handler, FilterMapping.FilterType.BEFORE);
        RouteMapping routeMapping = new RouteMapping.RouteMappingBuilder()
                .path(path).method(HttpMethod.GET).handler(handler).build();

        CompositeMapping compositeMapping = new CompositeMapping();
        compositeMapping.setRouteMapping(routeMapping);
        compositeMapping.setFilterMappings(new HashSet<>(Arrays.asList(filterMapping, filterMapping2)));

        assertThat(compositeMapping.getAfterFilterMappings(), hasItem(filterMapping));
        assertThat(compositeMapping.getBeforeFilterMappings(), hasItem(filterMapping2));
    }

    @Test
    public void testAddingAnyFilters() {
        FilterMapping afterAnyGetFilter = new FilterMapping(null, HttpMethod.GET, handler, FilterMapping.FilterType.AFTER);
        RouteMapping routeMapping = new RouteMapping.RouteMappingBuilder().path("/persons").method(HttpMethod.GET).handler(handler).build();

        CompositeMapping compositeMapping = new CompositeMapping();
        compositeMapping.setRouteMapping(routeMapping);
        compositeMapping.setFilterMappings(new HashSet<>(Arrays.asList(afterAnyGetFilter)));

        assertThat(compositeMapping.getAfterFilterMappings(), hasItem(afterAnyGetFilter));
    }
}
