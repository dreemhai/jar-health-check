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

package org.jarhc.utils;

import java.util.HashSet;
import java.util.Set;

public class JavaUtils {

	private static final ClassLoader BOOTSTRAP_CLASSLOADER = ClassLoader.getSystemClassLoader().getParent();

	private static final Set<String> primitiveTypes = new HashSet<>();

	static {
		primitiveTypes.add("byte");
		primitiveTypes.add("short");
		primitiveTypes.add("int");
		primitiveTypes.add("long");
		primitiveTypes.add("float");
		primitiveTypes.add("double");
		primitiveTypes.add("char");
		primitiveTypes.add("boolean");
	}

	public static boolean isPrimitiveType(String type) {
		return primitiveTypes.contains(type);
	}

	public static boolean isVoidType(String type) {
		return "void".equals(type);
	}

	public static boolean isBootstrapClass(String className) {
		Class javaClass = loadBootstrapClass(className);
		return javaClass != null;
	}

	public static Class loadBootstrapClass(String className) {
		className = className.replace('/', '.');
		try {
			return Class.forName(className, false, BOOTSTRAP_CLASSLOADER);
		} catch (ClassNotFoundException e) {
			return null;
		} catch (Throwable t) {
			// TODO: ignore ?
			return null;
		}
	}

	public static String getPackageName(String className) {
		className = className.replace('/', '.');
		if (className.contains(".")) {
			int pos = className.lastIndexOf('.');
			return className.substring(0, pos);
		} else {
			return ""; // empty package
		}
	}

}
