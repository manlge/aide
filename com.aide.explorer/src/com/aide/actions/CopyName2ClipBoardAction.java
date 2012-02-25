package com.aide.actions;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;

public class CopyName2ClipBoardAction extends AbstractCommandAction {

  public void doExec(File file) {
    StringSelection stringSelection = new StringSelection(file.getAbsolutePath()
      .replace('\\', '/'));
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,
      null);
  }

}
