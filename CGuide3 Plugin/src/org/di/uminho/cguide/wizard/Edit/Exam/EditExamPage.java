package org.di.uminho.cguide.wizard.Edit.Exam;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.protege.editor.core.ui.wizard.WizardPanel;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

public class EditExamPage extends AbstractOWLWizardPanel {

	public static final String ID = "EditExamPage";

	public static final String title = "Insert Exam Data Required";

	private JLabel exam_description_label;
	private JLabel exam_name_label;

	public JTextArea exam_description_jtext;
	public JTextField exam_name_jtext;

	private String examName = new String(), examDescription = new String();

	public EditExamPage(OWLEditorKit owlEditorKit, String exam_individual) {
		super(ID, title, owlEditorKit);
		getExam(exam_individual);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please insert data required for the edition of the associated Exam.");

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		parent.setLayout(gbl_contentPane);

		exam_name_label = new JLabel("Exam Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		parent.add(exam_name_label, gbc_lblName);

		exam_name_jtext = new JTextField(40);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		parent.add(exam_name_jtext, gbc_textField);

		exam_description_label = new JLabel("Exam Description:");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 3;
		parent.add(exam_description_label, gbc_lblDescription);

		exam_description_jtext = new JTextArea(5, 30);
		GridBagConstraints gbc_textArea1 = new GridBagConstraints();
		gbc_textArea1.fill = GridBagConstraints.BOTH;
		gbc_textArea1.gridx = 1;
		gbc_textArea1.gridy = 3;
		parent.add(new JScrollPane(exam_description_jtext), gbc_textArea1);
	}

	@Override
	public void aboutToDisplayPanel() {
		exam_name_jtext.setText(this.examName);
		exam_description_jtext.setText(this.examDescription);
		// TODO Auto-generated method stub
		super.aboutToDisplayPanel();
	}

	public Object getNextPanelDescriptor() {
		return WizardPanel.FINISH;
	}

	public void getExam(String exam_individual_name) {

		OWLNamedIndividual exam_individual = getOWLModelManager().getOWLDataFactory().getOWLNamedIndividual(
				IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#"
						+ exam_individual_name));

		try {
			OWLDataPropertyExpression examName_dataproperty = getOWLModelManager().getOWLDataFactory()
					.getOWLDataProperty(IRI.create(
							getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#examName"));
			Set<OWLLiteral> examName_literal = exam_individual.getDataPropertyValues(examName_dataproperty,
					getOWLModelManager().getActiveOntology());

			this.examName = examName_literal.iterator().next().getLiteral();
		} catch (Exception e) {
		}
		try {
			OWLDataPropertyExpression examDescription_dataproperty = getOWLModelManager().getOWLDataFactory()
					.getOWLDataProperty(
							IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
									+ "#examDescription"));
			Set<OWLLiteral> examDescription_literal = exam_individual
					.getDataPropertyValues(examDescription_dataproperty, getOWLModelManager().getActiveOntology());

			this.examDescription = examDescription_literal.iterator().next().getLiteral();

		} catch (Exception e) {
		}

	}

}
