package com.aide.explorer.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.aide.explorer.AidePlugin;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class AidePreferencePage extends FieldEditorPreferencePage implements
  IWorkbenchPreferencePage {

  public AidePreferencePage() {
    super(GRID);
    setPreferenceStore(AidePlugin.getDefault().getPreferenceStore());
    setDescription("Place Setup Executeable File Path for Aide");
  }

  /**
   * Creates the field editors. Field editors are abstractions of
   * the common GUI blocks needed to manipulate various types
   * of preferences. Each field editor knows how to save and
   * restore itself.
   */
  public void createFieldEditors() {
    FileFieldEditor editor = new FileFieldEditor(PreferenceConstants.EDITPLUS_PATH,
      "&EditPlus:", getFieldEditorParent());
    editor.setFileExtensions(new String[] { "*.exe" });
    addField(editor);

    editor = new FileFieldEditor(PreferenceConstants.ULTRAEDIT_PATH, "&UltraEdit:",
      getFieldEditorParent());
    editor.setFileExtensions(new String[] { "*.exe" });
    addField(editor);

    editor = new FileFieldEditor(PreferenceConstants.GVIM_PATH, "&GVim:",
      getFieldEditorParent());
    editor.setFileExtensions(new String[] { "*.exe" });
    addField(editor);
  }

  /* (non-Javadoc)
   * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
   */
  public void init(IWorkbench workbench) {
  }

}