/*
 * Copyright (C) 2014, 2017. Miroslav Wengner, Marcus Hirt
 * This RoboRequestElement.java  is part of robo4j.
 * module: robo4j-core
 *
 * robo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * robo4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with robo4j .  If not, see <http://www.gnu.org/licenses/>.
 */

package com.robo4j.core.client.request;

import java.util.Map;

/**
 * RoboRequestElement type is immutable value
 * represent key -> command, value -> possible command values
 *
 * @author Marcus Hirt (@hirt)
 * @author Miro Wengner (@miragemiko)
 */
public class RoboRequestEntity {

    private String method;
    private String key;
    private Map<String, String> values;

    public RoboRequestEntity(String method, String key, Map<String, String> values) {
        this.method = method;
        this.key = key;
        this.values = values;
    }

    public String getMethod() {
        return method;
    }

    public String getKey() {
        return key;
    }

    public Map<String, String> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "RoboRequestElement{" +
                "method='" + method + '\'' +
                ", key='" + key + '\'' +
                ", values=" + values +
                '}';
    }
}
