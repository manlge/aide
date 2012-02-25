package com.aide.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.pde.internal.ui.editor.OpenManifestAction;

public class AideOpenManifestAction extends OpenManifestAction {
	@Override
	public void run(IAction action) {
		super.run(action);
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// if (selection instanceof IStructuredSelection) {
		// IStructuredSelection ssel = (IStructuredSelection) selection;
		// Iterator it = ssel.iterator();
		// while (it.hasNext()) {
		// System.out.println(it.next().getClass());
		// }
		// } else {
		// System.out.println(selection.getClass());
		// }
		super.selectionChanged(action, selection);
	}
}
