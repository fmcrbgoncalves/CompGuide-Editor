package org.di.uminho.cguide.wizard.Create.Action;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;

public class CreateActionPage9 extends AbstractOWLWizardPanel {

	public static final String ID = "CreateActionPage9";

	public static final String title = "Action Clinical Type";

	public int choice = -1;

	private JRadioButton exam;
	private JRadioButton formula;
	private JRadioButton procedure;
	private JRadioButton medicalrecommendation;
	private JRadioButton nonmedicalrecommendation;
	private JRadioButton none;

	public CreateActionPage9(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please select the Clinical Action Type.");
		parent.setLayout(new BorderLayout());

		Box box = new Box(BoxLayout.Y_AXIS);
		parent.add(box, BorderLayout.CENTER);

		exam = new JRadioButton("Exam");
		box.add(exam);
		formula = new JRadioButton("Formula");
		box.add(formula);
		procedure = new JRadioButton("Procedure");
		box.add(procedure);
		medicalrecommendation = new JRadioButton("Medical Recommendation");
		box.add(medicalrecommendation);
		nonmedicalrecommendation = new JRadioButton("Non-Medical Recommendation");
		box.add(nonmedicalrecommendation);
		none = new JRadioButton("No Clinical Action");
		box.add(none);
		ButtonGroup bg = new ButtonGroup();
		bg.add(exam);
		bg.add(formula);
		bg.add(procedure);
		bg.add(medicalrecommendation);
		bg.add(nonmedicalrecommendation);
		bg.add(none);
	}

	public Object getBackPanelDescriptor() {
		return CreateActionPage8.ID;
	}

	public Object getNextPanelDescriptor() {
		if (exam.isSelected()) {
			choice = 1;
			return CreateActionPage9_1.ID;
		} else if (formula.isSelected()) {
			choice = 2;
			return CreateActionPage9_2.ID;
		} else if (procedure.isSelected()) {
			choice = 3;
			return CreateActionPage9_3.ID;
		} else if (medicalrecommendation.isSelected()) {
			choice = 4;
			return CreateActionPage9_4.ID;
		} else if (nonmedicalrecommendation.isSelected()) {
			choice = 5;
			return CreateActionPage9_5.ID;
		} else if (none.isSelected()) {
			choice = 6;
			return CreateActionPage10.ID;
		} else
			choice = -1;
		return CreateActionPage9.ID;
	}

}
