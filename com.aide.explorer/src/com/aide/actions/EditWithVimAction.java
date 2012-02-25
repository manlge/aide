package com.aide.actions;

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.aide.explorer.preferences.PreferenceConstants;

public class EditWithVimAction extends AbstractCommandAction {

  public void doExec(File file) {
    if (file == null || file.isDirectory()) {
      MessageDialog.openError(new Shell(), "error", "Selected Required  are file");
      return;
    }
    String cmd = getExePath(PreferenceConstants.GVIM_PATH) + " \""
      + file.getAbsolutePath() + "\"";
    super.executeCommand(cmd);
  }

}
