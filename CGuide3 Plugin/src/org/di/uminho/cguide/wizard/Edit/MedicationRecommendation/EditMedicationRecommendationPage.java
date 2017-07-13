package org.di.uminho.cguide.wizard.Edit.MedicationRecommendation;

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

public class EditMedicationRecommendationPage extends AbstractOWLWizardPanel {

	public static final String ID = "EditMedicationRecommendationPage";

	public static final String title = "Insert Medication Recommendation Data Required";

	private JLabel medicationrecommendation_description_label;
	private JLabel medicationrecommendation_name_label;
	private JLabel medicationrecommendation_dosage_label;
	private JLabel medicationrecommendation_activeIngredient_label;
	private JLabel medicationrecommendation_pharmaceuticalForm_label;
	private JLabel medicationrecommendation_posology_label;

	public JTextArea medicationrecommendation_description_jtext;
	public JTextField medicationrecommendation_name_jtext;
	public JTextField medicationrecommendation_dosage_jtext;
	public JTextField medicationrecommendation_activeIngredient_jtext;
	public JTextField medicationrecommendation_pharmaceuticalForm_jtext;
	public JTextField medicationrecommendation_posology_jtext;

	String medicationrecommendationDescription = new String(), medicationrecommendationName = new String(),
			Dosage = new String(), activeIngredient = new String(), pharmaceuticalForm = new String(),
			posology = new String();

	public EditMedicationRecommendationPage(OWLEditorKit owlEditorKit, String medicationrecommendation_individual) {
		super(ID, title, owlEditorKit);
		getMedicationRecommendation(medicationrecommendation_individual);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please insert data required for the edition of the associated Medication Recommendation.");

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		parent.setLayout(gbl_contentPane);

		medicationrecommendation_name_label = new JLabel("Medication Recommendation Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		parent.add(medicationrecommendation_name_label, gbc_lblName);

		medicationrecommendation_name_jtext = new JTextField(40);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		parent.add(medicationrecommendation_name_jtext, gbc_textField);

		medicationrecommendation_activeIngredient_label = new JLabel("Active Ingredient Name:");
		GridBagConstraints gbc_lblName1 = new GridBagConstraints();
		gbc_lblName1.anchor = GridBagConstraints.EAST;
		gbc_lblName1.insets = new Insets(0, 0, 5, 5);
		gbc_lblName1.gridx = 0;
		gbc_lblName1.gridy = 2;
		parent.add(medicationrecommendation_activeIngredient_label, gbc_lblName1);

		medicationrecommendation_activeIngredient_jtext = new JTextField(40);
		GridBagConstraints gbc_textField1 = new GridBagConstraints();
		gbc_textField1.insets = new Insets(0, 0, 5, 0);
		gbc_textField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField1.gridx = 1;
		gbc_textField1.gridy = 2;
		parent.add(medicationrecommendation_activeIngredient_jtext, gbc_textField1);

		medicationrecommendation_posology_label = new JLabel("Posology:");
		GridBagConstraints gbc_lblName2 = new GridBagConstraints();
		gbc_lblName2.anchor = GridBagConstraints.EAST;
		gbc_lblName2.insets = new Insets(0, 0, 5, 5);
		gbc_lblName2.gridx = 0;
		gbc_lblName2.gridy = 3;
		parent.add(medicationrecommendation_posology_label, gbc_lblName2);

		medicationrecommendation_posology_jtext = new JTextField(40);
		GridBagConstraints gbc_textField2 = new GridBagConstraints();
		gbc_textField2.insets = new Insets(0, 0, 5, 0);
		gbc_textField2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField2.gridx = 1;
		gbc_textField2.gridy = 3;
		parent.add(medicationrecommendation_posology_jtext, gbc_textField2);

		medicationrecommendation_dosage_label = new JLabel("Dosage:");
		GridBagConstraints gbc_lblName3 = new GridBagConstraints();
		gbc_lblName3.anchor = GridBagConstraints.EAST;
		gbc_lblName3.insets = new Insets(0, 0, 5, 5);
		gbc_lblName3.gridx = 0;
		gbc_lblName3.gridy = 4;
		parent.add(medicationrecommendation_dosage_label, gbc_lblName3);

		medicationrecommendation_dosage_jtext = new JTextField(40);
		GridBagConstraints gbc_textField3 = new GridBagConstraints();
		gbc_textField3.insets = new Insets(0, 0, 5, 0);
		gbc_textField3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField3.gridx = 1;
		gbc_textField3.gridy = 4;
		parent.add(medicationrecommendation_dosage_jtext, gbc_textField3);

		medicationrecommendation_pharmaceuticalForm_label = new JLabel("Pharmaceutical Form:");
		GridBagConstraints gbc_lblName4 = new GridBagConstraints();
		gbc_lblName4.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblName4.insets = new Insets(0, 0, 5, 5);
		gbc_lblName4.gridx = 0;
		gbc_lblName4.gridy = 5;
		parent.add(medicationrecommendation_pharmaceuticalForm_label, gbc_lblName4);

		medicationrecommendation_pharmaceuticalForm_jtext = new JTextField(40);
		GridBagConstraints gbc_textField4 = new GridBagConstraints();
		gbc_textField4.insets = new Insets(0, 0, 5, 0);
		gbc_textField4.anchor = GridBagConstraints.NORTHEAST;
		gbc_textField4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4.gridx = 1;
		gbc_textField4.gridy = 5;
		parent.add(medicationrecommendation_pharmaceuticalForm_jtext, gbc_textField4);

		medicationrecommendation_description_label = new JLabel("Medication Recommendation Description:");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 0);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 6;
		parent.add(medicationrecommendation_description_label, gbc_lblDescription);

		medicationrecommendation_description_jtext = new JTextArea(5, 30);
		GridBagConstraints gbc_textArea1 = new GridBagConstraints();
		gbc_textArea1.fill = GridBagConstraints.BOTH;
		gbc_textArea1.anchor = GridBagConstraints.NORTHEAST;
		gbc_textArea1.gridx = 1;
		gbc_textArea1.gridy = 6;
		parent.add(new JScrollPane(medicationrecommendation_description_jtext), gbc_textArea1);
	}

