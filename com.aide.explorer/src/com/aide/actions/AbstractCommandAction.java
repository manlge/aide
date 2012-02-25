package com.aide.actions;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.internal.core.JarPackageFragmentRoot;
import org.eclipse.jdt.internal.core.PackageFragment;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.aide.explorer.AidePlugin;

public abstract class AbstractCommandAction implements
  IWorkbenchWindowActionDelegate {

  private Object selected = null;

  private Class selectedClass = null;

  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
  }

  public void run(IAction action) {
    if (selected == null) {
      MessageDialog.openInformation(new Shell(), "Aide Explorer",
        "Unable to explore " + selectedClass.getName());
      AidePlugin.log("Unable to explore " + selectedClass);
      return;
    }
    File selectFile = null;
    if (selected instanceof IResource) {
      selectFile = new File(((IResource) selected).getLocation().toOSString());
    } else if (selected instanceof File) {
      selectFile = (File) selected;
    }
    doExec(selectFile);
  }

  public void selectionChanged(IAction action, ISelection selection) {
    //System.out.println("selectionChanged : " + System.currentTimeMillis());
    this.selected = null;
    if (selection instanceof IStructuredSelection) {
      IStructuredSelection structuredSelection = (IStructuredSelection) selection;
      Object el = structuredSelection.getFirstElement();
      if (!(el instanceof IAdaptable)) {
        return;
      }
      IAdaptable adaptable = (IAdaptable) el;
      if (adaptable == null) {
        System.out.println("adaptable is null");
        return;
      }
      // System.err.println(adaptable.getClass().getName());
      this.selectedClass = adaptable.getClass();
      if (adaptable instanceof IResource) {
        this.selected = adaptable;
      } else if (adaptable instanceof PackageFragment
        && ((PackageFragment) adaptable).getPackageFragmentRoot() instanceof JarPackageFragmentRoot) {
        this.selected = getJarFile(((PackageFragment) adaptable)
          .getPackageFragmentRoot());
      } else if (adaptable instanceof JarPackageFragmentRoot) {
        this.selected = getJarFile(adaptable);
      } else {
        this.selected = adaptable.getAdapter(IResource.class);
      }
    }

  }

  protected File getJarFile(IAdaptable adaptable) {
    JarPackageFragmentRoot jarPackageFragmentRoot = (JarPackageFragmentRoot) adaptable;
    File selected = jarPackageFragmentRoot.getPath().makeAbsolute().toFile();
    if (!selected.exists()) {
      File projectFile = new File(jarPackageFragmentRoot.getJavaProject()
        .getProject().getLocation().toOSString());
      selected = new File(projectFile.getParent() + selected.toString());
    }
    return selected;
  }

  void executeCommand(String command) {
    try {
      Runtime.getRuntime().exec(command);
    } catch (IOException e) {
      MessageDialog.openError(new Shell(), "error", e.getMessage()
        + "\n，请对aide参数进行配置(Window->Perferences->aide)");
    }
  }

  public abstract void doExec(File file);

  protected String getExePath(String key) {
    String exe = AidePlugin.getDefault().getPreferenceStore().getString(key);
    exe = "\"" + exe + "\"";
    return exe;
  }

  public void init(IWorkbenchWindow window) {
  }

  public void dispose() {
  }
}
