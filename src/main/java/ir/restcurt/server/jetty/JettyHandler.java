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

package ir.restcurt.server.jetty;

import ir.restcurt.http.HttpServletRequestHolder;
import ir.restcurt.http.HttpServletResponseHolder;
import ir.restcurt.route.DefaultRouteMatcher;
import ir.restcurt.route.RouteMatcher;
import ir.restcurt.route.mapping.RouteMapping;
import ir.restcurt.route.mapping.repository.RouteMappingRepository;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class JettyHandler extends AbstractHandler {

    private RouteMappingRepository repository;

    private RouteMatcher routeMatcher = new DefaultRouteMatcher();

    public JettyHandler(RouteMappingRepository repository) {
        this.repository = repository;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jetty.server.Handler#handle(java.lang.String,
     * org.eclipse.jetty.server.Request, javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        for (RouteMapping mapping : repository.getAllMappings()) {
            if (routeMatcher.isSatisfyMapping(target, mapping)) {
                mapping.getHandler().handle(new HttpServletRequestHolder(mapping.getPath(), target, request),
                        new HttpServletResponseHolder(response));
                baseRequest.setHandled(true);
            }

        }

    }

}
