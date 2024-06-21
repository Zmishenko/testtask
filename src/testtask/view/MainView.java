package testtask.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import testtask.controller.DataController;
import testtask.exceptions.ErrorHandler;
import testtask.model.Atribute;
import testtask.model.Revison;

public class MainView extends Shell {

	private DataController dataController;

	private Text textFull;
	private Combo comboVerRev;
	private Composite compositeBth;
	private Composite compositeDynamyc;

	private Revison selectedRevison;

	public MainView() {
		setSize(450, 300);
		setLayout(new GridLayout(1, false));

		try {
			this.dataController = new DataController();
		} catch (Exception e) {
			ErrorHandler.showErrorMsg(e);
		}

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		Label label = new Label(composite, SWT.NONE);
		label.setText("Наименование:");

		this.textFull = new Text(composite, SWT.BORDER);
		this.textFull.setEditable(false);
		this.textFull.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("Тип изделия:");

		this.comboVerRev = new Combo(composite, SWT.READ_ONLY);
		this.comboVerRev.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		initComboRevs(this.comboVerRev);

		Button button = new Button(composite, SWT.NONE);
		button.setText("Запросить");
		button.addListener(SWT.Selection, e -> makeReqest());

		// =====

		initBaseView();

		// =====
	}

	private void initComboRevs(Combo comboVerRev) {
		if (this.dataController.getRevisions() == null || this.dataController.getRevisions().isEmpty()) {
			return;
		}

		for (Revison revison : this.dataController.getRevisions()) {
			comboVerRev.add(revison.getCode());
		}

		if (comboVerRev.getItemCount() > 0) {
			comboVerRev.select(0);
		}
	}

	private void initBaseView() {
		if (this.compositeDynamyc != null && !this.compositeDynamyc.isDisposed()) {
			this.compositeDynamyc.dispose();
		}

		this.compositeDynamyc = new Composite(this, SWT.NONE);
		this.compositeDynamyc.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		this.compositeDynamyc.setSize(414, 185);
		this.compositeDynamyc.setLayout(new GridLayout(2, false));

		String[] lbls = new String[] { "Кол-во ядер:", "Макс. Частота:", "Тепловыделение:", "Емкость, ГБ:",
				"Тмп памяти:" };
		for (String string : lbls) {
			Label label = new Label(this.compositeDynamyc, SWT.NONE);
			label.setText(string);
			Combo combo = new Combo(this.compositeDynamyc, SWT.READ_ONLY);
			combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		}

		initBaseButtons();
	}

	private void initBaseButtons() {
		if (this.compositeBth != null && !this.compositeBth.isDisposed()) {
			this.compositeBth.dispose();
		}

		this.compositeBth = new Composite(this, SWT.NONE);
		this.compositeBth.setLayout(new GridLayout(3, false));
		this.compositeBth.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));

		Button bthNext = new Button(this.compositeBth, SWT.NONE);
		bthNext.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		bthNext.setText("Далее");
		bthNext.addListener(SWT.Selection, e -> initBaseView());

		Button bthDone = new Button(this.compositeBth, SWT.NONE);
		bthDone.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		bthDone.setText("Готово");
		bthDone.addListener(SWT.Selection, e -> initBaseView());

		Button bthCancel = new Button(this.compositeBth, SWT.NONE);
		bthCancel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		bthCancel.setText("Отмена");
		bthCancel.addListener(SWT.Selection, e -> initBaseView());

		this.compositeDynamyc.layout();
		this.layout();
	}

	private void makeReqest() {
		try {
			if (this.comboVerRev.getItemCount() == 0) {
				return;
			}

			if (this.comboVerRev.getSelectionIndex() == -1) {
				return;
			}

			this.dataController.initDataByIndex(this.comboVerRev.getSelectionIndex());
			if (this.dataController.getAtributes() == null || this.dataController.getAtributes().isEmpty()) {
				return;
			}
			getRequstedData();
		} catch (Exception e) {
			initBaseView();
			ErrorHandler.showErrorMsg(e);
		}
	}

	private void getRequstedData() {
		String searchCode = this.comboVerRev.getItem(this.comboVerRev.getSelectionIndex());

		selectedRevison = this.dataController.getRevisions().stream().filter(r -> r.getCode().equals(searchCode))
				.findFirst().orElseThrow(() -> new NoSuchElementException("No Revison found with code: " + searchCode));

		List<Atribute> attr = this.dataController.getAtributes();
		if (attr == null) {
			initBaseView();
			return;
		}
		Collections.reverse(this.dataController.getAtributes());

		setRequestedData(attr);
	}

	private void setRequestedData(List<Atribute> attr) {
		if (this.compositeDynamyc != null && !this.compositeDynamyc.isDisposed()) {
			this.compositeDynamyc.dispose();
		}

		this.compositeDynamyc = new Composite(this, SWT.NONE);
		this.compositeDynamyc.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		this.compositeDynamyc.setSize(414, 185);
		this.compositeDynamyc.setLayout(new GridLayout(2, false));

		for (Atribute atribute : attr) {
			String lablText = atribute.getTemplate().getLocalizedName();

			Label label = new Label(this.compositeDynamyc, SWT.NONE);
			label.setText(lablText);

			Combo combo = new Combo(this.compositeDynamyc, SWT.READ_ONLY);
			combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			combo.addListener(SWT.Selection, e -> comboListener(e));
			for (String comboValue : atribute.getAttr()) {
				combo.add(comboValue);
			}
			combo.select(0);
		}

		this.compositeDynamyc.layout();
		this.layout();

		initBaseButtons();
		setFullText();
	}

	private void comboListener(Event e) {
		setFullText();
	}

	private static List<Combo> getAllCombos(Composite composite) {
		return Arrays.stream(composite.getChildren()).filter(child -> child instanceof Combo)
				.map(child -> (Combo) child).collect(Collectors.toList());
	}

	private void setFullText() {
		List<String> values = new ArrayList<String>();
		for (Combo combo : getAllCombos(this.compositeDynamyc)) {
			int selectedIndex = combo.getSelectionIndex();
			String text = combo.getItem(selectedIndex);
			values.add(text);
		}
		this.textFull.setText(this.dataController.getFullText(selectedRevison, values));
		this.textFull.update();
	}

	@Override
	protected void checkSubclass() {
		// TODO Auto-generated method stub
		// super.checkSubclass();
	}
}
