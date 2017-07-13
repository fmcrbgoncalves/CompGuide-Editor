package org.di.uminho.cguide.wizard.Edit.NonMedicationRecommendation;

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

public class EditNonMedicationRecommendationPage extends AbstractOWLWizardPanel {

	public static final String ID = "EditNonMedicationRecommendationPage";

	public static final String title = "Insert Non-Medication Recommendation Data Required";

	private JLabel nonmedicationrecommendation_description_label;
	private JLabel nonmedicationrecommendation_name_label;

	public JTextArea nonmedicationrecommendation_description_jtext;
	public JTextField nonmedicationrecommendation_name_jtext;

	String nonmedicationrecommendationDescription = new String(), nonmedicationrecommendationName = new String();

	public EditNonMedicationRecommendationPage(OWLEditorKit owlEditorKit,
			String nonmedicationrecommendation_individual) {
		super(ID, title, owlEditorKit);
		getNonMedicationRecommendation(nonmedicationrecommendation_individual);
	}

	protected void createUI(JComponent parent) {
		setInstructions(
				"Please insert data required for the creation of the associated Non-Medication Recommendation.");

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		parent.setLayout(gbl_contentPane);

		nonmedicationrecommendation_name_label = new JLabel("Non-Medication Recommendation Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		parent.add(nonmedicationrecommendation_name_label, gbc_lblName);

		nonmedicationrecommendation_name_jtext = new JTextField(40);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		parent.add(nonmedicationrecommendation_name_jtext, gbc_textField);

		nonmedicationrecommendation_description_label = new JLabel("Non-Medication Recommendation Description:");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 3;
		parent.add(nonmedicationrecommendation_description_label, gbc_lblDescription);

		nonmedicationrecommendation_description_jtext = new JTextArea(5, 30);
		GridBagConstraints gbc_textArea1 = new GridBagConstraints();
		gbc_textArea1.fill = GridBagConstraints.BOTH;
		gbc_textArea1.gridx = 1;
		gbc_textArea1.gridy = 3;
		parent.add(new JScrollPane(nonmedicationrecommendation_description_jtext), gbc_textArea1);
	}

	public Object getNextPanelDescriptor() {
		return WizardPanel.FINISH;
	}

	@Override
	public void aboutToDisplayPanel() {
		nonmedicationrecommendation_name_jtext.setText(this.nonmedicationrecommendationName);
		nonmedicationrecommendation_description_jtext.setText(this.nonmedicationrecommendationDescription);
		// TODO Auto-generated method stub
		super.aboutToDisplayPanel();
	}

	public void getNonMedicationRecommendation(String nonmedicationrecommendation_individual_name) {
		OWLNamedIndividual nonmedicationrecommendation_individual = getOWLModelManager().getOWLDataFactory()
				.getOWLNamedIndividual(
						IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#"
								+ nonmedicationrecommendation_individual_name));

		try {
			OWLDataPropertyExpression nonmedicationrecommendationName_dataproperty = getOWLModelManager()
					.getOWLDataFactory().getOWLDataProperty(
							IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
									+ "#nonMedicationRecommendationName"));
			Set<OWLLiteral> nonmedicationrecommendationName_literal = nonmedicationrecommendation_individual
					.getDataPropertyValues(nonmedicationrecommendationName_dataproperty,
							getOWLModelManager().getActiveOntology());

			this.nonmedicationrecommendationName = nonmedicationrecommendationName_literal.iterator().next()
					.getLiteral();
		} catch (Exception e) {

		}
		try {
			OWLDataPropertyExpression nonmedicationrecommendationDescription_dataproperty = getOWLModelManager()
					.getOWLDataFactory().getOWLDataProperty(
							IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
									+ "#nonMedicationRecommendationDescription"));
			Set<OWLLiteral> nonmedicationrecommendationDescription_literal = nonmedicationrecommendation_individual
					.getDataPropertyValues(nonmedicationrecommendationDescription_dataproperty,
							getOWLModelManager().getActiveOntology());

			this.nonmedicationrecommendationDescription = nonmedicationrecommendationDescription_literal.iterator()
					.next().getLiteral();
		} catch (Exception e) {

		}
	}

}
