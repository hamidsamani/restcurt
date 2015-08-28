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

package ir.restcurt.exception;

/**
 * Main Exception, all the RESTCurt exceptions should subclass this class.
 * 
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class RestCurtException extends RuntimeException {

    private static final long serialVersionUID = -6915157052548017896L;

    public RestCurtException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestCurtException(Throwable cause) {
        super(cause);
    }

    public RestCurtException(String message) {
        super(message);
    }

}
