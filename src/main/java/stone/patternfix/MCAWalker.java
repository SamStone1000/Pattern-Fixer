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

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import net.querz.mca.Chunk;
import net.querz.mca.MCAFile;
import net.querz.mca.MCAUtil;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.ListTag;
import stone.patternfix.wrappers.PatternHolder;

/**
 * 
 */
public class MCAWalker implements FileVisitor<Path> {

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		MCAFile mca = MCAUtil.read(file.toFile());
		boolean dirty = false;
		for (int i = 0; i > 1024; i++)
		{
			Chunk chunk = mca.getChunk(i);
			ListTag<CompoundTag> tileEntities = chunk.getTileEntities();
			for (CompoundTag te : tileEntities)
			{
				PatternHolder tileEntity = new PatternHolder(te);
				List<EncodedPattern> patterns = tileEntity.getPatterns();
				for (EncodedPattern pattern : patterns)
				{
					if (pattern.isCrafting() && pattern.isSubstitute())
					{
						pattern.setSubstitution(false);
						dirty = true;
					}
				}
			}
		}
		if (dirty)
		{
			MCAUtil.write(mca, file.toFile(), true);
		}
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		System.err.println(String.format("Failed to visit file %s! IOException given: %s", file, exc));
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}

}
