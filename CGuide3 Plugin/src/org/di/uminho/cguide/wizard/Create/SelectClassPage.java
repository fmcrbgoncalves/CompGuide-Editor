package org.di.uminho.cguide.wizard.Create;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import org.di.uminho.cguide.wizard.Create.CPG.CreateCPGPage1;
import org.di.uminho.cguide.wizard.Create.Condition.CreateConditionPage1;
import org.di.uminho.cguide.wizard.Create.Option.CreateOptionPage2;
import org.di.uminho.cguide.wizard.Create.Parameter.CreateParameterPage;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;

public class SelectClassPage extends AbstractOWLWizardPanel {

	public static final String ID = "SelectClassPage";

	public static final String title = "Select Class Type";

	public int choice = -1;

	private JRadioButton clinicalprotocolguideline;
	private JRadioButton clinicaltask;
	private JRadioButton condition;
	private JRadioButton clinicalaction;
	private JRadioButton option;
	private JRadioButton parameter;

	public SelectClassPage(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please select the Class you wish to create new Individuals.");
		parent.setLayout(new BorderLayout());

		Box box = new Box(BoxLayout.Y_AXIS);
		parent.add(box, BorderLayout.CENTER);

		clinicalprotocolguideline = new JRadioButton("Clinical Protocol Guideline");
		box.add(clinicalprotocolguideline);
		clinicaltask = new JRadioButton("Clinical Task");
		box.add(clinicaltask);
		clinicalaction = new JRadioButton("Clinical Action");
		box.add(clinicalaction);
		condition = new JRadioButton("Condition");
		box.add(condition);
		option = new JRadioButton("Option");
		box.add(option);
		parameter = new JRadioButton("Parameter");
		box.add(parameter);
		ButtonGroup bg = new ButtonGroup();
		bg.add(clinicalprotocolguideline);
		bg.add(clinicaltask);
		bg.add(clinicalaction);
		bg.add(condition);
		bg.add(option);
		bg.add(parameter);
	}

	public Object getNextPanelDescriptor() {
		if (clinicalprotocolguideline.isSelected()) {
			choice = 1;
			return CreateCPGPage1.ID;
		} else if (clinicaltask.isSelected()) {
			choice = 2;
			return SelectClinicalTaskPage.ID;
		} else if (condition.isSelected()) {
			choice = 3;
			return CreateConditionPage1.ID;
		} else if (clinicalaction.isSelected()) {
			choice = 4;
			return SelectClinicalActionPage.ID;
		} else if (option.isSelected()) {
			choice = 5;
			return CreateOptionPage2.ID;
		} else if (parameter.isSelected()) {
			choice = 6;
			return CreateParameterPage.ID;
		} else
			choice = -1;
		return SelectClassPage.ID;
	}

}
