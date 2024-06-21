package testtask.controller;

import org.eclipse.swt.widgets.Shell;

public class ShellController {

	public static void open(Shell shell) {
		shell.open();
		while (!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch())
				shell.getDisplay().sleep();
		}
	}
}
