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

import javax.servlet.http.HttpServletResponse;

import ir.restcurt.exception.RestCurtException;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class HttpServletResponseHolder {

    private HttpServletResponse reponse;

    public HttpServletResponseHolder(HttpServletResponse reponse) {
        this.reponse = reponse;
    }

    /**
     * @return the reponse
     */
    public HttpServletResponse getReponse() {

        return reponse;
    }

    public void println(String text) {

        try {
            this.reponse.getWriter().println(text);
        } catch (IOException e) {
            throw new RestCurtException(e);
        }
    }

}