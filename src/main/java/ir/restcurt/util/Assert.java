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

package ir.restcurt.util;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Hamid Samani
 * @since 0.0.1
 */
public class Assert {

    private Assert() {
    }

    public static void notNull(Object obj, String message) {

        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException(message);
    }

    public static <E extends Object> void hasValue(Collection<E> coll, String message) {

        notNull(coll, "Collection should not be null");
        if (!coll.isEmpty()) {
            return;
        }
        throw new IllegalArgumentException(message);

    }

    public static void notNull(String message, Object... obj) {

        Collection<Object> coll = Arrays.asList(obj);
        hasValue(coll, message);
    }

    public static void notNull(Class<?>[] classes, String message) {

        if (classes == null) {
            throw new IllegalArgumentException(message);
        }
        hasValue(Arrays.asList(classes), message);
    }

    public static void hasText(String text, String message) {
        notNull(text, message);
        if (text.length() < 1) {
            throw new IllegalArgumentException(message);
        }
    }

}
