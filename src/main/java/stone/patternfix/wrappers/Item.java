/**
 * This file is part of pattern-fix. 
 * Copyright (c) 2023, Stone, All rights reserved.
 * 
 * pattern-fix is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * pattern-fix is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with pattern-fix. If not, see <https://www.gnu.org/licenses/>.
 */
package stone.patternfix.wrappers;

import net.querz.nbt.tag.CompoundTag;

/**
 * 
 */
public class Item {

	private final CompoundTag data;

	public Item(CompoundTag data) {
		this.data = data;
	}

}
