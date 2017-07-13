package org.di.uminho.cguide.wizard.Create;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import org.di.uminho.cguide.wizard.Create.Exam.CreateExamPage;
import org.di.uminho.cguide.wizard.Create.Formula.CreateFormulaPage2;
import org.di.uminho.cguide.wizard.Create.MedicationRecommendation.CreateMedicationRecommendationPage;
import org.di.uminho.cguide.wizard.Create.NonMedicationRecommendation.CreateNonMedicationRecommendationPage;
import org.di.uminho.cguide.wizard.Create.Procedure.CreateProcedurePage;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;

public class SelectClinicalActionPage extends AbstractOWLWizardPanel {

	public static final String ID = "SelectClinicalActionPage";

	public static final String title = "Select Clinical Action Type";

	public int choice = -1;

	private JRadioButton exam;
	private JRadioButton formula;
	private JRadioButton procedure;
	private JRadioButton medicalrecommendation;
	private JRadioButton nonmedicalrecommendation;

	public SelectClinicalActionPage(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please select the Action Type you wish to create new Individuals.");
		parent.setLayout(new BorderLayout());

		Box box = new Box(BoxLayout.Y_AXIS);
		parent.add(box, BorderLayout.CENTER);

		exam = new JRadioButton("Exam");
		box.add(exam);
		formula = new JRadioButton("Formula");
		box.add(formula);
		procedure = new JRadioButton("Procedure");
		box.add(procedure);
		medicalrecommendation = new JRadioButton("Medication Recommendation");
		box.add(medicalrecommendation);
		nonmedicalrecommendation = new JRadioButton("Non-Medication Recommendation");
		box.add(nonmedicalrecommendation);
		ButtonGroup bg = new ButtonGroup();
		bg.add(exam);
		bg.add(formula);
		bg.add(procedure);
		bg.add(medicalrecommendation);
		bg.add(nonmedicalrecommendation);
	}

	public Object getBackPanelDescriptor() {
		return SelectClassPage.ID;
	}

	public Object getNextPanelDescriptor() {
		if (exam.isSelected()) {
			choice = 1;
			return CreateExamPage.ID;
		} else if (formula.isSelected()) {
			choice = 2;
			return CreateFormulaPage2.ID;
		} else if (procedure.isSelected()) {
			choice = 3;
			return CreateProcedurePage.ID;
		} else if (medicalrecommendation.isSelected()) {
			choice = 4;
			return CreateMedicationRecommendationPage.ID;
		} else if (nonmedicalrecommendation.isSelected()) {
			choice = 5;
			return CreateNonMedicationRecommendationPage.ID;
		} else
			choice = -1;
		return SelectClinicalActionPage.ID;
	}

}
