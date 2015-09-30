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

package ir.restcurt.route.repository;

import ir.restcurt.route.DefaultRouteMatcher;
import ir.restcurt.route.RouteMatcher;
import ir.restcurt.route.mapping.CompositeMapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class CompositeMappingRepository implements MappingRepository<CompositeMapping> {
    private Map<String, CompositeMapping> compositeMappings = new HashMap<>();
    private RouteMatcher matcher = new DefaultRouteMatcher();


    @Override
    public void add(CompositeMapping mapping) {
        this.compositeMappings.put(mapping.getPath(), mapping);
    }

    @Override
    public void addAll(Set<CompositeMapping> mappings) {
        mappings.stream().forEach((mapping) -> compositeMappings.put(mapping.getPath(), mapping));
    }

    @Override
    public Set<CompositeMapping> getAllMappings() {

        return new HashSet<>(compositeMappings.values());
    }

    @Override
    public CompositeMapping getSuitableMapping(String target) {

        for (String path : compositeMappings.keySet()) {
            if (matcher.isSatisfyMapping(target, path)) {
                return compositeMappings.get(path);
            }
        }
        return null;


    }
}
