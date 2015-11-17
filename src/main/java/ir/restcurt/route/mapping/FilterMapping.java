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
 * @author  Hamid Samani
 * @since 0.0.1
 */
public class FilterMapping extends AbstractMapping {
    public enum FilterType{
        BEFORE,AFTER
    }

    private FilterType type;

    public FilterMapping(String path, HttpMethod method, Handler handler, FilterType type) {
        super(path,method,handler);
        this.type = type;
    }

    public FilterType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FilterMapping that = (FilterMapping) o;

        return type == that.type;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FilterMapping{");
        sb.append("path=").append(getPath());
        sb.append(", method=").append(getMethod());
        sb.append(", type=").append(getType());
        sb.append('}');
        return sb.toString();
    }
}
