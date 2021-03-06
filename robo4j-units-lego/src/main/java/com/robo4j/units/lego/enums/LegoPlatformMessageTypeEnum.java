/*
 * Copyright (C) 2014, 2017. Miroslav Wengner, Marcus Hirt
 * This LegoPlatformMessageTypeEnum.java  is part of robo4j.
 * module: robo4j-units-lego
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

package com.robo4j.units.lego.enums;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * LegoMindstorm available buttons
 *
 * @author Marcus Hirt (@hirt)
 * @author Miro Wengner (@miragemiko)
 */
public enum LegoPlatformMessageTypeEnum {

    //@formatter:off
    STOP        (0, "stop"),
    MOVE        (1, "move"),
    BACK        (2, "back"),
    LEFT        (3, "left"),
    RIGHT       (4, "right")
    ;


    //@formatter:on

    private static volatile Map<Integer, LegoPlatformMessageTypeEnum> internMapByType;
    private Integer type;
    private String name;

    LegoPlatformMessageTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    //@formatter:off
    private static Map<Integer, LegoPlatformMessageTypeEnum> initMapping() {
        return Stream.of(values())
                .collect(Collectors.toMap(LegoPlatformMessageTypeEnum::getType, e -> e));
    }
    public static LegoPlatformMessageTypeEnum getInternalByName(String name) {
        if (internMapByType == null)
            internMapByType = initMapping();
        return internMapByType.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(e -> e.getName().equals(name))
                .findFirst().get();
    }
    //@formatter:on

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

	public Set<String> commandNames() {
		//@formatter:off
        return Stream.of(values())
                .map(LegoPlatformMessageTypeEnum::getName)
                .collect(Collectors.toSet());
        //@formatter:on
	}

	public LegoPlatformMessageTypeEnum getByName(String name) {
		return getInternalByName(name);
	}


    @Override
    public String toString() {
        return "LegoPlatformMessageType{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }


}
