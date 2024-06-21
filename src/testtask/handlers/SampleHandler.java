package testtask.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import testtask.controller.ShellController;
import testtask.view.MainView;

public class SampleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// NOTE: Если оставить в таком виде, то этот модуль будет открываться этот по
		// каждому нажанию кнопки в ТС
		ShellController.open(new MainView());
		return null;
	}
}
