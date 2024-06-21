package testtask.exceptions;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class ErrorHandler {

	public static void showErrorMsg(Exception e) {
		Shell localShell = new Shell(SWT.ICON_ERROR);
		String msg = e.getMessage().concat("\nОбратитесь к вашему системному администратору.");
		MessageBox messageBox = new MessageBox(localShell);
		messageBox.setMessage(msg);
		messageBox.setText(msg);

		messageBox.open();
	}

	public static void showInfoMsg(Exception e) {
		Shell localShell = new Shell(SWT.ICON_WARNING);
		String msg = e.getMessage();
		MessageBox messageBox = new MessageBox(localShell);
		messageBox.setMessage(msg);

		messageBox.open();
	}
}
