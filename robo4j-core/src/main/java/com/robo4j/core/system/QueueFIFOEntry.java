/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This QueueFIFOEntry.java is part of robo4j.
 *
 *     robo4j is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     robo4j is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with robo4j .  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.robo4j.core.system;

import java.util.concurrent.atomic.AtomicLong;

/**
 * keep order in the queue
 * Created by miroslavkopecky on 13/04/16.
 */
public class QueueFIFOEntry<TransferType extends Comparable<TransferType>>  implements Comparable<QueueFIFOEntry<TransferType>>, TransferSignal {

    private static final AtomicLong seq = new AtomicLong(0);
    private final long seqNum;
    private final TransferType entry;
    public QueueFIFOEntry(TransferType entry) {
        seqNum = seq.getAndIncrement();
        this.entry = entry;
    }
    public TransferType getEntry() { return entry; }
    public int compareTo(QueueFIFOEntry<TransferType> other) {
        int res = entry.compareTo(other.entry);
        if (res == 0 && other.entry != this.entry)
            res = (seqNum < other.seqNum ? -1 : 1);
        return res;
    }
}