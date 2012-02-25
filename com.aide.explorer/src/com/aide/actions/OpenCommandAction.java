package com.aide.actions;

import java.io.File;

public class OpenCommandAction  extends AbstractCommandAction {
  public void doExec(File file){
    File f = file;
    if (f.isFile()) {
			f = f.getParentFile();
		}
    String cmd = "cmd /E:ON /C \"start /d \"\"\"" + f.getAbsolutePath() + "\"\"\"\"";
    executeCommand(cmd);
  }

}
