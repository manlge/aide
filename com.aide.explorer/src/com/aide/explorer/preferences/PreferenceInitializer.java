package com.aide.explorer.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.aide.explorer.AidePlugin;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
   */
  public void initializeDefaultPreferences() {
    IPreferenceStore store = AidePlugin.getDefault().getPreferenceStore();
    store.setDefault(PreferenceConstants.EDITPLUS_PATH, "");
    store.setDefault(PreferenceConstants.ULTRAEDIT_PATH, "");
    store.setDefault(PreferenceConstants.GVIM_PATH, "");
  }

}
