package com.aide.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.aide.explorer.AidePlugin;

public class AboutAideAction implements IObjectActionDelegate {

  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
  }

  public void run(IAction action) {
    MessageDialog
      .openInformation(
        new Shell(),
        "About Aide",
        "Plug-in: Aide For Eclipse(Windows)\r\nVersion: "
          + AidePlugin.getDefault().getBundle().getHeaders().get("Bundle-Version")
          + "\r\nVendor:\u5218\u6c38\u4f1f\r\nMail: manlge@126.com \r\nMobile: 18611739701");
  }

  public void selectionChanged(IAction action, ISelection selection) {
  }
}