	public Object getNextPanelDescriptor() {
		return WizardPanel.FINISH;
	}

	@Override
	public void aboutToDisplayPanel() {
		medicationrecommendation_name_jtext.setText(this.medicationrecommendationName);
		medicationrecommendation_description_jtext.setText(this.medicationrecommendationDescription);
		medicationrecommendation_activeIngredient_jtext.setText(this.activeIngredient);
		medicationrecommendation_dosage_jtext.setText(this.Dosage);
		medicationrecommendation_pharmaceuticalForm_jtext.setText(this.pharmaceuticalForm);
		medicationrecommendation_posology_jtext.setText(this.posology);
		// TODO Auto-generated method stub
		super.aboutToDisplayPanel();
	}

	public void getMedicationRecommendation(String medicationrecommendation_individual_name) {

		OWLNamedIndividual medicationrecommendation_individual = getOWLModelManager().getOWLDataFactory()
				.getOWLNamedIndividual(
						IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#"
								+ medicationrecommendation_individual_name));

		try {
			OWLDataPropertyExpression medicationrecommendationName_dataproperty = getOWLModelManager()
					.getOWLDataFactory().getOWLDataProperty(
							IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
									+ "#medicationRecommendationName"));
			Set<OWLLiteral> medicationrecommendationName_literal = medicationrecommendation_individual
					.getDataPropertyValues(medicationrecommendationName_dataproperty,
							getOWLModelManager().getActiveOntology());

			this.medicationrecommendationName = medicationrecommendationName_literal.iterator().next().getLiteral();
		} catch (Exception e) {

		}
		try {

			OWLDataPropertyExpression activeIngredient_dataproperty = getOWLModelManager().getOWLDataFactory()
					.getOWLDataProperty(
							IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
									+ "#activeIngredient"));
			Set<OWLLiteral> activeIngredient_literal = medicationrecommendation_individual
					.getDataPropertyValues(activeIngredient_dataproperty, getOWLModelManager().getActiveOntology());

			this.activeIngredient = activeIngredient_literal.iterator().next().getLiteral();

		} catch (Exception e) {

		}
		try {

			OWLDataPropertyExpression posology_dataproperty = getOWLModelManager().getOWLDataFactory()
					.getOWLDataProperty(IRI.create(
							getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#posology"));
			Set<OWLLiteral> posology_literal = medicationrecommendation_individual
					.getDataPropertyValues(posology_dataproperty, getOWLModelManager().getActiveOntology());

			this.posology = posology_literal.iterator().next().getLiteral();

		} catch (Exception e) {

		}
		try {
			OWLDataPropertyExpression dosage_dataproperty = getOWLModelManager().getOWLDataFactory().getOWLDataProperty(
					IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#dosage"));
			Set<OWLLiteral> dosage_literal = medicationrecommendation_individual
					.getDataPropertyValues(dosage_dataproperty, getOWLModelManager().getActiveOntology());

			this.Dosage = dosage_literal.iterator().next().getLiteral();
		} catch (Exception e) {

		}
		try {
			OWLDataPropertyExpression pharmaceuticalform_dataproperty = getOWLModelManager().getOWLDataFactory()
					.getOWLDataProperty(
							IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
									+ "#pharmaceuticalForm"));
			Set<OWLLiteral> pharmaceuticalform_literal = medicationrecommendation_individual
					.getDataPropertyValues(pharmaceuticalform_dataproperty, getOWLModelManager().getActiveOntology());

			this.pharmaceuticalForm = pharmaceuticalform_literal.iterator().next().getLiteral();
		} catch (Exception e) {

		}
		try {
			OWLDataPropertyExpression medicationrecommendationDescription_dataproperty = getOWLModelManager()
					.getOWLDataFactory().getOWLDataProperty(
							IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI()
									+ "#medicationRecommendationDescription"));
			Set<OWLLiteral> medicationrecommendationDescription_literal = medicationrecommendation_individual
					.getDataPropertyValues(medicationrecommendationDescription_dataproperty,
							getOWLModelManager().getActiveOntology());

			this.medicationrecommendationDescription = medicationrecommendationDescription_literal.iterator().next()
					.getLiteral();
		} catch (Exception e) {
		}

	}

}
