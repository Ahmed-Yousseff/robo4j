/*
 * Copyright (c) 2014, 2017, Marcus Hirt, Miroslav Wengner
 *
 * Robo4J is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Robo4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Robo4J. If not, see <http://www.gnu.org/licenses/>.
 */

package com.robo4j.core.httpunit;

import com.robo4j.core.RoboContext;
import com.robo4j.core.RoboUnit;
import com.robo4j.core.logging.SimpleLoggingUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Register URIs by methods
 *
 * @author Marcus Hirt (@hirt)
 * @author Miro Wengner (@miragemiko)
 */
public final class HttpUriRegister {

	private static final String SOLIDUS = "/";

	private static volatile HttpUriRegister INSTANCE;
	private final Map<String, RoboUriInfo> pathMethods = new HashMap<>();

	private HttpUriRegister() {
	}

	public static HttpUriRegister getInstance() {
		if (INSTANCE == null) {
			synchronized (HttpUriRegister.class) {
				if (INSTANCE == null) {
					INSTANCE = new HttpUriRegister();
				}
			}
		}
		return INSTANCE;
	}

	public void addNote(String path, String method) {
		final String tmpPath = SOLIDUS.concat(path);
		if (pathMethods.containsKey(tmpPath)) {
			pathMethods.get(tmpPath).addMethod(method);
		} else {
			pathMethods.put(tmpPath, new RoboUriInfo().addMethod(method));
		}
	}

	public void addUnitToNote(String path, RoboUnit<?> unit) {
		final String tmpPath = SOLIDUS.concat(path);
		if (pathMethods.containsKey(tmpPath)) {
			pathMethods.get(tmpPath).setUnit(unit);
		} else {
			SimpleLoggingUtil.error(getClass(), "there is not path: " + path);
		}
	}

	public void updateUnits(RoboContext context) {
		pathMethods.entrySet().forEach(e -> {
			final RoboUnit<?> roboUnit = getRoboUnitById(context, e.getKey().substring(1));
			e.getValue().setUnit(roboUnit);
		});
	}

	public RoboUriInfo getMethodsBytPath(String path) {
		return pathMethods.get(SOLIDUS.concat(path));
	}

	public RoboUnit<?> getRoboUnitByPath(String path) {
		return pathMethods.get(SOLIDUS.concat(path)).getUnit();
	}

	public boolean isUnitAvailable(String id) {
		//@formatter:off
        return pathMethods.entrySet().stream()
                .filter(e -> e.getKey().equals(SOLIDUS.concat(id)))
                .map(Map.Entry::getValue)
                .map(RoboUriInfo::getUnit)
                .anyMatch(Objects::nonNull);
        //@formatter:on
	}

	// Private Methods
	private RoboUnit<?> getRoboUnitById(RoboContext context, String id) {
		//@formatter:off
        return context.getUnits().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
        //@formatter:on
	}

}
