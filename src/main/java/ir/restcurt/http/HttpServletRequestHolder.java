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

package ir.restcurt.http;

import ir.restcurt.route.parameter.UrlPathVariablesHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class HttpServletRequestHolder {

    private HttpServletRequest request;

    private UrlPathVariablesHolder pathVariables;

    public HttpServletRequestHolder(String templateUrl, String givenUrl, HttpServletRequest request) {
        this.request = request;
        this.pathVariables = new UrlPathVariablesHolder(templateUrl, givenUrl);
    }

    /**
     * @return the request
     */
    public HttpServletRequest getRequest() {

        return request;
    }

    public Object variable(String variable) {
        Object value = request.getAttribute(variable);
        if (value != null) {
            return value;
        }
        return pathVariables.getValue(variable);
    }

    public <T> T variable(String variable, Class<T> clazz) {
        return (T) variable(variable);
    }

    public String param(String param) {
        return request.getParameter(param);
    }

    public void resolve(String key, Object value) {
        request.setAttribute(key, value);
    }

    public String header(String name) {
        return request.getHeader(name);
    }

}
