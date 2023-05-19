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

import java.util.ArrayList;
import java.util.List;

import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.ListTag;
import stone.patternfix.EncodedPattern;

/**
 * 
 */
public class PatternHolder {

	public static final String CUBE_INTERFACE = "appliedenergistics2:interface";

	private static final String PATTERNS_KEY = "patterns";
	private static final String ID_KEY = "id";

	private final CompoundTag data;

	public PatternHolder(CompoundTag data) {
		this.data = data;
	}

	public String getId() {
		return data.getString(ID_KEY);
	}
	
	public List<EncodedPattern> getPatterns() {
		List<EncodedPattern> patternList = new ArrayList<>(9);
		switch (this.getId()) {
		case (CUBE_INTERFACE):
			ListTag<CompoundTag> patterns = data.getCompoundTag(PATTERNS_KEY).getListTag("Items").asCompoundTagList();
			for (CompoundTag pattern : patterns)
			{
				patternList.add(new EncodedPattern(pattern));
			}
			break;
		}

		return patternList;
	}

}
