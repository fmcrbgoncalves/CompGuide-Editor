package org.di.uminho.cguide.wizard.Edit.Action;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

public class EditActionPage9 extends AbstractOWLWizardPanel {

	public static final String ID = "EditActionPage9";

	public static final String title = "Action Clinical Type";

	public int choice = -1;

	private JRadioButton exam;
	private JRadioButton formula;
	private JRadioButton procedure;
	private JRadioButton medicalrecommendation;
	private JRadioButton nonmedicalrecommendation;
	private JRadioButton none;

	public int selected_next_task;

	public EditActionPage9(OWLEditorKit owlEditorKit, String individual) {
		super(ID, title, owlEditorKit);
		this.selected_next_task = getIndividual(individual);
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
		return EditActionPage8.ID;
	}

	public Object getNextPanelDescriptor() {
		if (exam.isSelected()) {
			choice = 1;
			return EditActionPage9_1.ID;
		} else if (formula.isSelected()) {
			choice = 2;
			return EditActionPage9_2.ID;
		} else if (procedure.isSelected()) {
			choice = 3;
			return EditActionPage9_3.ID;
		} else if (medicalrecommendation.isSelected()) {
			choice = 4;
			return EditActionPage9_4.ID;
		} else if (nonmedicalrecommendation.isSelected()) {
			choice = 5;
			return EditActionPage9_5.ID;
		} else if (none.isSelected()) {
			choice = 6;
			return EditActionPage10.ID;
		} else
			choice = -1;
		return EditActionPage9.ID;
	}

	@Override
	public void aboutToDisplayPanel() {
		if (this.selected_next_task == 1)
			exam.setSelected(true);
		else if (this.selected_next_task == 2)
			formula.setSelected(true);
		else if (this.selected_next_task == 3)
			procedure.setSelected(true);
		else if (this.selected_next_task == 4)
			medicalrecommendation.setSelected(true);
		else if (this.selected_next_task == 5)
			nonmedicalrecommendation.setSelected(true);
		// TODO Auto-generated method stub
		super.aboutToDisplayPanel();
	}

	public int getIndividual(String individual_name) {

		int res = 0;

		OWLNamedIndividual individual = getOWLModelManager().getOWLDataFactory().getOWLNamedIndividual(IRI.create(
				getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#" + individual_name));

		OWLObjectProperty hasClinicalActionType_objectproperty = getOWLModelManager().getOWLDataFactory()
				.getOWLObjectProperty(
						IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
								+ "#hasClinicalActionType"));

		try {
			OWLNamedIndividual hasClinicalActionType_individual = individual
					.getObjectPropertyValues(hasClinicalActionType_objectproperty,
							getOWLModelManager().getActiveOntology())
					.iterator().next().asOWLNamedIndividual();

			String type_name = hasClinicalActionType_individual.getTypes(getOWLModelManager().getActiveOntology())
					.iterator().next().asOWLClass().getIRI().getFragment();

			if (type_name.equals("Exam")) {
				res = 1;
			} else if (type_name.equals("Formula")) {
				res = 2;
			} else if (type_name.equals("Procedure")) {
				res = 3;
			} else if (type_name.equals("MedicationRecommendation")) {
				res = 4;
			} else if (type_name.equals("NonMedicationRecommendation")) {
				res = 5;
			}

		} catch (Exception e) {
		}

		return res;
	}

}
