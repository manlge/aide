package com.aide.filter;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.team.core.RepositoryProvider;
import org.tigris.subversion.subclipse.core.ISVNLocalResource;
import org.tigris.subversion.subclipse.core.SVNException;
import org.tigris.subversion.subclipse.core.SVNProviderPlugin;
import org.tigris.subversion.subclipse.core.SVNTeamProvider;
import org.tigris.subversion.subclipse.core.resources.LocalResourceStatus;
import org.tigris.subversion.subclipse.core.resources.SVNWorkspaceRoot;
import org.tigris.subversion.subclipse.ui.SVNUIPlugin;

public abstract class SvnProjectFilter extends ViewerFilter {

	public static boolean isDirty(ISVNLocalResource svnResource) {
		try {
			if (!svnResource.exists())
				return false;
			if (svnResource.getIResource().getType() == 1) {
				LocalResourceStatus status = svnResource.getStatus();

				return ((status.isTextModified()) || (status.isPropModified()) || (status
						.isReplaced()))
						&& (!status.isIgnored()) && (!svnResource.isIgnored());
			}

			return svnResource.isDirty();
		} catch (SVNException e) {
			SVNUIPlugin.log(e.getStatus());
		}
		return true;
	}

	private IResource getResource(Object object) {
		if ((object instanceof IResource)) {
			return (IResource) object;
		}
		if ((object instanceof IAdaptable)) {
			return (IResource) ((IAdaptable) object)
					.getAdapter(IResource.class);
		}
		return null;
	}

	public SvnProjectFilter() {
		super();
	}

	protected boolean isModifiedSvnProjectObject(Object element) {
		IResource resource = getResource(element);
		if (resource == null) {
			return true;
		}
		if (!(element instanceof IProject))
			resource = resource.getProject(); // 定位到project，只要project修改就显示

		if ((resource == null) || (resource.getType() == 8)) {
			return false;
		}
		SVNTeamProvider svnProvider = (SVNTeamProvider) RepositoryProvider
				.getProvider(resource.getProject(), SVNProviderPlugin
						.getTypeId());
		if (svnProvider == null) {
			return false;
		}

		ISVNLocalResource svnResource = SVNWorkspaceRoot
				.getSVNResourceFor(resource);
		boolean isDirty = false;
		isDirty = isDirty(svnResource);
		if (isDirty) {
			return true;
		}
		return false;
	}

	protected boolean isUnmodifiedSvnProjectObject(Object element) {
		IResource resource = getResource(element);
		if (resource == null) {
			return true;
		}
		if (!(element instanceof IProject))
			resource = resource.getProject(); // 定位到project，只要project修改就显示

		if ((resource == null) || (resource.getType() == 8)) {
			return false;
		}
		SVNTeamProvider svnProvider = (SVNTeamProvider) RepositoryProvider
				.getProvider(resource.getProject(), SVNProviderPlugin
						.getTypeId());
		if (svnProvider == null) {
			return false;
		}

		ISVNLocalResource svnResource = SVNWorkspaceRoot
				.getSVNResourceFor(resource);
		boolean isDirty = false;
		isDirty = isDirty(svnResource);
		if (isDirty) {
			return false;
		}
		return true;
	}
}