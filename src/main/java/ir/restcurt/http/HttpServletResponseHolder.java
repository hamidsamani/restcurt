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

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import ir.restcurt.exception.RestCurtException;
import ir.restcurt.util.Assert;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class HttpServletResponseHolder {

    private HttpServletResponse response;
    private ObjectMapper mapper = new ObjectMapper();
    private ResponseHeaderManager header;

    public HttpServletResponseHolder(HttpServletResponse response) {

        Assert.notNull(response, "response should not be null");
        this.response = response;
        this.header = new ResponseHeaderManager(this.response);
    }

    public void println(String text) {

        getWriter().println(text);
    }

    public void toJson(Object object) {

        header.contentType(MediaType.application_json);
        writeJson(object);

    }

    private PrintWriter getWriter() {

        try {
            return this.response.getWriter();
        } catch (IOException e) {
            throw new RestCurtException(e);
        }

    }

    private void writeJson(Object obj) {

        try {
            mapper.writeValue(getWriter(), obj);
        } catch (IOException e) {
            throw new RestCurtException(e);
        }
    }

}
