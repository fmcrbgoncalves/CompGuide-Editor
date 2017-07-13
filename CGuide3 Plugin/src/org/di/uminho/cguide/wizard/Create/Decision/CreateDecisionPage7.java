package org.di.uminho.cguide.wizard.Create.Decision;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;

public class CreateDecisionPage7 extends AbstractOWLWizardPanel {

	public static final String ID = "CreateDecisionPage7";

	public static final String title = "Decision Next Clinical Task";

	public int choice = -1;

	private JRadioButton paralleltask;
	private JRadioButton preferencealternativetask;
	private JRadioButton alternativetask;
	private JRadioButton nexttask;

	public CreateDecisionPage7(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please select the type of next Clinical Task.");
		parent.setLayout(new BorderLayout());

		Box box = new Box(BoxLayout.Y_AXIS);
		parent.add(box, BorderLayout.CENTER);

		paralleltask = new JRadioButton("Parallel Task");
		box.add(paralleltask);
		preferencealternativetask = new JRadioButton("Preference Alternative Task");
		box.add(preferencealternativetask);
		alternativetask = new JRadioButton("Alternative Task");
		box.add(alternativetask);
		nexttask = new JRadioButton("Next Task");
		box.add(nexttask);
		ButtonGroup bg = new ButtonGroup();
		bg.add(paralleltask);
		bg.add(preferencealternativetask);
		bg.add(alternativetask);
		bg.add(nexttask);
	}

	public Object getBackPanelDescriptor() {
		return CreateDecisionPage6.ID;
	}

	public Object getNextPanelDescriptor() {
		if (paralleltask.isSelected()) {
			choice = 1;
			return CreateDecisionPage8parallel.ID;
		} else if (preferencealternativetask.isSelected()) {
			choice = 2;
			return CreateDecisionPage8preferencealternative.ID;
		} else if (alternativetask.isSelected()) {
			choice = 3;
			return CreateDecisionPage8alternative.ID;
		} else if (nexttask.isSelected()) {
			choice = 4;
			return CreateDecisionPage8next.ID;
		} else
			choice = -1;
		return CreateDecisionPage7.ID;
	}

}
