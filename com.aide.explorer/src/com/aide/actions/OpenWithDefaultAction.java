package com.aide.actions;

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class OpenWithDefaultAction extends AbstractCommandAction{

  public void doExec(File file) {
    if (file == null || file.isDirectory()){
      MessageDialog.openError(new Shell(), "error", "Selected Required  are file");
      return;
    }
    String cmd = "cmd /E:ON /C \"start /b cmd /c \"\"" + file.getAbsolutePath() + "\"\"\"";
   super.executeCommand(cmd);
  }

}
