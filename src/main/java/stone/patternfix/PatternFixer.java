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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import net.querz.mca.MCAUtil;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

/**
 * 
 */
@Command(name = "patternfix", version = "PatternFix 0.1", mixinStandardHelpOptions = true)
public class PatternFixer implements Callable<Integer> {

	@Parameters(paramLabel = "<world>", defaultValue = "world")
	Path worldPath;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		MCAUtil.read("/home/stone/Eclipse/eclipse-workspace/pattern-fix/Substitution Testing/region/r.-1.-1.mca");
		
		//int exitCode = new CommandLine(new PatternFixer()).execute(args);
		//System.exit(exitCode);
	}

	@Override
	public Integer call() throws Exception {
		List<Path> regions = new ArrayList<Path>();
		regions.add(Paths.get("region"));
		for (Path region : regions)
		{
			region = worldPath.resolve(region);
			System.out.println(region.toAbsolutePath());
			Files.walkFileTree(region, new MCAWalker());
		}
		return 0;
	}

}
