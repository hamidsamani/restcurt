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

package ir.restcurt.route.mapping.repository;

import java.util.HashSet;
import java.util.Set;

import ir.restcurt.route.mapping.RouteMapping;
import ir.restcurt.util.Assert;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class DefaultRouteMappingRepository implements RouteMappingRepository {

    private Set<RouteMapping> allMappings = new HashSet<>();

    /*
     * (non-Javadoc)
     * 
     * @see
     * ir.restcurt.route.handler.repository.RouteHandlerRepository#add(java.util
     * .Set)
     */
    @Override
    public void add(Set<RouteMapping> mappings) {

        Assert.hasValue(mappings, "mappings should have at least one value");
        allMappings.addAll(mappings);

    }

    /*
     * (non-Javadoc)
     * 
     * @see ir.restcurt.route.handler.repository.RouteHandlerRepository#
     * getAllMappings()
     */
    @Override
    public Set<RouteMapping> getAllMappings() {

        return allMappings;
    }

}
