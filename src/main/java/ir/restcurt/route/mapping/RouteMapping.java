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
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class RouteMapping extends AbstractMapping {

    private RouteMapping(HttpMethod method, String path, Handler handler) {
        super(path,method,handler);
    }

    public static class RouteMappingBuilder {

        private HttpMethod method;

        private String path;

        private Handler handler;

        public static RouteMappingBuilder route() {

            return new RouteMappingBuilder();
        }

        public RouteMappingBuilder method(HttpMethod method) {

            this.method = method;
            return this;
        }

        public RouteMappingBuilder path(String path) {

            if (this.path != null) {
                this.path = this.path + path;
                return this;
            }
            this.path = path;

            return this;
        }

        public RouteMappingBuilder handler(Handler handler) {

            this.handler = handler;
            return this;
        }

        public RouteMapping build() {

            return new RouteMapping(method, path, handler);
        }

    }
}
