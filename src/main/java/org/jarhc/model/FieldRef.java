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

package org.jarhc.model;

import java.util.Objects;

public class FieldRef implements Comparable<FieldRef> {

	private final String fieldOwner;
	private final String fieldType;
	private final String fieldName;
	private final boolean staticAccess;
	private final boolean writeAccess;

	public FieldRef(String fieldOwner, String fieldType, String fieldName, boolean staticAccess, boolean writeAccess) {
		this.fieldOwner = fieldOwner;
		this.fieldType = fieldType;
		this.fieldName = fieldName;
		this.staticAccess = staticAccess;
		this.writeAccess = writeAccess;
	}

	public String getFieldOwner() {
		return fieldOwner;
	}

	public String getFieldType() {
		return fieldType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public boolean isStaticAccess() {
		return staticAccess;
	}

	public boolean isReadAccess() {
		return !writeAccess;
	}

	public boolean isWriteAccess() {
		return writeAccess;
	}

	public String getDisplayName() {
		if (staticAccess) {
			return String.format("static %s %s.%s", fieldType, fieldOwner, fieldName);
		} else {
			return String.format("%s %s.%s", fieldType, fieldOwner, fieldName);
		}
	}

	@Override
	public String toString() {
		return String.format("FieldRef[%s]", getDisplayName());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		FieldRef fieldRef = (FieldRef) obj;
		return staticAccess == fieldRef.staticAccess &&
				writeAccess == fieldRef.writeAccess &&
				Objects.equals(fieldOwner, fieldRef.fieldOwner) &&
				Objects.equals(fieldType, fieldRef.fieldType) &&
				Objects.equals(fieldName, fieldRef.fieldName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fieldOwner, fieldType, fieldName, staticAccess, writeAccess);
	}

	@Override
	public int compareTo(FieldRef fieldRef) {
		int diff = fieldOwner.compareTo(fieldRef.fieldOwner);
		if (diff != 0) return diff;
		diff = fieldName.compareTo(fieldRef.fieldName);
		if (diff != 0) return diff;
		diff = fieldType.compareTo(fieldRef.fieldType);
		if (diff != 0) return diff;
		diff = Boolean.compare(staticAccess, fieldRef.staticAccess);
		if (diff != 0) return diff;
		return Boolean.compare(writeAccess, fieldRef.writeAccess);
	}

}
