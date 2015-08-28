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

package ir.restcurt.route.parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ir.restcurt.util.Assert;

/**
 *
 * @author Hamid Samani
 * @since 0.0.1
 * 
 */
public class ColonPathVariable implements PathVariable {

    private static final String VARIABLES_PATTERN = "\\/?(:\\w+)+\\/?";

    private String path;

    private List<String> pathVariables = new ArrayList<>(10);

    public ColonPathVariable(String path) {
        Assert.notNull(path, "path should not be null");
        this.path = path;

        findVariables();
    }

    /**
     * 
     */
    private void findVariables() {

        Matcher matcher = Pattern.compile(VARIABLES_PATTERN).matcher(path);
        while (matcher.find()) {
            pathVariables.add(matcher.group());
        }

    }

    /**
     * @return the path
     */
    public String getPath() {

        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {

        this.path = path;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ir.restcurt.route.parameter.PathVariableConfigurer#getPathVariables()
     */
    @Override
    public List<String> getPathVariables() {

        return pathVariables;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ir.restcurt.route.parameter.PathVariableConfigurer#isPathVariable(java.
     * lang.String)
     */
    @Override
    public boolean isPathVariable(String variable) {

        return variable.startsWith("/:") || variable.startsWith(":");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ir.restcurt.route.parameter.PathVariable#pathVariable(java.lang.String)
     */
    @Override
    public String pathVariable(String variable) {

        // TODO Auto-generated method stub
        return null;
    }
}
