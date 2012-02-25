package com.aide.actions;

import java.io.File;

public class BrowseDirAction extends AbstractCommandAction {

  public void doExec(File file){
    String cmd = "explorer /e, ";
    if (file.isFile()) {
      cmd += "/select, ";
    }
    cmd += "\"" + file.getAbsolutePath() + "\"";
    executeCommand(cmd);
  }
}
