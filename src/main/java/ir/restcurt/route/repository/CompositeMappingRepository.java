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

import ir.restcurt.route.mapping.CompositeMapping;
import ir.restcurt.route.matcher.CompositeMappingRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class CompositeMappingRepository implements MappingRepository<CompositeMapping> {
    private Set<CompositeMapping> compositeMappings = new HashSet<>();

    @Override
    public void add(CompositeMapping mapping) {
        this.compositeMappings.add(mapping);
    }

    @Override
    public void addAll(Set<CompositeMapping> mappings) {
        compositeMappings.addAll(mappings);
    }

    @Override
    public Set<CompositeMapping> getAllMappings() {

        return compositeMappings;
    }

    @Override
    public CompositeMapping getSuitableMapping(HttpServletRequest request, HttpServletResponse response) {

        CompositeMappingRequestMatcher compositeMappingRequestMatcher = new CompositeMappingRequestMatcher(request, response);

        for (CompositeMapping cm : compositeMappings) {
            compositeMappingRequestMatcher.setCompositeMapping(cm);

            if (compositeMappingRequestMatcher.isSatisfyMapping()) {
                if (compositeMappingRequestMatcher.isMethodEqual()) {
                    if (compositeMappingRequestMatcher.isSatisfyHeaders()) { //always is true except in the case of existing header.
                        return cm;
                    } else {
                        compositeMappingRequestMatcher.addCandidateNeglectHeaders(cm);
                    }
                } else {
                    compositeMappingRequestMatcher.addCandidateNeglectMethod(cm);
                }
            }
        }
        compositeMappingRequestMatcher.determineResponse();
        return null;
    }
}
