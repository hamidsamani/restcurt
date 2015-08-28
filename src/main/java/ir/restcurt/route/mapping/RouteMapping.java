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
public class RouteMapping {

    private HttpMethod method;

    private String path;

    private Handler handler;

    private RouteMapping(HttpMethod method, String path, Handler handler) {
        this.method = method;
        this.path = path;
        this.handler = handler;
    }

    /**
     * @return the method
     */
    public HttpMethod getMethod() {

        return method;
    }

    /**
     * @return the path
     */
    public String getPath() {

        return path;
    }

    /**
     * @return the handler
     */
    public Handler getHandler() {

        return handler;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((method == null) ? 0 : method.hashCode());
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RouteMapping other = (RouteMapping) obj;
        if (method != other.method)
            return false;
        if (path == null) {
            if (other.path != null)
                return false;
        } else if (!path.equals(other.path))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return "RouteMapping [method=" + method + ", path=" + path + ", handler=" + handler + "]";
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
