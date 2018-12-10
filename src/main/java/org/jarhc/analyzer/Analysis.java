/*
 * Copyright 2018 Stephan Markwalder
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

package org.jarhc.analyzer;

import org.jarhc.model.Classpath;
import org.jarhc.report.Report;
import org.jarhc.report.ReportSection;

import java.util.Arrays;
import java.util.List;

public class Analysis {

	private final List<Analyzer> analyzers;

	public Analysis(Analyzer... analyzers) {
		this.analyzers = Arrays.asList(analyzers);
	}

	public Report run(Classpath classpath) {
		Report report = new Report();
		for (Analyzer analyzer : analyzers) {
			ReportSection section = analyzer.analyze(classpath);
			report.addSection(section);
		}
		return report;
	}

}
