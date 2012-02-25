package com.aide.filter;

import org.eclipse.jface.viewers.Viewer;

public class UnModifiedSvnProjectFilter extends SvnProjectFilter {
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return isUnmodifiedSvnProjectObject(element);
	}

}
