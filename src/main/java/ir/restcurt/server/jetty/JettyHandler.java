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
import ir.restcurt.route.mapping.CompositeMapping;
import ir.restcurt.route.repository.CompositeMappingInvoker;
import ir.restcurt.route.repository.MappingRepository;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class JettyHandler extends AbstractHandler {

    private MappingRepository<CompositeMapping> repository;

    public JettyHandler(MappingRepository<CompositeMapping> repository) {
        this.repository = repository;
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        CompositeMapping mapping = repository.getSuitableMapping(request);

        if (mapping != null) {


            final HttpServletRequestHolder requestHolder = httpServletRequestHolder(mapping.getPath(), target, request);
            final HttpServletResponseHolder responseHolder = httpServletResponseHolder(response);

            CompositeMappingInvoker invoker = new CompositeMappingInvoker(mapping, requestHolder, responseHolder);
            try {
                invoker.invokeBeforeFilters();
                invoker.invokeHandler();
                invoker.invokeAfterFilters();
            } catch (Exception ex) {
                invoker.handleException(ex);
            }

        } else {
            //should refactored into a more general approach.
            response.setStatus(404);
        }
        baseRequest.setHandled(true);


    }


    public HttpServletRequestHolder httpServletRequestHolder(String template, String given, HttpServletRequest reques) {
        return new HttpServletRequestHolder(template, given, reques);
    }

    public HttpServletResponseHolder httpServletResponseHolder(HttpServletResponse response) {
        return new HttpServletResponseHolder(response);
    }

}
