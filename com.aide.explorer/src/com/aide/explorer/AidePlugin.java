package com.aide.explorer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The main plugin class to be used in the desktop.
 */
public class AidePlugin extends AbstractUIPlugin {
  //The shared instance.
  private static AidePlugin plugin;
  //Resource bundle.
  private ResourceBundle resourceBundle;

  /**
   * The constructor.
   */
  public AidePlugin(IPluginDescriptor descriptor) {
    super(descriptor);
    plugin = this;
    try {
      resourceBundle = ResourceBundle.getBundle("com.aide.AidePluginResources");
    } catch (MissingResourceException x) {
      resourceBundle = null;
    }
  }

  /**
   * Returns the shared instance.
   */
  public static AidePlugin getDefault() {
    return plugin;
  }

  /**
   * Returns the workspace instance.
   */
  public static IWorkspace getWorkspace() {
    return ResourcesPlugin.getWorkspace();
  }

  /**
   * Returns the string from the plugin's resource bundle,
   * or 'key' if not found.
   */
  public static String getResourceString(String key) {
    ResourceBundle bundle = AidePlugin.getDefault().getResourceBundle();
    try {
      return bundle.getString(key);
    } catch (MissingResourceException e) {
      return key;
    }
  }

  /**
   * Returns the plugin's resource bundle,
   */
  public ResourceBundle getResourceBundle() {
    return resourceBundle;
  }

  static public void log(Object msg) {
    ILog log = AidePlugin.getDefault().getLog();
    Status status = new Status(IStatus.ERROR, AidePlugin.getDefault()
      .getDescriptor().getUniqueIdentifier(), IStatus.ERROR, msg + "\n", null);
    log.log(status);
  }

  static public void log(Throwable ex) {
    ILog log = AidePlugin.getDefault().getLog();
    StringWriter stringWriter = new StringWriter();
    ex.printStackTrace(new PrintWriter(stringWriter));
    String msg = stringWriter.getBuffer().toString();
    Status status = new Status(IStatus.ERROR, AidePlugin.getDefault()
      .getDescriptor().getUniqueIdentifier(), IStatus.ERROR, msg, null);
    log.log(status);
  }

}
