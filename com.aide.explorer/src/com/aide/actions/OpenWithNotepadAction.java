package com.aide.actions;

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class OpenWithNotepadAction extends AbstractCommandAction {

  public void doExec(File file) {
    if (file == null || file.isDirectory()){
      MessageDialog.openError(new Shell(), "error", "Selected Required  are file");
      return;
    }
    String cmd = "notepad \"" + file.getAbsolutePath() + "\"\"";
    executeCommand(cmd);
  }

}
