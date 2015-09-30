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
import ir.restcurt.route.registrar.DefaultRouteHandlerRegistrar;
import ir.restcurt.route.registrar.DummyRouteHandlers;
import org.junit.Test;

import java.util.Set;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class CompositeMappingCreatorTest {
    @Test
    public void testCompositeMappingsCreatedAsExpected() {

        DefaultRouteHandlerRegistrar hr = new DefaultRouteHandlerRegistrar(DummyRouteHandlers.PersonsResource.class);
        CompositeMappingCreator cmc = new CompositeMappingCreator(hr.getHandlers());
        Set<CompositeMapping> allMappings = cmc.getRepository().getAllMappings();

        allMappings.forEach(System.out::println);


    }

}