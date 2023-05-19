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
package stone.patternfix;

import java.util.ArrayList;
import java.util.List;

import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.ListTag;
import stone.patternfix.wrappers.Item;

/**
 * Thin wrapper class for the NBT data of an encoded pattern
 * 
 * WARNING This class directly edits the underlying compoundTag
 */
public class EncodedPattern {

	private static final String CRAFTING_KEY = "crafting";
	private static final String SUBSTITUTE_KEY = "substitute";
	private static final String TAG_KEY = "tag";
	private static final String INPUT_KEY = "in";

	private final CompoundTag data;
	
	public EncodedPattern(CompoundTag data) {
		this.data = data;
	}
	
	public boolean isCrafting() {
		return data.getBoolean(CRAFTING_KEY);
	}

	public void setCrafting(boolean isCrafting) {
		data.putBoolean(CRAFTING_KEY, isCrafting);
	}

	public void setAsCrafting() {
		setCrafting(true);
	}

	public void setAsProcessing() {
		setCrafting(false);
	}

	public boolean isProcessing() {
		return !data.getBoolean(CRAFTING_KEY);
	}

	public boolean isSubstitute() {
		return data.getBoolean(SUBSTITUTE_KEY);
	}

	public void setSubstitution(boolean isSubstitute) {
		data.putBoolean(SUBSTITUTE_KEY, isSubstitute);
	}

	/**
	 * @return
	 */
	public List<Item> getInput() {
		ListTag<CompoundTag> inputs = data.getCompoundTag(TAG_KEY).getListTag(INPUT_KEY).asCompoundTagList();
		List<Item> items = new ArrayList<>(9);
		for (CompoundTag input : inputs)
		{
			items.add(new Item(input));
		}

		return items;
	}

}
