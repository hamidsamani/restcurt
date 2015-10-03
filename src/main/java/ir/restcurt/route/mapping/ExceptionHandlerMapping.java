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

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class ExceptionHandlerMapping extends AbstractMapping {
    private Class<? extends Exception> clazz;

    public ExceptionHandlerMapping(String path, HttpMethod method, Handler handler) {
        super(path, method, handler);
    }

    public ExceptionHandlerMapping(Class<? extends Exception> clazz, Handler handler) {
        this(null, null, handler);
        this.clazz = clazz;
    }

    public Class<? extends Exception> getClazz() {
        return clazz;
    }
}
