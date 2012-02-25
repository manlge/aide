package com.aide.editor;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;

public class AideEditor extends EditorPart implements IPartListener {

  void executeCommand(String command) {
    try {
      Runtime.getRuntime().exec(command);
    } catch (IOException e) {
      MessageDialog.openError(new Shell(), "error", e.getMessage()
        + "\n，请对aide参数进行配置(Window->Perferences->aide)");
    }
  }

  @Override
  public void doSave(IProgressMonitor monitor) {

  }

  @Override
  public void doSaveAs() {
  }

  @Override
  public void init(IEditorSite site, IEditorInput input) throws PartInitException {
    setSite(site);
    setInput(input);
    FileEditorInput fileEditorInput = (FileEditorInput) input;
    File file = new File(fileEditorInput.getFile().getLocation().toString());

    String cmd = "explorer /e, ";
    if (file.isFile()) {
      cmd += "/select, ";
    }
    cmd += "\"" + file.getAbsolutePath() + "\"";
    executeCommand(cmd);

    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
      .getActivePage();
    activePage.addPartListener(this);
  }

  @Override
  public boolean isDirty() {
    return false;
  }

  @Override
  public boolean isSaveAsAllowed() {
    return false;
  }

  @Override
  public void createPartControl(Composite parent) {

  }

  @Override
  public void setFocus() {
  }

  @Override
  public void partActivated(IWorkbenchPart part) {
  }

  @Override
  public void partBroughtToTop(IWorkbenchPart part) {
  }

  @Override
  public void partClosed(IWorkbenchPart part) {
  }

  @Override
  public void partDeactivated(IWorkbenchPart part) {
  }

  @Override
  public void partOpened(final IWorkbenchPart part) {
    Display.getDefault().syncExec(new Runnable() {

      @Override
      public void run() {
        IWorkbenchPage activePage = PlatformUI.getWorkbench()
          .getActiveWorkbenchWindow().getActivePage();
        if (part == AideEditor.this) {
          activePage.closeEditor(AideEditor.this, false);
          activePage.removePartListener(AideEditor.this);
        }
      }
    });
  }
}