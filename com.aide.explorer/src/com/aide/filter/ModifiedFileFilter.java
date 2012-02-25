package com.aide.filter;

import org.eclipse.jface.viewers.Viewer;

public class ModifiedFileFilter extends SvnProjectFilter {
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return isModifiedSvnProjectObject(element);
	}

}
